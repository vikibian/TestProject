package com.neu.testimageload.nicevideo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.media.MediaMetadataRetriever;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import com.neu.testimageload.R;
import com.xiao.nicevideoplayer.NiceVideoPlayerManager;

import java.util.HashMap;

public class NiceVideoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nice_video);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
//        NiceVideoFragment niceVideoFragment = new NiceVideoFragment();
//        Log.e(getClass().getSimpleName(),"<-------------------Add BlankFragment1------------------------>");
//        ft.add(R.id.test_nicevideo,niceVideoFragment);
        NiceVideoRecyclerFragment niceVideoRecyclerFragment = new NiceVideoRecyclerFragment();
        ft.add(R.id.test_nicevideo,niceVideoRecyclerFragment);
        ft.commit();
        String address = Environment.getExternalStorageDirectory().getAbsolutePath();///storage/emulated/0
        Log.e("地址："," "+address);

//        try {
//            HashMap<String, String> headers = null;
//            if (headers == null)
//            {
//                headers = new HashMap<String, String>();
//                headers.put("User-Agent", "Mozilla/5.0 (Linux; U; Android 4.4.2; zh-CN; MW-KW-001 Build/JRO03C) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 UCBrowser/1.0.0.001 U4/0.8.0 Mobile Safari/533.1");
//            }
//            MediaMetadataRetriever mmr = new MediaMetadataRetriever();
//            mmr.setDataSource("http://39.97.108.172:8080/pic/1234567880VIDEO0.mp4", headers);
//            String d = mmr.extractMetadata(android.media.MediaMetadataRetriever.METADATA_KEY_DURATION);
//            Log.e("shijian"," "+d);
//        }catch (Exception e){
//            e.printStackTrace();
//        }

    }

    @Override
    public void onBackPressed() {
        if (NiceVideoPlayerManager.instance().onBackPressd()) return;
        super.onBackPressed();
    }
}
