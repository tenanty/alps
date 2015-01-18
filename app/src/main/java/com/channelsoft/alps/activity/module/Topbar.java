package com.channelsoft.alps.activity.module;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.channelsoft.alps.R;

/**
 * 自定义topbar组件
 * Created by yuanshun on 2015/1/13.
 */
public class Topbar extends RelativeLayout {

    //基本元素信息：两个按钮+一个描述
//    private Button leftButton, rightButton;
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

    //增加ImageView来替换左右按钮
    private ImageView leftImageView;
    private ImageView rightImageView;

    private topbarClickListener listener;

    public void setTopbarListener(topbarClickListener listener) {
        this.listener = listener;
    }

    /**
     * 定义监听事件传入接口,由调用方设置左右按钮点击事件
     */
    public interface topbarClickListener {
        public void leftClick();

        public void rightClick();
    }

    /**
     * 设置左按钮是否启用
     *
     * @param isVisable 传入false时将不显示组件,传入true时显示该组件
     */
    public void setLeftIsVisable(boolean isVisable) {
        if (isVisable) {
            leftImageView.setVisibility(View.VISIBLE);
        } else {
            leftImageView.setVisibility(View.GONE);
        }
    }

    /**
     * 设置右按钮是否启用
     *
     * @param isVisable 传入false时将不显示组件,传入true时显示该组件
     */
    public void setRightIsVisable(boolean isVisable) {
        if (isVisable) {
            rightImageView.setVisibility(View.VISIBLE);
        } else {
            rightImageView.setVisibility(View.GONE);
        }
    }

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
//        leftButton = new Button(context);
//        rightButton = new Button(context);
        //左右ImageView替换Button
        leftImageView = new ImageView(context);
        rightImageView = new ImageView(context);

        tvTitle = new TextView(context);

        //获取到的属性赋值给控件
//        leftButton.setTextColor(leftTextColor);
//        leftButton.setBackground(leftBackground);
//        leftButton.setText(leftText);
        leftImageView.setBackgroundResource(R.drawable.u33_normal);


        //获取到的属性赋值给控件
//        rightButton.setTextColor(rightTextColor);
//        rightButton.setBackground(rightBackground);
//        rightButton.setText(rightText);
        rightImageView.setBackgroundResource(R.drawable.u10_normal);

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
        leftParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        //增加规则：居左对其
        leftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);
        leftParams.addRule(RelativeLayout.CENTER_IN_PARENT, TRUE);

        //        addView(leftButton, leftParams);
        //使用ImageView替换Button
        addView(leftImageView, leftParams);

        //放在layout中,传入长宽信息,下列设置宽高为WRAP_CONTENT
        rightParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        //增加规则：居左对其
        rightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, TRUE);
        rightParams.addRule(RelativeLayout.CENTER_IN_PARENT, TRUE);
//        addView(rightButton, rightParams);
        //使用ImageView替换Button
        addView(rightImageView, rightParams);


        //放在layout中,传入长宽信息,下列设置宽高为WRAP_CONTENT
        titleParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        //增加规则：居左对其
        titleParams.addRule(RelativeLayout.CENTER_IN_PARENT, TRUE);
        addView(tvTitle, titleParams);//通过addView加入相关视图

        //设置左按钮点击事件
        leftImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.leftClick();
                }
            }
        });

        //设置右按钮点击事件
        rightImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.rightClick();
                }
            }
        });

    }
}
