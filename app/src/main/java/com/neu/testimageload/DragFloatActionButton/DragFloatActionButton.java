package com.neu.testimageload.DragFloatActionButton;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.view.ViewGroup;

import androidx.core.view.ViewConfigurationCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * created by Viki on 2020/4/17
 * system login name : lg
 * created time : 22:00
 * email : 710256138@qq.com
 */
public class DragFloatActionButton extends FloatingActionButton {
    private static final String TAG = "DragFloatActionButton";
    private int screenWidth;
    private int screenHeight;
    private int screenWidthHalf;
    private int statusHeight;
    private int virtualHeight;

    private int parentWidth;
    private int parentHeight;
    private ViewGroup parent;
    private int lastX;
    private int lastY;

    private boolean isDrag;
    private int slop;

    public DragFloatActionButton(Context context) {
        super(context);
        init(context);
    }

    public DragFloatActionButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DragFloatActionButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
        slop= ViewConfigurationCompat.getScaledPagingTouchSlop(ViewConfiguration.get(context));
        Log.e(TAG, "init:"+slop);
    }

    private void init(Context context) {
//        screenWidth = ScreenUtils.getScreenWidth(getContext());
//        screenWidthHalf = screenWidth / 2;
//        screenHeight = ScreenUtils.getScreenHeight(getContext());
//        statusHeight = ScreenUtils.getStatusHeight(getContext());
//        virtualHeight=ScreenUtils.getVirtualBarHeigh(getContext());
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int rawX = (int) event.getRawX();
        int rawY = (int) event.getRawY();
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                isDrag = false;
                getParent().requestDisallowInterceptTouchEvent(true);
                lastX = rawX;
                lastY = rawY;
                if(getParent()!=null){
                    parent = (ViewGroup) getParent();
                    parentHeight = parent.getHeight();
                    parentWidth = parent.getWidth();
                }
//                Log.e("down---->", "getX=" + getX() + "；screenWidthHalf=" + screenWidthHalf);
                break;
            case MotionEvent.ACTION_MOVE:
                if(parentHeight<=0||parentWidth<=0){
                    //如果不存在父类的宽高则无法拖动，默认直接返回false
                    isDrag=false;
                    break;
                }

                //计算手指移动了多少
                int dx = rawX - lastX;
                int dy = rawY - lastY;
                //这里修复一些手机无法触发点击事件的问题
                int distance= (int) Math.sqrt(dx*dx+dy*dy);
                Log.e("distance---->",distance+"");
//                if(distance<3){//给个容错范围，不然有部分手机还是无法点击
//                    isDrag=false;
//                    break;
//                }
                //此处稍微增加一些移动的偏移量，防止手指抖动，误判为移动无法触发点击时间
                if(distance==0||distance<=slop){
                    isDrag=false;
                    break;
                }
                isDrag = true;
                float x = getX() + dx;
                float y = getY() + dy;

                //检测是否到达边缘 左上右下
                x = x < 0 ? 0 : x > parentWidth - getWidth() ? parentWidth - getWidth() : x ;
                y = getY() < 0 ? 0 : getY() + getHeight() > parentHeight ? parentHeight - getHeight() : y ;
                setX(x);
                setY(y);
                lastX=rawX;
                lastY=rawY;

//                //检测是否到达边缘 左上右下
//                x = x < 0 ? 0 : x > screenWidth - getWidth() ? screenWidth - getWidth() : x;
//                // y = y < statusHeight ? statusHeight : (y + getHeight() >= screenHeight ? screenHeight - getHeight() : y);
//                if (y<0){
//                    y=0;
//                }
//                if (y>screenHeight-statusHeight-getHeight()){
//                    y=screenHeight-statusHeight-getHeight();
//                }
//                setX(x);
//                setY(y);
//
//                lastX = rawX;
//                lastY = rawY;
//                Log.e("move---->", "getX=" + getX() + "；screenWidthHalf=" + screenWidthHalf + " " + isDrag+"  statusHeight="+statusHeight+ " virtualHeight"+virtualHeight+ " screenHeight"+ screenHeight+"  getHeight="+getHeight()+" y"+y);
                break;
            case MotionEvent.ACTION_UP:
                if (isDrag) {
                    //恢复按压效果
                    setPressed(false);
//                    Log.e("ACTION_UP---->", "getX=" + getX() + "；screenWidthHalf=" + screenWidthHalf);
//                    if (rawX >= screenWidthHalf) {
//                        animate().setInterpolator(new DecelerateInterpolator())
//                                .setDuration(500)
//                                .xBy(screenWidth - getWidth() - getX())
//                                .start();
//                    } else {
//                        ObjectAnimator oa = ObjectAnimator.ofFloat(this, "x", getX(), 0);
//                        oa.setInterpolator(new DecelerateInterpolator());
//                        oa.setDuration(500);
//                        oa.start();
//                    }
                }
                Log.e("up---->",isDrag+"");
                break;
        }
        //如果是拖拽则消耗事件，否则正常传递即可。
        return isDrag || super.onTouchEvent(event);

    }

    public boolean isDrag() {
        return isDrag;
    }

    //    private int parentHeight;
