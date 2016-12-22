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
    private long createTime;

    private String nickName;
    private String signature;

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    private Bitmap avatar;

    public ReplyItem(long topicHashcode,long createTime, String nickName, String content, Bitmap avatar) {
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

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
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
