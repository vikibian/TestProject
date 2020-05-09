package com.neu.testimageload.trillgates;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.neu.testimageload.R;

import java.util.ArrayList;
import java.util.List;

/**
 * created by Viki on 2020/5/6
 * system login name : lg
 * created time : 23:23
 * email : 710256138@qq.com
 *
 * 总结：
 * 1:首先我们要有控件，这个RecycleView，需要添加相应的依赖包
 * 2：绑定id
 * 3：准备好数据
 * 4：设置布局管理器
 * 5：设置适配器
 *
 */
public class MainActivity extends Activity {
    private RecyclerView recyclerView;
    private boolean isVertical = false;
    private RecyclerViewBaseAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_recyclerview);
        recyclerView = findViewById(R.id.recycler_view);
        swipeRefreshLayout = findViewById(R.id.refrash_layout);
        initData();

        //初始化事件
        initListener();

        //设置默认的显示样式为ListView
        showList();

        //处理下拉刷新
        handleDownPullUpdate();
    }

    private void handleDownPullUpdate() {
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent,R.color.colorPrimary,R.color.colorPrimary);
        swipeRefreshLayout.setEnabled(true);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //在这里去执行刷新数据的操作
                /**
                 * 当我们在顶部，下拉的时候，这个方法会被触发，
                 * 但是，这个方法是MainThread是主线程，不可以去执行耗时操作
                 * 一般来说，我们去请求数据在开一个线程去获取
                 * //这里演示的话，直接添加一条数据
                 */

                //设置数据

                //更新UI
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //这里要做两件事，一件是让刷新停止，另一件是要更新列表
                        adapter.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                },3000);
            }
        });
    }

    private void initview() {

    }

    private void initListener() {
        adapter.setOnItemClickListener(new RecyclerViewBaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });

        if (adapter instanceof ListViewAdapter){
            ((ListViewAdapter) adapter).setOnRefreshListener(new ListViewAdapter.OnRefreshListener() {
                @Override
                public void onUpPullRefrsh(ListViewAdapter.LoadMoreHolder loadMoreHolder) {
                    //这里要去加载更多数据，需要在子线程完成，这里仅做演示
                    //数理数据

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //这里需要做两件事，一件事实刷新停止，一件事更新列表
                            adapter.notifyDataSetChanged();
                            loadMoreHolder.update(loadMoreHolder.LOADER_STATE_NORMAL);
                        }
                    },3000);
                }
            });
        }
    }

    private void initData() {
        //设置数据  省略
        showList();

    }

    /**
     * 这个方法用于实现ListView 的效果
     */
    private void showList(){
        //RecycleView需要设置样式，其实就是设置布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置布局管理器来控制
        //设置水平还是垂直
        layoutManager.setOrientation(isVertical ? RecyclerView.VERTICAL:RecyclerView.HORIZONTAL);
        //设置标准（正向）还是反向
        layoutManager.setReverseLayout(true);

        recyclerView.setLayoutManager(layoutManager);

        List<String> stringList = new ArrayList<>();
        //创建适配器
        adapter = new ListViewAdapter(stringList);
        recyclerView.setAdapter(adapter);
        initListener();
    }

    private void showGrid(boolean isVertical, boolean isReverse){
        //创建布局管理器
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        //设置水平还是垂直
        layoutManager.setOrientation(isVertical?RecyclerView.VERTICAL:LinearLayoutManager.HORIZONTAL);
        //设置标准（正向）还是反向
        layoutManager.setReverseLayout(isReverse);

        //设置布局管理器
        recyclerView.setLayoutManager(layoutManager);

        //创建适配器
        adapter = new GridViewAdapter();
        recyclerView.setAdapter(adapter);
        initListener();
    }

    private void showStagger(boolean isVertical,boolean isReverse){
        //创建布局管理器
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, isVertical?StaggeredGridLayoutManager.VERTICAL:StaggeredGridLayoutManager.HORIZONTAL);

        //设置标准（正向）还是反向
        layoutManager.setReverseLayout(isReverse);

        //设置布局管理器
        recyclerView.setLayoutManager(layoutManager);

        //创建适配器
        adapter = new StaggerAdapter();
        recyclerView.setAdapter(adapter);
        initListener();
    }
}
