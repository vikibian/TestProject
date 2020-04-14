package com.neu.testimageload.ExpandableTextView;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.ms.square.android.expandabletextview.ExpandableTextView;
import com.neu.testimageload.R;

public class ExpandableTextViewActivity extends AppCompatActivity {

    private ExpandableTextView expTv1;
    private ExpandableTextView expTv2;
    private ExpandableTextView expTv3;
    private ExpandableTextView expTv4;
    private ExpandableTextView expTv5;
    private ExpandableTextView expTv6;
    private ExpandableTextView expTv7;
    private ExpandableTextView expTv8;
    private ExpandableTextView expTv9;
    private ExpandableTextView expTv10;

    private jaydenxiao.com.expandabletextview.ExpandableTextView expTV11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_text_view);
        expTv1 = findViewById(R.id.sample1).findViewById(R.id.expand_text_view);
        expTv2 = findViewById(R.id.sample2).findViewById(R.id.expand_text_view);
        expTv3 = findViewById(R.id.sample3).findViewById(R.id.expand_text_view);
        expTv4 = findViewById(R.id.sample4).findViewById(R.id.expand_text_view);
        expTv5 = findViewById(R.id.sample5).findViewById(R.id.expand_text_view);
        expTv6 = findViewById(R.id.sample6).findViewById(R.id.expand_text_view);
        expTv7 = findViewById(R.id.sample7).findViewById(R.id.expand_text_view);
        expTv8 = findViewById(R.id.sample8).findViewById(R.id.expand_text_view);
        expTv9 = findViewById(R.id.sample9).findViewById(R.id.expand_text_view);
        expTv10 = findViewById(R.id.sample10).findViewById(R.id.expand_text_view);
        expTV11 = findViewById(R.id.sample11).findViewById(R.id.etv);

        expTv1.setText("法律法规：1.设置安全管理机构单位应配备专职安全管理员； 2.符合下列条件之一的单位，应配备专职安全管理员： （1）使用额定工作压力大于或者等于2.5MPa锅炉的； （2）使用5台以上（含5台）第Ⅲ类固定式压力容器的； （3）从事移动式压力容器或者气瓶充装的； （4）使用10公里以上（含10公里）工业管道的； （5）使用移动式压力容器，或者客运拖牵索道，或者大型游乐 设施的； （6）使用各类特种设备（不含气瓶）总量20台以上（含20 台）的； 3.除上述规定以外的单位可以配备兼职安全管理员，也可以委 托具有特种设备安全管理人员资格的人员负责使用管理。" );
        expTv2.setText("法律法规：TSG08-2017 第2.4.2条 《特设法》第 八十六条。");
        expTv3.setText("法律法规：1.采购、使用取得许可生产（含设计、制造、安装改造、修 理），并且经检验合格的特种设备； 2.不得采购超过设计使用年限的特种设备；3.禁止使用国家明令淘汰的特种设备； 4.禁止使用报废的特种设备。");
        expTv4.setText("法律法规：TSG08-2017 第2.4.2条 《特设法》第 八十六条。");
        expTv5.setText("法律法规：1.设置安全管理机构单位应配备专职安全管理员； 2.符合下列条件之一的单位，应配备专职安全管理员： （1）使用额定工作压力大于或者等于2.5MPa锅炉的； （2）使用5台以上（含5台）第Ⅲ类固定式压力容器的； （3）从事移动式压力容器或者气瓶充装的； （4）使用10公里以上（含10公里）工业管道的； （5）使用移动式压力容器，或者客运拖牵索道，或者大型游乐 设施的； （6）使用各类特种设备（不含气瓶）总量20台以上（含20 台）的； 3.除上述规定以外的单位可以配备兼职安全管理员，也可以委 托具有特种设备安全管理人员资格的人员负责使用管理。" );
        expTv6.setText("法律法规：TSG08-2017 第2.4.2条 《特设法》第 八十六条。");
        expTv7.setText("法律法规：1.采购、使用取得许可生产（含设计、制造、安装改造、修 理），并且经检验合格的特种设备； 2.不得采购超过设计使用年限的特种设备；3.禁止使用国家明令淘汰的特种设备； 4.禁止使用报废的特种设备。");
        expTv8.setText("法律法规：TSG08-2017 第2.4.2条 《特设法》第 八十六条。");
        expTv9.setText("法律法规：1.采购、使用取得许可生产（含设计、制造、安装改造、修 理），并且经检验合格的特种设备； 2.不得采购超过设计使用年限的特种设备；3.禁止使用国家明令淘汰的特种设备； 4.禁止使用报废的特种设备。");
        expTv10.setText("法律法规：TSG08-2017 第2.4.2条 《特设法》第 八十六条。");
        expTV11.setText("法律法规：1.设置安全管理机构单位应配备专职安全管理员； 2.符合下列条件之一的单位，应配备专职安全管理员： （1）使用额定工作压力大于或者等于2.5MPa锅炉的； （2）使用5台以上（含5台）第Ⅲ类固定式压力容器的； （3）从事移动式压力容器或者气瓶充装的； （4）使用10公里以上（含10公里）工业管道的； （5）使用移动式压力容器，或者客运拖牵索道，或者大型游乐 设施的； （6）使用各类特种设备（不含气瓶）总量20台以上（含20 台）的； 3.除上述规定以外的单位可以配备兼职安全管理员，也可以委 托具有特种设备安全管理人员资格的人员负责使用管理。");


        expTv1.setOnExpandStateChangeListener(new ExpandableTextView.OnExpandStateChangeListener() {
            @Override
            public void onExpandStateChanged(TextView textView, boolean isExpanded) {
                Toast.makeText(getApplicationContext(), isExpanded ? "Expanded" : "Collapsed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
