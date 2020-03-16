package com.neu.testimageload.photoandvideo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.neu.testimageload.BuildConfig;
import com.neu.testimageload.ImageloaderListviewActivity;
import com.neu.testimageload.R;
import com.neu.testimageload.SearchView.SearchViewActivity;
import com.neu.testimageload.SmartTable.SmartTableActivity;
import com.neu.testimageload.VideoRecordDemo.VideoRecordDemoActivity;
import com.neu.testimageload.androidtree.AndroidTreeActivity;
import com.neu.testimageload.glidenetimage.GlideNetImageActivity;
import com.neu.testimageload.jiaoziplayer.JiaoziPlayerActivity;
import com.neu.testimageload.listitem.ListItemActivity;
import com.neu.testimageload.multilevelTreeList.MultilevelTreeListActivity;
import com.neu.testimageload.nicevideo.NiceVideoActivity;
import com.neu.testimageload.pdfView.PDFViewerActivity;
import com.neu.testimageload.pdfprint.PrintPdfActivity;
import com.neu.testimageload.sms.SmsActivity;
import com.neu.testimageload.surfaceview.SurfaceViewActivity;
import com.neu.testimageload.toolbar.ToolbarActivity;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class PhotoActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int REQUEST_CODE_CHOOSE = 23;
    Button button;
    TextView textView;

    private Button button_surfview;
    private Button button_video;
    private Button button_searchview;
    private Button button_smarttable;
    private Button button_glideimage;
    private Button button_nicevideo;
    private Button button_toolbar;
    private Button button_sms;
    private Button button_jiaoziplayer;
    private Button button_pdfViewer;
    private Button button_listitem;
    private Button button_androidtree;
    private Button button_multilevelTreeList;
    private Button button_editpdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);

        setContentView(R.layout.activity_photo);
        //Toolbar toolbar;
//        setTitleCenter();
        //initToolbar(1,1,1);

        //getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title);


        checkPermissions();

        initView();



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Matisse.from(PhotoActivity.this)
                        .choose(MimeType.ofAll())//图片类型
                        .countable(true)//true:选中后显示数字;false:选中后显示对号
                        .maxSelectable(5)//可选的最大数
                        .capture(true)//选择照片时，是否显示拍照
                        //参数1 true表示拍照存储在共有目录，false表示存储在私有目录；
                        // 参数2与 AndroidManifest中authorities值相同，用于适配7.0系统 必须设置
                        //"com.neu.testimageload.fileprovider"
                        .captureStrategy(new CaptureStrategy(true, "com.neu.testimageload.fileprovider"))
                        .imageEngine(new GlideEngine())//图片加载引擎
                        .forResult(REQUEST_CODE_CHOOSE);//
            }
        });

        button_surfview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PhotoActivity.this, SurfaceViewActivity.class);
                startActivity(intent);
            }
        });

        button_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PhotoActivity.this, VideoRecordDemoActivity.class);
                startActivity(intent);
            }
        });

        button_searchview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PhotoActivity.this, SearchViewActivity.class);
                startActivity(intent);
            }
        });

        button_smarttable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PhotoActivity.this, SmartTableActivity.class);
                startActivity(intent);
            }
        });

        button_glideimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PhotoActivity.this, GlideNetImageActivity.class);
                startActivity(intent);
            }
        });

        button_nicevideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PhotoActivity.this, NiceVideoActivity.class);
                startActivity(intent);
            }
        });

        button_toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PhotoActivity.this, ToolbarActivity.class);
                startActivity(intent);
            }
        });

        button_sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PhotoActivity.this, SmsActivity.class);
                startActivity(intent);
            }
        });


        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(BuildConfig.APPLICATION_ID, PackageManager.GET_SIGNATURES);
            String signValidString = getSignValidString(packageInfo.signatures[0].toByteArray());
            Log.e("获取应用签名", BuildConfig.APPLICATION_ID + "__" + signValidString);
        } catch (Exception e) {
            Log.e("获取应用签名", "异常__" + e);
        }


    }

    private void initView() {
        button = findViewById(R.id.button);
        textView = findViewById(R.id.textview);
        button_surfview = findViewById(R.id.button_surfview);
        button_video = findViewById(R.id.button_video);
        button_searchview = findViewById(R.id.button_searchview);
        button_smarttable = findViewById(R.id.button_smarttable);
        button_glideimage = findViewById(R.id.button_glideImage);
        button_nicevideo = findViewById(R.id.button_nicevideo);
        button_toolbar = findViewById(R.id.button_toolbar);
        button_sms = findViewById(R.id.button_sms);
        button_jiaoziplayer = findViewById(R.id.button_jiaoziplayer);
        button_pdfViewer = findViewById(R.id.button_pdfViewer);
        button_listitem = findViewById(R.id.button_listitem);
        button_androidtree = findViewById(R.id.button_androidtree);
        button_multilevelTreeList = findViewById(R.id.button_multilevelTreeList);
        button_editpdf = findViewById(R.id.button_editpdf);


        button_jiaoziplayer.setOnClickListener(this);
        button_pdfViewer.setOnClickListener(this);
        button_listitem.setOnClickListener(this);
        button_androidtree.setOnClickListener(this);
        button_multilevelTreeList.setOnClickListener(this);
        button_editpdf.setOnClickListener(this);
    }


    private String getSignValidString( byte[] paramArrayOfByte) throws NoSuchAlgorithmException {
        MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
        localMessageDigest.update(paramArrayOfByte);
        return toHexString(localMessageDigest.digest());
    }
    public String toHexString(byte[] paramArrayOfByte) {
        if (paramArrayOfByte == null) {
            return null;
        }
        StringBuilder localStringBuilder = new StringBuilder(2 * paramArrayOfByte.length);
        for (int i = 0; ; i++) {
            if (i >= paramArrayOfByte.length) {
                return localStringBuilder.toString();
            }
            String str = Integer.toString(0xFF & paramArrayOfByte[i], 16);
            if (str.length() == 1) {
                str = "0" + str;
            }
            localStringBuilder.append(str);
        }
    }

    /**
     * 初始化toolbar
     */
