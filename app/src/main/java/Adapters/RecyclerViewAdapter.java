package Adapters;

import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hustunique.inschat.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;



/**
 * Created by 吴航辰 on 2016/12/20.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewViewHolder> {
    private Context context;
    private List<WifiConfiguration> wifiConfigurationList;

    public RecyclerViewAdapter(Context context, List<WifiConfiguration> wifiConfigurationList) {
        this.context = context;
        this.wifiConfigurationList = wifiConfigurationList;
    }

    @Override
    public RecyclerViewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerViewViewHolder holder = new RecyclerViewViewHolder(LayoutInflater.from(context).inflate(R.layout.wifi_chat_item, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewViewHolder holder, int position) {
        holder.WifiSSID.setText(wifiConfigurationList.get(position).SSID.toString().replace("\"", ""));

        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss     ");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);
        holder.LastReplyTime.setText(str);
    }

    @Override
    public int getItemCount() {
        return wifiConfigurationList.size();
    }

    class RecyclerViewViewHolder extends RecyclerView.ViewHolder {
        TextView LastReplyTime;
        TextView WifiSSID;
        TextView LastReplyContent;

        public RecyclerViewViewHolder(View itemView) {
            super(itemView);
            LastReplyContent = (TextView) itemView.findViewById(R.id.LastReplyContent);
            WifiSSID = (TextView) itemView.findViewById(R.id.WifiSSID);
            LastReplyTime = (TextView) itemView.findViewById(R.id.LastReplyTime);
        }
    }
}
