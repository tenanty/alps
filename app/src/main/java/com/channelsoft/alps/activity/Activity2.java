package com.channelsoft.alps.activity;

import android.app.Activity;
import android.os.Bundle;

import com.channelsoft.alps.R;
import com.channelsoft.alps.activity.base.BaseActivity;
import com.channelsoft.alps.activity.module.Topbar;

public class Activity2 extends BaseActivity {

    private Topbar topbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab2_layout);

        topbar = (Topbar) findViewById(R.id.topbar);
        topbar.setLeftIsVisable(false);
        topbar.setRightIsVisable(false);

	}
}
