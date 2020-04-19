package com.neu.testimageload.zhengzebiaodashi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.neu.testimageload.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ZhengzebiaodashiActivity extends AppCompatActivity {
    private static final String TAG = "ZhengzebiaodashiActivit";
    private EditText editText1;
    private EditText editText2;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhengzebiaodashi);

        editText1 = findViewById(R.id.zhengzebiaodashi_editText1);
        editText2 = findViewById(R.id.zhengzebiaodashi_editText2);
        submit = findViewById(R.id.zhengzebiaodashi_button);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG,"测试1:"+checkPassword1(editText1.getText().toString()));
                Log.e(TAG,"测试2:"+checkPassword2(editText2.getText().toString()));
            }
        });

    }

    public  boolean checkPassword1(String password){
        //正则表达式判断为数字和密码
        Pattern Password_Pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-zA-Z])(.{4,10})$");
        //正则表达式判断数字或密码
//        Pattern Password_Pattern = Pattern.compile("^(?=.*[a-zA-Z0-9])(.{4,10})$");
        Matcher matcher = Password_Pattern.matcher(password);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    public  boolean checkPassword2(String password){
        //正则表达式判断为数字和密码
//        Pattern Password_Pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-zA-Z])(.{4,10})$");
        //正则表达式判断数字或密码
//        Pattern Password_Pattern = Pattern.compile("^(?=.*[a-zA-Z])(.{4,10})|(?=.*[0-9])(.{4,10})$");
        Pattern Password_Pattern = Pattern.compile("^[A-Za-z0-9]+$");
        Matcher matcher = Password_Pattern.matcher(password);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }
}
