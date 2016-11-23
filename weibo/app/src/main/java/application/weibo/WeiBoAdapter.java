package application.weibo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/18.
 */
public class WeiBoAdapter extends BaseAdapter {
    LayoutInflater layoutInflater;
    Context context;
    ArrayList<WeiBo>list;
    public WeiBoAdapter(Context context, ArrayList<WeiBo>list){
        this.context=context;
        this.list=list;

    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        layoutInflater = LayoutInflater.from(context);
        if (convertView!=null){
            convertView = layoutInflater.inflate(R.layout.weibo_list,null);
            viewHolder = new ViewHolder();
            viewHolder.id = convertView.findViewById(R.id.id);
            viewHolder.mid = convertView.findViewById(R.id.mid);
            viewHolder.created_at = convertView.findViewById(R.id.created_at);
            viewHolder.source = convertView.findViewById(R.id.source);
            viewHolder.text = convertView.findViewById(R.id.text);
            viewHolder.thumbnail_pic = convertView.findViewById(R.id.thumbnail_pic);
            viewHolder.reposts_count = convertView.findViewById(R.id.reposts_count);
            viewHolder.comments_count = convertView.findViewById(R.id.comments_count);
            viewHolder.attitudes_count = convertView.findViewById(R.id.attitudes_count);
            convertView.setTag(viewHolder);
        }

        return convertView;
    }
}
