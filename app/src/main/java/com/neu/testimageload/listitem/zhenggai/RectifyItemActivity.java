package com.neu.testimageload.listitem.zhenggai;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.neu.testimageload.R;
import com.neu.testimageload.imagedialog.ImageDialogActivity;
import com.neu.testimageload.listitem.other.SidebarUtils;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class RectifyItemActivity extends AppCompatActivity  {
    private static final String TAG = "RectifyItemActivity";
    private static final int REQUEST_CODE_CHOOSE = 23;
    private EditText editText;
    private GridView gridView;
    private SuggestionGridViewAdapter suggestionGridViewAdapter;
    public List<String> pathlistOfPhoto = new ArrayList<>();
    private int deleteIndex;

    private TextView textView_selectTime;
    private TextView textView_rectifyTime;
    private Button button;
    final int REQUEST_TEST = 66;

    private String testvideoPath = " ";
    private String ImagePath = "";
    private String VideoPath = "";

    private int hourOfDay, minute;

    private Dialog dialog = null;
    private ImageView image;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rectify_item);

        deleteIndex = -1;

        editText = findViewById(R.id.rectify_item_rectifyaction);
        editText.setBackground(getResources().getDrawable(R.drawable.text_border_gray));

        gridView = findViewById(R.id.suggestion_gridview);
        suggestionGridViewAdapter = new SuggestionGridViewAdapter(getApplicationContext(), pathlistOfPhoto,0);
        gridView.setAdapter(suggestionGridViewAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                deleteIndex = position;
//                if (pathlistOfPhoto.size()==position){
//                    view.showContextMenu();
//                }else {
//                    view.showContextMenu();
//                }
                //展示选中图片
//                showImage(position);
                Toast.makeText(RectifyItemActivity.this, ""+position, Toast.LENGTH_SHORT).show();
                dialog.show();

            }
        });

        startLoadImage();

        gridView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                menu.add(0,1,0,"相册");
                menu.add(0,2,0,"相机");
                menu.add(0,3,0,"取消");
                menu.add(0,4,0,"删除");
            }
        });

        textView_selectTime = findViewById(R.id.rectify_item_rectifiedtime);
        textView_selectTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext()," 点击了",Toast.LENGTH_SHORT).show();
                SidebarUtils.initSelectStartTime(RectifyItemActivity.this,textView_selectTime);
            }
        });

        textView_rectifyTime = findViewById(R.id.rectify_item_rectifytime);
        textView_rectifyTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SidebarUtils.initSelectStartTime(RectifyItemActivity.this,textView_rectifyTime);
            }
        });

        button = findViewById(R.id.rectify_item_save);

    }

    private void startLoadImage() {
        //展示在dialog上面的大图
        dialog = new Dialog(RectifyItemActivity.this,R.style.FullScreen);

        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.width = WindowManager.LayoutParams.MATCH_PARENT;
        attributes.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialog.getWindow().setAttributes(attributes);

        image = getImageView();
        dialog.setContentView(image);

        //大图的点击事件（点击让他消失）
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Log.e(TAG, "onClick: ");
            }
        });
    }

    //动态的ImageView
    private ImageView getImageView(){
        ImageView imageView = new ImageView(this);

        //宽高
        imageView.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        //imageView设置图片
//        @SuppressLint("ResourceType") InputStream is = getResources().openRawResource(R.drawable.ic_empty_zhihu);
//
//        Drawable drawable = BitmapDrawable.createFromStream(is, null);
//        imageView.setImageDrawable(drawable);
        Glide
                .with(RectifyItemActivity.this)
                .load("http://img.shu163.com/uploadfiles/wallpaper/2010/6/2010063006111517.jpg")
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .into(imageView);

        return imageView;
    }

