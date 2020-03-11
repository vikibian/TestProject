package com.neu.testimageload.fresco.activity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.drawable.ProgressBarDrawable;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.neu.testimageload.MainActivity;
import com.neu.testimageload.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FrescoSpimgActivity extends AppCompatActivity {

    @BindView(R.id.sdv_fresco_spimg)
    SimpleDraweeView sdvFrescoSpimg;
    @BindView(R.id.activity_fresco_spimg)
    LinearLayout activityFrescoSpimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化Fresco
        Fresco.initialize(FrescoSpimgActivity.this);

        setContentView(R.layout.activity_fresco_spimg);

        ButterKnife.bind(this);

        initData();
    }

    private void initData() {
        // 设置样式
        GenericDraweeHierarchyBuilder builder = new GenericDraweeHierarchyBuilder(getResources());

        GenericDraweeHierarchy hierarchy = builder.setProgressBarImage(new ProgressBarDrawable()).build();

        sdvFrescoSpimg.setHierarchy(hierarchy);

        // 加载图片的地址
        Uri uri = Uri.parse("http://www.win4000.com/meinv25958_3.html");

        // 加载图片
        sdvFrescoSpimg.setImageURI(uri);
    }
}