//    private int parentWidth;
//    private int slop;
//
//    public DragFloatActionButton(Context context) {
//        this(context,null);
//    }
//
//    public DragFloatActionButton(Context context, AttributeSet attrs) {
//        this(context, attrs,0);
//
//    }
//
//    public DragFloatActionButton(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        slop= ViewConfigurationCompat.getScaledPagingTouchSlop(ViewConfiguration.get(context));
//    }
//
//
//    private int lastX;
//    private int lastY;
//
//    private boolean isDrag;
//
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        int rawX = (int) event.getRawX();
//        int rawY = (int) event.getRawY();
//        switch (event.getAction() & MotionEvent.ACTION_MASK) {
//            case MotionEvent.ACTION_DOWN:
//                setPressed(true);
//                isDrag=false;
//                getParent().requestDisallowInterceptTouchEvent(true);
//                lastX=rawX;
//                lastY=rawY;
//                ViewGroup parent;
//                if(getParent()!=null){
//                    parent= (ViewGroup) getParent();
//                    parentHeight=parent.getHeight();
//                    parentWidth=parent.getWidth();
//                }
//                break;
//            case MotionEvent.ACTION_MOVE:
//                if(parentHeight<=0||parentWidth<=0){
//                    //如果不存在父类的宽高则无法拖动，默认直接返回false
//                    isDrag=false;
//                    break;
//                }
//                int dx=rawX-lastX;
//                int dy=rawY-lastY;
//                //这里修复一些华为手机无法触发点击事件
//                int distance= (int) Math.sqrt(dx*dx+dy*dy);
//                //此处稍微增加一些移动的偏移量，防止手指抖动，误判为移动无法触发点击时间
//                if(distance==0||distance<=slop){
//                    isDrag=false;
//                    break;
//                }
//                //程序到达此处一定是正在拖动了
//                isDrag=true;
//                float x=getX()+dx;
//                float y=getY()+dy;
//                //检测是否到达边缘 左上右下
//                x=x<0?0:x>parentWidth-getWidth()?parentWidth-getWidth():x;
//                y=getY()<0?0:getY()+getHeight()>parentHeight?parentHeight-getHeight():y;
//                setX(x);
//                setY(y);
//                lastX=rawX;
//                lastY=rawY;
//                break;
//            case MotionEvent.ACTION_UP:
//                if(isDrag()){
//                    //恢复按压效果
//                    setPressed(false);
//                }
//                welt(rawX);
//                break;
//        }
//        //如果是拖拽则消耗事件，否则正常传递即可。
//        return isDrag() || super.onTouchEvent(event);
//    }
//
//    private boolean isDrag(){
//        return isDrag;
//    }
//
//    private boolean isLeftSide(){
//        return getX()==0;
//    }
//    private boolean isRightSide(){
//        return getX()==parentWidth-getWidth();
//    }
//
//    private void welt(int currentX){
//        if(!isLeftSide()||!isRightSide()){
//            if(currentX>=parentWidth/2){
//                //靠右吸附
//                animate().setInterpolator(new DecelerateInterpolator())
//                        .setDuration(500)
//                        .xBy(parentWidth-getWidth()-getX())
//                        .start();
//            }else {
//                //靠左吸附
//                ObjectAnimator oa=ObjectAnimator.ofFloat(this,"x",getX(),0);
//                oa.setInterpolator(new DecelerateInterpolator());
//                oa.setDuration(500);
//                oa.start();
//            }
//        }
//
//    }

}
