package com.neu.testimageload.mimaxianshi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.neu.testimageload.R;

public class PasswordShowOrHideActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "PasswordShowOrHideActiv";

    private EditText et_passwd;
    private ImageView iv_showPassword;//密码是否明文显示
    private Boolean showPassword = true;

    private EditText et_newpasswd;
    private ImageView iv_showNewPassword;//密码是否明文显示
    private Boolean showNewPassword = true;

    private EditText et_renewpasswd;
    private ImageView iv_showReNewPassword;//密码是否明文显示
    private Boolean showReNewPassword = true;

    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_show_or_hide);

        initview();
    }

    private void initview() {


        et_passwd = (EditText) findViewById(R.id.me_resetpassword_passwd);
        iv_showPassword = (ImageView) findViewById(R.id.me_resetpassword_showPassword);
        iv_showPassword.setImageDrawable(getResources().getDrawable(R.drawable.ic_remove_red_eye_grey_500_24dp));
        iv_showPassword.setOnClickListener(this);

        et_newpasswd = findViewById(R.id.me_resetpassword_newpasswd);
        iv_showNewPassword = findViewById(R.id.me_resetpassword_showNewPassword);
        iv_showNewPassword.setImageDrawable(getResources().getDrawable(R.drawable.ic_remove_red_eye_grey_500_24dp));
        iv_showNewPassword.setOnClickListener(this);

        et_renewpasswd = findViewById(R.id.me_resetpassword_renewpasswd);
        iv_showReNewPassword = findViewById(R.id.me_resetpassword_showReNewPassword);
        iv_showReNewPassword.setImageDrawable(getResources().getDrawable(R.drawable.ic_remove_red_eye_grey_500_24dp));
        iv_showReNewPassword.setOnClickListener(this);

        submit = findViewById(R.id.me_resetpassword_submit);
        submit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.me_resetpassword_showPassword:
                if (showPassword) {// 显示密码
                    iv_showPassword.setImageDrawable(getResources().getDrawable(R.drawable.ic_vpn_key_grey_500_24dp));
                    et_passwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    et_passwd.setSelection(et_passwd.getText().toString().length());
                    showPassword = !showPassword;
                } else {// 隐藏密码
                    iv_showPassword.setImageDrawable(getResources().getDrawable(R.drawable.ic_remove_red_eye_grey_500_24dp));
                    et_passwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    et_passwd.setSelection(et_passwd.getText().toString().length());
                    showPassword = !showPassword;
                }
                break;

            case R.id.me_resetpassword_showNewPassword:
                if (showNewPassword) {// 显示密码
                    iv_showNewPassword.setImageDrawable(getResources().getDrawable(R.drawable.ic_vpn_key_grey_500_24dp));
                    et_newpasswd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    et_newpasswd.setSelection(et_newpasswd.getText().toString().length());
                    showNewPassword = !showNewPassword;
                } else {// 隐藏密码
                    iv_showNewPassword.setImageDrawable(getResources().getDrawable(R.drawable.ic_remove_red_eye_grey_500_24dp));
                    et_newpasswd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    et_newpasswd.setSelection(et_newpasswd.getText().toString().length());
                    showNewPassword = !showNewPassword;
                }
                break;
            case R.id.me_resetpassword_showReNewPassword:
                if (showReNewPassword) {// 显示密码
                    iv_showReNewPassword.setImageDrawable(getResources().getDrawable(R.drawable.ic_vpn_key_grey_500_24dp));
                    et_renewpasswd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    et_renewpasswd.setSelection(et_renewpasswd.getText().toString().length());
                    showReNewPassword = !showReNewPassword;
                } else {// 隐藏密码
                    iv_showReNewPassword.setImageDrawable(getResources().getDrawable(R.drawable.ic_remove_red_eye_grey_500_24dp));
                    et_renewpasswd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    et_renewpasswd.setSelection(et_renewpasswd.getText().toString().length());
                    showReNewPassword = !showReNewPassword;
                }
                break;
            case R.id.me_resetpassword_submit:

                break;
            default:
                break;
        }

    }
}
