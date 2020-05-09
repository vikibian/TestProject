package com.neu.testimageload.QMUITAB;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.neu.testimageload.R;
import com.qmuiteam.qmui.util.QMUIResHelper;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.tab.QMUITab;
import com.qmuiteam.qmui.widget.tab.QMUITabBuilder;
import com.qmuiteam.qmui.widget.tab.QMUITabSegment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.mob.MobSDK.getContext;

public class QMUITABActivity extends AppCompatActivity {

    private static final String TAG = "QMUITABActivity";
    @BindView(R.id.tab_segment)
    QMUITabSegment tabSegment;
    @BindView(R.id.contentViewPager)
    ViewPager mContentViewPager;
    private List<Fragment> mFragments;
    private List<View> viewList = new ArrayList<>();
    private View view1,view2;

    private Map<ContentPage, View> mPageMap = new HashMap<>();
    private ContentPage mDestPage = ContentPage.Item1;
    private PagerAdapter mPagerApadter = new PagerAdapter() {
        @Override
        public int getCount() {
            return ContentPage.SIZE;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
//            ContentPage page = ContentPage.getPage(position);
//            View view = getPageView(page);
//            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
//                ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT
//            );
//            container.addView(view,params);
            View view;

            if (position == 0){
                view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_qmuitab_test1, null);
            }else {
                view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_qmuitab_test2, null);
            }
            getSupportFragmentManager().beginTransaction().add(R.id.data_main_container, mFragments.get(position), null).commit();
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qmuitab);

        ButterKnife.bind(this);

        LayoutInflater inflater=getLayoutInflater();
        view1 = inflater.inflate(R.layout.fragment_qmuitab_test1, null);
        view2 = inflater.inflate(R.layout.fragment_qmuitab_test2,null);

        viewList = new ArrayList<View>();// 将要分页显示的View装入数组中
        viewList.add(view1);
        viewList.add(view2);

        PagerAdapter pagerAdapter = new PagerAdapter() {
            @Override
            public int getCount() {
                return viewList.size();
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view == object;
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                container.addView(viewList.get(position));
                return viewList.get(position);
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                container.removeView(viewList.get(position));
            }
        };


        mFragments = new ArrayList<>();
        mFragments.add(new QMUITabTest1Fragment());
        mFragments.add(new QMUITabTest2Fragment());

        int normalColor = QMUIResHelper.getAttrColor(this, R.attr.qmui_config_color_gray_6);
        int selectColor = QMUIResHelper.getAttrColor(this, R.attr.qmui_config_color_blue);

//        tabSegment.setOuterNormalColor(normalColor);
//        tabSegment.setBorderColor(selectColor);

        QMUITabBuilder qmuiTabBuilder = tabSegment.tabBuilder();
        QMUITab tab = qmuiTabBuilder.setText("12").build(this);

        tabSegment.addTab(qmuiTabBuilder.setText("测试1").build(this));
        tabSegment.addTab(tab);
        tabSegment.setMode(QMUITabSegment.MODE_FIXED);
        tabSegment.selectTab(0);
//        mContentViewPager.setAdapter(mPagerApadter);
//        mContentViewPager.setCurrentItem(mDestPage.getPosition(),false);
//        tabSegment.setupWithViewPager(mContentViewPager,false);

        mContentViewPager.setAdapter(pagerAdapter);
        mContentViewPager.setCurrentItem(0);
        tabSegment.setupWithViewPager(mContentViewPager,false);




//        getSupportFragmentManager().beginTransaction().add(R.id.data_main_container, mFragments.get(0), null).commit();

        tabSegment.addOnTabSelectedListener(new QMUITabSegment.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int index) {
                Log.e(TAG,"onTabSelected:"+index);
//                getSupportFragmentManager().beginTransaction().replace(R.id.data_main_container, mFragments.get(index), null).commit();
            }

            @Override
            public void onTabUnselected(int index) {
                Log.e(TAG,"onTabUnselected:"+index);
            }

            @Override
            public void onTabReselected(int index) {
                Log.e(TAG,"onTabReselected:"+index);
            }

            @Override
            public void onDoubleTap(int index) {
                Log.e(TAG,"onDoubleTap:"+index);
                tabSegment.clearSignCountView(index);
            }
        });
    }

    private View getPageView(ContentPage page){
        View view = mPageMap.get(page);
        if (view == null){
            TextView textView = new TextView(this);
            textView.setGravity(Gravity.CENTER);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
            textView.setTextColor(ContextCompat.getColor(this,R.color.red));

            if (page == ContentPage.Item1){
                textView.setText("第一个");
//                view = LayoutInflater.inflate(R.layout.fragment_qmuitab_test1, null);
            }else if (page == ContentPage.Item2){
                textView.setText("第二个");
//                view = LayoutInflater.inflate(R.layout.fragment_qmuitab_test2, null);
            }
            view = textView;
            mPageMap.put(page,view);
        }
        return view;
    }

    public enum ContentPage {
        Item1(0),
        Item2(1);
        public static final int SIZE = 2;
        private final int position;

        ContentPage(int pos) {
            position = pos;
        }

        public static ContentPage getPage(int position) {
            switch (position) {
                case 0:
                    return Item1;
                case 1:
                    return Item2;
                default:
                    return Item1;
            }
        }

        public int getPosition() {
            return position;
        }
    }
}
