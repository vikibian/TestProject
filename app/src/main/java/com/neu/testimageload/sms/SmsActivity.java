package com.neu.testimageload.sms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.bm.library.PhotoView;
import com.neu.testimageload.R;
import com.neu.testimageload.photoandvideo.PhotoActivity;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;

public class SmsActivity extends AppCompatActivity implements View.OnClickListener{
    private static String TAG = "SmsActivity";
    public static final String TEMP_CODE = "1319972";

//    private EditText edit_phone;
//    private EditText edit_cord;
//    //private TextView now;
//    private Button bt_getcord;
//    private Button bt_login;
//    private String phone_number;
//    private String cord_number;
//    private ImageButton wxlogin;
//    EventHandler eventHandler;
//    private int time=60;
//    private boolean flag=true;

    private Button validateNum_btn;
    private Button landing_btn;
    private EditText userName;
    private EditText validateNum;
    public EventHandler eh; //事件接收器
    private TimeCount mTimeCount;//计时器

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        initEvent();
        init();



        //初始化操作
//        initview();



//        eventHandler = new EventHandler() {
//            public void afterEvent(int event, int result, Object data) {
//                Message msg=new Message();//创建了一个对象
//                msg.arg1=event;
//                msg.arg2=result;
//                msg.obj=data;
//                handler.sendMessage(msg);
//            }
//        };
//
//        SMSSDK.registerEventHandler(eventHandler);//注册短信回调（记得销毁，避免泄露内存）*/

    }

