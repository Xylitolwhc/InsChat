package com.hustunique.inschat;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import Adapters.TopicsAdapter;
import Items.TopicItem;
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
    private String wifiSSID;
    private List<TopicItem> topicItemList = new ArrayList<>();
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            TopicsAdapter topicsAdapter = new TopicsAdapter(TopicsActivity.this, topicItemList);
            recyclerViewOfTopics.setAdapter(topicsAdapter);
            swipeRefreshLayoutOfTopics.setRefreshing(false);
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topics);
        ButterKnife.bind(this);

        wifiSSID = getIntent().getStringExtra("SSID");
        getSupportActionBar().setTitle(wifiSSID);

        swipeRefreshLayoutOfTopics.setRefreshing(true);
        recyclerViewOfTopics.setLayoutManager(new LinearLayoutManager(this));
        swipeRefreshLayoutOfTopics.setRefreshing(true);
        refresh();
        swipeRefreshLayoutOfTopics.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
    }

    private void refresh() {
        handler.sendEmptyMessage(0);
    }
}
