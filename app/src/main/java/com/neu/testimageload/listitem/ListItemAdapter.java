package com.neu.testimageload.listitem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.neu.testimageload.R;

/**
 * created by Viki on 2020/3/13
 * system login name : lg
 * created time : 13:53
 * email : 710256138@qq.com
 */
public class ListItemAdapter extends BaseAdapter {
    private static String TAG = "ListItemAdapter";
    private Context context;
    private String[] num;

    public ListItemAdapter(Context context,String[] num){
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
        ViewHolder viewHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.test_list_item,null);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.leftnum.setText(num[position]);

        if (position%2 == 0){
            viewHolder.warning.setVisibility(View.INVISIBLE);
            viewHolder.check.setVisibility(View.VISIBLE);
        }else {
            viewHolder.warning.setVisibility(View.VISIBLE);
            viewHolder.check.setVisibility(View.INVISIBLE);
        }

        return convertView;
    }

    class ViewHolder{
        TextView leftnum;
        ImageView check;
        ImageView warning;

        public ViewHolder(View view){
            leftnum = view.findViewById(R.id.detction_item_text_leftnum);
            check = view.findViewById(R.id.detction_item_image_check);
            warning = view.findViewById(R.id.detction_item_image_alert);
        }
    }
}
