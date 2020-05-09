package com.neu.testimageload.trillgates;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * created by Viki on 2020/5/7
 * system login name : lg
 * created time : 22:55
 * email : 710256138@qq.com
 * 将adapter复用
 */
//public abstract class RecyclerViewBaseAdapter extends RecyclerView.Adapter<RecyclerViewBaseAdapter.InnerHolder> {
//
//    private OnItemClickListener onItemClickListener;
//
//    public RecyclerViewBaseAdapter(){
//        //传入数据
//    }
//
//    @NonNull
//    @Override
//    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = getSubView(parent,viewType);
//        return new InnerHolder(view);
//    }
//
//    protected abstract View getSubView(ViewGroup parent, int viewType);
//
//    @Override
//    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
//        holder.setData(position);
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
//    public void setOnItemClickListener(OnItemClickListener listener){
//        //设置一个监听，其实，就是设置一个接口，一个回调接口
//        onItemClickListener = listener;
//    }
//
//    /**
//     * 编写接口的步骤
//     * 1：创建这个接口
//     * 2：定义接口内部的方法
//     * 3：外部设置接口（其实是外部实现）
//     * 4：接口方法的调用
//     */
//    public interface OnItemClickListener{
//        void onItemClick(int position);
//    }
//
//    public class InnerHolder extends RecyclerView.ViewHolder{
//        private int position;
//
//        public InnerHolder(@NonNull View itemView) {
//            super(itemView);
//            //每一个item的布局中的id 需要相同才可以
//
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (onItemClickListener != null){
//                        onItemClickListener.onItemClick(position);
//                    }
//                }
//            });
//        }
//
//        public void setData(int position) {
//            this.position = position;
//        }
//    }
//}

public abstract class RecyclerViewBaseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private OnItemClickListener onItemClickListener;


    public RecyclerViewBaseAdapter(){
        //传入数据
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = getSubView(parent,viewType);
        return new InnerHolder(view);
    }

    protected abstract View getSubView(ViewGroup parent, int viewType);

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((InnerHolder)holder).setData(position);
    }

    @Override
    public int getItemCount() {
//        if (mData != null){
//            return mData.get(position);
//        }
        return 0;
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        //设置一个监听，其实，就是设置一个接口，一个回调接口
        onItemClickListener = listener;
    }





    /**
     * 编写接口的步骤
     * 1：创建这个接口
     * 2：定义接口内部的方法
     * 3：外部设置接口（其实是外部实现）
     * 4：接口方法的调用
     */
    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public class InnerHolder extends RecyclerView.ViewHolder{
        private int position;

        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            //每一个item的布局中的id 需要相同才可以

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null){
                        onItemClickListener.onItemClick(position);
                    }
                }
            });
        }

        public void setData(int position) {
            this.position = position;
        }
    }
}
