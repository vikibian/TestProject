package com.neu.testimageload.glide.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.neu.testimageload.R;
import com.neu.testimageload.glide.adapter.GlideRecyclerviewAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GlideRecyclerviewActivity extends AppCompatActivity {

    @BindView(R.id.rv_glide)
    RecyclerView rvGlide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_recyclerview);
        ButterKnife.bind(this);

        initData();
    }

    private void initData() {
        // 初始化Recyclerview
        GlideRecyclerviewAdapter glideRecyclerviewAdapter = new GlideRecyclerviewAdapter(this);
        rvGlide.setAdapter(glideRecyclerviewAdapter);
        rvGlide.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
    }
}
