package com.neu.testimageload.trillgates;

import android.view.View;
import android.view.ViewGroup;

import com.neu.testimageload.R;

/**
 * created by Viki on 2020/5/7
 * system login name : lg
 * created time : 23:19
 * email : 710256138@qq.com
 */
public class StaggerAdapter extends RecyclerViewBaseAdapter{
    @Override
    protected View getSubView(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item_grid_view,null);
        return view;
    }
}
