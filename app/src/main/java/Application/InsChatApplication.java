package Application;

import android.app.Application;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.avos.avoscloud.AVOSCloud;

import Items.User;


/**
 * Created by 吴航辰 on 2016/12/20.
 */

public class InsChatApplication extends Application {
    private static InsChatApplication insChatApplication;
    private static Toast toast;
    private static User user;

    @Override
    public void onCreate() {
        super.onCreate();
        insChatApplication = this;
        toast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
        //初始化参数依次为 this, AppId, AppKey
        AVOSCloud.initialize(this, "LqHW6OxqSFijlFWcwRtdV32d-gzGzoHsz", "cAxlcdWutU6rY9SfoXd5SskN");
        SharedPreferences sharedPreferences = insChatApplication.getSharedPreferences("data.dll", MODE_PRIVATE);
        boolean hasInit = sharedPreferences.getBoolean("hasInit", false);
        if (hasInit) {
            resetUser();
        }

    }

    public static InsChatApplication getInstance() {
        return insChatApplication;
    }

    public static void toast(String text) {
        toast.setText(text);
        toast.show();
    }


    public static User getUser() {
        return user;
    }

    public static void resetUser() {
        SharedPreferences sharedPreferences = insChatApplication.getSharedPreferences("data.dll", MODE_PRIVATE);
        String nickname = sharedPreferences.getString("nickname", null);
        String signature = sharedPreferences.getString("signature", null);
        String gender = sharedPreferences.getString("gender", null);
        String imei = sharedPreferences.getString("imei", null);
        user = new User();
        user.setSignature(signature);
        user.setNickname(nickname);
        user.setGender(gender);
        user.setImei(imei);
        Log.d("holo", "finish reset");
    }


}
