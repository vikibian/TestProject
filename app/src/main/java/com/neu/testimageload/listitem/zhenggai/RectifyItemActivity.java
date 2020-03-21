package com.neu.testimageload.listitem.zhenggai;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.neu.testimageload.R;
import com.neu.testimageload.listitem.other.SidebarUtils;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

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
                if (pathlistOfPhoto.size()==position){
                    view.showContextMenu();
                }else {
                    view.showContextMenu();
                }


            }
        });

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


}
