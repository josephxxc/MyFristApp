package application.weibo;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.openapi.UsersAPI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class MainActivity extends Activity {
    private static final String TAG = "weibosdk";
    ArrayList<WeiBo> list;
    ListView listView;

    UsersAPI mUsersAPI;
    /**
     * 显示认证后的信息，如 AccessToken
     */
    private TextView mTokenText;

    private AuthInfo mAuthInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weibo_shouye);
        listView= (ListView) findViewById(R.id.list_view);
        mAuthInfo = new AuthInfo(this, Constants.APP_KEY,
                Constants.REDIRECT_URL, Constants.SCOPE);//创建微博授权类对象，将应用的信息保存
    }
    class AuthListener implements WeiboAuthListener {

        public void onComplete(Bundle values) {
            Oauth2AccessToken mAccessToken = Oauth2AccessToken.parseAccessToken(values); // 从 Bundle 中解析 Token
            if (mAccessToken.isSessionValid()) {
//                AccessTokenKeeper.writeAccessToken(MainActivity.this, mAccessToken); //保存Token
            } else {
                String code = values.getString("code");
                String message = getString(R.string.weibosdk_demo_toast_auth_failed);
                if (!TextUtils.isEmpty(code)) {
                    message = message + "\nObtained the code: " + code;
                }
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onWeiboException(WeiboException e) {
            Toast.makeText(MainActivity.this,
                    "Auth exception : " + e.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCancel() {
            Toast.makeText(MainActivity.this,
                    R.string.weibosdk_demo_toast_auth_canceled, Toast.LENGTH_LONG).show();
        }
    }

    public class MyAsyncTask extends AsyncTask<String, Integer, String> {
        @Override
        protected void onPostExecute(String s) {//对UI的操作
            if (s != null) {
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray jsonArray = jsonObject.getJSONArray("newlist");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject obj = jsonArray.getJSONObject(i);
                        WeiBo weiBo = new WeiBo();
                        weiBo.setId(obj.getInt("id"));
                        weiBo.setMid(obj.getInt("mid"));
                        weiBo.setCreated_at(obj.getString("created_at"));
                        weiBo.setSource(obj.getString("source"));
                        weiBo.setText(obj.getString("text"));
                        weiBo.setThumbnail_pic(obj.getString("thumbnail_pic"));
                        weiBo.setReposts_count(obj.getInt("reposts_count"));
                        weiBo.setComments_count(obj.getInt("comments_count"));
                        weiBo.setAttitudes_count(obj.getInt("attitudes_count"));
                        list.add(weiBo);
                    }
                    WeiBoAdapter weiBoAdapter = new WeiBoAdapter(MainActivity.this, list);
                    listView.setAdapter(weiBoAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            super.onPostExecute(s);
        }

        @Override
        protected String doInBackground(String... strings) {//在后台运行的回调方法，所有的耗时操作都在此
            //进行
            StringBuilder stringBuilder = new StringBuilder();
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestProperty("apikey", "1517365558");
                httpURLConnection.connect();
                if (httpURLConnection.getResponseCode() == httpURLConnection.HTTP_OK) {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                            httpURLConnection.getInputStream(), "utf-8"
                    ));
                    String s;
                    while ((s = bufferedReader.readLine()) != null) {
                        this.publishProgress(s.length());
                        stringBuilder.append(s);
                    }
                    Log.i("data=======", stringBuilder.toString());
                    return stringBuilder.toString();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

    public void startAsyncTask() {
            MyAsyncTask myAsyncTask = new MyAsyncTask();
            String httpUrl = "https://api.weibo.com/2/statuses/public_timeline.json";
            myAsyncTask.execute(httpUrl);
        }

    }
    public interface Constants {
        public static final String APP_KEY = "1517365558";           // 应用的APP_KEY
        public static final String REDIRECT_URL = "http://www.sina.com";// 应用的回调页
        public static final String SCOPE =                               // 应用申请的高级权限 是否需要实现好友邀请接口
                //需要在SCOPE参数中添加"invitation_write"值。
                "email,direct_messages_read,direct_messages_write,"
                        + "friendships_groups_read,friendships_groups_write,statuses_to_me_read,"
                        + "follow_app_official_microblog," + "invitation_write";
    }

}


