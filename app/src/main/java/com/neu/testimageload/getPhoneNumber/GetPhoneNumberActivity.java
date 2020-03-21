package com.neu.testimageload.getPhoneNumber;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.github.javiersantos.materialstyleddialogs.enums.Style;
import com.kongzue.dialog.interfaces.OnDialogButtonClickListener;
import com.kongzue.dialog.interfaces.OnInputDialogButtonClickListener;
import com.kongzue.dialog.util.BaseDialog;
import com.kongzue.dialog.util.DialogSettings;
import com.kongzue.dialog.util.InputInfo;
import com.kongzue.dialog.util.TextInfo;
import com.kongzue.dialog.v3.InputDialog;
import com.kongzue.dialog.v3.MessageDialog;
import com.kongzue.dialog.v3.TipDialog;
import com.neu.testimageload.R;
import com.neu.testimageload.photoandvideo.PhotoActivity;
import com.orhanobut.logger.Logger;

import de.greenrobot.event.EventBus;

public class GetPhoneNumberActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "GetPhoneNumberActivity";

    public static final int PERMISSION_REQUEST_CODE_BASIC_INFORMATION = 1;
    private final int GET_PERMISSION_REQUEST = 100; //权限申请自定义码
    private SimConnectReceive mSimConnectReceive;
    private SimStateReceive mSimStateReceive;
    private boolean granted = false;
    private TextView textview_getPhoneNumber;
    private String phoneNumber;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_phone_number);
        init();


//        getSimInfo();
        PhoneInfoUtils phoneInfoUtils = new PhoneInfoUtils(getApplicationContext());
        Logger.e(phoneInfoUtils.getNativePhoneNumber());
        Logger.e(phoneInfoUtils.getIccid());
        Logger.e("double?: "+phoneInfoUtils.getDouble());
        Logger.e("slotid?: "+phoneInfoUtils.getSubid1BySlotId(getApplicationContext(),0));
        Logger.e("sim?: "+phoneInfoUtils.getSimByMethod("getLine1Number",0));
        phoneNumber = phoneInfoUtils.getNativePhoneNumber();
        if (!PhotoActivity.phonenumber.equals("")){
            if (phoneNumber.equals(PhotoActivity.phonenumber)){
                textview_getPhoneNumber.setText(phoneInfoUtils.getNativePhoneNumber());
            }else {
                textview_getPhoneNumber.setText(PhotoActivity.phonenumber);
            }
        }else {
            textview_getPhoneNumber.setText(phoneInfoUtils.getNativePhoneNumber());
        }

