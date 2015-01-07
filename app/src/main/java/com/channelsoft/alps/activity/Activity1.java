package com.channelsoft.alps.activity;

import android.app.Activity;
import android.os.Bundle;

import com.channelsoft.alps.R;
import com.channelsoft.alps.activity.base.BaseActivity;

public class Activity1 extends BaseActivity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		  setContentView(R.layout.tab1_layout);
	}
}
