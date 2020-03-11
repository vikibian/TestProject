package com.neu.testimageload.photoandvideo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.neu.testimageload.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PhotoCameraActivity extends AppCompatActivity {

    private ImageView mImageView;
    private Button btnStartCameraThumb, btnStartCameraFull;
    private File photoFile;

    //requestCode
    private static final int REQUEST_IMAGE_CAPTURE_THUMB = 1;
    private static final int REQUEST_IMAGE_CAPTURE_FULL = 2;

    private static final String JPEG_FILE_PREFIX = "IMG_";
    private static final String JPEG_FILE_SUFFIX = ".jpg";

    private static final String CAMERA_DIR = "/dcim/";
    private static final String albumName = "CameraSample";

    //获得ImageView的width height
    private int targetW;
    private int targetH;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_camera);

//        initView();
//
//        try {
//            photoFile = createFile();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}

//    //获得文件路径,这里以public为例
//
//    private File getPhotoDir(){
//        File storDirPrivate = null;
//        File storDirPublic = null;
//
//        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
//
//            //private,只有本应用可访问
//            storDirPrivate = new File (
//                    Environment.getExternalStorageDirectory()
//                            + CAMERA_DIR
//                            + albumName
//            );
//
//            //public 所有应用均可访问
//            storDirPublic = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
//                    albumName);
//
//            if (storDirPublic != null) {
//                if (! storDirPublic.mkdirs()) {
//                    if (! storDirPublic.exists()){
//                        Log.d("CameraSample", "failed to create directory");
//                        return null;
//                    }
//                }
//            }
//        }else {
//            Log.v(getString(R.string.app_name), "External storage is not mounted READ/WRITE.");
//        }
//
//        return storDirPublic;//或者return storDirPrivate;
//
//    }
//
//    private File createFile() throws IOException {
//        photoFile = null;
//
//        String fileName;
//        //通过时间戳区别文件名
//        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//        fileName = JPEG_FILE_PREFIX+timeStamp+"_";
//
//        photoFile = File.createTempFile(fileName,JPEG_FILE_SUFFIX,getPhotoDir());
//
//        return photoFile;
//    }
//
//
//    //获得控件
//    private void initView() {
//        mImageView = (ImageView) findViewById(R.id.imageView);
//        btnStartCameraThumb = (Button) findViewById(R.id.startCameraThumb);
//        btnStartCameraFull = (Button) findViewById(R.id.startCameraFull);
//
//        targetW = mImageView.getWidth();
//        targetH = mImageView.getHeight();
//
//
//        btnStartCameraFull.setOnClickListener(this);
//        btnStartCameraThumb.setOnClickListener(this);
//    }
//
//
//    @Override
//    public void onClick(View v) {
//
//        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        switch (v.getId()){
//            case R.id.startCameraThumb:
//                startActivityForResult(takePictureIntent,REQUEST_IMAGE_CAPTURE_THUMB);
//                break;
//            case R.id.startCameraFull:
//                //传入File的uri
//                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
//                startActivityForResult(takePictureIntent,REQUEST_IMAGE_CAPTURE_FULL);
//                break;
//        }
//    }
//
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        mImageView.setImageBitmap(null);
//
//        switch (requestCode){
//            case REQUEST_IMAGE_CAPTURE_THUMB:
//                if(resultCode == RESULT_OK){
//                    Bundle extras = data.getExtras();
//                    Bitmap imageBitmap = (Bitmap) extras.get("data");
//
//                    mImageView.setImageBitmap(imageBitmap);
//                }
//
//                break;
//            case REQUEST_IMAGE_CAPTURE_FULL:
//                if(resultCode == RESULT_OK){
//                    setPic();
//                    galleryAddPic();
//                }
//
//                break;
//
//        }
//
//    }
