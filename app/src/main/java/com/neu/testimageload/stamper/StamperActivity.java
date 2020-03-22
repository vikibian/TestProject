package com.neu.testimageload.stamper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kongzue.dialog.interfaces.OnInputDialogButtonClickListener;
import com.kongzue.dialog.util.BaseDialog;
import com.kongzue.dialog.util.DialogSettings;
import com.kongzue.dialog.util.InputInfo;
import com.kongzue.dialog.util.TextInfo;
import com.kongzue.dialog.v3.InputDialog;
import com.kongzue.dialog.v3.TipDialog;
import com.neu.testimageload.R;
import com.neu.testimageload.getPhoneNumber.GetPhoneNumberActivity;
import com.neu.testimageload.listitem.zhenggai.ImageUtil;
import com.neu.testimageload.photoandvideo.PhotoActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cn.walkpast.stamperlib.StampPadding;
import cn.walkpast.stamperlib.StampType;
import cn.walkpast.stamperlib.StampWatcher;
import cn.walkpast.stamperlib.Stamper;

public class StamperActivity extends AppCompatActivity  implements PermissionUtils.PermissionCallbacks {
    private static final String TAG = "StamperActivity";
    private TextView mBtnImage;
    private TextView mBtnText;
    private ImageView mShowImage;

    private LocationManager mLocationManager;
    private String location;

    private String[] permissions = {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };

