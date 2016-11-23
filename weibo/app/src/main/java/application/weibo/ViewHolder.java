package application.weibo;

import android.view.View;

/**
 * Created by Administrator on 2016/11/22.
 */
public class ViewHolder {
    View created_at; //	微博创建时间
    View id	;//	微博ID
    View mid	;//	微博MID
    String idstr;//	字符串型的微博ID
    View text;;//	微博信息内容
    View source;//微博来源
    boolean favorited;	//是否已收藏，true：是，false：否
    boolean truncated;//是否被截断，true：是，false：否
    String in_reply_to_status_id;//暂未支持）回复ID
    String in_reply_to_user_id;//（暂未支持）回复人UID
    String in_reply_to_screen_name;	//（暂未支持）回复人昵称
    View thumbnail_pic;//缩略图片地址，没有时不返回此字段
    String bmiddle_pic;//中等尺寸图片地址，没有时不返回此字段
    String original_pic;//原始图片地址，没有时不返回此字段
    Object geo	;//地理信息字段 详细
    Object user	;//微博作者的用户信息字段 详细
    Object retweeted_status;//被转发的原微博信息字段，当该微博为转发微博时返回 详细
    View reposts_count;//	转发数
    View comments_count;//评论数
    View attitudes_count;//表态数
    int mlevel;//暂未支持
    Object visible;//微博的可见性及指定可见分组信息。该object中type取值，0：普通微博，1：私密微博，3：指定分组微博，4：密友微博；list_id为分组的组号
    Object pic_ids;//微博配图ID。多图时返回多图ID，用来拼接图片url。用返回字段thumbnail_pic的地址配上该返回字段的图片ID，即可得到多个图片url。
    Object ad;	// array	微博流内的推广微博ID
}
