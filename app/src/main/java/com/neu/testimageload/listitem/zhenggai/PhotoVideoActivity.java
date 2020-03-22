package com.neu.testimageload.listitem.zhenggai;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.cjt2325.cameralibrary.JCameraView;
import com.cjt2325.cameralibrary.lisenter.JCameraLisenter;
import com.neu.testimageload.R;
import com.neu.testimageload.stamper.PermissionUtils;
import com.neu.testimageload.stamper.StamperActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PhotoVideoActivity extends AppCompatActivity implements PermissionUtils.PermissionCallbacks{
    private static String TAG = "PhotoVideoActivity";
    private final int GET_PERMISSION_REQUEST = 100; //权限申请自定义码
    private LocationService locationService;
    private JCameraView jCameraView;
    private boolean granted = false;
    private boolean GPSgranted = false;
    byte[] result;  //将bitmap转化的byte数组
    //Bitmap bitmap;
    Intent intent;
    private  String ImagePath = "";
    private  String VideoPath = "";

    private String loal = "";
    private String time = "";

    private LocationManager mLocationManager;
    private String location;

    private String[] permissionsOfGPS = {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };

//    private String[] permissionsOfCAMERA = {
//            Manifest.permission.ACCESS_FINE_LOCATION,
//            Manifest.permission.ACCESS_COARSE_LOCATION
//    };

    private static final int REQUEST_PERMISSION_CODE = 12;
//    private static final int REQUEST_PERMISSION_CODE_CAMERA = 13;
    private String username = "viki";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_video);
        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        //6.0动态权限获取
        getPermissions();
        intent = getIntent();


        if (!PermissionUtils.hasPermissions(getApplicationContext(), permissionsOfGPS)) {
            PermissionUtils.requestPermissions(PhotoVideoActivity.this, REQUEST_PERMISSION_CODE, permissionsOfGPS);
        } else {
            startLocate();
        }

        jCameraView = (JCameraView) findViewById(R.id.jcameraview);

        //设置视频保存路径
        jCameraView.setSaveVideoPath(Environment.getExternalStorageDirectory() +"/DCIM/"+username+"/Video");



        //JCameraView监听
        jCameraView.setJCameraLisenter(new JCameraLisenter() {
            @Override
            public void captureSuccess(Bitmap bitmap) {

                Log.e(TAG,"bitmap宽度： "+bitmap.getWidth());
                Log.e(TAG,"bitmap高度： "+bitmap.getHeight());
//                String[] texts={" "," "};
                List<String> texts = new ArrayList<>();
                time = getDateLabel();
//                texts[0] = time;
//                texts[1] = getDateLabel();
                texts.add(time);
                texts.add(loal);
                Calendar calendar = Calendar.getInstance();
                int day =  calendar.get(Calendar.DAY_OF_MONTH);
                Log.e(TAG,"day:"+day);

                DecimalFormat df=new DecimalFormat("0.0");//设置保留位
                String rate1 = df.format((float)day/31);
                Log.e(TAG,"rate:"+rate1);
                int width = (int) ((bitmap.getWidth()-180)*Float.valueOf(rate1));
                Log.e(TAG,"width:"+width);
                int height = ((int) ((bitmap.getHeight()-40)*Float.valueOf(rate1))+20);
                Log.e(TAG,"height:"+height);

                Bitmap bitmap2 = ImageUtil.drawTexts(getApplicationContext(),bitmap,texts,12,getResources().getColor(R.color.red),width,height);

                //获取图片bitmap
                Log.e("JCameraView", "bitmap = " + bitmap2.getWidth());
                File filePic = new File(Environment.getExternalStorageDirectory() +"/DCIM/"+username+"/Photo/image_"+ getDate().toString()+".jpg");
                //bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.demo);

                try {

                    if (!filePic.exists()) {
                        filePic.getParentFile().mkdirs();
                        filePic.createNewFile();
                    }
                    FileOutputStream fos = new FileOutputStream(filePic);
                    bitmap2.compress(Bitmap.CompressFormat.JPEG, 70, fos);
                    fos.flush();
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
//                Intent intent = getIntent();
                Log.e(TAG,"path: "+filePic.getAbsolutePath());
                ImagePath = filePic.getAbsolutePath();
                String[] splitPath = ImagePath.split("/");
                ImagePath = splitPath[splitPath.length-1];
                intent.putExtra("ImagePath",ImagePath);
                intent.putExtra("VideoPath",VideoPath);
                setResult(RESULT_OK,intent);
                finish();
            }

            @Override
            public void recordSuccess(String url) {
                //录像
                Log.e("JCameraView", "String = " + url);
                VideoPath = url;
                //Intent intent = getIntent();
                String[] splitPath = VideoPath.split("/");
                VideoPath = splitPath[splitPath.length-1];
                intent.putExtra("ImagePath",ImagePath);
                intent.putExtra("VideoPath",VideoPath);
                setResult(RESULT_OK,intent);
                finish();
            }

            @Override
            public void quit() {
                //退出按钮
                PhotoVideoActivity.this.finish();
            }
        });




    }

    @Override
    protected void onStart() {
        super.onStart();
        //全屏显示
        if (Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        } else {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(option);
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        if (granted) {
            jCameraView.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        jCameraView.onPause();
    }

    /**
     * 获取系统时间
     * @return
     */
    String getDate(){
        Calendar ca = Calendar.getInstance();
        int year = ca.get(Calendar.YEAR);          // 获取年份
        int month = ca.get(Calendar.MONTH);       // 获取月份
        int day = ca.get(Calendar.DATE);            // 获取日
        int minute = ca.get(Calendar.MINUTE) ;      // 分
        int hour = ca.get(Calendar.HOUR);           // 小时
        int second = ca.get(Calendar.SECOND);    // 秒
        return "" + year + (month + 1) + day + hour + minute + second;
    }

    private String getDateLabel(){
        Calendar ca = Calendar.getInstance();
        int year = ca.get(Calendar.YEAR);          // 获取年份
        int month = ca.get(Calendar.MONTH);       // 获取月份
        int day = ca.get(Calendar.DATE);            // 获取日
        return ""+year+"-"+(month+1)+"-"+day;
    }

    /**
     * 获取权限
     */
    private void getPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                //具有权限
                granted = true;
            } else {
                //不具有获取权限，需要进行权限申请
                ActivityCompat.requestPermissions(PhotoVideoActivity.this, new String[]{
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.CAMERA}, GET_PERMISSION_REQUEST);
                granted = false;
            }

//            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
//                    ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED ) {
//                //具有权限
//                GPSgranted = true;
//            } else {
//                //不具有获取权限，需要进行权限申请
//                ActivityCompat.requestPermissions(PhotoVideoActivity.this, new String[]{
//                        Manifest.permission.ACCESS_FINE_LOCATION,
//                        Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_PERMISSION_CODE);
//                GPSgranted = false;
//            }




        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == GET_PERMISSION_REQUEST) {
            int size = 0;
            if (grantResults.length >= 1) {
                int writeResult = grantResults[0];
                //读写内存权限
                boolean writeGranted = writeResult == PackageManager.PERMISSION_GRANTED;//读写内存权限
                if (!writeGranted) {
                    size++;
                }
                //录音权限
                int recordPermissionResult = grantResults[1];
                boolean recordPermissionGranted = recordPermissionResult == PackageManager.PERMISSION_GRANTED;
                if (!recordPermissionGranted) {
                    size++;
                }
                //相机权限
                int cameraPermissionResult = grantResults[2];
                boolean cameraPermissionGranted = cameraPermissionResult == PackageManager.PERMISSION_GRANTED;
                if (!cameraPermissionGranted) {
                    size++;
                }
                if (size == 0) {
                    granted = true;
                    jCameraView.onResume();
                }else{
                    Toast.makeText(this, "请到设置-权限管理中开启", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }

//        if (requestCode == REQUEST_PERMISSION_CODE) {
//            int size = 0;
//            if (grantResults.length >= 1) {
//                int locationResult = grantResults[0];
//                //读写内存权限
//                boolean locationGranted = locationResult == PackageManager.PERMISSION_GRANTED;//读写内存权限
//                if (!locationGranted) {
//                    size++;
//                }
//                //录音权限
//                int recordPermissionResult = grantResults[1];
//                boolean recordPermissionGranted = recordPermissionResult == PackageManager.PERMISSION_GRANTED;
//                if (!recordPermissionGranted) {
//                    size++;
//                }
//
//                if (size == 0) {
//                    GPSgranted = true;
//
//                }else{
//                    Toast.makeText(this, "请到设置-权限管理中开启", Toast.LENGTH_SHORT).show();
//                    finish();
//                }
//            }
//        }



        if (requestCode == REQUEST_PERMISSION_CODE){
            PermissionUtils.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
        }


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationManager.removeUpdates(locationListener);
    }

    @SuppressLint("MissingPermission")
    private void startLocate() {

        boolean providerEnabled = mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (providerEnabled) { //GPS已开启
            /**
             * 绑定监听
             * 参数1，设备：有GPS_PROVIDER和NETWORK_PROVIDER两种，前者是GPS,后者是GPRS以及WIFI定位
             * 参数2，位置信息更新周期.单位是毫秒
             * 参数3，位置变化最小距离：当位置距离变化超过此值时，将更新位置信息
             * 参数4，监听
             * 备注：参数2和3，如果参数3不为0，则以参数3为准；参数3为0，则通过时间来定时更新；两者为0，则随时刷新
             */
            mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        } else {
            Toast.makeText(this, "请打开GPS定位", Toast.LENGTH_SHORT).show();
        }
    }

    private LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            //位置信息变化时触发
            Log.e("xyh", "定位方式：" + location.getProvider());
            Log.e("xyh", "纬度：" + location.getLatitude());
            Log.e("xyh", "经度：" + location.getLongitude());
            Log.e("xyh", "海拔：" + location.getAltitude());
            Log.e("xyh", "时间：" + location.getTime());

            DecimalFormat df = new DecimalFormat("0.00");
            StringBuffer sb = new StringBuffer(256);
            String El = df.format(location.getLongitude());
            String Nl = df.format(location.getLatitude());
            sb.append("E:"+El);
            sb.append(" N:"+Nl);
            loal = sb.toString();

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            //GPS状态变化时触发
            switch (status) {
                case LocationProvider.AVAILABLE:
                    Log.e("onStatusChanged", "当前GPS状态为可见状态");
                    break;
                case LocationProvider.OUT_OF_SERVICE:
                    Log.e("onStatusChanged", "当前GPS状态为服务区外状态");
                    break;
                case LocationProvider.TEMPORARILY_UNAVAILABLE:
                    Log.e("onStatusChanged", "当前GPS状态为暂停服务状态");
                    break;
            }
        }

        @Override
        public void onProviderEnabled(String provider) {
            //GPS开启时触发
            Log.e("xyh", "onProviderEnabled: ");
        }

        @Override
        public void onProviderDisabled(String provider) {
            //GPS禁用时触发
            Log.e("xyh", "onProviderDisabled: ");
        }
    };

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//
//
//    }


    @Override
    public void onPermissionsAllGranted(int requestCode, List<String> perms, boolean isAllGranted) {
        if (isAllGranted) {
            startLocate();
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
//        if (requestCode == REQUEST_PERMISSION_CODE){
//
//
//        }

        if (PermissionUtils.somePermissionPermanentlyDenied(this, perms)) {
            PermissionUtils.showDialogGoToAppSettting(this);
        } else {
            PermissionUtils.showPermissionReason(requestCode, this, permissionsOfGPS, "需要定位权限");
        }


//        if (requestCode == REQUEST_PERMISSION_CODE_CAMERA){
//            if (PermissionUtils.somePermissionPermanentlyDenied(this, perms)) {
//                PermissionUtils.showDialogGoToAppSettting(this);
//            } else {
//                PermissionUtils.showPermissionReason(requestCode, this, permissionsOfCAMERA, "需要相机权限");
//            }
//        }

    }

}
