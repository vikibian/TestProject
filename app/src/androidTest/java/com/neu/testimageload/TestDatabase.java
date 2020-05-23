package com.neu.testimageload;

import android.content.Context;

import androidx.test.filters.MediumTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import com.neu.testimageload.databasedemo.Dao;

import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * created by Viki on 2020/5/22
 * system login name : lg
 * created time : 21:21
 * email : 710256138@qq.com
 */
@MediumTest
@RunWith(AndroidJUnit4.class)
public class TestDatabase {
    @Test
    public void testCreate(){

    }

    @Test
    public void testInsert(){
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        Dao dao = new Dao(appContext);
        dao.insert();
    }

    @Test
    public void testDelete(){
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        Dao dao = new Dao(appContext);
        dao.delete();
    }

    @Test
    public void testUpdate(){
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        Dao dao = new Dao(appContext);
        dao.update();
    }

    @Test
    public void testQuery(){
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        Dao dao = new Dao(appContext);
        dao.query();
    }
}
