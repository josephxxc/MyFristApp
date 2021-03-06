package application.weibo;

/**
 * Created by Administrator on 2016/11/18.
 */
public class WeiBo {
    String created_at; //	微博创建时间
    int id	;//	微博ID
    int mid	;//	微博MID
    String idstr;//	字符串型的微博ID
    String text;;//	微博信息内容
    String source;//微博来源
    boolean favorited;	//是否已收藏，true：是，false：否
    boolean truncated;//是否被截断，true：是，false：否
    String in_reply_to_status_id;//暂未支持）回复ID
    String in_reply_to_user_id;//（暂未支持）回复人UID
    String in_reply_to_screen_name;	//（暂未支持）回复人昵称
    String thumbnail_pic;//缩略图片地址，没有时不返回此字段
    String bmiddle_pic;//中等尺寸图片地址，没有时不返回此字段
    String original_pic;//原始图片地址，没有时不返回此字段
    Object geo	;//地理信息字段 详细
    Object user	;//微博作者的用户信息字段 详细
    Object retweeted_status;//被转发的原微博信息字段，当该微博为转发微博时返回 详细
    int reposts_count;//	转发数
    int comments_count;//评论数

    public Object getAd() {
        return ad;
    }

    public void setAd(Object ad) {
        this.ad = ad;
    }

    public Object getPic_ids() {
        return pic_ids;
    }

    public void setPic_ids(Object pic_ids) {
        this.pic_ids = pic_ids;
    }

    public Object getVisible() {
        return visible;
    }

    public void setVisible(Object visible) {
        this.visible = visible;
    }

    public int getAttitudes_count() {
        return attitudes_count;
    }

    public void setAttitudes_count(int attitudes_count) {
        this.attitudes_count = attitudes_count;
    }

    public int getComments_count() {
        return comments_count;
    }

    public void setComments_count(int comments_count) {
        this.comments_count = comments_count;
    }

    public Object getUser() {
        return user;
    }

    public void setUser(Object user) {
        this.user = user;
    }

    public int getMlevel() {
        return mlevel;
    }

    public void setMlevel(int mlevel) {
        this.mlevel = mlevel;
    }

    public Object getRetweeted_status() {
        return retweeted_status;
    }

    public void setRetweeted_status(Object retweeted_status) {
        this.retweeted_status = retweeted_status;
    }

    public int getReposts_count() {
        return reposts_count;
    }

    public void setReposts_count(int reposts_count) {
        this.reposts_count = reposts_count;
    }

    public Object getGeo() {
        return geo;
    }

    public void setGeo(Object geo) {
        this.geo = geo;
    }

    public String getOriginal_pic() {
        return original_pic;
    }

    public void setOriginal_pic(String original_pic) {
        this.original_pic = original_pic;
    }

    public String getThumbnail_pic() {
        return thumbnail_pic;
    }

    public void setThumbnail_pic(String thumbnail_pic) {
        this.thumbnail_pic = thumbnail_pic;
    }

    public String getIn_reply_to_screen_name() {
        return in_reply_to_screen_name;
    }

    public void setIn_reply_to_screen_name(String in_reply_to_screen_name) {
        this.in_reply_to_screen_name = in_reply_to_screen_name;
    }

    public String getBmiddle_pic() {
        return bmiddle_pic;
    }

    public void setBmiddle_pic(String bmiddle_pic) {
        this.bmiddle_pic = bmiddle_pic;
    }

    public String getIn_reply_to_user_id() {
        return in_reply_to_user_id;
    }

    public void setIn_reply_to_user_id(String in_reply_to_user_id) {
        this.in_reply_to_user_id = in_reply_to_user_id;
    }

    public boolean isTruncated() {
        return truncated;
    }

    public void setTruncated(boolean truncated) {
        this.truncated = truncated;
    }

    public String getIn_reply_to_status_id() {
        return in_reply_to_status_id;
    }

    public void setIn_reply_to_status_id(String in_reply_to_status_id) {
        this.in_reply_to_status_id = in_reply_to_status_id;
    }

    public boolean isFavorited() {
        return favorited;
    }

    public void setFavorited(boolean favorited) {
        this.favorited = favorited;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIdstr() {
        return idstr;
    }

    public void setIdstr(String idstr) {
        this.idstr = idstr;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    int attitudes_count;//表态数
    int mlevel;//暂未支持
    Object visible;//微博的可见性及指定可见分组信息。该object中type取值，0：普通微博，1：私密微博，3：指定分组微博，4：密友微博；list_id为分组的组号
    Object pic_ids;//微博配图ID。多图时返回多图ID，用来拼接图片url。用返回字段thumbnail_pic的地址配上该返回字段的图片ID，即可得到多个图片url。
    Object ad;	// array	微博流内的推广微博ID

}
