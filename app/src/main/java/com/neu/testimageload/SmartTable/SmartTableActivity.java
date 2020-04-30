package com.neu.testimageload.SmartTable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.bin.david.form.core.SmartTable;
import com.bin.david.form.core.TableConfig;
import com.bin.david.form.data.CellInfo;
import com.bin.david.form.data.column.Column;
import com.bin.david.form.data.column.ColumnInfo;
import com.bin.david.form.data.format.bg.BaseCellBackgroundFormat;
import com.bin.david.form.data.format.draw.IDrawFormat;
import com.bin.david.form.data.format.draw.ImageResDrawFormat;
import com.bin.david.form.data.style.FontStyle;
import com.bin.david.form.data.table.ArrayTableData;
import com.bin.david.form.data.table.TableData;
import com.bin.david.form.listener.OnColumnClickListener;
import com.bin.david.form.listener.OnColumnItemClickListener;
import com.bin.david.form.utils.DensityUtils;
import com.google.gson.Gson;
import com.neu.testimageload.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

import static java.security.AccessController.getContext;

public class SmartTableActivity extends AppCompatActivity {
    private static String TAG = "SmartTableActivity";
    private List<Task> tasks;
    Column<String> checkdate;
    Column<String> deadline;
    Column<String> devclass;
    Column<String> devid;
    Column<String> latitude;
    Column<String> place;
    Column<String> result;
    Column<String> taskID;
    Column<String> tasktype;
    Column<String> danwei;

    SmartTable smartTable;

    private SmartTable<User> table;
    Column<String> name;
    Column<Integer> age;
    Column<Boolean> operation;
    List<String> name_selected = new ArrayList<String>();

    private SmartTable<Task> taskSmartTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smart_table);

//        getSearchedData();
        init();

//        smartTable = findViewById(R.id.table);
//
//        List<UserInfo> list = new ArrayList<>();
//
//        list.add(new UserInfo("沈阳",100, 150, 50, 240, 1100, 450, 23458));
//        list.add(new UserInfo("沈阳",100, 150, 50, 240, 1100, 450, 23458));
//        list.add(new UserInfo("沈阳",100, 150, 50, 240, 1100, 450, 23458));
//        list.add(new UserInfo("沈阳",100, 150, 50, 240, 1100, 450, 23458));
//        list.add(new UserInfo("乌鲁木齐",100, 150, 50, 240, 1100, 450, 23458));
//        list.add(new UserInfo("乌鲁木齐",100, 150, 50, 240, 1100, 450, 23458));
//        list.add(new UserInfo("乌鲁木齐",100, 150, 50, 240, 1100, 450, 23458));
//        list.add(new UserInfo("乌鲁木齐",100, 150, 50, 240, 1100, 450, 23458));
//        list.add(new UserInfo("沈阳",100, 150, 50, 240, 1100, 450, 23458));
//        list.add(new UserInfo("沈阳",100, 150, 50, 240, 1100, 450, 23458));
//        list.add(new UserInfo("沈阳",100, 150, 50, 240, 1100, 450, 23458));
//        list.add(new UserInfo("沈阳",100, 150, 50, 240, 1100, 450, 23458));
//
//        smartTable.setData(list);
//        smartTable.getConfig().setContentStyle(new FontStyle(50, Color.BLUE));
//        smartTable.setOnColumnClickListener(new OnColumnClickListener() {
//            @Override
//            public void onClick(ColumnInfo columnInfo) {
//                Log.e("测试："," ");
//            }
//        });



