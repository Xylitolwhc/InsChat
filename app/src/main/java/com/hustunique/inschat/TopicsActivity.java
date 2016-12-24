package com.hustunique.inschat;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.os.PersistableBundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import Adapters.TopicsAdapter;
import Application.InsChatApplication;
import Items.RecycleViewDivider;
import Items.ReplyItem;
import Items.TopicItem;
import Util.LeanCloudUtil;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 吴航辰 on 2016/12/22.
 */

public class TopicsActivity extends AppCompatActivity {
    @BindView(R.id.recyclerViewOfTopics)
    RecyclerView recyclerViewOfTopics;
    @BindView(R.id.swipeRefreshLayoutOfTopics)
    SwipeRefreshLayout swipeRefreshLayoutOfTopics;
    @BindView(R.id.button_add)
    LinearLayout button_add;
    @BindView(R.id.button_back)
    LinearLayout button_back;

    private String wifiSSID;
    TopicsAdapter topicsAdapter;


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            ArrayList<TopicItem>list = (ArrayList<TopicItem>)msg.obj;
            //topicsAdapter.setList(list);
            swipeRefreshLayoutOfTopics.setRefreshing(false);
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topics);
        getSupportActionBar().hide();
        ButterKnife.bind(this);

        wifiSSID = getIntent().getStringExtra("SSID");
        getSupportActionBar().setTitle(wifiSSID);

        swipeRefreshLayoutOfTopics.setRefreshing(true);
        recyclerViewOfTopics.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewOfTopics.addItemDecoration(new RecycleViewDivider(this,LinearLayout.HORIZONTAL));
        swipeRefreshLayoutOfTopics.setRefreshing(true);
        topicsAdapter =  new TopicsAdapter(this, new ArrayList<TopicItem>());
        recyclerViewOfTopics.setAdapter(topicsAdapter);
        //refresh();
        LeanCloudUtil.getTopicList(wifiSSID,handler);
        //Log.d("holo", (list == null) + " s");
        swipeRefreshLayoutOfTopics.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });

        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(TopicsActivity.this);
                View dialog = LayoutInflater.from(TopicsActivity.this).inflate(R.layout.activity_add_topic,null);
                builder.setView(dialog);
                final EditText topic_add_topic=(EditText)dialog.findViewById(R.id.topic_add_topic);
                final EditText topic_add_content=(EditText)dialog.findViewById(R.id.topic_add_content);
                builder.setPositiveButton("发送", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String content=topic_add_content.getText().toString();
                        String topic=topic_add_topic.getText().toString();
                        Log.d("holo", "content" + content + "topic" + topic);
                        TopicItem topicItem = new TopicItem();
                        topicItem.setTitleAndContent(topic, content);
                        topicItem.setWifiName(wifiSSID);
                        topicItem.setCreatorNickName(InsChatApplication.getUser().getNickname());
                        topicItem.setCreatorSignature(InsChatApplication.getUser().getSignature());
                        LeanCloudUtil.addTopic(topicItem);
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });

        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 onBackPressed();
            }
        });
    }

    private void refresh() {


    }
}


