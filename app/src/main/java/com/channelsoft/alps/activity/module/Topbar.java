package com.channelsoft.alps.activity.module;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.channelsoft.alps.R;

/**
 * 自定义topbar组件
 * Created by yuanshun on 2015/1/13.
 */
public class Topbar  extends RelativeLayout{

    //基本元素信息：两个按钮+一个描述
    private Button leftButton, rightButton;
    private TextView tvTitle;

    //左按钮的修饰信息
    private int leftTextColor;
    private Drawable leftBackground;
    private String leftText;

    //右按钮的修饰信息
    private int rightTextColor;
    private Drawable rightBackground;
    private String rightText;

    //标题的描述信息
    private float titleTextSize;
    private int titleTextColor;
    private String title;

    //放在layout中
    private LayoutParams leftParams, rightParams, titleParams;


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public Topbar(Context context, AttributeSet attrs) {
        super(context, attrs);

        //自定义属性获取
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.Topbar);

        leftTextColor = ta.getColor(R.styleable.Topbar_leftTextColor, 0);
        leftBackground = ta.getDrawable(R.styleable.Topbar_leftBackground);
        leftText = ta.getString(R.styleable.Topbar_leftText);

        rightTextColor = ta.getColor(R.styleable.Topbar_rightTextColor, 0);
        rightBackground = ta.getDrawable(R.styleable.Topbar_rightBackground);
        rightText = ta.getString(R.styleable.Topbar_rightText);

        titleTextSize = ta.getDimensionPixelSize(R.styleable.Topbar_titleTextSize, 0);
        titleTextColor = ta.getColor(R.styleable.Topbar_titleTextColor, 0);
        title = ta.getString(R.styleable.Topbar_titleText);

        //回收ta
        ta.recycle();

        //实例化button和view
        leftButton = new Button(context);
        rightButton = new Button(context);
        tvTitle = new TextView(context);

        //获取到的属性赋值给控件
        leftButton.setTextColor(leftTextColor);
        leftButton.setBackground(leftBackground);
        leftButton.setText(leftText);

        //获取到的属性赋值给控件
        rightButton.setTextColor(rightTextColor);
        rightButton.setBackground(rightBackground);
        rightButton.setText(rightText);

        tvTitle.setTextColor(titleTextColor);
        tvTitle.setTextSize(titleTextSize);
        tvTitle.setText(title);
        tvTitle.setGravity(Gravity.CENTER);//居中
        setBackgroundColor(Color.BLACK);
//        //放在layout中,传入长宽信息
//        leftParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
//        //居左对其
//        leftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);
//        addView(leftButton, leftParams);

        //放在layout中,传入长宽信息,下列设置宽高为WRAP_CONTENT
        leftParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        //增加规则：居左对其
        leftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);
        addView(leftButton, leftParams);

        //放在layout中,传入长宽信息,下列设置宽高为WRAP_CONTENT
        rightParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        //增加规则：居左对其
        rightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, TRUE);
        addView(rightButton, rightParams);

        //放在layout中,传入长宽信息,下列设置宽高为WRAP_CONTENT
        titleParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        //增加规则：居左对其
        titleParams.addRule(RelativeLayout.CENTER_IN_PARENT, TRUE);
        addView(tvTitle, titleParams);//通过addView加入相关视图
    }
}
