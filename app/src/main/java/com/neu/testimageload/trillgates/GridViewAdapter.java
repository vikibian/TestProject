package com.neu.testimageload.trillgates;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.neu.testimageload.R;

/**
 * created by Viki on 2020/5/7
 * system login name : lg
 * created time : 22:42
 * email : 710256138@qq.com
 */
//public class GridViewAdapter extends RecyclerView.Adapter<GridViewAdapter.InnerHolder> {
//
//    @Override
//    public InnerHolder onCreateViewHolder( ViewGroup parent, int viewType) {
//        View view = View.inflate(parent.getContext(), R.layout.item_grid_view, null);
//        return new InnerHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder( InnerHolder holder, int position) {
//        holder.setData();
//    }
//
//    @Override
//    public int getItemCount() {
////        if (mData != null){
////            return mData.get(position);
////        }
//        return 0;
//    }
//
//    public class InnerHolder extends RecyclerView.ViewHolder{
//        public InnerHolder( View itemView) {
//            super(itemView);
//            TextView textView = itemView.findViewById(R.id.grid_view_title);
//            ImageView imageView = itemView.findViewById(R.id.grid_view_icon);
//        }
//
//        public void setData() {
//
//        }
//    }
//}

public class GridViewAdapter extends RecyclerViewBaseAdapter {


    @Override
    protected View getSubView(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item_grid_view,null);
        return view;
    }
}
