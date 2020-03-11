package com.neu.testimageload.glide;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.neu.testimageload.R;
import com.neu.testimageload.glide.Activity.GlideBaseActivity;
import com.neu.testimageload.glide.Activity.GlideRecyclerviewActivity;
import com.neu.testimageload.glide.Activity.GlideTranformationsActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GlideActivity extends AppCompatActivity {

    @BindView(R.id.bt_glide_base)
    Button btGlideBase;
    @BindView(R.id.bt_glide_recyclerview)
    Button btGlideRecyclerview;
    @BindView(R.id.bt_glide_tranfromations)
    Button btGlideTranfromations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {

    }

    @OnClick(R.id.bt_glide_base)
    void bt_glide_base_click(View view){
        Intent intent = new Intent(GlideActivity.this, GlideBaseActivity.class);
        startActivity(intent);
    }

    // 在Recyclerview中使用
    @OnClick(R.id.bt_glide_recyclerview)
    void bt_glide_recyclerview_click(View view){
        Intent intent = new Intent(GlideActivity.this, GlideRecyclerviewActivity.class);
        startActivity(intent);
    }

    // 图片变换
    @OnClick(R.id.bt_glide_tranfromations)
    void bt_glide_tranfromations_click(View view){
        Intent intent = new Intent(GlideActivity.this, GlideTranformationsActivity.class);

        startActivity(intent);
    }
}
