package com.neu.testimageload.picasso.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.neu.testimageload.R;
import com.neu.testimageload.picasso.Constants;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PicassoListviewAdapter extends BaseAdapter {
    private Context mContext;

    public PicassoListviewAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return Constants.IMAGES.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_picasso_listview,null);

            holder = new ViewHolder(convertView);

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        // 名称
        holder.name.setText("item"+(position+1));

        // 加载图片
        Picasso.get()
                .load(Constants.IMAGES[position])
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(holder.iv);

        return convertView;
    }

    class ViewHolder{
        @BindView(R.id.iv_picasso_item)
        ImageView iv;

        @BindView(R.id.tv_picasso_name)
        TextView name;

        public ViewHolder(View view) {
            ButterKnife.bind(this,view);
        }
    }
}
