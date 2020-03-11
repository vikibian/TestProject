package com.neu.testimageload.nicevideo;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.neu.testimageload.R;
import com.xiao.nicevideoplayer.NiceVideoPlayer;
import com.xiao.nicevideoplayer.NiceVideoPlayerManager;
import com.xiao.nicevideoplayer.TxVideoPlayerController;

import java.lang.reflect.Field;


public class NiceVideoFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private NiceVideoPlayer niceVideoPlayer;



    public NiceVideoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nice_video, container, false);
        // Inflate the layout for this fragment
        init(view);

        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
        NiceVideoPlayerManager.instance().releaseNiceVideoPlayer();
    }

    private void init(View view) {
        niceVideoPlayer = (NiceVideoPlayer) view.findViewById(R.id.nice_player);
        niceVideoPlayer.setPlayerType(NiceVideoPlayer.TYPE_NATIVE); // or NiceVideoPlayer.TYPE_NATIVE
        niceVideoPlayer.setUp("http://39.97.108.172:8080/pic/1234567880VIDEO0.mp4", null);

        TxVideoPlayerController controller = new TxVideoPlayerController(getContext());
        controller.setTitle("title");
        controller.setImage(R.mipmap.test);
        try {
            Field sharedFiled = controller.getClass().getDeclaredField("mShare");
            sharedFiled.setAccessible(true);
            TextView sharedTv = (TextView) sharedFiled.get(controller);
            sharedTv.setVisibility(View.INVISIBLE);

//            Field reback = controller.getClass().getDeclaredField("mReplay");
//            reback.setAccessible(true);
//            TextView rebplay = (TextView) reback.get(controller);
//            LinearLayout.LayoutParams rp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//            rp.;
//            rebplay.setLayoutParams(rp);

            Field imageField = controller.getClass().getDeclaredField("mImage");
            imageField.setAccessible(true);
            ImageView image = (ImageView) sharedFiled.get(controller);
            image.setImageResource(R.mipmap.test);

        } catch (Exception e) {
            e.printStackTrace();
        }





        niceVideoPlayer.setController(controller);
    }
}
