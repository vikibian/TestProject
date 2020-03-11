package com.neu.testimageload;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 *
 */
public class MainActivity extends AppCompatActivity {


    @BindView(R.id.bt_imageloader_listview)
    Button btImageloaderListview;
    @BindView(R.id.bt_imageloader_gridview)
    Button btImageloaderGridview;
    @BindView(R.id.bt_imageloader_viewpager)
    Button btImageloaderViewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        // 初始化Imageloader
        initImageloader(this);

        initData();
    }



    private void initData() {

    }

    @OnClick(R.id.bt_imageloader_listview)
    void bt_imageloader_listview_click(View view){
        // 跳转到listview案例页面
        Intent intent = new Intent(MainActivity.this, ImageloaderListviewActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.bt_imageloader_gridview)
    void bt_imageloader_gridview_click(View view){
        // 跳转到gridview案例页面
        Intent intent = new Intent(MainActivity.this, ImageloaderGridviewActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.bt_imageloader_viewpager)
    void bt_imageloader_viewpager_click(View view){
        // 跳转到viewpager案例页面
        Intent intent = new Intent(MainActivity.this, ImageloaderViewpagerActivity.class);
        startActivity(intent);
    }

    private void initImageloader(Context context) {

        // 初始化参数
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .threadPriority(Thread.NORM_PRIORITY - 2)               // 线程优先级
                .denyCacheImageMultipleSizesInMemory()                  // 当同一个Uri获取不同大小的图片，缓存到内存时，只缓存一个。默认会缓存多个不同的大小的相同图片
                .discCacheFileNameGenerator(new Md5FileNameGenerator()) // 将保存的时候的URI名称用MD5
                .tasksProcessingOrder(QueueProcessingType.LIFO)         // 设置图片下载和显示的工作队列排序
                .writeDebugLogs()                                       // 打印debug log
                .build();

        // 全局初始化此配置
        ImageLoader.getInstance().init(config);
    }
}
