package com.channelsoft.alps.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.channelsoft.alps.R;
import com.channelsoft.alps.activity.adapter.SettingsAdapter;
import com.channelsoft.alps.activity.base.BaseActivity;
import com.channelsoft.alps.activity.module.Topbar;
import com.channelsoft.alps.activity.to.Settings;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * 设置页面效果
 */
public class Activity4 extends BaseActivity {

    public static final int TAKE_PHOTO = 1;
    public static final int CROP_PHOTO = 2;

    private Button takePhoto = null;
    private ImageView picture = null;
    private Uri imageUri;

    private Topbar topbar;

    private List<Settings> settingsList = new ArrayList<>();
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab4_layout);
        initSettings();
        SettingsAdapter sa = new SettingsAdapter(Activity4.this, R.layout.settings_item, settingsList);

        topbar = (Topbar) findViewById(R.id.topbar);
        topbar.setLeftIsVisable(false);//设置左按钮不可见
        topbar.setRightIsVisable(false);//设置右按钮不可见

        lv = (ListView) findViewById(R.id.settings_list_view);
        lv.setAdapter(sa);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Settings settings = settingsList.get(position);
                //获取手机信息
                TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
                String phoneId = tm.getLine1Number();//获取手机号码

                Toast.makeText(Activity4.this, settings.getSettingsName(), Toast.LENGTH_SHORT).show();
                Toast.makeText(Activity4.this, "手机号码:" + phoneId, Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent();
//                intent.setClass(Activity1.this, CustomSoundActivity.class);
//                startActivity(intent);
                new AlertDialog.Builder(Activity4.this).
                        setTitle("请输入您的" + settings.getSettingsName() + ":").
                        setView(new EditText(Activity4.this)).
                        setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if("姓名".equals(settings.getSettingsName())){
                                    saveContext("name",settings.getSettingsName());
                                }
                            }
                        }).
                        setNegativeButton("取消", null).show();
            }
        });

//        takePhoto = (Button) findViewById(R.id.take_photo);
        picture = (ImageView) findViewById(R.id.settings_img);
//        takePhoto.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                File outputImage = new File(Environment.getExternalStorageDirectory(), "output_image.jpg");
//
//                //判断文件是否存在,如果存在则删除文件
//                if (outputImage.exists()) {
//                    outputImage.delete();
//                }
//
//                imageUri = Uri.fromFile(outputImage);
//                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");//
//                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
//                startActivityForResult(intent, TAKE_PHOTO);//启动相机程序
//
//            }
//        });


    }

    /**
     * 信息设置
     * @param name    关键值
     * @param content 内容
     */
    private void saveContext(String name,Object content){
        SharedPreferences.Editor editor = getSharedPreferences("data",MODE_PRIVATE).edit();
        if(content instanceof String){
            editor.putString(name,(String)content);
        }else if(content instanceof Integer){
            editor.putInt(name, (Integer)content);
        }else{
            //TODO 增加其他类型信息
        }
        editor.commit();
    }

    private void initSettings() {

        Settings settings1 = new Settings("头像", R.drawable.u10_normal);
        settingsList.add(settings1);
        Settings settings2 = new Settings("姓名", R.drawable.u10_normal);
        settingsList.add(settings2);
        Settings settings3 = new Settings("性别", R.drawable.u10_normal);
        settingsList.add(settings3);
        Settings settings4 = new Settings("家庭位置", R.drawable.u24_normal);
        settingsList.add(settings4);
        Settings settings5 = new Settings("单位位置", R.drawable.u24_normal);
        settingsList.add(settings5);
        Settings settings6 = new Settings("手机号码", R.drawable.u10_normal);
        settingsList.add(settings6);
        Settings settings7 = new Settings("密码", R.drawable.u10_normal);
        settingsList.add(settings7);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    Intent intent = new Intent("com.android.camera.action.CROP");
                    intent.setDataAndType(imageUri, "image/*");
                    intent.putExtra("scale", true);

                    intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                    startActivityForResult(intent, CROP_PHOTO);//启动剪裁程序
                }
                break;
            case CROP_PHOTO:
                if (resultCode == RESULT_OK) {
                    Bitmap bitmap = null;
                    try {
                        bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        bitmap = getRoundedCornerBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    picture.setImageBitmap(bitmap);//将剪裁后的照片显示出来
                }
                break;
            default:
                break;
        }
    }

    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap) {

        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final Paint paint = new Paint();
        //保证是方形，并且从中心画
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int w;
        int deltaX = 0;
        int deltaY = 0;
        if (width <= height) {
            w = width;
            deltaY = height - w;
        } else {
            w = height;
            deltaX = width - w;
        }
        final Rect rect = new Rect(deltaX, deltaY, w, w);
        final RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        //圆形，所有只用一个

        int radius = (int) (Math.sqrt(w * w * 2.0d) / 2);
        canvas.drawRoundRect(rectF, radius, radius, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }
}