//    private void initImageView(int position) {
//        final WindowManager windowManager = getWindowManager();
//        final RelativeLayout relativeLayout = new RelativeLayout(this);
//        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
//
//        layoutParams.width =WindowManager.LayoutParams.MATCH_PARENT;
//        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;
//        //FLAG_LAYOUT_IN_SCREEN
//        layoutParams.flags = WindowManager.LayoutParams.FLAG_FULLSCREEN;
//        layoutParams.format = PixelFormat.RGBA_8888;//让背景透明，放大过程可以看到当前界面
//        layoutParams.verticalMargin = 0;
//        windowManager.addView(relativeLayout,layoutParams);
//
//
//
//        final PhotoView photoview = new PhotoView(this);
//        photoview.enable();
//        photoview.setScaleType(ImageView.ScaleType.FIT_CENTER);
//        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
//        relativeLayout.addView(photoview,params);
//        relativeLayout.setFocusableInTouchMode(true);
////        Picasso.with(getContext()).load(imageId).into(photoview);
//        Glide
//                .with(relativeLayout.getContext())
//                .load(imageList.get(position))
//                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
//                .into(photoview);
//
//        photoview.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                windowManager.removeView(relativeLayout);
//            }
//        });
//
//        relativeLayout.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                if (keyCode == KeyEvent.KEYCODE_BACK) {
//                    if (null != windowManager && null != relativeLayout) {
//                        windowManager.removeView(relativeLayout);
//                    }
//                    return true;
//                }
//                return false;
//            }
//        });
//    }

    /**
     * 虽然menu选项在SuggestionGridViewAdapter.java文件中但是一样可以使用这里的选择事件按钮
     * @param item
     * @return
     */
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case 1:
                Log.e(TAG," item  "+item.getTitle());
                startPhotoGallery();
                break;
            case 2:
                Intent intentall = new Intent(RectifyItemActivity.this,PhotoVideoActivity.class);
                startActivityForResult(intentall,REQUEST_TEST);
                break;
            case 3:
                break;
            case 4:
                if (deleteIndex>=0&&deleteIndex<pathlistOfPhoto.size()){
                    pathlistOfPhoto.remove(deleteIndex);
                    suggestionGridViewAdapter = new SuggestionGridViewAdapter(getApplicationContext(), pathlistOfPhoto,1);
                    gridView.setAdapter(suggestionGridViewAdapter);
                }else {
                    Toast.makeText(getApplicationContext(),"此图片不能删除",Toast.LENGTH_SHORT).show();
                }
                //pathlistOfPhoto
                break;


        }
        return super.onContextItemSelected(item);
    }

    /**
     * @date : 2020/2/23
     * @time : 20:55
     * @author : viki
     * @description :获取相册
     */

    private void startPhotoGallery() {

        Matisse.from(RectifyItemActivity.this)
                .choose(MimeType.ofAll())//图片类型
                .countable(true)//true:选中后显示数字;false:选中后显示对号
                .maxSelectable(5)//可选的最大数
                //.capture(true)//选择照片时，是否显示拍照
                //参数1 true表示拍照存储在共有目录，false表示存储在私有目录；
                // 参数2与 AndroidManifest中authorities值相同，用于适配7.0系统 必须设置
                //"com.neu.testimageload.fileprovider"
                .captureStrategy(new CaptureStrategy(true, "com.neu.test.fileprovider"))
                .imageEngine(new GlideEngine())//图片加载引擎
                .forResult(REQUEST_CODE_CHOOSE);//
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //测试多张图片显示
        String imgString = new String();
        String videoString = new String();


        if (resultCode == RESULT_OK) {

            if (requestCode == REQUEST_TEST) {

                imgString = data.getStringExtra("ImagePath");
                testvideoPath = data.getStringExtra("VideoPath");
                videoString = data.getStringExtra("VideoPath");
                //不需要旋转90度  需要在设置图片的时候进行判断

                //在此处需要更新图片数组
                if (!(imgString.equals(""))) {
                    ImagePath += imgString + ",";
                    imgString = Environment.getExternalStorageDirectory() + "/DCIM/" + "viki" + "/Photo/" + imgString;
                    pathlistOfPhoto.add(imgString);
                }
                if (!(testvideoPath.equals(""))) {
                    VideoPath += videoString + ",";
                    videoString = Environment.getExternalStorageDirectory() + "/DCIM/" + "viki" + "/Video/" + videoString;
                    pathlistOfPhoto.add(videoString);
                }
                suggestionGridViewAdapter = new SuggestionGridViewAdapter(getApplicationContext(), pathlistOfPhoto, 1);
                gridView.setAdapter(suggestionGridViewAdapter);

            }

            //Masstise返回的图片数据
            if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
                Log.d("Matisse", "Uris: " + Matisse.obtainResult(data));
                Log.d("Matisse", "Paths: " + Matisse.obtainPathResult(data));
                Log.e("Matisse", "Use the selected photos with original: "+String.valueOf(Matisse.obtainOriginalState(data)));
                for (int i = 0;i<Matisse.obtainPathResult(data).size();i++){
                    pathlistOfPhoto.add(Matisse.obtainPathResult(data).get(i));
                }

                suggestionGridViewAdapter = new SuggestionGridViewAdapter(getApplicationContext(), pathlistOfPhoto,0);
                gridView.setAdapter(suggestionGridViewAdapter);
                //            List<Uri> result = Matisse.obtainResult(data);
                //            textView.setText(result.toString());
            }
        }
    }


    public void showImage(int position){

        dialog = new Dialog(RectifyItemActivity.this,R.style.FullScreen);
        View vview = View.inflate(getApplicationContext(),R.layout.fujian_layout_redetection,null);
        ImageView imageView = vview.findViewById(R.id.tv_tupian);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        Log.e(TAG, "showImage: 测试" );

        Bitmap bitmap = getLocalBitmap("1");
        imageView.setImageBitmap(bitmap);
//        imageView.setImageResource(R.drawable.atguigu_logo);
        WindowManager.LayoutParams artt = getWindow().getAttributes();
        artt.width = WindowManager.LayoutParams.MATCH_PARENT;
        artt.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialog.getWindow().setAttributes(artt);
        dialog.setContentView(vview);
        dialog.show();
        Log.e(TAG, "showImage: show后面");

    }

    //本地获取图片
    public Bitmap getLocalBitmap(String url){
        try {
            FileInputStream fileInputStream = new FileInputStream("/storage/emulated/0/DCIM/Camera/1582464108204IMG.jpg");
            return BitmapFactory.decodeStream(fileInputStream);

        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }
    }

}
