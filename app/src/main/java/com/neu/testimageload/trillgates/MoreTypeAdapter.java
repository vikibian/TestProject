package com.neu.testimageload.trillgates;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.neu.testimageload.R;

import java.util.ArrayList;
import java.util.List;

/**
 * created by Viki on 2020/5/8
 * system login name : lg
 * created time : 9:19
 * email : 710256138@qq.com
 */
public class MoreTypeAdapter extends RecyclerView.Adapter {

    //设置三个常量标识，因为有三个类型
    public static final int TYPE_FULL_IMAGE =0;
    public static final int TYPE_LEFT_IMAGE =1;
    public static final int TYPE_THREE_IMAGE =2;

    private final List<MoreTypeBean> mDatas = new ArrayList<>();
    public MoreTypeAdapter(){
        //设置数据
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /**
         * 根据viewType来创建条目，这样条目就可以不一样了
         */
        View view;
        //下面的view是为了方便随便设置的
        if (viewType == TYPE_FULL_IMAGE){
            view = View.inflate(parent.getContext(), R.layout.item_grid_view,null);
            return new FullImageHolder(view);
        }else if (viewType == TYPE_LEFT_IMAGE){
            view =View.inflate(parent.getContext(), R.layout.item_grid_view,null);
            return new LeftImageHolder(view);
        }else {
            view = View.inflate(parent.getContext(), R.layout.item_grid_view,null);
            return  new ThreeImageHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    //我们需要重写一个方法，这个方法时根据条件来返回条目类型的
    @Override
    public int getItemViewType(int position) {
        MoreTypeBean moreTypeBean = mDatas.get(position);
        if (moreTypeBean.type == 0){
            return TYPE_FULL_IMAGE;
        }else if (moreTypeBean.type == 1){
            return TYPE_LEFT_IMAGE;
        }else {
            return TYPE_THREE_IMAGE;
        }
    }

    private class FullImageHolder extends RecyclerView.ViewHolder{

        public FullImageHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    private class ThreeImageHolder extends RecyclerView.ViewHolder{

        public ThreeImageHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    private class LeftImageHolder extends RecyclerView.ViewHolder{

        public LeftImageHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