//    private void initview() {
//        edit_phone=(EditText)findViewById(R.id.ed_phone); //你的手机号
//
//        edit_cord=(EditText)findViewById(R.id.ed_code);//你的验证码
//        bt_getcord=(Button)findViewById(R.id.btn_getcord);//获取验证码按钮
//        bt_login = (Button)findViewById(R.id.bt_login);//登陆按钮
//        wxlogin = (ImageButton)findViewById(R.id.wxlogin);//微信登录按钮
//        //三个触发事件
//        bt_getcord.setOnClickListener(this);//验证码的触发事件
//        bt_login.setOnClickListener(this);
//        wxlogin.setOnClickListener(this);
//    }
//    protected void onDestroy() {//销毁
//        super.onDestroy();
//        SMSSDK.unregisterEventHandler(eventHandler);
//    }
//
//    /**
//     * 使用Handler来分发Message对象到主线程中，处理事件
//     */
//    Handler handler=new Handler()
//    {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            int event=msg.arg1;
//            int result=msg.arg2;
//            Object data=msg.obj;
//            if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {//获取验证码成功
//                if(result == SMSSDK.RESULT_COMPLETE) {
//                    //回调完成
//                    boolean smart = (Boolean)data;
//                    if(smart) {
//                        Toast.makeText(getApplicationContext(),"该手机号已经注册过，请重新输入",
//                                Toast.LENGTH_LONG).show();
//                        edit_phone.requestFocus();//焦点
//                        return;
//                    }
//                }
//            }
//            //回调完成
//            if (result==SMSSDK.RESULT_COMPLETE){
//                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {//提交验证码成功
//                    Toast.makeText(getApplicationContext(), "验证码输入正确",
//                            Toast.LENGTH_LONG).show();
//                }
//            }else {//其他出错情况
//                if(flag)
//                {
//                    bt_getcord.setVisibility(View.VISIBLE);
//                    Toast.makeText(getApplicationContext(),"验证码获取失败请重新获取", Toast.LENGTH_LONG).show();
//                    edit_phone.requestFocus();
//                }
//                else
//                {
//                    Toast.makeText(getApplicationContext(),"验证码输入错误", Toast.LENGTH_LONG).show();
//                }
//
//            }
//        }
//
//    };
//
//    //按钮点击事件
//    @Override
//    public void onClick(View v) {
//        /*String phone_number=edit_phone.getText().toString();//1
//        String cord_number=bt_getcord.getText().toString().trim();//1 */
//        switch (v.getId()){
//
//            case  R.id.btn_getcord://获取验证码的ID
//                if(judPhone())//去掉左右空格获取字符串，是正确的手机号
//                {
//                    SMSSDK.getVerificationCode("86",phone_number);//获取你的手机号的验证码
//                    edit_cord.requestFocus();//判断是否获得焦点
//                }
//                break;
//            //  获取后要提交你的验证码以判断是否正确，并登陆成功
//
//            case R.id.bt_login://登陆页面的ID
//
//                if(judCord())//判断验证码
//                    SMSSDK.submitVerificationCode("86",phone_number,cord_number);//提交手机号和验证码
//
//                startActivity(new Intent(this, PhotoActivity.class));
//                flag=false;
//                break;
//
//            case R.id.wxlogin://跳转到微信登陆
//                break;
//
//        }
//
//    }
//    private boolean judPhone() {//判断手机号是否正确
//        //不正确的情况
//        if(TextUtils.isEmpty(edit_phone.getText().toString().trim()))//对于字符串处理Android为我们提供了一个简单实用的TextUtils类，如果处理比较简单的内容不用去思考正则表达式不妨试试这个在android.text.TextUtils的类，主要的功能如下:
//        //是否为空字符 boolean android.text.TextUtils.isEmpty(CharSequence str)
//        {
//            Toast.makeText(getApplicationContext(),"请输入您的电话号码",Toast.LENGTH_LONG).show();
//            edit_phone.requestFocus();//设置是否获得焦点。若有requestFocus()被调用时，后者优先处理。注意在表单中想设置某一个如EditText获取焦点，光设置这个是不行的，需要将这个EditText前面的focusable都设置为false才行。
//            return false;
//        }
//        else if(edit_phone.getText().toString().trim().length()!=11)
//        {
//            Toast.makeText(getApplicationContext(),"您的电话号码位数不正确",Toast.LENGTH_LONG).show();
//            edit_phone.requestFocus();
//            return false;
//        }
//        //正确的情况
//        else
//        {
//            phone_number=edit_phone.getText().toString().trim();
//            String num="[1][358]\\d{9}";
//            if(phone_number.matches(num))
//                return true;
//            else
//            {
//                Toast.makeText(getApplicationContext(),"请输入正确的手机号码",Toast.LENGTH_LONG).show();
//                return false;
//            }
//        }
//    }
//
//    private boolean judCord() {//判断验证码是否正确
//        judPhone();//先执行验证手机号码正确与否
//        if(TextUtils.isEmpty(edit_cord.getText().toString().trim()))//验证码
//        {
//            Toast.makeText(getApplicationContext(),"请输入您的验证码",Toast.LENGTH_LONG).show();
//            edit_cord.requestFocus();//聚集焦点
//            return false;
//        }
//        else if(edit_cord.getText().toString().trim().length()!=4)
//        {
//            Toast.makeText(getApplicationContext(),"您的验证码位数不正确",Toast.LENGTH_LONG).show();
//            edit_cord.requestFocus();
//
//            return false;
//        }
//        else
//        {
//            cord_number=edit_cord.getText().toString().trim();
//            return true;
//        }
//    }

    private void initEvent(){
        userName = (EditText) findViewById(R.id.userName);
        validateNum = (EditText) findViewById(R.id.validateNum);
        validateNum_btn = (Button) findViewById(R.id.validateNum_btn);
        landing_btn = (Button) findViewById(R.id.landing_btn);
        validateNum_btn.setOnClickListener(this);
        landing_btn.setOnClickListener(this);
        mTimeCount = new TimeCount(60000, 1000);
    }

    /**
     * 初始化事件接收器
     */
    private void init(){
        eh = new EventHandler(){
            @Override
            public void afterEvent(int event, int result, Object data) {

                if (result == SMSSDK.RESULT_COMPLETE) { //回调完成

                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) { //提交验证码成功
                        Log.e(TAG," 提交验证码成功");

                        startActivity(new Intent(SmsActivity.this, PhotoActivity.class)); //页面跳转

                    }else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){ //获取验证码成功
                        Log.e(TAG," 获取验证码成功");

                    }else if (event ==SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES){ //返回支持发送验证码的国家列表

                    }
                }else{
                    ((Throwable)data).printStackTrace();
                }
            }
        };
        SMSSDK.registerEventHandler(eh); //注册短信回调
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.validateNum_btn:
                SMSSDK.getSupportedCountries();//获取短信目前支持的国家列表
                if(!userName.getText().toString().trim().equals("")){
                    if (checkTel(userName.getText().toString().trim())) {
                        SMSSDK.getVerificationCode("+86",userName.getText().toString());//获取验证码
                        mTimeCount.start();
                    }else{
                        Toast.makeText(SmsActivity.this, "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(SmsActivity.this, "请输入手机号码", Toast.LENGTH_SHORT).show();
                }
                break;
//                RegisterPage registerPage = new RegisterPage();
//                // 使用自定义短信模板(不设置则使用默认模板)
//                registerPage.setTempCode(TEMP_CODE);
//                registerPage.setRegisterCallback(new EventHandler() {
//                    public void afterEvent(int event, int result, Object data) {
//                        // 解析注册结果
//                        if (result == SMSSDK.RESULT_COMPLETE) {
//                            @SuppressWarnings("unchecked")
//                            HashMap<String,Object> phoneMap = (HashMap<String, Object>) data;
//                            String country = (String) phoneMap.get("country");
//                            String phone = (String) phoneMap.get("phone");
//                            // 提交用户信息
////						registerUser(country, phone);
//                        }
//                    }
//                });
//                registerPage.show(this);
//                break;
            case R.id.landing_btn:
                if (!userName.getText().toString().trim().equals("")) {
                    if (checkTel(userName.getText().toString().trim())) {
                        if (!validateNum.getText().toString().trim().equals("")) {
                            SMSSDK.submitVerificationCode("+86",userName.getText().toString().trim(),validateNum.getText().toString().trim());//提交验证
                        }else{
                            Toast.makeText(SmsActivity.this, "请输入验证码", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(SmsActivity.this, "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(SmsActivity.this, "请输入手机号码", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    /**
     * 正则匹配手机号码
     * @param tel
     * @return
     */
    public boolean checkTel(String tel){
        Pattern p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$");
        Matcher matcher = p.matcher(tel);
        return matcher.matches();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(eh);
    }

    /**
     * 计时器
     */
    class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long l) {
            validateNum_btn.setClickable(false);
            validateNum_btn.setText(l/1000 + "秒后重新获取");
        }

        @Override
        public void onFinish() {
            validateNum_btn.setClickable(true);
            validateNum_btn.setText("获取验证码");
        }
    }


}