//        if (!PermissionUtil.hasSelfPermission(this,
//                Manifest.permission.READ_PHONE_STATE)) {
//            requestPermissions(
//                    new String[]{Manifest.permission.READ_PHONE_STATE},
//                    PERMISSION_REQUEST_CODE_BASIC_INFORMATION);
//        } else {
//            initView();
//        }

        //6.0动态权限获取
        getPermissions();


    }

    private void init() {
        textview_getPhoneNumber = findViewById(R.id.textview_getphonenumber);

        textview_getPhoneNumber.setOnClickListener(this);

    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initView() {
//        if (mSimConnectReceive == null) {
//            mSimConnectReceive = new SimConnectReceive();
//            IntentFilter filter = new IntentFilter();
//            filter.addAction(SimConnectReceive.ACTION_SIM_STATE_CHANGED);
//            registerReceiver(mSimConnectReceive, filter);
//        }
//        if (mSimStateReceive == null) {
//            mSimStateReceive = new SimStateReceive();
//            IntentFilter filter1 = new IntentFilter();
//            filter1.addAction(SimStateReceive.ACTION_SIM_STATE_CHANGED);
//            registerReceiver(mSimStateReceive, filter1);
//        }
//        if (!EventBus.getDefault().isRegistered(this)) {
//            EventBus.getDefault().register(this);
//        }
//        String number1 = SimUtils.getSimPhonenumber(this, 0);
//        if (!TextUtils.isEmpty(number1)) {
//            ((TextView) findViewById(R.id.sim1_number)).setText(number1);
//        } else {
//            ((TextView) findViewById(R.id.sim1_number)).setText("未检测到卡1");
//        }
//
//        String number2 = SimUtils.getSimPhonenumber(this, 1);
//        if (!TextUtils.isEmpty(number2)) {
//            ((TextView) findViewById(R.id.sim2_number)).setText(number2);
//        } else {
//            ((TextView) findViewById(R.id.sim2_number)).setText("未检测到卡2");
//        }
//
//        String imei1 = SimUtils.getSimImei(this, 0);
//        if (!TextUtils.isEmpty(imei1)) {
//            ((TextView) findViewById(R.id.sim1_imei)).setText(imei1);
//        } else {
//            ((TextView) findViewById(R.id.sim1_imei)).setText("未检测到卡1");
//        }
//
//        String imei2 = SimUtils.getSimImei(this, 1);
//        if (!TextUtils.isEmpty(number2)) {
//            ((TextView) findViewById(R.id.sim2_imei)).setText(imei2);
//        } else {
//            ((TextView) findViewById(R.id.sim2_imei)).setText("未检测到卡2");
//        }
//
//        String operator1 = SimUtils.getSimOperatorName(this, 0);
//        if (!TextUtils.isEmpty(operator1)) {
//            ((TextView) findViewById(R.id.sim1_operatorname)).setText(operator1);
//        } else {
//            ((TextView) findViewById(R.id.sim1_operatorname)).setText("未检测到卡1");
//        }
//
//        String operator2 = SimUtils.getSimOperatorName(this, 1);
//        if (!TextUtils.isEmpty(operator2)) {
//            ((TextView) findViewById(R.id.sim2_operatorname)).setText(operator2);
//        } else {
//            ((TextView) findViewById(R.id.sim2_operatorname)).setText("未检测到卡2");
//        }
//
//        String net1 = SimUtils.getSimNetworkName(this, 0);
//        if (!TextUtils.isEmpty(net1)) {
//            ((TextView) findViewById(R.id.sim1_networkType)).setText(net1);
//        } else {
//            ((TextView) findViewById(R.id.sim1_networkType)).setText("未检测到卡1");
//        }
//
//        String net2 = SimUtils.getSimNetworkName(this, 1);
//        if (!TextUtils.isEmpty(net2)) {
//            ((TextView) findViewById(R.id.sim2_networkType)).setText(net2);
//        } else {
//            ((TextView) findViewById(R.id.sim2_networkType)).setText("未检测到卡2");
//        }

//        SimUtils.CurrentNetwork currentNetwork = SimUtils.getCurrentNetwork(this);
//        TextView tv = (TextView) findViewById(R.id.current_network);
//        if (TextUtils.isEmpty(currentNetwork.whichSim)) {
//            tv.setText("当前网络不是sim卡数据流量");
//        } else {
//            tv.setText("当前哪张卡在使用网络:" + currentNetwork.whichSim + "\n" +
//                    "当前网络状态:" + currentNetwork.netWorkName + "\n" +
//                    "当前卡的生产厂商:" + currentNetwork.operateName);
//        }

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void onEventMainThread(SimStateChange event) {
        initView();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void onEventMainThread(SimConnectChange event) {
        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        if (mSimConnectReceive != null) {
            unregisterReceiver(mSimConnectReceive);
            mSimConnectReceive = null;
        }
        if (mSimStateReceive != null) {
            unregisterReceiver(mSimStateReceive);
            mSimStateReceive = null;
        }
    }

//    @RequiresApi(api = Build.VERSION_CODES.M)
//    @Override
//    public void onRequestPermissionsResult(
//            int requestCode, String[] permissions, int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == PERMISSION_REQUEST_CODE_BASIC_INFORMATION) {
//            if (grantResults.length > 0) {
//                boolean grant = true;
//                for (int granted : grantResults) {
//                    if (granted != PackageManager.PERMISSION_GRANTED) {
//                        grant = false;
//                        break;
//                    }
//                }
//                if (grant == true) {
//                    initView();
//                } else {
//                    finish();
//                }
//            }
//        }
//    }


    ///data/data/com.android.providers.telephony/databases/telephony.db
    public void getSimInfo() {

        Uri uri = Uri.parse("content://telephony/siminfo");
        Cursor cursor = null;
        ContentResolver contentResolver = getApplicationContext().getContentResolver();
        cursor = contentResolver.query(uri,
                new String[]{"_id", "sim_id", "icc_id", "display_name"}, "0=0",
                new String[]{}, null);
        if (null != cursor) {
            while (cursor.moveToNext()) {
                String icc_id = cursor.getString(cursor.getColumnIndex("icc_id"));
                String display_name = cursor.getString(cursor.getColumnIndex("display_name"));
                int sim_id = cursor.getInt(cursor.getColumnIndex("sim_id"));
                int _id = cursor.getInt(cursor.getColumnIndex("_id"));
//                int number = cursor.getInt(cursor.getColumnIndex("number"));

                Logger.d("icc_id-->" + icc_id);
                Logger.d( "sim_id-->" + sim_id);
                Logger.d( "display_name-->" + display_name);
                Logger.d("subId或者说是_id->" + _id);
//                Logger.d(TAG, "number" + number);
                Logger.d("---------------------------------");
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.textview_getphonenumber:
//                InputDialog.show(GetPhoneNumberActivity.this, "输入对话框", "请输入6位密码", "确定")
//                        .setInputInfo(new InputInfo()
//                                .setMAX_LENGTH(11)//限制最大输入长度
//                                .setInputType(InputType.TYPE_CLASS_PHONE)     //仅输入密码类型
//                                .setTextInfo(new TextInfo()       //设置文字样式
//                                        .setFontColor(Color.RED)
//                                        .setGravity(Gravity.CENTER_HORIZONTAL)//修改文字样式颜色为红色
//                                )
//                        )
//                        .setTheme(DialogSettings.THEME.LIGHT)
//                        .setOkButton("确定", new OnInputDialogButtonClickListener() {
//                    @Override
//                    public boolean onClick(BaseDialog baseDialog, View v, String inputStr) {
//                        Toast.makeText(getApplicationContext(),inputStr,Toast.LENGTH_SHORT).show();
//                        return false;
//                    }
//                });
                InputDialog.build(GetPhoneNumberActivity.this)
                        .setButtonTextInfo(new TextInfo().setFontColor(Color.BLACK))
                        .setTitle("提示")
                        .setMessage("请输入您的手机号")
                        .setInputText(textview_getPhoneNumber.getText().toString())
                        .setOkButton("确定", new OnInputDialogButtonClickListener() {
                            @Override
                            public boolean onClick(BaseDialog baseDialog, View v, String inputStr) {
                                if (inputStr.length() == 11){
                                    textview_getPhoneNumber.setText(inputStr);
                                    if(!inputStr.equals(PhotoActivity.phonenumber)){
                                        savePhone(inputStr);
                                    }
                                    return false;
                                }else {
                                    TipDialog.show(GetPhoneNumberActivity.this, "手机号输入错误！", TipDialog.TYPE.ERROR);
                                    return true;
                                }
//                                if (inputStr.equals("123456")) {
//                                    TipDialog.show(GetPhoneNumberActivity.this, "成功！", TipDialog.TYPE.SUCCESS);
//                                    return false;
//                                } else {
//                                    TipDialog.show(GetPhoneNumberActivity.this, "密码错误", TipDialog.TYPE.ERROR);
//                                    return true;
//                                }
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
                ;
                break;
            default:
                break;
        }
    }

    private void savePhone(String phone) {
        /**
         * SharedPreferences将用户的数据存储到该包下的shared_prefs/config.xml文件中，
         * 并且设置该文件的读取方式为私有，即只有该软件自身可以访问该文件
         */
        SharedPreferences sPreferences=getSharedPreferences("config", MODE_PRIVATE);
        SharedPreferences.Editor editor=sPreferences.edit();
        //当然sharepreference会对一些特殊的字符进行转义，使得读取的时候更加准确
        editor.putString("username", "yuhang");
        editor.putString("password", phone);
        PhotoActivity.phonenumber = phone;
//        //这里我们输入一些特殊的字符来实验效果
//        editor.putString("specialtext", "hajsdh><?//");
//        editor.putBoolean("or", true);
//        editor.putInt("int", 47);
        //切记最后要使用commit方法将数据写入文件
        editor.commit();
    }

    private void getPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED ) {
                //具有权限
                granted = true;
            } else {
                //不具有获取权限，需要进行权限申请
                ActivityCompat.requestPermissions(GetPhoneNumberActivity.this, new String[]{
                        Manifest.permission.CALL_PHONE
                        }, GET_PERMISSION_REQUEST);
                granted = false;
            }
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

                if (size == 0) {
                    granted = true;

                }else{
                    Toast.makeText(this, "请到设置-权限管理中开启", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }
    }


}
