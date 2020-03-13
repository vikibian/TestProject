package com.neu.testimageload.multilevelTreeList;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.multilevel.treelist.TreeListViewAdapter;
import com.neu.testimageload.R;
import com.neu.testimageload.multilevelTreeList.adapter.SimpleTreeAdapter;

public class MultilevelTreeListActivity extends BaseActivity {
    private static String TAG = "MultilevelTreeListActivity";
    TreeListViewAdapter mAdapter;
    private ListView mTree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multilevel_tree_list);

        mTree = findViewById(R.id.lv_tree);
        //第一个参数  ListView
        //第二个参数  上下文
        //第三个参数  数据集
        //第四个参数  默认展开层级数 0为不展开
        //第五个参数  展开的图标
        //第六个参数  闭合的图标
        mAdapter = new SimpleTreeAdapter(mTree, MultilevelTreeListActivity.this,
                mDatas, 1,R.mipmap.tree_ex,R.mipmap.tree_ec);
        mTree.setAdapter(mAdapter);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        for (int i=0;i<mDatas.size();i++){
            Log.e(TAG,"name: "+mDatas.get(i).getName()+" check: "+mDatas.get(i).isChecked());
        }
    }
}
