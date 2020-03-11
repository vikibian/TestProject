package com.neu.testimageload.jiaoziplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.neu.testimageload.R;

import cn.jzvd.Jzvd;

public class JiaoziPlayerActivity extends AppCompatActivity {

    Jzvd jzvd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jiaozi_player);
        jzvd = findViewById(R.id.jz_video);
        jzvd.setUp("http://jzvd.nathen.cn/6ea7357bc3fa4658b29b7933ba575008/fbbba953374248eb913cb1408dc61d85-5287d2089db37e62345123a1be272f8b.mp4",null);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Jzvd.releaseAllVideos();
    }

    @Override
    public void onBackPressed() {
        if (Jzvd.backPress()) {
            return;
        }
        super.onBackPressed();
    }
}
