package com.neu.testimageload.qmListview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.neu.testimageload.R;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;

import java.io.File;

public class QMListviewActivity extends AppCompatActivity {

    private QMUIGroupListView groupListView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qmlistview);

        groupListView = new QMUIGroupListView(getApplicationContext());
        init();
        initGroupListview();
    }

    private void initGroupListview() {
        QMUICommonListItemView Item0 = groupListView.createItemView("个人信息");
        Item0.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        Item0.setTag(1);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;

                if(v instanceof QMUIGroupListView){
                    switch ((int)v.getTag()){
                        case 1:
                            Toast.makeText(QMListviewActivity.this, "click", Toast.LENGTH_SHORT).show();
                            //startActivity(intent);
                            break;

                    }
                }
            }
        };



        QMUIGroupListView.newSection(getApplicationContext())
                .setUseDefaultTitleIfNone(false) //默认标题内容
                .setUseTitleViewForSectionSpace(false) //取消标题显示
//                .setTitle("Section 2: 自定义右侧 View/红点/new 提示")
//                .setLeftIconSize(size, ViewGroup.LayoutParams.WRAP_CONTENT)
                .addItemView(Item0, onClickListener)
//                .addItemView(Item1, this)
//                .addItemView(Item2, this)
//                .addItemView(Item3, this)
//                .addItemView(Item4, this)
//                .addItemView(Item5, this)
                //   .setOnlyShowStartEndSeparator(true)
                .addTo(groupListView);
    }

    private void init() {
        groupListView = findViewById(R.id.me_fragment_ListView);

    }
}
