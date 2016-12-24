package com.hustunique.inschat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import Adapters.FragmentsAdapter;
import Fragments.UserTopicFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by 吴航辰 on 2016/12/24.
 */

public class UserDetailActivity extends AppCompatActivity {
    @BindView(R.id.setting_viewpager)
    ViewPager setting_viewpager;
    @BindView(R.id.UserDetailImage)
    CircleImageView UserDetailImage;
    @BindView(R.id.button_add)
    LinearLayout button_add;
    @BindView(R.id.button_back)
    LinearLayout button_back;

    private List<Fragment> fragmentList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        getSupportActionBar().hide();
        ButterKnife.bind(this);

        UserDetailImage.setImageResource(R.mipmap.welcome_page);
        UserTopicFragment userTopicFragment=new UserTopicFragment();
        fragmentList.add(userTopicFragment);
        setting_viewpager.setAdapter(new FragmentsAdapter(getSupportFragmentManager(),fragmentList));
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserDetailActivity.this,UserSettingActivity.class));
            }
        });
    }
}
