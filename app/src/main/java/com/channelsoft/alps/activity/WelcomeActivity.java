package com.channelsoft.alps.activity;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;

import com.channelsoft.alps.R;

/**
 * Created by yuanshun on 2015/1/5.
 */
public class WelcomeActivity extends TabActivity {

    private final String ACTIVITY_TAG = "WELCOME_TAG";

    private EditText info = null;

    private TabHost tabhost;
    private RadioGroup main_radiogroup;
    private RadioButton tab_icon_weixin, tab_icon_address, tab_icon_friend,tab_icon_setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo);
//
//        info = (EditText) findViewById(R.id.my_info);
//        Intent intent = getIntent();
//        Bundle mUserdata = intent.getExtras();
//
//        String mUsername = mUserdata.getString("username");
//        String mPassword = mUserdata.getString("password");
//        Log.d(ACTIVITY_TAG, "获取用户名，密码信息：[" + mUsername + "," + mPassword + "]");
//        info.setText("欢迎[" + mUsername + "]进入系统.");
        main_radiogroup = (RadioGroup) findViewById(R.id.main_radiogroup);

//        tab_icon_weixin = (RadioButton) findViewById(R.id.tab_icon_weixin);
//        tab_icon_address = (RadioButton) findViewById(R.id.tab_icon_address);
//        tab_icon_friend = (RadioButton) findViewById(R.id.tab_icon_friend);
//        tab_icon_setting = (RadioButton) findViewById(R.id.tab_icon_setting);


        tabhost = getTabHost();
        tabhost.addTab(tabhost.newTabSpec("tag1").setIndicator("0").setContent(new Intent(this,Activity1.class)));
        tabhost.addTab(tabhost.newTabSpec("tag2").setIndicator("1").setContent(new Intent(this,Activity2.class)));
        tabhost.addTab(tabhost.newTabSpec("tag3").setIndicator("2").setContent(new Intent(this,Activity3.class)));
        tabhost.addTab(tabhost.newTabSpec("tag4").setIndicator("3").setContent(new Intent(this,Activity4.class)));

//        CheckListener checkradio = new CheckListener();
//        main_radiogroup.setOnCheckedChangeListener(checkradio);
    }

//    public class CheckListener implements RadioGroup.OnCheckedChangeListener {
//        @Override
//        public void onCheckedChanged(RadioGroup group, int checkedId) {
//            // TODO Auto-generated method stub
//            switch(checkedId){
//                case R.id.tab_icon_weixin:
//                    tabhost.setCurrentTab(0);
//                    //��
//                    //tabhost.setCurrentTabByTag("tag1");
//                    break;
//                case R.id.tab_icon_address:
//                    tabhost.setCurrentTab(1);
//                    break;
//                case R.id.tab_icon_friend:
//                    tabhost.setCurrentTab(2);
//                    break;
//                case R.id.tab_icon_setting:
//                    tabhost.setCurrentTab(3);
//                    break;
//            }
//        }
//    }

}
