package com.neu.testimageload.imagedialog;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.neu.testimageload.R;

import java.io.InputStream;

public class ImageDialogActivity extends AppCompatActivity {

    private ImageView imageView;
    private Dialog dialog;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_dialog);

        init();

        //小图的点击事件（弹出大图）
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });


    }

    private void init() {
        imageView = (ImageView) findViewById(R.id.image);

        //展示在dialog上面的大图
        dialog = new Dialog(ImageDialogActivity.this,R.style.FullScreen);

        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.width = WindowManager.LayoutParams.MATCH_PARENT;
        attributes.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialog.getWindow().setAttributes(attributes);

        image = getImageView();
        dialog.setContentView(image);

        //大图的点击事件（点击让他消失）
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }

    //动态的ImageView
    private ImageView getImageView(){
        ImageView imageView = new ImageView(this);

        //宽高
        imageView.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        //imageView设置图片
//        @SuppressLint("ResourceType") InputStream is = getResources().openRawResource(R.drawable.ic_empty_zhihu);
//
//        Drawable drawable = BitmapDrawable.createFromStream(is, null);
//        imageView.setImageDrawable(drawable);
        Glide
                .with(ImageDialogActivity.this)
                .load("http://img.shu163.com/uploadfiles/wallpaper/2010/6/2010063006111517.jpg")
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .into(imageView);

        return imageView;
    }

}
