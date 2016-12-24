package Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.hustunique.inschat.R;
import com.hustunique.inschat.UserSettingActivity;

import Application.InsChatApplication;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by 吴航辰 on 2016/12/21.
 */

public class SettingsFragment extends Fragment {
    @OnClick(R.id.set)
    public void setOnClick() {
        Intent intent = new Intent(InsChatApplication.getInstance(), UserSettingActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        InsChatApplication.getInstance().startActivity(intent);
    }
    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        ButterKnife.bind(this,view);


        return view;
    }
}