//    private void initToolbar() {
//        Toolbar mToolbar = findViewById(R.id.toolbar);
//        if (null != mToolbar) {
//            // 清除标题
//            mToolbar.setTitle("");
//            setSupportActionBar(mToolbar);
//            mToolbar.setBackgroundColor(getToolbarBackground());
//            //设置返回按钮
//            mToolbar.setNavigationIcon(getNavigationIcon());
//            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    onNavigationOnClickListener();
//                }
//            });
//            isInitToolbar = true;
//        }
//    }

    private boolean checkPermissions() {
        //本app需要以下权限
        final  String[] ps = {Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.CAMERA,
        Manifest.permission.RECORD_AUDIO};

        //检查是否有这些权限
        for (int i =0;i<ps.length;i++){
            int src = ActivityCompat.checkSelfPermission(this,ps[i]);
            if (src != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,ps,0);
                return false;
            }
        }

        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            Log.d("Matisse", "Uris: " + Matisse.obtainResult(data));
            Log.d("Matisse", "Paths: " + Matisse.obtainPathResult(data));
            Log.e("Matisse", "Use the selected photos with original: "+String.valueOf(Matisse.obtainOriginalState(data)));

            List<Uri> result = Matisse.obtainResult(data);
            textView.setText(result.toString());
        }
    }

    public Toolbar initToolbar(int id, int titleId, int titleString) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        toolbar.setTitle("");
        TextView textView = (TextView) findViewById(R.id.toolbar_title);
        textView.setText("ceshi");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);//让原始的toolbar的title不显示，可以使用
        }
        return toolbar;
    }

    protected void setTitleCenter(Toolbar toolbar) {

        TextView textView = (TextView)toolbar.getChildAt(0);//主标题,title对应的textview
        textView.setTextSize(15);//根据标题长度设置合适的字体大小，不设置默认时候五个中文字符就会向右严重偏移
        textView.setText(" 1 ");
        textView.setGravity(Gravity.CENTER);//设置textview里面字体居中
        Toolbar.LayoutParams params = new Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.MATCH_PARENT);
        params.gravity = Gravity.CENTER;//设置textview居中
        textView.setLayoutParams(params);//相当于设置成layout_width="wrap_content"      layout_height="match_parent"

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.button_jiaoziplayer:
                startActivity(new Intent(PhotoActivity.this, JiaoziPlayerActivity.class));
                break;
            case R.id.button_pdfViewer:
                startActivity(new Intent(PhotoActivity.this, PDFViewerActivity.class));
                break;
            case R.id.button_listitem:
                startActivity(new Intent(PhotoActivity.this, ListItemActivity.class));
                break;
            case R.id.button_androidtree:
                startActivity(new Intent(PhotoActivity.this, AndroidTreeActivity.class));
                break;
            case R.id.button_multilevelTreeList:
                startActivity(new Intent(PhotoActivity.this, MultilevelTreeListActivity.class));
                break;
            case R.id.button_editpdf:
                startActivity(new Intent(PhotoActivity.this, PrintPdfActivity.class));
                break;
            default:
                break;
        }
    }
}
