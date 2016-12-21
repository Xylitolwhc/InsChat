package com.hustunique.inschat;

import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import Adapters.FragmentsAdapter;
import Fragments.MainPageFragment;
import Fragments.MessageBoardFragment;
import Fragments.SettingsFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //    @BindView(R.id.GET)
//    Button get;
    @BindView(R.id.MainViewPager)
    ViewPager MainViewPager;
    @BindView(R.id.MainPageTabText)
    TextView tv0;
    @BindView(R.id.MessageBoardTabText)
    TextView tv1;
    @BindView(R.id.SettingsTabText)
    TextView tv2;
    @BindView(R.id.MainPageTabImage)
    ImageView iv0;
    @BindView(R.id.MessageBoardTabImage)
    ImageView iv1;
    @BindView(R.id.SettingsTabImage)
    ImageView iv2;
    @BindView(R.id.MainPageTab)
    LinearLayout ll0;
    @BindView(R.id.MessageBoardTab)
    LinearLayout ll1;
    @BindView(R.id.SettingsTab)
    LinearLayout ll2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
//        get.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                WifiManager wifimanager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
//                List<WifiConfiguration> wifiConfigurationList = wifimanager.getConfiguredNetworks();
//                TelephonyManager TelephonyMgr = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
//                String imei = TelephonyMgr.getDeviceId();
////                String s = "";
////                for (WifiConfiguration wifiConfiguration : wifiConfigurationList) {
////                    s = s + wifiConfiguration.SSID.toString() + "\n";
////                }
//            }
//        });

//        // 测试 SDK 是否正常工作的代码
//        AVObject testObject = new AVObject("TestObject");
//        testObject.put("words", "Hello World!");
//        testObject.saveInBackground(new SaveCallback() {
//            @Override
//            public void done(AVException e) {
//                if (e == null) {
//                    Log.d("saved", "success!");
//                }
//            }
//        });
    }

    private void init() {
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new MainPageFragment());
        fragmentList.add(new MessageBoardFragment());
        fragmentList.add(new SettingsFragment());

        iv0.setBackgroundColor(getResources().getColor(R.color.colorBarUnSelected));
        iv1.setBackgroundColor(getResources().getColor(R.color.colorBarUnSelected));
        iv2.setBackgroundColor(getResources().getColor(R.color.colorBarUnSelected));

        changeTab(0);
        MainViewPager.setAdapter(new FragmentsAdapter(getSupportFragmentManager(), fragmentList));
        MainViewPager.setOffscreenPageLimit(3);
        MainViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                changeTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        ll0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainViewPager.setCurrentItem(0);
            }
        });
        ll1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainViewPager.setCurrentItem(1);
            }
        });
        ll2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainViewPager.setCurrentItem(2);
            }
        });
    }

    private void changeTab(int position) {
        tv0.setTextColor(getResources().getColor(R.color.colorBarUnSelected));
        tv1.setTextColor(getResources().getColor(R.color.colorBarUnSelected));
        tv2.setTextColor(getResources().getColor(R.color.colorBarUnSelected));
        iv0.setBackgroundColor(getResources().getColor(R.color.colorBarUnSelected));
        iv1.setBackgroundColor(getResources().getColor(R.color.colorBarUnSelected));
        iv2.setBackgroundColor(getResources().getColor(R.color.colorBarUnSelected));
        switch (position) {
            case 0: {
                iv0.setBackgroundColor(getResources().getColor(R.color.colorBarSelected));
                tv0.setTextColor(getResources().getColor(R.color.colorBarSelected));
                break;
            }
            case 1: {
                iv1.setBackgroundColor(getResources().getColor(R.color.colorBarSelected));
                tv1.setTextColor(getResources().getColor(R.color.colorBarSelected));
                break;
            }
            case 2: {
                iv2.setBackgroundColor(getResources().getColor(R.color.colorBarSelected));
                tv2.setTextColor(getResources().getColor(R.color.colorBarSelected));
                break;
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.MainPageTab: {
                changeTab(0);
                break;
            }
            case R.id.MessageBoardTab: {
                changeTab(1);
                break;
            }
            case R.id.SettingsTab: {
                changeTab(2);
                break;
            }
        }
    }
}
