package Application;

import android.app.Application;


/**
 * Created by 吴航辰 on 2016/12/20.
 */

public class MyLeanCloudApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // 初始化参数依次为 this, AppId, AppKey
        // AVOSCloud.initialize(this,"YFUupthAYxFAEYzJu2gCAruJ-gzGzoHsz","AcLBcf9XyBWQFl0FsjUPbFTK");
    }
}