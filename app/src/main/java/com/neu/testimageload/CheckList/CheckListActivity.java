package com.neu.testimageload.CheckList;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.neu.testimageload.R;

public class CheckListActivity extends AppCompatActivity {
    private static final String TAG = "CheckListActivity";
    private ListView lv_check_list;
    private static final String[] data = { "北京", "上海", "武汉", "广州", "西安", "南京", "合肥","上海", "武汉", "广州", "西安", "南京", "合肥" };
    private CheckAdapter checkAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_list);
        lv_check_list = findViewById(R.id.card_listView);
        checkAdapter = new CheckAdapter();
        lv_check_list.setAdapter(checkAdapter);

        lv_check_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),""+position,Toast.LENGTH_SHORT).show();
            }
        });

    }


    class CheckAdapter extends BaseAdapter {


        //返回集合数据数量
        @Override
        public int getCount() {
            return data.length;
        }

        //返回指定下标的数据对象
        @Override
        public Object getItem(int position) {
            return data[position];
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            //如果没有复用的
            if(convertView == null){
                //加载item的布局
                convertView = View.inflate(getApplicationContext(), R.layout.test_check_list, null);

            }

            //根据position设置对应数据
            //获得当前数据对象

//            TextView tv_check_device = convertView.findViewById(R.id.tv_check_device);
//            TextView tv_check_address = convertView.findViewById(R.id.tv_check_address);
//            TextView tv_check_endtime = convertView.findViewById(R.id.tv_check_endtime);
//            TextView tv_issave_device = convertView.findViewById(R.id.tv_issave_device);
//            LinearLayout ll_check_bg = convertView.findViewById(R.id.ll_check_bg);
            TextView check_list_name = convertView.findViewById(R.id.check_list_devname);
            CircleRelativeLayout check_list_imageview = convertView.findViewById(R.id.check_list_imagebutton);

            check_list_name.setText(data[position]);

            int color = 0xFFFFFF00;
            if(position>15){
                color = 0xC606F600;
            }
            else{
                color = 0xC6F00000-(0x000F0000-0x00000F00)*position;

            }
//            check_list_imageview.setImageResource(R.color.red);
            check_list_imageview.setColor(color);
            //ll_check_bg.setBackgroundColor(color);
//            tv_check_device.setTextColor(color);
//            tv_check_address.setTextColor(color);
//            tv_check_endtime.setTextColor(color);
//
//            tv_check_device.setText(task.getDEVID());
//            tv_check_address.setText(task.getPLACE());
//            tv_check_endtime.setText(task.getDEADLINE());
            return convertView;
        }
    }
}
