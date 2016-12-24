package Util;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.SaveCallback;

import java.lang.reflect.Array;
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
    public static final String NAME = "NAME";
    public static final String WIFI_HASHCODE = "WIFI_HASHCODE";
    public static final String TOPIC_HASHCODE = "TOPIC_HASHCODE";
    public static final String CONTENT = "CONTENT";
    public static final String REPLY_CONTENT = "REPLY_CONTENT";
    public static final String TITLE = "TITLE";
    public static final String IMEI = "IMEI";
    public static final String SIGNATURE = "SIGNATURE";
    public static final String AVATAR = "AVATAR";
    public static final String NICKNAME = "NICKNAME";
    public static final String HEAT = "HEAT";


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
        topicObject.add(NICKNAME, item.getCreatorNickName());
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

        query.findInBackground(new FindCallback<AVObject>() {
                                   @Override
                                   public void done(List<AVObject> list, AVException e) {
                                       if (e == null) {
                                           ArrayList<TopicItem> topicList = new ArrayList<>();
                                           for (int i = 0; i < list.size(); i++) {
                                               AVObject object = list.get(i);
                                               TopicItem item = new TopicItem();
                                               String title = ((ArrayList<String>) object.get(TITLE)).get(0);
                                               String content = ((ArrayList<String>) object.get(CONTENT)).get(0);


                                               item.setTitleAndContent(title, content);

                                               item.setCreatorNickName(((ArrayList<String>) object.get(NICKNAME)).get(0));
                                               item.setCreatorSignature(((ArrayList<String>)list.get(i).get(SIGNATURE)).get(0));
                                               item.setImei(((ArrayList<String>) list.get(i).get(IMEI)).get(0));
                                               item.setCreateTime(object.getCreatedAt().getTime());
                                               String tem = ((ArrayList<String>) list.get(i).get(TOPIC_HASHCODE)).toString();
                                               String hashcode = tem.substring(1,tem.length()-1);
                                               Log.d("holo", hashcode + "aa");

                                               
                                               Long hash = new Long(hashcode);
                                               item.setTopicHashcode(hash);
                                               //item.setTopicHashcode(((ArrayList<String>) list.get(i).get(IMEI)).get(0));
                                             //  Log.d("holo",((ArrayList<String>) object.get(HEAT)).get(0));

//                                               item.setHeat(heat);

                                               topicList.add(item);
                                           }
                                       Message message = new Message();
                                       message.obj = topicList;
                                       Log.d("holo", topicList.size() + "a");
                                           handler.sendMessage(message);

                                       }
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
////                item.setCreatorNickName((String) list.get(i).get(NICKNAME));
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

    public static ArrayList<ReplyItem> getRepliList(long hashcode) {
        AVQuery<AVObject> query = new AVQuery<>(TABLE_REPLY_LIST);
        query.whereEqualTo(TOPIC_HASHCODE, hashcode);
        query.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                ArrayList<ReplyItem> replyList = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    ReplyItem item = new ReplyItem();
                    AVObject object = list.get(i);
                    String imei = ((ArrayList<String>) object.get(IMEI)).get(0);
                    item.setImei(imei);
                    item.setContent(((ArrayList<String>) object.get(REPLY_CONTENT)).get(0));
                    item.setCreateTime(object.getCreatedAt().getTime());
                    item.setNickName(((ArrayList<String>) object.get(NICKNAME)).get(0));
                    item.setSignature(((ArrayList<String>) object.get(SIGNATURE)).get(0));
                    replyList.add(item);
                }
                Log.d("holo", replyList.toString());
            }
        });
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
