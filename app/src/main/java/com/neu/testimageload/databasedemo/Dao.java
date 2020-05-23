package com.neu.testimageload.databasedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * created by Viki on 2020/5/16
 * system login name : lg
 * created time : 16:31
 * email : 710256138@qq.com
 */
public class Dao {

    private static final String TAG = "Dao";
    private final DatabaseHelper helper;

    public Dao(Context context){
        //创建数据库
        helper = new DatabaseHelper(context);
//        helper.getWritableDatabase();
    }

    public void insert(){
        SQLiteDatabase db = helper.getWritableDatabase();
//        String sql = "insert into "+Constants.TABLE_NAME+"(_id,name,age,salary) values(?,?,?,?)";
//        db.execSQL(sql,new Object[]{1,"billgates",60,1});


        ContentValues values = new ContentValues();
        values.put("_id",2);
        values.put("name","larrypage");
        values.put("age","3");
        values.put("salary","1");


        db.insert(Constants.TABLE_NAME,null,values);
        db.close();
    }

    public void delete(){
        SQLiteDatabase db = helper.getWritableDatabase();
//        String sql = "delete from "+Constants.TABLE_NAME+" where age = 60";
//        db.execSQL(sql);

        db.delete(Constants.TABLE_NAME,null,null);
        db.close();
    }

    public void update(){
        SQLiteDatabase db = helper.getWritableDatabase();
//        String sql = "update  "+Constants.TABLE_NAME+" set salary = 2 where age = 60";
//        db.execSQL(sql);

        ContentValues values = new ContentValues();

        values.put("salary","2");


        db.update(Constants.TABLE_NAME,values,null,null);
        db.close();
    }

    public void query(){
        SQLiteDatabase db = helper.getWritableDatabase();
//        String sql = "select * from  "+Constants.TABLE_NAME;
//        Cursor cursor = db.rawQuery(sql, null);
//        while (cursor.moveToNext()){
//            int index = cursor.getColumnIndex("name");
//            String string = cursor.getString(index);
//            Log.e(TAG,"name ==:"+string);
//        }

        Cursor cursor = db.query(false, Constants.TABLE_NAME, null, null, null, null, null, null, null, null);
        while (cursor.moveToNext()){
            int id= cursor.getInt(0);
            String name = cursor.getString(id);
        }
        db.close();
    }
}
