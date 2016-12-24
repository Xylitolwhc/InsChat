package Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hustunique.inschat.R;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by 吴航辰 on 2016/12/21.
 */

public class MessageBoardFragment extends Fragment {
    @BindView(R.id.action_bar_text)
    TextView action_bar_text;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_messageboard,container,false);
        ButterKnife.bind(this,view);
        action_bar_text.setText("留言板");
        return view;
    }
}
