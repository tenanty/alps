package com.channelsoft.alps.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.channelsoft.alps.R;

/**
 * 注册页面
 * Created by yuanshun on 2015/1/5.
 */
public class RegisterActivity extends Activity {

    private final String ACTIVITY_TAG = "REGISTER_TAG";
    private Button mRegisterButton = null;
    private EditText mUsername = null;
    private EditText mPassword = null;
    private EditText mConfirmPwd = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(ACTIVITY_TAG, "进入 RegisterActivity.onCreate() ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        mRegisterButton = (Button) findViewById(R.id.register_button);
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mUsername = (EditText) findViewById(R.id.new_account_username);
                mPassword = (EditText) findViewById(R.id.new_account_password);
                mConfirmPwd = (EditText) findViewById(R.id.confirm_account_password);

                Log.d(ACTIVITY_TAG, "获取用户名：" + mUsername.getText().toString());
                Log.d(ACTIVITY_TAG, "获取密码：" + mPassword.getText().toString());
                Log.d(ACTIVITY_TAG, "获取确认密码：" + mConfirmPwd.getText().toString());

                Intent intent = new Intent();
                intent.setClass(RegisterActivity.this, WelcomeActivity.class);

                //设置数据绑定
                Bundle mBundle = new Bundle();
                mBundle.putString("username", mUsername.getText().toString());
                mBundle.putString("password", mPassword.getText().toString());
                intent.putExtras(mBundle);
                startActivity(intent);
                finish();
            }
        });


    }
}
