package com.neu.testimageload.drawableview.github;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.neu.testimageload.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.panavtec.drawableview.DrawableView;
import me.panavtec.drawableview.DrawableViewConfig;

public class DrawableActivity extends AppCompatActivity {
    private static final String TAG = "DrawableActivity";
    @BindView(R.id.drawable_save)
    Button button_save;
    @BindView(R.id.paintView)
    DrawableView drawableView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawable);
        ButterKnife.bind(this);

        DrawableViewConfig config = new DrawableViewConfig();
        config.setStrokeColor(getResources().getColor(android.R.color.black));
        config.setShowCanvasBounds(true); // If the view is bigger than canvas, with this the user will see the bounds (Recommended)
        config.setStrokeWidth(10.0f);
        config.setMinZoom(1.0f);
        config.setMaxZoom(3.0f);
        config.setCanvasHeight(500);
        config.setCanvasWidth(720);
        drawableView.setConfig(config);
    }

    @OnClick(R.id.drawable_save)
    void button_save(){
        Toast.makeText(this, "save", Toast.LENGTH_SHORT).show();
        Bitmap bitmap = drawableView.obtainBitmap();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
        byte[] buffer = bos.toByteArray();
        String path = Environment.getExternalStorageDirectory()+"/DCIM/wenkui/"+getDate()+".jpg";
        if (buffer != null) {
            Log.e(TAG, "button_save: buffer not null" );
            File file = new File(path);
            FileOutputStream outputStream = null;
            try {
                if (!file.exists()) {
                    file.getParentFile().mkdirs();
                    file.createNewFile();
                }
                outputStream = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 70, outputStream);
//                outputStream.write(buffer);
                outputStream.flush();
                outputStream.close();
            } catch (IOException e) {
                Log.e(TAG, "button_save: "+e.toString() );
            }

        }

    }

    String getDate(){
        Calendar ca = Calendar.getInstance();
        int year = ca.get(Calendar.YEAR);          // 获取年份
        int month = ca.get(Calendar.MONTH);       // 获取月份
        int day = ca.get(Calendar.DATE);            // 获取日
        int minute = ca.get(Calendar.MINUTE) ;      // 分
        int hour = ca.get(Calendar.HOUR_OF_DAY);           // 小时
        int second = ca.get(Calendar.SECOND);    // 秒
        Log.e(TAG," "+"" + year +"-"+ (month + 1) +"-"+ day+"-" + hour+"-" + minute+"-" + second);
        return "" + year + (month + 1) + day + hour + minute + second;
    }
}
