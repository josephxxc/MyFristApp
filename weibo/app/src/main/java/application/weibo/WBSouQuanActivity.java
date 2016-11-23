package application.weibo;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.exception.WeiboException;

import java.text.SimpleDateFormat;

/**
 * Created by Administrator on 2016/11/23.
 */
public class WBSouQuanActivity extends Activity {
    private SsoHandler mSsoHandler;
    private AuthInfo mAuthInfo;
    private Oauth2AccessToken mAccessToken;
    Button souquan_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wb_souquan);
        souquan_btn = (Button) findViewById(R.id.souquan_btn);

        mAuthInfo = new AuthInfo(this, MainActivity.Constants.APP_KEY, MainActivity.Constants.REDIRECT_URL, MainActivity.Constants.SCOPE);
        mSsoHandler = new SsoHandler(WBSouQuanActivity.this, mAuthInfo);

        souquan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSsoHandler.authorizeWeb(new WeiboAuthListener() {
                    public void onComplete(Bundle values) {
                        // 从 Bundle 中解析 Token
                        mAccessToken = Oauth2AccessToken.parseAccessToken(values);
                        //从这里获取用户输入的 电话号码信息
                        String phoneNum = mAccessToken.getPhoneNum();
                        if (mAccessToken.isSessionValid()) {
                            // 显示 Token
                            updateTokenView(false);

                            // 保存 Token 到 SharedPreferences
//                            AccessTokenKeeper.writeAccessToken(WBSouQuanActivity.this, mAccessToken);
                            Toast.makeText(WBSouQuanActivity.this,
                                    R.string.weibosdk_demo_toast_auth_success, Toast.LENGTH_SHORT).show();
                        } else {
                            // 以下几种情况，您会收到 Code：
                            // 1. 当您未在平台上注册的应用程序的包名与签名时；
                            // 2. 当您注册的应用程序包名与签名不正确时；
                            // 3. 当您在平台上注册的包名和签名与您当前测试的应用的包名和签名不匹配时。
                            String code = values.getString("code");
                            String message = getString(R.string.weibosdk_demo_toast_auth_failed);
                            if (!TextUtils.isEmpty(code)) {
                                message = message + "\nObtained the code: " + code;
                            }
                            Toast.makeText(WBSouQuanActivity.this, message, Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onCancel() {
                        Toast.makeText(WBSouQuanActivity.this,
                                R.string.weibosdk_demo_toast_auth_canceled, Toast.LENGTH_LONG).show();
                    }
                    @Override
                    public void onWeiboException(WeiboException e) {
                        Toast.makeText(WBSouQuanActivity.this,
                                "Auth exception : " + e.getMessage(), Toast.LENGTH_LONG).show();
                    }

                });
            }
        });

    }
    private void updateTokenView(boolean hasExisted) {
        String date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(
                new java.util.Date(mAccessToken.getExpiresTime()));
        String format = getString(R.string.weibosdk_demo_token_to_string_format_1);
//        mTokenText.setText(String.format(format, mAccessToken.getToken(), date));

        String message = String.format(format, mAccessToken.getToken(), date);
        if (hasExisted) {
            message = getString(R.string.weibosdk_demo_token_has_existed) + "\n" + message;
        }
//        mTokenText.setText(message);
    }
}
