package com.neu.testimageload.trillgates;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.neu.testimageload.R;
import com.sun.mail.iap.Literal;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * created by Viki on 2020/5/8
 * system login name : lg
 * created time : 9:09
 * email : 710256138@qq.com
 */
public class MoreTypeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<MoreTypeBean> mDatas;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_type_activity);

        recyclerView = this.findViewById(R.id.more_type_list);

        //准备数据
        mDatas = new ArrayList<>();

        initDatas();
        //创建和设置布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //创建适配器
        MoreTypeAdapter adapter = new MoreTypeAdapter();
        recyclerView.setAdapter(adapter);
    }

    private void initDatas() {
        Random random = new Random();
        for (int i=0;i<20;i++){
            MoreTypeBean data = new MoreTypeBean();
            data.pic = 1;
            data.type = random.nextInt(3);
            mDatas.add(data);
        }
    }
}
