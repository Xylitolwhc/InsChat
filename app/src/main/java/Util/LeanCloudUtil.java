package Util;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.SaveCallback;

import Application.InsChatApplication;
import Items.ReplyItem;
import Items.TopicItem;
import Items.User;

/**
 * Created by QiMeng on 2016/12/22.
 */

public class LeanCloudUtil {
    public static final String TABLE_WIFI_LIST = "WIFI_LIST";
    public static final String TABLE_TOPIC_LIST = "TOPIC_LIST";
    public static final String TABLE_REPLY_LIST = "CONTENT_LIST";
    public static final String TABLE_USER_LIST = "USER_LIST";
    private static final String NAME = "NAME";
    private static final String WIFI_HASHCODE = "WIFI_HASHCODE";
    private static final String TOPIC_HASHCODE = "TOPIC_HASHCODE";
    private static final String CONTENT = "CONTENT";
    private static final String REPLY_CONTENT = "REPLY_CONTENT";
    private static final String TITLE = "TITLE";
    private static final String IMEI = "IMEI";
    private static final String SIGNATURE = "SIGNATURE";
    private static final String AVATAR = "AVATAR";

    private static AVObject wifiObject = new AVObject(TABLE_WIFI_LIST);
    private static AVObject topicObject = new AVObject(TABLE_TOPIC_LIST);
    private static AVObject userObject = new AVObject(TABLE_USER_LIST);
    private static AVObject replyObject = new AVObject(TABLE_REPLY_LIST);

    public static void addWIFI(final String WIFIName) {
        wifiObject.add(NAME, WIFIName);
        wifiObject.add(WIFI_HASHCODE,WIFIName.hashCode());
        wifiObject.saveInBackground(new SaveCallback() {
            @Override
            public void done(AVException e) {
                if (e == null) {
                    Toast.makeText(InsChatApplication.getInstance(), "分组：" + WIFIName + "创建成功！", Toast.LENGTH_SHORT);
                }
            }
        });
    }

    public static void addTopic(TopicItem item) {

        String title = item.getTitle();
        String content = item.getContent();
        String imei = item.getImei();
        long wifiHashcode = item.getWifiName().hashCode();

        topicObject.add(WIFI_HASHCODE,wifiHashcode);
        topicObject.add(TITLE, title);
        topicObject.add(CONTENT, content);
        topicObject.add(IMEI,imei);
        topicObject.add(TOPIC_HASHCODE, item.getTopicHashcode());
        topicObject.saveInBackground(new SaveCallback() {
            @Override
            public void done(AVException e) {
                if (e == null) {
                    InsChatApplication.toast("话题创建成功！");
                } else {
                    InsChatApplication.toast("话题创建失败！");
                }
            }
        });
    }

    public static void replyTopic(ReplyItem item) {
        replyObject.add(TOPIC_HASHCODE, item.getTopicHashcode());
        replyObject.add(IMEI, item.getImei());
        replyObject.add(REPLY_CONTENT,item.getContent());
        replyObject.saveInBackground();
    }

    public static void addUser(User user) {
        userObject.add(IMEI, user.getImei());
        userObject.add(NAME, user.getNickname());
        userObject.add(SIGNATURE, user.getSignature());
        userObject.add(AVATAR, user.getAvatar());
        userObject.saveInBackground(new SaveCallback() {
            @Override
            public void done(AVException e) {
                if (e == null) {
                    InsChatApplication.toast("用户创建成功");
                }else {
                    InsChatApplication.toast("用户创建失败");
                }
            }
        });
    }

    public static void updateUser(User user) {
        final SharedPreferences userData = InsChatApplication.getInstance().getSharedPreferences("userData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = userData.edit();

    }
}
