package com.neu.testimageload.nicevideo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.neu.testimageload.R;
import com.xiao.nicevideoplayer.NiceVideoPlayer;
import com.xiao.nicevideoplayer.TxVideoPlayerController;

import java.lang.reflect.Field;
import java.util.List;

/**
 * created by Viki on 2020/2/28
 * system login name : lg
 * created time : 14:54
 * email : 710256138@qq.com
 */
public class NiceVideoAdapter extends RecyclerView.Adapter<NiceVideoAdapter.NiceVideoViewHolder> {
    private Context mContext;
    private List<String> mVideoList;
    private int pos;

    public NiceVideoAdapter(Context context, List<String> videoList) {
        mContext = context;
        mVideoList = videoList;
    }

    @NonNull
    @Override
    public NiceVideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_video, parent, false);
        NiceVideoViewHolder holder = new NiceVideoViewHolder(itemView);
        CustomController controller = new CustomController(mContext);
        holder.setController(controller);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull NiceVideoViewHolder holder, int position) {
        String video = mVideoList.get(position);
        holder.bindData(video);
        pos = position;

    }

    @Override
    public int getItemCount() {
        return mVideoList.size();
    }


    class NiceVideoViewHolder extends RecyclerView.ViewHolder{

        public CustomController mController;
        public NiceVideoPlayer mVideoPlayer;

        public NiceVideoViewHolder(@NonNull View itemView) {
            super(itemView);

            mVideoPlayer = (NiceVideoPlayer) itemView.findViewById(R.id.nice_video_player);
            mVideoPlayer.setPlayerType(NiceVideoPlayer.TYPE_NATIVE);
            // 将列表中的每个视频设置为默认16:9的比例
            ViewGroup.LayoutParams params = mVideoPlayer.getLayoutParams();
            params.width = itemView.getResources().getDisplayMetrics().widthPixels; // 宽度为屏幕宽度
            params.height = (int) (params.width * 9f / 16f);    // 高度为宽度的9/16
            mVideoPlayer.setLayoutParams(params);
        }

        public void setController(CustomController controller) {
            mController = controller;
            try {

                Field imageField = mController.getClass().getDeclaredField("mImage");
                imageField.setAccessible(true);
                ImageView image = (ImageView) imageField.get(mController);
                image.setImageResource(R.mipmap.test);

                Field sharedFiled = mController.getClass().getDeclaredField("mShare");
                sharedFiled.setAccessible(true);
                TextView sharedTv = (TextView) sharedFiled.get(mController);
                sharedTv.setVisibility(View.INVISIBLE);

//                Field replayField = controller.getClass().getDeclaredField("mReplay");
//                replayField.setAccessible(true);
//                TextView replay = (TextView) replayField.get(controller);
//                LinearLayout.LayoutParams rp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//                rp.;
//                replay.setLayoutParams();

                Field timeField = mController.getClass().getDeclaredField("mLength");
                timeField.setAccessible(true);
                TextView time = (TextView) timeField.get(mController);
                time.setVisibility(View.INVISIBLE);


            } catch (Exception e) {
                e.printStackTrace();
            }
            mVideoPlayer.setController(mController);
        }

        public void bindData(String video) {
            Video v = new Video("1",9800,"1","1");

            mController.setTitle(" ");
            mController.setLenght(v.getLength());

//            mController.setLenght(video.getLength());
//            Glide.with(itemView.getContext())
//                    .load(R.mipmap.test)
//                    //.placeholder(R.drawable.img_default)
//                    .into(mController.imageView());
            mVideoPlayer.setUp(video, null);
        }
    }
}
