package com.neu.testimageload.glide.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.neu.testimageload.R;
import com.neu.testimageload.glide.adapter.GlideTranformationsAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GlideTranformationsActivity extends AppCompatActivity {

    @BindView(R.id.rv_glide_transformations)
    RecyclerView rvGlideTransformations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_tranformations);
        ButterKnife.bind(this);

        initData();
    }

    private void initData() {
        // 初始化Recyclerview
        GlideTranformationsAdapter glideTranformationsAdapter = new GlideTranformationsAdapter(this);
        rvGlideTransformations.setAdapter(glideTranformationsAdapter);
        rvGlideTransformations.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
    }
}
