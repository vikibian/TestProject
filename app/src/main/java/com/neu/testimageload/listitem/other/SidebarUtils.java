package com.neu.testimageload.listitem.other;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SidebarUtils {
    private static final String TAG = "SidebarUtils";
    public static void initSelectStartTime(Context activity, final TextView selectStartTime){
        //控制时间范围(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
        //因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
        boolean[] booleanSelecTime  = new boolean[6];
        booleanSelecTime = new boolean[]{true, true, true, false, false, false};
        Calendar selectDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        startDate.set(2010,0,23);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2030,11,28);

        Log.e(TAG,"   initSelectStartTime");

        TimePickerView timePickerView = new TimePickerBuilder(activity, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                selectStartTime.setText(getTime(date));
                Log.d("SidebarUtils  ","getTime(date)"+getTime(date));
            }
        })
                .setType(booleanSelecTime)
                .setCancelText("取消")//取消按钮文字
                .setSubmitText("确认")//确认按钮文字
                .setContentTextSize(21)//滚轮文字大小
                .setTitleText("选择时间")//标题文字
                .setTitleSize(25)//标题文字大小
                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setDate(selectDate)// 如果不设置的话，默认是系统时间*/
                .setRangDate(startDate,endDate)// 如果不设置的话，默认是系统时间*/
                .setLabel("", "", "", "", "", "")//默认设置为年月日时分秒
                .setTitleColor(Color.BLACK)//标题文字颜色
                .setSubmitColor(Color.BLUE)//设置确定按钮颜色
                .setCancelColor(Color.BLUE)//设置取消按钮颜色
                .isDialog(true)//是否以对话框形式显示
                .build();
        timePickerView.show();
    }

    public static void initSelectEndTime(Context activity, final TextView selectEndTime){
        //控制时间范围(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
        //因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
        boolean[] booleanSelecTime  = new boolean[6];
        booleanSelecTime = new boolean[]{true, true, true, false, false, false};
        Calendar selectDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        startDate.set(2010,0,23);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2030,11,28);

        TimePickerView timePickerView = new TimePickerBuilder(activity, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                selectEndTime.setText(getTime(date));
            }
        })
                .setType(booleanSelecTime)
                .setCancelText("取消")//取消按钮文字
                .setSubmitText("确认")//确认按钮文字
                .setContentTextSize(21)//滚轮文字大小
                .setTitleText("选择时间")//标题文字
                .setTitleSize(25)//标题文字大小
                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setDate(selectDate)// 如果不设置的话，默认是系统时间*/
                .setRangDate(startDate,endDate)// 如果不设置的话，默认是系统时间*/
                .setLabel("", "", "", "", "", "")//默认设置为年月日时分秒
                .setTitleColor(Color.BLACK)//标题文字颜色
                .setSubmitColor(Color.BLUE)//设置确定按钮颜色
                .setCancelColor(Color.BLUE)//设置取消按钮颜色
                .isDialog(true)//是否以对话框形式显示
                .build();
        timePickerView.show();

    }

    private static String getTime(Date date) {
        SimpleDateFormat format =  new SimpleDateFormat("yyyy年MM月dd日");
        return format.format(date);
    }

    public static String getSystemTime(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日" );
        Calendar calendar = Calendar.getInstance();
        Date date = new Date(System.currentTimeMillis());
        return format.format(date);
    }

    public static boolean isStartBeforeEnd(String starttime, String endtime)throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        Date start = format.parse(starttime);
        Date end = format.parse(endtime);
        if (start.before(end)||start.equals(end)){
            return true;
        }
        else {
            return false;
        }
    }

}
