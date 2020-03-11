package com.neu.testimageload.SmartTable;

import android.util.Log;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONObject;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;


public class OkHttp {
    private static String TAG = "OkHttp";


    /**
     * 使用okhttp-utils的post请求网络文本数据 使用postString函数
     */
    public void postBypostString(String url, String gsonString, Callback Callback) {
        //String url = "http://www.zhiyun-tech.com/App/Rider-M/changelog-zh.txt";
        //url = "http://api.m.mtime.cn/PageSubArea/TrailerList.api";

        OkHttpUtils
                .postString()
                .url(url)
//                .id(100)
                .content(gsonString)
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(Callback);
        Log.d(TAG,"Okhttp: --> postString");

    }




    /**
     * 使用okhttp-utils的post请求网络文本数据 使用post函数
     */
    public void postBypost(String url, JSONObject jsonObject, StringCallback stringCallback) {
        OkHttpUtils
                .post()
                .url(url)
                .id(100)
                .build()
                .execute(stringCallback);
        Log.d(TAG,"Okhttp: --> post");

    }


    /**
     * 使用okhttp-utils上传多个或者单个文件
     */
    public void postFilesByPost(String url, Map<String, String> resultMap,
                                List<String> pathlist, Callback Callback)//Map<String, File> filemap,
    {
        PostFormBuilder formBuilder = OkHttpUtils.post();
//        Log.e(TAG,"postFiles: "+pathlist.size());

        if (pathlist != null){
            int indexOfimage =0;
            int indexOfvideo =0;
            Map<String, File> fileMap = new LinkedHashMap<>();
            for (int i =0;i<pathlist.size();i++){
                int length = pathlist.get(i).length();
                Log.d(TAG," length: "+length);
                String suffix = pathlist.get(i).substring(length -3,length);
                Log.d(TAG," i: "+i);
                File file =  new File(pathlist.get(i));
                if (suffix.equals("mp4")){
                    //fileMap.put("videoFile"+(++indexOfvideo),new File(pathlist.get(i)));
                    formBuilder.addFile("videoFile"+(++indexOfvideo), file.getName(),file);
                }else {
                    //fileMap.put("imageFile"+(++indexOfvideo),new File(pathlist.get(i)));
                    formBuilder.addFile("imageFile"+(++indexOfimage), file.getName(), file);
                }
            }
            Log.e(TAG," postFilesByPost: pathlist 为空");

        }

        formBuilder
                .url(url)
                .id(100)
                .params(resultMap)
                .build()
                .execute(Callback);

    }


}
