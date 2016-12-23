package Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hustunique.inschat.R;
import com.hustunique.inschat.TopicDetailActivity;

import java.util.ArrayList;
import java.util.List;

import Items.TopicItem;

/**
 * Created by 吴航辰 on 2016/12/22.
 */

public class TopicsAdapter extends RecyclerView.Adapter<TopicsAdapter.TopicsViewHolder> {
    private Context context;
    private List<TopicItem> topicItemList;

    public TopicsAdapter(Context context, List<TopicItem> topicItemList) {
        this.context = context;
        this.topicItemList = topicItemList;
    }

    @Override
    public TopicsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TopicsViewHolder holder;
        if (topicItemList.size() == 0) {
            holder = new TopicsViewHolder(LayoutInflater.from(context).inflate(R.layout.item_empty, parent, false));
        } else {
            holder = new TopicsViewHolder(LayoutInflater.from(context).inflate(R.layout.wifi_topic_item, parent, false));
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(TopicsViewHolder holder, int position) {
        if (topicItemList.size() != 0) {
            TopicItem topicItem = topicItemList.get(position);
            holder.theUsername.setText(topicItem.getCreatorNickName());
            holder.theTopicTitle.setText(topicItem.getTitle());
            holder.theReplyNumber.setText(topicItem.getReaplyList().size() + "");
            holder.theUserImage.setImageBitmap(topicItem.getCreatorAvatar());
            holder.WifiTopicLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, TopicDetailActivity.class);
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (topicItemList.size()==0){
            return 1;
        }
        return topicItemList.size();
    }

    class TopicsViewHolder extends RecyclerView.ViewHolder {
        TextView theUsername, theReplyNumber, theTopicTitle;
        ImageView theUserImage;
        LinearLayout WifiTopicLayout;

        public TopicsViewHolder(View itemView) {
            super(itemView);
            theUsername = (TextView) itemView.findViewById(R.id.theUsername);
            theReplyNumber = (TextView) itemView.findViewById(R.id.theReplyNumber);
            theTopicTitle = (TextView) itemView.findViewById(R.id.theTopicTitle);
            theUserImage = (ImageView) itemView.findViewById(R.id.theUserImage);
            WifiTopicLayout = (LinearLayout) itemView.findViewById(R.id.WifiTopicLayout);
        }
    }

    public void setList(ArrayList<TopicItem> list) {
        this.topicItemList = list;
        this.notifyDataSetChanged();
    }
}