//
//        table = (SmartTable<User>)findViewById(R.id.table);
//        List<User> codeList = new ArrayList<User>();
//        codeList.add(new User("user_01",20,false));
//        codeList.add(new User("user_02",21,false));
//        codeList.add(new User("user_03",22,false));
//        codeList.add(new User("user_04",22,false));
//        codeList.add(new User("user_05",21,false));
//        codeList.add(new User("user_06",21,false));
//        codeList.add(new User("user_07",23,false));
//        codeList.add(new User("user_08",20,false));
//
//        name = new Column<>("姓名", "name");
//        name.setOnColumnItemClickListener(new OnColumnItemClickListener<String>() {
//            @Override
//            public void onClick(Column<String> column, String value, String bool, int position) {
//                Toast.makeText(SmartTableActivity.this,"点击了"+value,Toast.LENGTH_SHORT).show();
//                if(operation.getDatas().get(position)){
//                    showName(position, false);
//                    operation.getDatas().set(position,false);
//                }else{
//                    showName(position, true);
//                    operation.getDatas().set(position,true);
//                }
//                table.refreshDrawableState();           //不要忘记刷新表格，否则选中效果会延时一步
//                table.invalidate();
//            }
//        });
//
//        age = new Column<>("年龄", "age");
//        age.setOnColumnItemClickListener(new OnColumnItemClickListener<Integer>() {
//            @Override
//            public void onClick(Column<Integer> column, String value, Integer bool, int position) {
////                Toast.makeText(CodeListActivity.this,"点击了"+value,Toast.LENGTH_SHORT).show();
//                if(operation.getDatas().get(position)){
//                    showName(position, false);
//                    operation.getDatas().set(position,false);
//                }else{
//                    showName(position, true);
//                    operation.getDatas().set(position,true);
//                }
//                table.refreshDrawableState();
//                table.invalidate();
//            }
//        });
//
//        int size = DensityUtils.dp2px(this,30);
//
//        operation = new Column<>("勾选", "operation", new ImageResDrawFormat<Boolean>(size,size) {    //设置"操作"这一列以图标显示 true、false 的状态
//            @Override
//            protected Context getContext() {
//                return SmartTableActivity.this;
//            }
//            @Override
//            protected int getResourceID(Boolean isCheck, String value, int position) {
//                if(isCheck){
//                    return R.mipmap.check;      //将图标提前放入 app/res/mipmap 目录下
//                }
//                return R.mipmap.icon_button_click;
//            }
//        });
//        operation.setComputeWidth(40);
//        operation.setOnColumnItemClickListener(new OnColumnItemClickListener<Boolean>() {
//            @Override
//            public void onClick(Column<Boolean> column, String value, Boolean bool, int position) {
////                Toast.makeText(CodeListActivity.this,"点击了"+value,Toast.LENGTH_SHORT).show();
//                if(operation.getDatas().get(position)){
//                    showName(position, false);
//                    operation.getDatas().set(position,false);
//                }else{
//                    showName(position, true);
//                    operation.getDatas().set(position,true);
//                }
//                table.refreshDrawableState();
//                table.invalidate();
//            }
//        });

