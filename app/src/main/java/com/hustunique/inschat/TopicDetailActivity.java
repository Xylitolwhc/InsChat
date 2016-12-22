package com.hustunique.inschat;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import Adapters.TopicsDetailAdapter;
import Items.ReplyItem;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 吴航辰 on 2016/12/22.
 */

public class TopicDetailActivity extends AppCompatActivity {
    @BindView(R.id.swipeRefreshLayoutOfTopicDetail)
    SwipeRefreshLayout swipeRefreshLayoutOfTopicDetail;
    @BindView(R.id.recyclerViewOfTopicDetail)
    RecyclerView recyclerViewOfTopicDetail;

    private List<ReplyItem> replyItemList=new ArrayList<>();
    private TopicsDetailAdapter topicsDetailAdapter;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            topicsDetailAdapter=new TopicsDetailAdapter(TopicDetailActivity.this,replyItemList);
            recyclerViewOfTopicDetail.setAdapter(topicsDetailAdapter);
            swipeRefreshLayoutOfTopicDetail.setRefreshing(false);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_detail);
        ButterKnife.bind(this);
        swipeRefreshLayoutOfTopicDetail.setRefreshing(true);
        recyclerViewOfTopicDetail.setLayoutManager(new LinearLayoutManager(this));
        swipeRefreshLayoutOfTopicDetail.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
    }
    private  void refresh(){
        handler.sendEmptyMessage(0);
    }
}
