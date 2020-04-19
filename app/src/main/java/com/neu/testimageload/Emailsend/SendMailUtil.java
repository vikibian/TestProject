package com.neu.testimageload.Emailsend;

import androidx.annotation.NonNull;

import java.io.File;

/**
 * created by Viki on 2020/4/18
 * system login name : lg
 * created time : 16:36
 * email : 710256138@qq.com
 */
public class SendMailUtil {
    //qq
    private static final String HOST = "smtp.qq.com";
    private static final String PORT = "587";
    private static final String FROM_ADD = "2152234494@qq.com"; //发送方邮箱
    private static final String FROM_PSW = "inyjfzsajlxqebji";//发送方邮箱授权码

//    //163
//    private static final String HOST = "smtp.163.com";
//    private static final String PORT = "25"; //或者465  994
//    private static final String FROM_ADD = "wkbian@163.com";
//    private static final String FROM_PSW = "YIFIHEVAGYCEMUPC";
//    private static final String TO_ADD = "2584770373@qq.com";

    public static void send(final File file, String toAdd){
        final MailInfo mailInfo = creatMail(toAdd, "Hello", "Android 测试");
        final MailSender2 sms = new MailSender2();
        new Thread(new Runnable() {
            @Override
            public void run() {
                sms.sendFileMail(mailInfo,file);
            }
        }).start();
    }

    public static void send(String toAdd,String subject,String content){
        final MailInfo mailInfo = creatMail(toAdd,subject,content);
        final MailSender2 sms = new MailSender2();
        new Thread(new Runnable() {
            @Override
            public void run() {
//                sms.sendTextMail2(mailInfo);
                sms.sendHtmlMail(mailInfo);
            }
        }).start();
    }

    @NonNull
    private static MailInfo creatMail(String toAdd, String subject, String content) {
        final MailInfo mailInfo = new MailInfo();
        mailInfo.setMailServerHost(HOST);
        mailInfo.setMailServerPort(PORT);
        mailInfo.setValidate(true);
        mailInfo.setUserName(FROM_ADD); // 你的邮箱地址
        mailInfo.setPassword(FROM_PSW);// 您的邮箱密码
        mailInfo.setFromAddress(FROM_ADD); // 发送的邮箱
        mailInfo.setToAddress(toAdd); // 发到哪个邮件去
        mailInfo.setSubject(subject); // 邮件主题
        mailInfo.setContent(content); // 邮件文本
        return mailInfo;
    }
}

