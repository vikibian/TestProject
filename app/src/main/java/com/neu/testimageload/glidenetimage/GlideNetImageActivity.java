package com.neu.testimageload.glidenetimage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;

import com.neu.testimageload.R;

import java.util.ArrayList;
import java.util.List;

public class GlideNetImageActivity extends AppCompatActivity {

    private ImageView mImageView;
    private String photoPath;

    //测试ListView
    private ListView photoCheckListview;
    private List<String> photoPaths = new ArrayList<>();
    private CheckPhotoListViewAdapter checkPhotoListViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_net_image);
        photoCheckListview = findViewById(R.id.listview_fragment_check_details_photo);

        photoPaths.add("http://39.97.108.172:8080/pic/123456789103Image1.jpg");
        photoPaths.add("http://39.97.108.172:8080/pic/123456789103Image0.jpg");
        photoPaths.add("http://39.97.108.172:8080/pic/ 123456789102Image0.jpg");

        checkPhotoListViewAdapter = new CheckPhotoListViewAdapter(this,photoPaths);
        photoCheckListview.setAdapter(checkPhotoListViewAdapter);
    }
}
