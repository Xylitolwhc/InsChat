package Items;

import android.graphics.Bitmap;

/**
 * Created by QiMeng on 2016/12/22.
 */

public class User {
    private String nickname;
    private String signature;
    private String imei;
    private Bitmap avatar;
    private String userId;
    private String avatarPath;

    public User() {

    }

    public User(String nickname, String signature, String imei, Bitmap avatar, String avatarPath) {
        this.avatarPath = avatarPath;
        this.nickname = nickname;
        this.signature = signature;
        this.imei = imei;
        this.avatar = avatar;
    }


    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public Bitmap getAvatar() {
        return avatar;
    }

    public void setAvatar(Bitmap avatar) {
        this.avatar = avatar;
    }
}
