package com.channelsoft.alps.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Chronometer.OnChronometerTickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.channelsoft.alps.R;
import com.channelsoft.alps.activity.base.BaseActivity;
import com.channelsoft.alps.activity.module.Topbar;
import com.channelsoft.alps.activity.util.SoundMeter;

import java.io.File;
import java.io.IOException;


public class CustomSoundActivity extends BaseActivity {

    private Button bntRecord;
    private int flag = 1;
    private View rcChat_popup;
    private Handler mHandler = new Handler();
    private long startVoiceT, endVoiceT;
    private String voiceName;
    private SoundMeter mSensor;
    private static final int POLL_INTERVAL = 300;
    private ImageView volume;
    private LinearLayout voice_rcd_hint_rcding, sound_file;
    private TextView txtName;//显示声音文件
    private ImageButton use_bnt;  //是否使用声音文件按钮
    public static boolean isUsed = false;//是否使用自定义声音文件
    private Chronometer timedown;//显示倒计时
    //保存使用状态的文件
    private SharedPreferences sp;
    private long timeTotalInS = 0;
    private long timeLeftInS = 0;
    private MediaPlayer player;

    private Topbar topbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_sound);
        topbar = (Topbar) findViewById(R.id.topbar);
        topbar.setRightIsVisable(false);
        topbar.setTopbarListener(new Topbar.topbarClickListener() {
            @Override
            public void leftClick() {
                CustomSoundActivity.this.finish();
            }

            @Override
            public void rightClick() {

            }
        });

        bntRecord = (Button) findViewById(R.id.bntRecord);
        rcChat_popup = this.findViewById(R.id.rcChat_popup);
        volume = (ImageView) this.findViewById(R.id.volume);

        voice_rcd_hint_rcding = (LinearLayout) this.findViewById(R.id.voice_rcd_hint_rcding);
        sound_file = (LinearLayout) this.findViewById(R.id.sound_file);
        txtName = (TextView) findViewById(R.id.show_sound);
        timedown = (Chronometer) findViewById(R.id.timedown);
        use_bnt = (ImageButton) findViewById(R.id.used_bnt);
        voiceName = "MySound.mp3";
        mSensor = new SoundMeter();
        //触摸录音按钮触发事件
        bntRecord.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                // TODO Auto-generated method stub
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
// TODO
//  bntRecord.setBackgroundColor(getResources().getColor(R.color.blue));
                    bntRecord.setBackgroundColor(Color.BLUE);
                    bntRecord.setText("松开  结束");
                    //TODO
