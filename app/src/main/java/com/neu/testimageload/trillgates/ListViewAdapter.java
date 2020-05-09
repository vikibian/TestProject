package com.neu.testimageload.trillgates;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.neu.testimageload.R;

import java.util.List;

/**
 * created by Viki on 2020/5/6
 * system login name : lg
 * created time : 23:14
 * email : 710256138@qq.com
 */
//public class ListViewAdapter extends RecyclerView.Adapter<ListViewAdapter.InnerHolder> {
//    /**
//     * 这个方法用于创建条目
//     * @param parent
//     * @param viewType
//     * @return
//     */
//    @Override
//    public InnerHolder onCreateViewHolder( ViewGroup parent, int viewType) {
//        //传进入的这个view就是条目的界面
//        //两个步骤
//        //1：拿到view
//        //2：创建InnerHolder
//        //item的布局可以用CardView 来包括  CardView做父布局 子布局放一个RelativeLayout
//        View view = View.inflate(parent.getContext(), android.R.layout.simple_list_item_1,null);
//
//        return new InnerHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder( InnerHolder holder, int position) {
//        //在这里设置数据
//        holder.setData();
//    }
//
//    @Override
//    public int getItemCount() {
//        return 0;
//    }
//
//    public class InnerHolder extends RecyclerView.ViewHolder{
//        private ImageView imageview;
//        private TextView textView;
//
//        public InnerHolder(@NonNull View itemView) {
//            super(itemView);
//            //绑定id
//        }
//
//        /**
//         * 用于设置数据
//         */
//        public void setData() {
//        }
//    }
//}


public class ListViewAdapter extends RecyclerViewBaseAdapter {
    //普通条目类型
    public static final int TYPE_NORMAL = 0;
    //加载更多
    public static final int TYPE_LOADER_MORE = 1;
    private OnRefreshListener onRefreshListener;


    public ListViewAdapter(List<String> datas){
//        super(datas);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = getSubView(parent,viewType);
        if (viewType == TYPE_NORMAL){
            return new InnerHolder(view);
        }else {
            //这个加载更多
           LoadMoreHolder loadMoreHolder = new LoadMoreHolder(view);
           loadMoreHolder.update(LoadMoreHolder.LOADER_STATE_LOADING);
           return loadMoreHolder;
        }
    }

    @Override
    protected View getSubView(ViewGroup parent, int viewType) {
        View view ;
        //根据类型来创建view
        if (viewType == TYPE_NORMAL){
            view = View.inflate(parent.getContext(), R.layout.item_grid_view,null);
        }else {
            //这个加载更多
            view = View.inflate(parent.getContext(), R.layout.item_grid_view,null);
        }

        return view;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() -1){
            //最后一个则返回加载更多
            return TYPE_LOADER_MORE;
        }
        return TYPE_NORMAL;
    }
    /**
     * 设置刷新的监听接口
     */
    public void setOnRefreshListener(OnRefreshListener listener) {
        onRefreshListener = listener;
    }

    //定义接口
    public interface  OnRefreshListener{
        void onUpPullRefrsh(LoadMoreHolder loadMoreHolder);
    }

    public class LoadMoreHolder extends RecyclerView.ViewHolder{

        public static final int LOADER_STATE_LOADING = 0;
        public static final int LOADER_STATE_RELOAD = 1;
        public static final int LOADER_STATE_NORMAL = 2;

        private LinearLayout linearLayout;
        private TextView load;

        public LoadMoreHolder(@NonNull View itemView) {
            super(itemView);
            load.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //这里触发加载数据
                    startLoderMore();
                }
            });
        }

        public void update(int state){
            //重置控件状态
            switch (state){
                case LOADER_STATE_LOADING:
                    load.setVisibility(View.VISIBLE);
                    startLoderMore();
                    break;
                case LOADER_STATE_RELOAD:
                    linearLayout.setVisibility(View.VISIBLE);
                    break;
                case LOADER_STATE_NORMAL:
                    load.setVisibility(View.GONE);
                    linearLayout.setVisibility(View.GONE);
                    break;
            }
        }
        private void startLoderMore(){
            if (onRefreshListener!=null){
                onRefreshListener.onUpPullRefrsh(this);
            }
        }
    }

}