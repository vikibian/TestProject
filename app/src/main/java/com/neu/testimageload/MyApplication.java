package com.neu.testimageload;

import android.app.Application;

import androidx.multidex.MultiDex;

/**
 * created by Viki on 2020/3/14
 * system login name : lg
 * created time : 10:08
 * email : 710256138@qq.com
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // 添加下面这个
        MultiDex.install(this);
    }
}