//                    bntRecord.setTextColor(getResources().getColor(R.color.white));
                    bntRecord.setTextColor(Color.WHITE);
                    int[] location = new int[2];
                    bntRecord.getLocationInWindow(location); // 获取在当前窗口内的绝对坐标
                    int btn_rc_Y = location[1];
                    int btn_rc_X = location[0];
                    if (flag == 1) {
                        if (!Environment.getExternalStorageDirectory().exists()) {
                            Toast.makeText(CustomSoundActivity.this, "No SDCard", Toast.LENGTH_LONG).show();
                            return false;
                        }
                        System.out.println("2");
                        System.out.println(event.getY() + "..." + btn_rc_Y + "...." + event.getX() + "...." + btn_rc_X);
                        if (event.getY() < btn_rc_Y && event.getX() > btn_rc_X) {//判断手势按下的位置是否是语音录制按钮的范围内
                            System.out.println("3");
                            rcChat_popup.setVisibility(View.VISIBLE);
                            mHandler.postDelayed(new Runnable() {
                                public void run() {
                                }
                            }, 300);
                            startVoiceT = SystemClock.currentThreadTimeMillis();
//							voiceName = startVoiceT + ".amr";
                            start(voiceName);
                            //设置录音时间
                            timedown.setVisibility(View.VISIBLE);
                            initTimer(30);
                            timedown.start();
                            flag = 2;
                        }
                    }
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    //TODO
//                    bntRecord.setBackgroundColor(getResources().getColor(R.color.white));
                    bntRecord.setBackgroundColor(Color.WHITE);
                    bntRecord.setText("按住  说话");
                    bntRecord.setTextColor(Color.BLACK);
                    timedown.stop();
                    if (flag == 2) {
                        rcChat_popup.setVisibility(View.GONE);
                        timedown.setVisibility(View.GONE);
                        stop();
                        flag = 1;
                        soundUse(voiceName);

//						File file = new File(android.os.Environment.getExternalStorageDirectory()+"/hq_100/"+ voiceName);
//						if (file.exists()) {
//							file.delete();
//						}
                    } else {
                        voice_rcd_hint_rcding.setVisibility(View.GONE);
                        stop();
                        endVoiceT = SystemClock.currentThreadTimeMillis();
                        flag = 1;
                        int time = (int) ((endVoiceT - startVoiceT) / 1000);
                        System.out.println(time);
                    }
                }
                return false;
            }
        });
        soundUse(voiceName);
    }

    private void soundUse(String fileName) {
        //判断sd卡上是否有声音文件，有的话就显示名称并播放，录音文件默认存放位置/hq_100
        final String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/hq_100/" + voiceName;
        File file = new File(path);
        if (file.exists()) {
            sound_file.setVisibility(View.VISIBLE);
            String soundName = file.getName();
            txtName.setText(soundName);
            txtName.setTextColor(Color.WHITE);
            //点击声音文件播放声音
            sound_file.setOnTouchListener(new OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        //TODO
//                        sound_file.setBackgroundColor(getResources().getColor(R.color.blue));
                        sound_file.setBackgroundColor(Color.BLUE);
                        player = new MediaPlayer();
                        try {
                            player.setDataSource(path);
                            player.prepare();
                            player.start();
                        } catch (IllegalArgumentException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (SecurityException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (IllegalStateException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    } else if (event.getAction() == MotionEvent.ACTION_UP) {
                        //TODO 为什么使用资源文件的颜色属性就会宕机
                        sound_file.setBackgroundColor(Color.GREEN);
//                        sound_file.setBackgroundColor(getResources().getColor(R.color.white));
                    }
                    return true;
                }
            });

            //获取是按钮使用状态，初始化按钮文本
            sp = getSharedPreferences("customSound", Activity.MODE_PRIVATE);
            isUsed = sp.getBoolean("ISUSED", false);
            if (isUsed) {
                use_bnt.setBackgroundResource(R.drawable.on);
            } else {
                use_bnt.setBackgroundResource(R.drawable.off);
            }
            //点击使用按钮替换车找到的后声音提示
            use_bnt.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    // TODO Auto-generated method stub
                    if (isUsed == false) {
                        isUsed = true;
                        sp = getSharedPreferences("customSound", Activity.MODE_PRIVATE);
                        Editor editor = sp.edit();
                        editor.putBoolean("ISUSED", true);
                        editor.commit();
                        use_bnt.setBackgroundResource(R.drawable.on);
                    } else {
                        isUsed = false;
                        sp.edit().putBoolean("ISUSED", false).commit();
                        use_bnt.setBackgroundResource(R.drawable.off);
                    }
                }
            });
        }
    }


    private Runnable mSleepTask = new Runnable() {
        public void run() {
            stop();
        }
    };
    private Runnable mPollTask = new Runnable() {
        public void run() {
            double amp = mSensor.getAmplitude();
            updateDisplay(amp);
            mHandler.postDelayed(mPollTask, POLL_INTERVAL);
        }
    };

    private void start(String name) {
        mSensor.start(name);
        mHandler.postDelayed(mPollTask, POLL_INTERVAL);
    }

    private void stop() {
        mHandler.removeCallbacks(mSleepTask);
        mHandler.removeCallbacks(mPollTask);
        mSensor.stop();
        volume.setImageResource(R.drawable.amp1);
    }

    private void updateDisplay(double signalEMA) {

        switch ((int) signalEMA) {
            case 0:
            case 1:
                volume.setImageResource(R.drawable.amp1);
                break;
            case 2:
            case 3:
                volume.setImageResource(R.drawable.amp2);
                break;
            case 4:
            case 5:
                volume.setImageResource(R.drawable.amp3);
                break;
            case 6:
            case 7:
                volume.setImageResource(R.drawable.amp4);
                break;
            case 8:
            case 9:
                volume.setImageResource(R.drawable.amp5);
                break;
            case 10:
            case 11:
                volume.setImageResource(R.drawable.amp6);
                break;
            default:
                volume.setImageResource(R.drawable.amp7);
                break;
        }
    }

    /**
     * 初始化计时器，计时器是通过widget.Chronometer来实现的
     *
     * @param total 一共多少秒
     */
    private void initTimer(long total) {
        this.timeTotalInS = total;
        this.timeLeftInS = total;
        timedown.setOnChronometerTickListener(new OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                if (timeLeftInS <= 0) {
                    Toast.makeText(CustomSoundActivity.this, "录音时间到", Toast.LENGTH_SHORT).show();
                    timedown.stop();
                    //录音停止
                    stop();
                    rcChat_popup.setVisibility(View.GONE);
                    timedown.setVisibility(View.GONE);
                    return;
                }
                timeLeftInS--;
                refreshTimeLeft();
            }
        });
    }

    private void refreshTimeLeft() {
        this.timedown.setText("录音时间剩余：" + timeLeftInS);
        //TODO 格式化字符串
    }

    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        if (player != null) {
            player.stop();
            player.release();
        }
    }
}
