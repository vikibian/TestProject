package com.neu.testimageload.listitem.zhenggai;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.neu.testimageload.R;

/**
 * created by Viki on 2020/3/14
 * system login name : lg
 * created time : 16:38
 * email : 710256138@qq.com
 */
public class RectifyListItemAdapter extends BaseAdapter {
    private static final String TAG = "RectifyListItemAdapter";
    private Context context;
    private String[] num;

    public RectifyListItemAdapter(Context context,String[] num){
        this.context = context;
        this.num = num;
    }

    @Override
    public int getCount() {
        return num.length;
    }

    @Override
    public Object getItem(int position) {
        return num[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHoler viewHoler;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.test_rectify_list_item,null);
            viewHoler = new ViewHoler(convertView);

            convertView.setTag(viewHoler);
        }else {
            viewHoler = (ViewHoler) convertView.getTag();
        }

        if (position%2 ==0){
            viewHoler.unrectify.setBackgroundColor(context.getResources().getColor(R.color.green));
            viewHoler.rectify_status.setText("已整改");
        }
        viewHoler.num1.setText(num[position]);
        viewHoler.num2.setText(num[position]+1);
        return convertView;
    }

    class ViewHoler{
        TextView question;
        TextView item;
        LinearLayout unrectify;
        TextView num1;
        TextView num2;
        TextView rectify_status;

        public ViewHoler(View view){
            question = view.findViewById(R.id.rectify_item_text_question);
            item = view.findViewById(R.id.rectify_item_text_item);
            unrectify = view.findViewById(R.id.rectity_linearlayout);
            num1 = view.findViewById(R.id.rectify_item_text_num1);
            num2 = view.findViewById(R.id.rectify_item_text_num2);
            rectify_status = view.findViewById(R.id.rectify_linearlayout_textview);
        }
    }
}
