package com.neu.testimageload.picasso.activity;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.neu.testimageload.R;
import com.neu.testimageload.picasso.adapter.PicassoTransformationsAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PicassoTransfromationsActivity extends AppCompatActivity {

    @BindView(R.id.lv_picasso_transfromations)
    ListView lvPicassoTransfromations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picasso_transfromations);
        ButterKnife.bind(this);

        initData();
    }

    private void initData() {
        List<String> data = new ArrayList<>();

        for (int i = 1; i<= 36; i++){
            data.add(i+"");
        }

        // 初始化listview
        PicassoTransformationsAdapter picassoTransformationsAdapter = new PicassoTransformationsAdapter(PicassoTransfromationsActivity.this,data);

        lvPicassoTransfromations.setAdapter(picassoTransformationsAdapter);
    }
}
