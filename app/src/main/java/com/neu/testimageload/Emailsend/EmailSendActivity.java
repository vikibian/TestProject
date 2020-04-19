package com.neu.testimageload.Emailsend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.neu.testimageload.R;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public class EmailSendActivity extends AppCompatActivity {
    private static final String TAG = "EmailSendActivity";

    private Button btnSend = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_send);

        btnSend = (Button) findViewById(R.id.btnSend);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //耗时操作要起线程...有几个新手都是这个问题
                if (isNetworkConnected(getApplicationContext())){
                    new Thread(new Runnable() {

                        @Override
                        public void run() {

                            SendMailUtil.send("710256138@qq.com","找回密码","请在规定的时间内输入以下的验证码："+getRandomCode());
                            //间内输入以下的验证码：\n \t\t1234
//                        try {
//                            EmailSender sender = new EmailSender();
//                            //设置服务器地址和端口，网上搜的到
//                            sender.setProperties("smtp.163.com", "994");
//                            //分别设置发件人，邮件标题和文本内容
//                            sender.setMessage("wkbian@163.com", "This is title", "This is content！");
//                            //设置收件人的邮箱
//
//                            sender.setReceiver(new String[]{"710256138@qq.com"});
////                            File file = new File("/sdcard/DCIM/Camera/IMG_20150917_141427.jpg");
////                            if(file.exists()) {
////                                Log.d(TAG,"file.exists()------>>>>>>");
////                                //添加附件
////                                //这个附件的路径是我手机里的啊，要发你得换成你手机里正确的路径
////                                sender.addAttachment("/sdcard/DCIM/Camera/IMG_20150917_141427.jpg");
////
////                            } else {
////                                Log.d(TAG,"file.notexists()------>>>>>>");
////                            }
//
//                            //发送邮件,sender.setMessage("你的163邮箱账号", "EmailS//ender", "Java Mail ！");这里面两个邮箱账号要一致
//                            sender.sendEmail("smtp.163.com", "wikibian0@163.com", "YIFIHEVAGYCEMUPC");
//
//                        } catch (AddressException e) {
//                            // TODO Auto-generated catch block
//                            e.printStackTrace();
//                        } catch (MessagingException e) {
//                            // TODO Auto-generated catch block
//                            e.printStackTrace();
//                        }
                        }
                    }).start();
                }else {
                    Toast.makeText(EmailSendActivity.this, "网络不可获取！", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


    private String getRandomCode() {
        String strRand = "";
        for (int i = 0; i < 4; i++) {
            strRand += String.valueOf((int) (Math.random() * 10));
        }
        Log.i("aaa", "randomCode: " + strRand);
        return strRand;
    }

    public boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }
}
