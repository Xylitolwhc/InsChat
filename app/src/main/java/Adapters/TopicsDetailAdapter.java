package Adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hustunique.inschat.R;

import java.util.List;

import Application.InsChatApplication;
import Items.ReplyItem;

/**
 * Created by 吴航辰 on 2016/12/22.
 */

public class TopicsDetailAdapter extends RecyclerView.Adapter<TopicsDetailAdapter.TopicsDetailViewHolder> {
    private Context context;
    private List<ReplyItem> replyItemList;

    public TopicsDetailAdapter(Context context, List<ReplyItem> replyItemList) {
        this.context = context;
        this.replyItemList = replyItemList;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 1;
        }
        return super.getItemViewType(position);
    }

    @Override
    public TopicsDetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TopicsDetailViewHolder holder;
        if (viewType == 1) {
            holder = new TopicsDetailViewHolder(LayoutInflater.from(context).inflate(R.layout.wifi_topic_reply_item, parent, false));
        } else {
            holder = new TopicsDetailViewHolder(LayoutInflater.from(context).inflate(R.layout.wifi_topic_reply_item, parent, false));
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(TopicsDetailViewHolder holder, final int position) {
        if (position != 0) {
            ReplyItem replyItem = replyItemList.get(position-1);
            holder.replyContent.setText(replyItem.getContent());
            holder.replyFloor.setText(position + "楼");
            holder.replyUsername.setText(replyItem.getNickName());
            holder.replyUserImage.setImageBitmap(replyItem.getAvatar());
            holder.replyLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    InsChatApplication.toast(position + "楼");
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return replyItemList.size()+1;
    }

    class TopicsDetailViewHolder extends RecyclerView.ViewHolder {
        ImageView replyUserImage;
        TextView replyUsername, replyFloor, replyContent;
        LinearLayout replyLayout;

        public TopicsDetailViewHolder(View itemView) {
            super(itemView);
            replyContent = (TextView) itemView.findViewById(R.id.replyContent);
            replyFloor = (TextView) itemView.findViewById(R.id.replyFloor);
            replyUsername = (TextView) itemView.findViewById(R.id.replyUsername);
            replyUserImage = (ImageView) itemView.findViewById(R.id.replyUserImage);
            replyLayout = (LinearLayout) itemView.findViewById(R.id.replyLayout);
        }
    }
}
