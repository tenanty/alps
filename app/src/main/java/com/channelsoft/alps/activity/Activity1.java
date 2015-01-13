package com.channelsoft.alps.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.channelsoft.alps.R;
import com.channelsoft.alps.activity.base.BaseActivity;

/**
 * 路线activity
 */
public class Activity1 extends BaseActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab1_layout);
        button = (Button) findViewById(R.id.to_record);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Activity1.this,CustomSoundActivity.class);
                startActivity(intent);
            }
        });

    }
}
