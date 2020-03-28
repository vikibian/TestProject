package com.neu.testimageload.drawableview.test1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;

import com.neu.testimageload.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Drawable1Activity extends AppCompatActivity {
    private static final String TAG = "Drawable1Activity";
    LinePathView linePathView;
    @BindView(R.id.button_drawableview1_save)
    Button button_save;
    @BindView(R.id.button_drawableview1_clear)
    Button button_clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawable1);
        ButterKnife.bind(this);

        linePathView = findViewById(R.id.linepathview);

    }

    @OnClick(R.id.button_drawableview1_save)
    void button_save(View view){
        Bitmap bitmap = linePathView.saveToBitmap(false,0);
        try {

//            linePathView.save(Environment.getExternalStorageDirectory() +"/DCIM/drawable1/Photo/image_"+ getDate().toString()+".jpg");
            linePathView.save(Environment.getExternalStorageDirectory() +"/DCIM/drawable1/Photo/image_"+ getDate().toString()+".jpg",true,5);

        } catch (IOException e) {
            e.printStackTrace();
        }

//        File filePic = new File(Environment.getExternalStorageDirectory() +"/DCIM/drawable1/Photo/image_"+ getDate().toString()+".jpg");
//        try {
//
//            if (!filePic.exists()) {
//                filePic.getParentFile().mkdirs();
//                filePic.createNewFile();
//            }
//            FileOutputStream fos = new FileOutputStream(filePic);
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 70, fos);
//            fos.flush();
//            fos.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

    @OnClick(R.id.button_drawableview1_clear)
    void button_clear(View view){
        linePathView.clear();
    }

    String getDate(){
        Calendar ca = Calendar.getInstance();
        int year = ca.get(Calendar.YEAR);          // 获取年份
        int month = ca.get(Calendar.MONTH);       // 获取月份
        int day = ca.get(Calendar.DATE);            // 获取日
        int minute = ca.get(Calendar.MINUTE) ;      // 分
        int hour = ca.get(Calendar.HOUR);           // 小时
        int second = ca.get(Calendar.SECOND);    // 秒
        return "" + year + (month + 1) + day + hour + minute + second;
    }
}
