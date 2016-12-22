package Items;


import android.graphics.Bitmap;

import Application.InsChatApplication;

/**
 * Created by QiMeng on 2016/12/20.
 */

public class ReplyItem {
    //回复时需要的属性
    private long topicHashcode;

    private String content;

    //其他属性

    private String imei = InsChatApplication.getInstance().getUser().getImei();
    private int createTime;

    private String nickName;

    private Bitmap avatar;

    public ReplyItem(long topicHashcode,int createTime, String nickName, String content, Bitmap avatar) {
        this.createTime = createTime;
        this.topicHashcode = topicHashcode;
        this.nickName = nickName;
        this.content = content;
        this.avatar = avatar;
    }
    public ReplyItem() {

    }

    public long getTopicHashcode() {
        return topicHashcode;
    }

    public void setTopicHashcode(long topicHashcode) {
        this.topicHashcode = topicHashcode;
    }

    public int getCreateTime() {
        return createTime;
    }

    public void setCreateTime(int createTime) {
        this.createTime = createTime;
    }

    public String getImei() {
        return imei;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Bitmap getAvatar() {
        return avatar;
    }

    public void setAvatar(Bitmap avatar) {
        this.avatar = avatar;
    }
}
