package Application;

import android.app.Application;
import android.content.SharedPreferences;
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
    }

    public static InsChatApplication getInstance() {
        return insChatApplication;
    }

    public static void toast(String text) {
        toast.setText(text);
        toast.show();
    }

    public static void setUser(User auser) {
        user = auser;
    }

    public static User getUser() {
        return user;
    }

    public static boolean ifInitUser() {
        SharedPreferences data = getInstance().getSharedPreferences("data.dll", MODE_PRIVATE);
        return data.getBoolean("ifInitUser", false);
    }

}
