package com.neu.testimageload.photoandvideo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.neu.testimageload.QMUITAB.ViewPagerActivity;
import com.neu.testimageload.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SecondActivity extends AppCompatActivity {

    @BindView(R.id.button_viewpager)
    Button button_viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.button_viewpager)
    void setButton_viewpager(View view ){
        startActivity(new Intent(SecondActivity.this, ViewPagerActivity.class));
    }
}
