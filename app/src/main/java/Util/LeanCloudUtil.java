package Util;

import android.content.Context;
import android.content.SharedPreferences;
import android.nfc.cardemulation.HostApduService;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.SaveCallback;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    private static final String NICKMAME = "NICKNAME";
    private static final String HEAT = "HEAT";


    public static void addWIFI(final String WIFIName) {
        AVObject wifiObject = new AVObject(TABLE_WIFI_LIST);
        wifiObject.add(NAME, WIFIName);
        wifiObject.add(WIFI_HASHCODE, WIFIName.hashCode());
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
        final AVObject topicObject = new AVObject(TABLE_TOPIC_LIST);

        topicObject.add(WIFI_HASHCODE, wifiHashcode);
        topicObject.add(TITLE, title);
        topicObject.add(CONTENT, content);
        topicObject.add(IMEI, imei);
        topicObject.add(TOPIC_HASHCODE, item.getTopicHashcode());
        topicObject.add(SIGNATURE, item.getCreatorSignature());
        topicObject.add(NICKMAME, item.getCreatorNickName());
        topicObject.add(HEAT, 1);
        topicObject.saveInBackground(new SaveCallback() {
            @Override
            public void done(AVException e) {
                if (e == null) {
                    topicObject.increment(HEAT);
                    topicObject.setFetchWhenSave(true);
                    topicObject.saveInBackground();
                    InsChatApplication.toast("话题创建成功！");
                } else {
                    e.printStackTrace();
                    InsChatApplication.toast("话题创建失败！");
                }
            }
        });
    }

    public static void replyTopic(ReplyItem item) {
        AVObject replyObject = new AVObject(TABLE_REPLY_LIST);
        replyObject.add(TOPIC_HASHCODE, item.getTopicHashcode());
        replyObject.add(IMEI, item.getImei());
        replyObject.add(REPLY_CONTENT, item.getContent());
        replyObject.saveInBackground();
        AVQuery<AVObject> query = new AVQuery<>(TABLE_TOPIC_LIST);
        query.whereEqualTo(TOPIC_HASHCODE, item.getTopicHashcode());
        query.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                String id = list.get(0).getObjectId();
                AVObject object = AVObject.createWithoutData(TABLE_TOPIC_LIST, id);
                object.increment(HEAT);
                object.setFetchWhenSave(true);
                object.saveInBackground();
            }
        });

    }

    public static void addUser(User user) {
        AVObject userObject = new AVObject(TABLE_USER_LIST);
        userObject.add(IMEI, user.getImei());
        userObject.add(NAME, user.getNickname());
        userObject.add(SIGNATURE, user.getSignature());
        userObject.add(AVATAR, user.getAvatar());
        userObject.saveInBackground(new SaveCallback() {
            @Override
            public void done(AVException e) {
                if (e == null) {
                    InsChatApplication.toast("用户创建成功");
                } else {
                    InsChatApplication.toast("用户创建失败");
                }
            }
        });
    }

    public static void updateUser(User user) {

    }

    public static void getTopicList(String wifiName, final Handler handler) {
        AVQuery<AVObject> query = new AVQuery<>(TABLE_TOPIC_LIST);
        query.whereEqualTo(WIFI_HASHCODE, wifiName.hashCode());
        Log.d("holo", wifiName);
        query.findInBackground(new FindCallback<AVObject>() {
                                   @Override
                                   public void done(List<AVObject> list, AVException e) {

                                       ArrayList<TopicItem> topicList = new ArrayList<>();
                                       for (int i = 0; i < list.size(); i++) {
                                           TopicItem item = new TopicItem();
                                           String title = (String) list.get(i).get(TITLE);
                                           String content = (String) list.get(i).get(CONTENT);

                                           item.setTitleAndContent(title, content);
                                           item.setCreatorNickName((String) list.get(i).get(NICKMAME));
                                           item.setCreatorSignature((String) list.get(i).get(SIGNATURE));
                                           item.setImei((String) list.get(i).get(IMEI));
 //                                          item.setCreateTime(list.get(i).getCreatedAt().getTime());
                                           //item.setCreateTime(((Date) list.get(i).get("createdAt")).getTime());
                                        //   item.setHeat(2);
                                           //(Integer) list.get(i).get(HEAT)
                                        //   topicList.add(item);
                                       }
//                                       Message message = new Message();
//                                       message.obj = topicList;
//                                       Log.d("holo", topicList.size() + "a");
                                       //handler.sendMessage(message);

                                   }
                               }
            );
//        try {
//            List<AVObject> list = query.find();
//            Log.d("holo", wifiName);
//            ArrayList<TopicItem> topicList = new ArrayList<>();
//            Log.d("holo", (list == null) + "  a");
////            for (int i = 0;i<list.size();i++) {
////                TopicItem item = new TopicItem();
////                String title = (String) list.get(i).get(TITLE);
////                String content = (String) list.get(i).get(CONTENT);
////
////                item.setTitleAndContent(title, content);
////                item.setCreatorNickName((String) list.get(i).get(NICKMAME));
////                item.setCreatorSignature((String) list.get(i).get(SIGNATURE));
////                item.setImei((String) list.get(i).get(IMEI));
////                item.setCreateTime(((Date)list.get(i).get("createdAt")).getTime());
////                item.setHeat((Integer)list.get(i).get(HEAT));
////                topicList.add(item);
////
////            }
//            return topicList;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        }

        public static ArrayList<ReplyItem> getRepliList ( long hashcode){
            AVQuery<AVObject> query = new AVQuery<>(TABLE_REPLY_LIST);
            query.whereEqualTo(TOPIC_HASHCODE, hashcode);
            try {
                List<AVObject> list = query.find();
                ArrayList<ReplyItem> replyList = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    ReplyItem item = new ReplyItem();
                    item.setImei((String) list.get(i).get(IMEI));
                    item.setContent((String) list.get(i).get(REPLY_CONTENT));
                    item.setCreateTime(((Date) list.get(i).get("createdAt")).getTime());
                    item.setNickName((String) list.get(i).get(NICKMAME));
                    item.setSignature((String) list.get(i).get(SIGNATURE));
                    replyList.add(item);
                }
                return replyList;
            } catch (AVException e) {
                e.printStackTrace();
            }
            return null;
        }

    public static boolean ifWIFIExist(String wifiName) {

        AVQuery<AVObject> query = new AVQuery<>(TABLE_WIFI_LIST);
        query.whereEqualTo(NAME, wifiName);
        query.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                //TODO 根据list大小判断wifi是否存在
            }
        });
        return true;
    }
}
