package com.hustunique.inschat;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
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

import Adapters.TopicsDetailAdapter;
import Application.InsChatApplication;
import Items.ReplyItem;
import Util.IMEIUtil;
import Util.LeanCloudUtil;
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
    @BindView(R.id.button_add)
    LinearLayout button_add;
    @BindView(R.id.button_back)
    LinearLayout button_back;
    private long hashcode;
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
        getSupportActionBar().hide();
        final Long topicHashCode=getIntent().getLongExtra("HashCode",0);
        swipeRefreshLayoutOfTopicDetail.setRefreshing(true);
        recyclerViewOfTopicDetail.setLayoutManager(new LinearLayoutManager(this));
        Intent intent = getIntent();
        hashcode = intent.getLongExtra("HashCode", 0);
        Log.d("holo", hashcode + " d");
        refresh();
        swipeRefreshLayoutOfTopicDetail.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(TopicDetailActivity.this);
                View dialog = LayoutInflater.from(TopicDetailActivity.this).inflate(R.layout.activity_add_reply,null);
                builder.setView(dialog);
                final EditText add_reply=(EditText)dialog.findViewById(R.id.add_reply);
                builder.setPositiveButton("发送", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String reply=add_reply.getText().toString();

                        ReplyItem replyItem=new ReplyItem();
                        replyItem.setAvatar(InsChatApplication.getUser().getAvatar());
                        replyItem.setContent(reply);
                        replyItem.setCreateTime(System.currentTimeMillis());
                        replyItem.setImei(IMEIUtil.getImei());
                        replyItem.setNickName(InsChatApplication.getUser().getNickname());
                        replyItem.setSignature(InsChatApplication.getUser().getSignature());
                        replyItem.setTopicHashcode(topicHashCode);
                        LeanCloudUtil.replyTopic(replyItem);
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
    }
    private  void refresh(){
        LeanCloudUtil.getRepliList(hashcode);
        handler.sendEmptyMessage(0);
    }
}
