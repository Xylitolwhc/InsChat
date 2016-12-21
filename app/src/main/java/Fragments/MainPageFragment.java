package Fragments;

import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.hustunique.inschat.R;

import java.util.List;

import Adapters.RecyclerViewAdapter;
import Items.RecycleViewDivider;
import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by 吴航辰 on 2016/12/21.
 */

public class MainPageFragment extends Fragment {
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private RecyclerViewAdapter recyclerViewAdapter;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            recyclerView.setAdapter(recyclerViewAdapter);
            swipeRefreshLayout.setRefreshing(false);
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new RecycleViewDivider(getActivity(), LinearLayout.VERTICAL));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
        swipeRefreshLayout.setRefreshing(true);
        refresh();
        return view;
    }

    private void refresh() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                WifiManager wifimanager = (WifiManager) getActivity().getSystemService(Context.WIFI_SERVICE);
                List<WifiConfiguration> wifiConfigurationList = wifimanager.getConfiguredNetworks();
                recyclerViewAdapter = new RecyclerViewAdapter(getActivity(), wifiConfigurationList);
                handler.sendEmptyMessage(0);
            }
        }).start();
    }
}