    private static final int REQUEST_PERMISSION_CODE = 12;
    private String loal="";
    private String time="";
    private int colorNum = 0;
    private int[] colors = {R.color.red,R.color.white,R.color.darkblue,R.color.black,
    R.color.darkviolet,R.color.chocolate,R.color.deeppink,R.color.green,
    R.color.indigo,R.color.orangered};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stamper);

        mBtnImage = findViewById(R.id.btn_image);
        mBtnText = findViewById(R.id.btn_text);
        mShowImage = findViewById(R.id.show_image);
        mBtnImage.setOnClickListener(mOnClickListener);
        mBtnText.setOnClickListener(mOnClickListener);
        if (!PermissionUtils.hasPermissions(getApplicationContext(), permissions)) {
            PermissionUtils.requestPermissions(StamperActivity.this, REQUEST_PERMISSION_CODE, permissions);
        } else {
            startLocate();
        }

        getDate();
    }

    View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {

                case R.id.btn_text:


                    Bitmap bitmap4 = BitmapFactory.decodeFile("/storage/emulated/0/DCIM/viki/Photo/image_202032275339.jpg");
                    Bitmap bitmap3 = BitmapFactory.decodeResource(getResources(), R.drawable.sample_plot_3);
//                    Stamper.with(StamperActivity.this)
//                            .setLabel("National Geography1\n"+"测试")
//                            .setLabelColor(getResources().getColor(R.color.gray))//Color.rgb(255, 60, 70)
//                            .setLabelSize(30)
//                            .setMasterBitmap(bitmap3)
//                            .setStampType(StampType.TEXT)
//                            .setStampPadding(new StampPadding(bitmap3.getWidth() / 4, bitmap3.getHeight() / 6))
//                            .setStampWatcher(mStampWatcher)
//                            .setRequestId(1001)
//                            .build();
//                    Bitmap bitmap = ImageUtil.drawText(getApplicationContext(),bitmap3," 你好\n12",12,getResources().getColor(R.color.red));

//                    String[] texts = {"你好", "12"};
                    List<String> texts = new ArrayList<>();
                    time = getDateLabel();
                    texts.add(time);
                    texts.add(loal);
                    Calendar calendar = Calendar.getInstance();
                    int day =  calendar.get(Calendar.DAY_OF_MONTH);
                    Log.e(TAG,"day:"+day);
                    int index = day%10;
                    DecimalFormat df=new DecimalFormat("0.0");//设置保留位
                    String rate1 = df.format((float)day/31);
                    Log.e(TAG,"rate:"+rate1);
                    int width = (int) ((bitmap3.getWidth()-180)*Float.valueOf(rate1));
                    Log.e(TAG,"width:"+width);
                    int height = ((int) ((bitmap3.getHeight()-40)*Float.valueOf(rate1))+20);
                    Log.e(TAG,"height:"+height);
                    Bitmap bitmap = ImageUtil.drawTexts(getApplicationContext(), bitmap3, texts, 12, getResources().getColor(colors[index]), width, height);
                    Log.e(TAG," width:"+bitmap.getWidth());
                    Log.e(TAG," height:"+bitmap.getHeight());
                    mShowImage.setImageBitmap(bitmap);
                    break;

                case R.id.btn_image:
//
//                    mShowImage.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.sample_plot_4));
//
//                    Bitmap bitmap4 = BitmapFactory.decodeResource(getResources(), R.drawable.sample_plot_4);
//                    Bitmap watermark = BitmapFactory.decodeResource(getResources(), R.drawable.ic_watermark);
//                    Stamper.with(StamperActivity.this)
//                            .setMasterBitmap(bitmap4)
//                            .setWatermark(watermark)
//                            .setStampType(StampType.IMAGE)
//                            .setStampPadding(new StampPadding(bitmap4.getWidth() - watermark.getWidth() - 40, 40))
//                            .setStampWatcher(mStampWatcher)
//                            .setRequestId(1002)
//                            .build();
//
                    InputDialog.build(StamperActivity.this)
                            .setButtonTextInfo(new TextInfo().setFontColor(Color.BLACK))
                            .setTitle("提示")
                            .setMessage("请输入您的手机号")
                            .setInputText("")
                            .setOkButton("确定", new OnInputDialogButtonClickListener() {
                                @Override
                                public boolean onClick(BaseDialog baseDialog, View v, String inputStr) {
                                     colorNum = Integer.valueOf(inputStr);
                                     return false;
                                }
                            })
                            .setStyle(DialogSettings.STYLE.STYLE_KONGZUE)
                            .setTheme(DialogSettings.THEME.LIGHT)
                            .setCancelButton("取消")
                            .setHintText("手机号")
                            .setInputInfo(new InputInfo()
                                    .setMAX_LENGTH(11)
                                    .setInputType(InputType.TYPE_CLASS_PHONE)
                                    .setTextInfo(new TextInfo().setFontColor(Color.BLACK)
                                    )
                            )
                            .setCancelable(false)
                            .show();
                    break;
            }
        }
    };

    String getDate(){
        Calendar ca = Calendar.getInstance();
        int year = ca.get(Calendar.YEAR);          // 获取年份
        int month = ca.get(Calendar.MONTH);       // 获取月份
        int day = ca.get(Calendar.DATE);            // 获取日
        int minute = ca.get(Calendar.MINUTE) ;      // 分
        int hour = ca.get(Calendar.HOUR_OF_DAY);           // 小时
        int second = ca.get(Calendar.SECOND);    // 秒
        Log.e(TAG," "+"" + year +"-"+ (month + 1) +"-"+ day+"-" + hour+"-" + minute+"-" + second);
        return "" + year + (month + 1) + day + hour + minute + second;
    }

    private String getDateLabel(){
        Calendar ca = Calendar.getInstance();
        int year = ca.get(Calendar.YEAR);          // 获取年份
        int month = ca.get(Calendar.MONTH);       // 获取月份
        int day = ca.get(Calendar.DATE);            // 获取日
        return ""+year+"-"+(month+1)+"-"+day;
    }

    StampWatcher mStampWatcher = new StampWatcher() {
        @Override
        protected void onSuccess(Bitmap bitmap, int requestId) {
            super.onSuccess(bitmap, requestId);

            switch (requestId) {

                case 1001:
                    //the result of text stamper

                    mShowImage.setImageBitmap(bitmap);

                    break;
                case 1002:
                    //the result of image stamper

                    mShowImage.setImageBitmap(bitmap);

                    break;
            }
        }

        @Override
        protected void onError(String error, int requestId) {
            super.onError(error, requestId);

            switch (requestId) {

                case 1001://

                    Toast.makeText(StamperActivity.this, "error:" + error, Toast.LENGTH_SHORT).show();

                    break;
                case 1002://

//                    Toast.makeText(MainActivity.this, "error:" + error, Toast.LENGTH_SHORT).show();

                    break;
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationManager.removeUpdates(locationListener);
    }

    @SuppressLint("MissingPermission")
    private void startLocate() {
        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
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
            Toast.makeText(this, "请打开GPS", Toast.LENGTH_SHORT).show();
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
            Log.e(TAG," "+loal);

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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        PermissionUtils.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }


    @Override
    public void onPermissionsAllGranted(int requestCode, List<String> perms, boolean isAllGranted) {
        if (isAllGranted) {
            startLocate();
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        if (PermissionUtils.somePermissionPermanentlyDenied(this, perms)) {
            PermissionUtils.showDialogGoToAppSettting(this);
        } else {
            PermissionUtils.showPermissionReason(requestCode, this, permissions, "需要定位权限");
        }
    }

}
