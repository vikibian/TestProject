package com.neu.testimageload.databasedemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * created by Viki on 2020/5/16
 * system login name : lg
 * created time : 16:16
 * email : 710256138@qq.com
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private String sql;

    public DatabaseHelper(Context context){
        super(context,Constants.DATABASE_NAME,null,Constants.VERSION_CODE);
    }

//    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
//        super(context, name, factory, version);
//    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table "+Constants.TABLE_NAME+"(_id integer,name varchar,age integer,salary integer)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


        switch (oldVersion){
            case 1:
                sql = "alter table "+Constants.TABLE_NAME+" add phone integer , address varchar";
                db.execSQL(sql);
                break;
            case 2:
                sql = "alter table "+Constants.TABLE_NAME+" add address varchar";
                db.execSQL(sql);
                break;
            case 3:

                break;
            default:
                break;
        }
    }
}
