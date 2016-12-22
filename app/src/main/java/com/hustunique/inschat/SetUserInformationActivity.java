package com.hustunique.inschat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by QiMeng on 2016/12/22.
 */

public class SetUserInformationActivity extends AppCompatActivity {
    @BindView(R.id.nickname)
    EditText inputNickName;

    @BindView(R.id.signature)
    EditText inputSignature;

    @OnClick(R.id.save)
    public void clickSave() {
        String nickname = inputNickName.getText().toString();

        String signature = inputSignature.getText().toString();

        String avatarPath;


    }


    @Override
    public void onCreate(Bundle state) {


        ButterKnife.bind(this);
    }

}
