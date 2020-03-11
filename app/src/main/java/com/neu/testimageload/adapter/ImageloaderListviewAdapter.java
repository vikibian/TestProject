package com.neu.testimageload.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.neu.testimageload.Constants;
import com.neu.testimageload.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ImageloaderListviewAdapter extends BaseAdapter {
    private Context mContext;
    private final ImageLoader imageLoader;
    private List<String> photoPaths = new ArrayList<>();

    private DisplayImageOptions options = new DisplayImageOptions.Builder()
            .showStubImage(R.drawable.ic_launcher_background)          // 设置图片下载期间显示的图片
            .showImageForEmptyUri(R.drawable.ic_launcher_background)  // 设置图片Uri为空或是错误的时候显示的图片
            .showImageOnFail(R.drawable.ic_launcher_background)       // 设置图片加载或解码过程中发生错误显示的图片
            .cacheInMemory(true)                        // 设置下载的图片是否缓存在内存中
            .cacheOnDisk(true)                          // 设置下载的图片是否缓存在SD卡中
            .displayer(new RoundedBitmapDisplayer(20))  // 设置成圆角图片
            .build();                                   // 创建配置过得DisplayImageOption对象;

    public ImageloaderListviewAdapter(Context context) {
        mContext = context;

        // 初始化imageloader
        imageLoader = ImageLoader.getInstance();

        photoPaths.add("http://39.97.108.172:8080/pic/123456789103Image1.jpg");
        photoPaths.add("http://39.97.108.172:8080/pic/123456789103Image0.jpg");
        photoPaths.add("http://39.97.108.172:8080/pic/ 123456789102Image0.jpg");
    }

    @Override
    public int getCount() {
        //return Constants.IMAGES.length;
        return photoPaths.size();
    }

    @Override
    public Object getItem(int position) {
        //return  Constants.IMAGES[position];
        return  photoPaths.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 获取或创建viewhoder
        Viewholder holder;

        if(convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_imageloader_listview, null);

            holder = new Viewholder(convertView);

            convertView.setTag(holder);
        }else {
            holder = (Viewholder) convertView.getTag();
        }

        // 获取当前item数据

        // 显示数据
        //holder.name.setText("item"+(position + 1));

        //imageLoader.displayImage(Constants.IMAGES[position],holder.iv,options);//第二个参数是要显示到那个控件
        imageLoader.displayImage(photoPaths.get(position),holder.iv,options);//第二个参数是要显示到那个控件

        return convertView;
    }

    class Viewholder{
        @BindView(R.id.iv_imageloader_listview)
        ImageView iv;

       // @BindView(R.id.tv_imageloader_name)
       // TextView name;

        public Viewholder(View view) {
            ButterKnife.bind(this,view);
        }
    }
}
