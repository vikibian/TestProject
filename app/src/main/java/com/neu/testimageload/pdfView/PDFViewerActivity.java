package com.neu.testimageload.pdfView;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import com.github.barteksc.pdfviewer.PDFView;
import com.neu.testimageload.R;
import com.neu.testimageload.okhttp.OkHttp;
import com.zhy.http.okhttp.callback.FileCallBack;

import java.io.File;

import okhttp3.Call;

public class PDFViewerActivity extends AppCompatActivity {
    private static String TAG = "PDFViewerActivity";
    private PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfviewer);
        pdfView = findViewById(R.id.pdfView);
        String path = Environment.getExternalStorageDirectory().getAbsolutePath();
        Log.e(TAG," absolutepath path:"+path);
//        File pdfFile = new File(path+"/AlphaMap/58号文.pdf");
//
//        String pdf = Environment.getExternalStorageDirectory()+"/pdf/test.pdf";
//        File file = new File(pdf);
//        Log.e(TAG," path:"+Environment.getExternalStorageDirectory());
//
//
//        if (file.exists()){
//             pdfView.fromFile(file)
//                     .load();
//        }else {
//            Log.e(TAG,"  文件不存在");
//        }

//        String pdf = "http://39.97.108.172:8080/pic/PDF/%E6%A8%A1%E7%B3%8A%E5%9B%BE%E5%83%8F%E7%9A%84%E6%9C%80%E5%A4%A7%E7%86%B5%E6%81%A2%E5%A4%8D.pdf";
        String pdf = "http://39.97.108.172:8080/pic/PDF/模糊图像的最大熵恢复.pdf";

        OkHttp okHttp = new OkHttp();

        String pdfname = Environment.getExternalStorageDirectory().getAbsolutePath()+"/Download";

        Log.e(TAG,"  "+pdfname);
        File folder = new File(pdfname);
        String name = "test.pdf";
        if (folder.exists()){
            okHttp.downloadFile(pdf, new FileCallBack(pdfname,name) {
                @Override
                public void onError(Call call, Exception e, int id) {
                    Log.e(TAG," error: "+e.toString());
                }

                @Override
                public void onResponse(File response, int id) {
                    Log.e(TAG," file reponse abPath: "+response.getAbsolutePath());
                    pdfView.fromFile(new File(response.getAbsolutePath())).load();
                }
            });
        }



//
//        Uri uri = Uri.parse(pdf);
//        pdfView.fromFile(new File(pdfname + "/" + name)).load();
    }
}
