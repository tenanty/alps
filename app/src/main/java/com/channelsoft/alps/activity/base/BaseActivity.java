package com.channelsoft.alps.activity.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

/**
 * 基类Activity，提供某些方法
 * Created by yuanshun on 2015/1/7.
 */
public class BaseActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
    }
}
