package com.hustunique.inschat;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import Application.InsChatApplication;
import Util.IMEIUtil;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by 吴航辰 on 2016/12/22.
 */

public class UserSettingActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.boy)Button  boy;

    @BindView(R.id.girl) Button girl;
    @BindView(R.id.unknown) Button unknown;

    @BindView(R.id.save) Button save;
    @BindView(R.id.input_nicnname)EditText inputNickName;
    @BindView(R.id.input_signature)EditText inputSignature;
    private String sex = "boy";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_setting);

        ButterKnife.bind(this);
        init();

    }

    public void init() {
        boy.setOnClickListener(this);
        girl.setOnClickListener(this);
        unknown.setOnClickListener(this);
        save.setOnClickListener(this);
        SharedPreferences sharedPreferences = getSharedPreferences("data.dll", MODE_PRIVATE);
        Boolean hasInit = sharedPreferences.getBoolean("hasInit", false);
        if (hasInit) {
            String nickname = sharedPreferences.getString("nickname",null);
            String signature = sharedPreferences.getString("signature", null);
            String sex = sharedPreferences.getString("gender", null);
            switch (sex) {
                case "boy":
                    View view = new View(this);
                    view.setId(R.id.boy);
                    this.onClick(view);
                    break;
                case "girl":
                    View view2 = new View(this);
                    view2.setId(R.id.boy);
                    this.onClick(view2);
                    break;
                case "unknown":
                    View view3 = new View(this);
                    view3.setId(R.id.boy);
                    this.onClick(view3);
                    break;
                default:
                    break;
            }
            inputNickName.setText(nickname);
            inputSignature.setText(signature);
        }


    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.boy:
                girl.setBackgroundColor(getResources().getColor(R.color.colorGray));
                unknown.setBackgroundColor(getResources().getColor(R.color.colorGray));
                boy.setBackgroundColor(getResources().getColor(R.color.colorBarSelected));
                sex = "boy";
                break;
            case R.id.girl:
                boy.setBackgroundColor(getResources().getColor(R.color.colorGray));
                unknown.setBackgroundColor(getResources().getColor(R.color.colorGray));
                girl.setBackgroundColor(getResources().getColor(R.color.colorBarSelected));
                sex = "girl";
                break;
            case R.id.unknown:
                girl.setBackgroundColor(getResources().getColor(R.color.colorGray));
                boy.setBackgroundColor(getResources().getColor(R.color.colorGray));
                unknown.setBackgroundColor(getResources().getColor(R.color.colorBarSelected));
                sex = "unknown";
                break;
            case R.id.save:
                String nickname = inputNickName.getText().toString();
                String signature = inputSignature.getText().toString();
                if (nickname.length() != 0 && signature.length() != 0) {
                    SharedPreferences.Editor editor = getSharedPreferences("data.dll", MODE_PRIVATE).edit();
                    editor.putString("imei", IMEIUtil.getImei());
                    editor.putString("nickname", nickname);
                    editor.putString("signature", signature);
                    editor.putString("gender", sex);
                    editor.putBoolean("hasInit", true);
                    Log.d("holo", "finish init");
                    editor.commit();
                    Log.d("holo", "finish init");
                    InsChatApplication.toast("设置成功");
                    Log.d("holo", "finish init");
                    setResult(MainActivity.HAS_INIT_USER_INFORMATION);
                    finish();
                    Log.d("holo", "finish init");
                }else {
                    InsChatApplication.toast("昵称和介绍不可为空");
                }

            default:
                break;
        }
    }
}
