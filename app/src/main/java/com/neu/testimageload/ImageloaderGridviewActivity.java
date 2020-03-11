package com.neu.testimageload;

import android.content.Context;
import android.os.Bundle;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import com.neu.testimageload.adapter.ImageloaderGridviewAdapter;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ImageloaderGridviewActivity extends AppCompatActivity {

    @BindView(R.id.gv_imageloader_gridview)
    GridView gvImageloaderGridview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imageloader_gridview);
        ButterKnife.bind(this);
        initImageloader(this);
        initData();
    }



    private void initData() {
        // 初始化Gridview
        ImageloaderGridviewAdapter imageloaderGridviewAdapter = new ImageloaderGridviewAdapter(this);

        gvImageloaderGridview.setAdapter(imageloaderGridviewAdapter);
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
