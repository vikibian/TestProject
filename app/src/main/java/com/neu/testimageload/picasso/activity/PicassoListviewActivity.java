package com.neu.testimageload.picasso.activity;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.neu.testimageload.R;
import com.neu.testimageload.picasso.adapter.PicassoListviewAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PicassoListviewActivity extends AppCompatActivity {

    @BindView(R.id.lv_picasso)
    ListView lvPicasso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picasso_listview);
        ButterKnife.bind(this);

        initData();
    }

    private void initData() {
        // 初始化listview
        PicassoListviewAdapter picassoListviewAdapter = new PicassoListviewAdapter(this);

        lvPicasso.setAdapter(picassoListviewAdapter);
    }
}
