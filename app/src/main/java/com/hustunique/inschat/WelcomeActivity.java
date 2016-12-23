package com.hustunique.inschat;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

/**
 * Created by 吴航辰 on 2016/12/23.
 */

public class WelcomeActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome);

        new Thread() {
            @Override
            public void run() {
                try {
                    sleep(3000);
                    SharedPreferences sharedPreferences = getSharedPreferences("First", MODE_APPEND);
                    if (sharedPreferences.contains("first_open")) {
                        startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                    } else {
                        firstOpen();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putInt("first_open", 1);
                        editor.commit();
                        startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

    private void firstOpen() {

    }
}
