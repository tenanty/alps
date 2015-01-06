package com.channelsoft.alps.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.channelsoft.alps.R;


/**
 * 登录页面
 */
public class MainActivity extends Activity {

    private Button mLoginButton = null;
    private Button mRegisterButton = null;
    private EditText mUsername = null;
    private EditText mPassword = null;

    private final String ACTIVITY_TAG = "LOGIN_ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mLoginButton = (Button) findViewById(R.id.login_button);
        mRegisterButton = (Button) findViewById(R.id.register_button);
        mUsername = (EditText) findViewById(R.id.new_account_username);
        mPassword = (EditText) findViewById(R.id.new_account_password);

        //登录按钮注册点击事件
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(ACTIVITY_TAG, "点击登录按钮");
                Log.d(ACTIVITY_TAG, "获取用户名：" + mUsername.getText().toString());
                Log.d(ACTIVITY_TAG, "获取用户密码：" + mPassword.getText().toString());

                //跳转到另一个Activity
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, WelcomeActivity.class);

                //设置数据绑定
                Bundle mBundle = new Bundle();
                mBundle.putString("username", mUsername.getText().toString());
                mBundle.putString("password", mPassword.getText().toString());
                intent.putExtras(mBundle);
                startActivity(intent);
                finish();
            }
        });

        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(ACTIVITY_TAG, "点击注册按钮");
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
