package Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.hustunique.inschat.R;

import java.util.ArrayList;

import Adapters.TopicsAdapter;
import Items.RecycleViewDivider;
import Items.TopicItem;
import Util.LeanCloudUtil;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 吴航辰 on 2016/12/24.
 */

public class UserTopicFragment extends Fragment {
    @BindView(R.id.user_reply_recyclerView)
    RecyclerView user_reply_recyclerView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_reply, container, false);
        ButterKnife.bind(this,view);
        user_reply_recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        user_reply_recyclerView.addItemDecoration(new RecycleViewDivider(getActivity(), LinearLayout.HORIZONTAL));
        user_reply_recyclerView.setAdapter(new TopicsAdapter(getActivity(), new ArrayList<TopicItem>()));
        return view;
    }
}
