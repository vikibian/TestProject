package com.neu.testimageload;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.neu.testimageload.adapter.ImageloaderViewpagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageloaderViewpagerActivity extends AppCompatActivity {

    @BindView(R.id.vp_imageloader_viewpager)
    ViewPager vpImageloaderViewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imageloader_viewpager);
        ButterKnife.bind(this);

        initData();
    }


    private void initData() {
        // 初始化viewpager
        ImageloaderViewpagerAdapter imageloaderViewpagerAdapter = new ImageloaderViewpagerAdapter(this);

        vpImageloaderViewpager.setAdapter(imageloaderViewpagerAdapter);

        // 显示第一个条目
        vpImageloaderViewpager.setCurrentItem(1);
    }
}
