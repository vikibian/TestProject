package com.neu.testimageload.getPhoneNumber;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.orhanobut.logger.Logger;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * created by Viki on 2020/3/20
 * system login name : lg
 * created time : 14:15
 * email : 710256138@qq.com
 */
public class PhoneInfoUtils {
    private static String TAG = "PhoneInfoUtils";

    private TelephonyManager telephonyManager;
    //移动运营商编号
    private String NetworkOperator;
    private Context context;

    public PhoneInfoUtils(Context context) {
        this.context = context;
        telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public boolean getDouble(){
        boolean isDouble = false;
        if (ContextCompat.checkSelfPermission(context,Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            if (telephonyManager.getPhoneCount() == 2){
                isDouble = true;
            }
        }
        return isDouble;
    }

    //获取sim卡iccid
    public String getIccid() {
        String iccid = "N/A";
        if (ContextCompat.checkSelfPermission(context,Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            iccid = telephonyManager.getSimSerialNumber();
        }

        return iccid;
    }

    //获取电话号码
    public String getNativePhoneNumber() {
        String nativePhoneNumber = "N/A";
        if (ContextCompat.checkSelfPermission(context,Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED
       ) {
            nativePhoneNumber = telephonyManager.getLine1Number();

        }

        return nativePhoneNumber;
    }

    /**
     * 通过slotid获取相应卡的subid
     * @param context
     * @param slotId
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
    public  int getSubid1BySlotId(Context context, int slotId) {
        SubscriptionManager subscriptionManager = (SubscriptionManager) context.getSystemService(
                Context.TELEPHONY_SUBSCRIPTION_SERVICE);
        try {
            Class<?> telephonyClass = Class.forName(subscriptionManager.getClass().getName());
            Class<?>[] parameter = new Class[1];
            parameter[0] = int.class;
            Method getSimState = telephonyClass.getMethod("getSubId", parameter);
            Object[] obParameter = new Object[1];
            obParameter[0] = slotId;
            Object ob_phone = getSimState.invoke(subscriptionManager, obParameter);
            if (ob_phone != null) {
                Logger.d( "slotId:" + slotId + ";" + ((int[]) ob_phone)[0]);
                return ((int[]) ob_phone)[0];
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public  Object getSimByMethod(String method, int param) {
//        TelephonyManager telephony = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        try {
            Class<?> telephonyClass = Class.forName(telephonyManager.getClass().getName());
            Logger.e(telephonyClass.toString());
            Method[] method1 = telephonyClass.getDeclaredMethods();
            Logger.e("method1: "+ "   长度："+method1.length+"   "+method1[0].getName());
            for (int i=0;i<method1.length;i++){
                if (method1[i].getName().equals("getLine1Number")){
                    Logger.d(method1[i].getName()+"  "+ i);
//                    Logger.d(method1[i].ge+"  "+ i);
                }
            }




            Class<?>[] parameter = new Class[1];
            Logger.e(parameter.toString()+" 长度: "+parameter.length);

            parameter[0] = int.class;

            Method getSimState = telephonyClass.getDeclaredMethod("getLine1Number",parameter);//, parameter
            Logger.e("method2: "+getSimState.getName());

            Object ob_phone = getSimState.invoke(telephonyManager,param);
            Logger.e(ob_phone.toString());

            if (ob_phone != null) {
                return ob_phone;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    //获取手机服务商信息
    public String getProvidersName() {
        String providersName = "N/A";
        NetworkOperator = telephonyManager.getNetworkOperator();
        //IMSI号前面3位460是国家，紧接着后面2位00 02是中国移动，01是中国联通，03是中国电信。
//        Flog.d(TAG,"NetworkOperator=" + NetworkOperator);
        if (NetworkOperator.equals("46000") || NetworkOperator.equals("46002")) {
            providersName = "中国移动";//中国移动
        } else if (NetworkOperator.equals("46001")) {
            providersName = "中国联通";//中国联通
        } else if (NetworkOperator.equals("46003")) {
            providersName = "中国电信";//中国电信
        }
        return providersName;

    }

//    public String getPhoneInfo() {
//        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
//        StringBuffer sb = new StringBuffer();
//
//        sb.append("\nLine1Number = " + tm.getLine1Number());
//        sb.append("\nNetworkOperator = " + tm.getNetworkOperator());//移动运营商编号
//        sb.append("\nNetworkOperatorName = " + tm.getNetworkOperatorName());//移动运营商名称
//        sb.append("\nSimCountryIso = " + tm.getSimCountryIso());
//        sb.append("\nSimOperator = " + tm.getSimOperator());
//        sb.append("\nSimOperatorName = " + tm.getSimOperatorName());
//        sb.append("\nSimSerialNumber = " + tm.getSimSerialNumber());
//        sb.append("\nSubscriberId(IMSI) = " + tm.getSubscriberId());
//        return sb.toString();
//    }

//    private AlertDialog dialog;
//    // 要申请的权限
//    private String[] permissions = {Manifest.permission.READ_PHONE_STATE,
//            Manifest.permission.READ_SMS,
//            Manifest.permission.READ_PHONE_NUMBERS,
//    };

    // 版本判断。当手机系统大于 23 时，才有必要去判断权限是否获取
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            // 检查该权限是否已经获取
//            int i = ContextCompat.checkSelfPermission(getApplicationContext(), permissions[0]);
//            int l = ContextCompat.checkSelfPermission(getApplicationContext(), permissions[1]);
//            int m = ContextCompat.checkSelfPermission(getApplicationContext(), permissions[2]);
////            int n = ContextCompat.checkSelfPermission(getApplicationContext(), permissions[3]);
//            // 权限是否已经 授权 GRANTED---授权  DINIED---拒绝
//            if (i != PackageManager.PERMISSION_GRANTED || l != PackageManager.PERMISSION_GRANTED || m != PackageManager.PERMISSION_GRANTED ) {
//                // 如果没有授予该权限，就去提示用户请求
//                startRequestPermission();
//            }
//        }
//}
//
//    /**
//     * 开始提交请求权限
//     */
//    private void startRequestPermission() {
//        ActivityCompat.requestPermissions(this, permissions, 321);
//    }
//
//
//    /**
//     * 用户权限 申请 的回调方法
//     * @param requestCode
//     * @param permissions
//     * @param grantResults
//     */
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == 321) {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
//                    // 判断用户是否 点击了不再提醒。(检测该权限是否还可以申请)
//                    boolean b = shouldShowRequestPermissionRationale(permissions[0]);
//                    // 以前是!b
//                    if (b) {
//                        // 用户还是想用我的 APP 的
//                        // 提示用户去应用设置界面手动开启权限
//                        showDialogTipUserGoToAppSettting();
//                    } else{
//                        finish();
//                    }
//                } else {
//                }
//            }
//        }
//    }
//
//    /**
//     * 提示用户去应用设置界面手动开启权限
//     */
//    private void showDialogTipUserGoToAppSettting() {
//
//        dialog = new AlertDialog.Builder(this)
//                .setTitle("存储权限不可用")
//                .setMessage("请在-应用设置-权限-中，允许应用使用存储权限来保存用户数据")
//                .setPositiveButton("立即开启", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        // 跳转到应用设置界面
//                        goToAppSetting();
//                    }
//                })
//                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
////                        finish();
//                    }
//                }).setCancelable(false).show();
//    }
//
//    /**
//     * 跳转到当前应用的设置界面
//     */
//    private void goToAppSetting() {
//        Intent intent = new Intent();
//
//        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//        Uri uri = Uri.fromParts("package", getPackageName(), null);
//        intent.setData(uri);
//
//        startActivityForResult(intent, 123);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        //权限管理
//        if (requestCode == 123) {
//            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                // 检查该权限是否已经获取
//                int i = ContextCompat.checkSelfPermission(this, permissions[0]);
//                // 权限是否已经 授权 GRANTED---授权  DINIED---拒绝
//                if (i != PackageManager.PERMISSION_GRANTED) {
//                    // 提示用户应该去应用设置界面手动开启权限
//                    showDialogTipUserGoToAppSettting();
//                } else {
//                    if (dialog != null && dialog.isShowing()) {
//                        dialog.dismiss();
//                    }
//                    Toast.makeText(this, "权限获取成功", Toast.LENGTH_SHORT).show();
//                }
//            }
//        }
//    }
}