//        final TableData<User> tableData = new TableData<User>("测试标题",codeList,name,
//        deadline,devclass, devid, latitude, place,result, taskID, tasktype, danwei);
//        taskSmartTable.getConfig().setShowTableTitle(false);
//
//        taskSmartTable.setTableData(tableData);
////        table.getConfig().setContentStyle(new FontStyle(50, Color.BLUE));
//        taskSmartTable.getConfig().setMinTableWidth(1024);       //设置表格最小宽度
//        FontStyle style = new FontStyle();
//        style.setTextSize(30);
//        taskSmartTable.getConfig().setContentStyle(style);       //设置表格主题字体样式
//        taskSmartTable.getConfig().setColumnTitleStyle(style);   //设置表格标题字体样式
//        taskSmartTable.getConfig().setContentCellBackgroundFormat(new BaseCellBackgroundFormat<CellInfo>() {     //设置隔行变色
//            @Override
//            public int getBackGroundColor(CellInfo cellInfo) {
//                if(cellInfo.row%2 ==1) {
//                    return ContextCompat.getColor(SmartTableActivity.this, R.color.colorAccent);      //需要在 app/res/values 中添加 <color name="tableBackground">#d4d4d4</color>
//                }else{
//                    return TableConfig.INVALID_COLOR;
//                }
//            }
//        });
//        taskSmartTable.getConfig().setMinTableWidth(1024);   //设置最小宽度
    }

    /**
     * 收集所有被勾选的姓名记录到 name_selected 集合中，并实时更新
     * @param position  被选择记录的行数，根据行数用来找到其他列中该行记录对应的信息
     * @param selectedState   当前的操作状态：选中 || 取消选中
     */
    public void showName(int position, boolean selectedState){
        List<String> rotorIdList = name.getDatas();
        if(position >-1){
            String rotorTemp = rotorIdList.get(position);
            if(selectedState && !name_selected.contains(rotorTemp)){            //当前操作是选中，并且“所有选中的姓名的集合”中没有该记录，则添加进去
                name_selected.add(rotorTemp);
            }else if(!selectedState && name_selected.contains(rotorTemp)){     // 当前操作是取消选中，并且“所有选中姓名的集合”总含有该记录，则删除该记录
                name_selected.remove(rotorTemp);
            }
        }
        for(String s:name_selected){
            System.out.print(s + " -- ");
        }
    }

    private void getSearchedData() {

        String url ="http://39.97.108.172:8080/WEB1010/selectUserResultServlet";
        Map<String, String> searchmap = new HashMap<>();
        searchmap.put("LOGINNAME", "test");
        searchmap.put("DEVCLASS", "3000");
        searchmap.put("RESULT", "1");
        searchmap.put("TASKTYPE", "自查");
//        searchmap.put("taskID","1affb4ca-1b34-4d99-9222-5ce1ed62afa5");
//        searchmap.put("DEVID","123456");

//        searchmap.put("StartedTime","12345");//起始时间
//        searchmap.put("EndTime","12345");//终止时间
//        searchmap.put("DEVCLASS","12345");//设备种类
//        searchmap.put("DEVCLASS","12345");//合格情况
//        searchmap.put("DEVCLASS","12345");//检查情况
//        searchmap.put("DEVCLASS","12345");//检查人员

        OkHttp okHttp = new OkHttp();
        okHttp.postBypostString(url, new Gson().toJson(searchmap), new ListTaskCallBack() {
            @Override
            public void onError(Call call, Exception e, int i) {
                System.out.println(e.toString());
                Log.e(TAG, " error: " + e.toString());
                Toast.makeText(SmartTableActivity.this, "客官，网络不给力", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onResponse(Result<List<Task>> response, int id) {
                if (response.getMessage().equals("获取任务成功")) {
//                    Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_LONG).show();
                    Toast.makeText(SmartTableActivity.this, "搜索数据成功!", Toast.LENGTH_LONG).show();

                    if (response.getContent().size() == 0) {
                        Toast.makeText(SmartTableActivity.this, "无数据", Toast.LENGTH_LONG).show();
                        Log.e("TAG", " response.getContent: " + "无数据");
                        tasks = new ArrayList<Task>();
                    } else {
                        Log.e("TAG", " response.getContent: " + "有数据");
                        tasks = response.getContent();
                       init();
                    }
                    Toast.makeText(SmartTableActivity.this, "成功", Toast.LENGTH_LONG).show();
                    //下面是显示搜索结果 并收起侧滑界面  要放在网络post请求之后

                } else {
                    Toast.makeText(SmartTableActivity.this, "搜索数据失败!", Toast.LENGTH_LONG).show();

                }
            }
        });
    }

    private void init() {
        taskSmartTable = findViewById(R.id.table);
        tasks = new ArrayList<Task>();
        Task task = new Task();
        task.setCHECKDATE("1");
        task.setDEADLINE("2");
        task.setDEVCLASS("3");
        task.setDEVID("4");
        task.setLATITUDE("5");
        task.setPLACE("6");
        task.setRESULT("7");
        task.setTASKTYPE("8");
        task.setUSEUNITNAME("9");
        for (int i=0;i<30;i++){
            tasks.add(task);
        }

        checkdate = new Column<String>("检查日期","CHECKDATE");
        checkdate.setOnColumnItemClickListener(new OnColumnItemClickListener<String>() {
            @Override
            public void onClick(Column<String> column, String value, String s, int position) {
                Toast.makeText(SmartTableActivity.this,"点击了"+value+" "+position,Toast.LENGTH_SHORT).show();
            }
        });
        checkdate.setFixed(true);

        deadline = new Column<String>("截止日期","DEADLINE");
        deadline.setOnColumnItemClickListener(new OnColumnItemClickListener<String>() {
            @Override
            public void onClick(Column<String> column, String value, String s, int position) {
                Toast.makeText(SmartTableActivity.this,"点击了"+value+" "+position,Toast.LENGTH_SHORT).show();
            }
        });

        devclass = new Column<String>("设备类型","DEVCLASS");
        devclass.setOnColumnItemClickListener(new OnColumnItemClickListener<String>() {
            @Override
            public void onClick(Column<String> column, String value, String s, int position) {
                Toast.makeText(SmartTableActivity.this,"点击了"+value+" "+position,Toast.LENGTH_SHORT).show();
            }
        });

        devid = new Column<String>("设备ID","DEVID");
        devid.setOnColumnItemClickListener(new OnColumnItemClickListener<String>() {
            @Override
            public void onClick(Column<String> column, String value, String s, int position) {
                Toast.makeText(SmartTableActivity.this,"点击了"+value+" "+position,Toast.LENGTH_SHORT).show();
            }
        });

        latitude = new Column<String>("纬度","LATITUDE");
        latitude.setOnColumnItemClickListener(new OnColumnItemClickListener<String>() {
            @Override
            public void onClick(Column<String> column, String value, String s, int position) {
                Toast.makeText(SmartTableActivity.this,"点击了"+value,Toast.LENGTH_SHORT).show();
            }
        });

        place = new Column<String>("地址","PLACE");
        place.setOnColumnItemClickListener(new OnColumnItemClickListener<String>() {
            @Override
            public void onClick(Column<String> column, String value, String s, int position) {
                Toast.makeText(SmartTableActivity.this,"点击了"+value,Toast.LENGTH_SHORT).show();
            }
        });

        result = new Column<String>("检查结果","RESULT");
        result.setOnColumnItemClickListener(new OnColumnItemClickListener<String>() {
            @Override
            public void onClick(Column<String> column, String value, String s, int position) {
                Toast.makeText(SmartTableActivity.this,"点击了"+value,Toast.LENGTH_SHORT).show();
            }
        });

        taskID = new Column<String>("任务ID","TASKID");
        taskID.setOnColumnItemClickListener(new OnColumnItemClickListener<String>() {
            @Override
            public void onClick(Column<String> column, String value, String s, int position) {
                Toast.makeText(SmartTableActivity.this,"点击了"+value,Toast.LENGTH_SHORT).show();
            }
        });

        tasktype = new Column<String>("任务类型","TASKTYPE");
        tasktype.setOnColumnItemClickListener(new OnColumnItemClickListener<String>() {
            @Override
            public void onClick(Column<String> column, String value, String s, int position) {
                Toast.makeText(SmartTableActivity.this,"点击了"+value,Toast.LENGTH_SHORT).show();
            }
        });
        danwei = new Column<String>("检查单位","USEUNITNAME");
        danwei.setOnColumnItemClickListener(new OnColumnItemClickListener<String>() {
            @Override
            public void onClick(Column<String> column, String value, String s, int position) {
                Toast.makeText(SmartTableActivity.this,"点击了"+value,Toast.LENGTH_SHORT).show();
            }
        });

        final TableData<Task> tableData = new TableData<Task>("测试标题",tasks,checkdate,
                deadline,devclass, devid, latitude, place,result, taskID, tasktype, danwei);
        taskSmartTable.getConfig().setShowTableTitle(false);//设置标题
//        FontStyle fontStyle = new FontStyle();
//        fontStyle.setTextSize(30);
//        fontStyle.setAlign(Paint.Align.CENTER);
//        taskSmartTable.getConfig().setTableTitleStyle(fontStyle);
//        taskSmartTable.getTableTitle().setSize(20);
        taskSmartTable.getConfig().setShowXSequence(false);
        taskSmartTable.setTableData(tableData);


//        table.getConfig().setContentStyle(new FontStyle(50, Color.BLUE));
        taskSmartTable.getConfig().setMinTableWidth(1024);       //设置表格最小宽度
        FontStyle style = new FontStyle();
        style.setTextSize(30);
        taskSmartTable.setZoom(true);
        taskSmartTable.getConfig().setContentStyle(style);       //设置表格主题字体样式
        taskSmartTable.getConfig().setColumnTitleStyle(style);   //设置表格标题字体样式
        taskSmartTable.getConfig().setContentCellBackgroundFormat(new BaseCellBackgroundFormat<CellInfo>() {     //设置隔行变色
            @Override
            public int getBackGroundColor(CellInfo cellInfo) {
                if(cellInfo.row%2 ==1) {
                    return ContextCompat.getColor(SmartTableActivity.this, R.color.colorAccent);      //需要在 app/res/values 中添加 <color name="tableBackground">#d4d4d4</color>
                }else{
                    return TableConfig.INVALID_COLOR;
                }
            }
        });
        taskSmartTable.getConfig().setMinTableWidth(1024);   //设置最小宽度
    }

}


