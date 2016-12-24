package Fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.hustunique.inschat.R;
import com.hustunique.inschat.UserDetailActivity;
import com.hustunique.inschat.UserSettingActivity;

import Application.InsChatApplication;
import Util.LeanCloudUtil;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.Context.MODE_PRIVATE;


/**
 * Created by 吴航辰 on 2016/12/21.
 */

public class SettingsFragment extends Fragment {
    @BindView(R.id.SettingPageUserImage)
    CircleImageView SettingPageUserImage;
    @BindView(R.id.SettingPageUserName)
    TextView SettingPageUserName;
    @BindView(R.id.SettingPageUserSign)
    TextView SettingPageUserSign;
    @BindView(R.id.action_bar_text)
    TextView action_bar_text;
    @OnClick(R.id.set)
    public void setOnClick() {
//        Intent intent = new Intent(InsChatApplication.getInstance(), UserDetailActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        InsChatApplication.getInstance().startActivity(intent);
        startActivity(new Intent(InsChatApplication.getInstance(),UserDetailActivity.class));
    }
    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        ButterKnife.bind(this,view);

        action_bar_text.setText("设置");
        SharedPreferences sharedPreferences=getActivity().getSharedPreferences("data.dll", MODE_PRIVATE);
        SettingPageUserImage.setImageResource(R.mipmap.welcome_page);
        SettingPageUserName.setText(sharedPreferences.getString("nickname",""));
        SettingPageUserSign.setText(sharedPreferences.getString("signature",""));
        return view;
    }
}
