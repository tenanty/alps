package com.channelsoft.alps.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.channelsoft.alps.R;
import com.channelsoft.alps.activity.base.BaseActivity;


/**
 * 登录页面
 */
public class MainActivity extends BaseActivity {

    private IntentFilter intentFilter;
    private NetworkChangeReceiver networkChangeReceiver;

    private Button mLoginButton = null;
    private Button mRegisterButton = null;
    private EditText mUsername = null;
    private EditText mPassword = null;

    private final String ACTIVITY_TAG = MainActivity.class.getName();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(networkChangeReceiver);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //不在活动中显示标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        //注册网络变化广播
        intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkChangeReceiver = new NetworkChangeReceiver();
        registerReceiver(networkChangeReceiver, intentFilter);

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
                intent.setClass(MainActivity.this, RegisterActivity.class);
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

    class NetworkChangeReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            //判断网络状态为断开还是连接
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isAvailable()) {
                Toast.makeText(context, "network changes:available", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context,"network changes:unavailable",Toast.LENGTH_SHORT).show();
            }


        }
    }

}
