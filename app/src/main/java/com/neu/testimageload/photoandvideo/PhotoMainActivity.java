package com.neu.testimageload.photoandvideo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.neu.testimageload.R;

public class PhotoMainActivity extends AppCompatActivity {

    private Button bt_matisse;
    private Button bt_camera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_main);

        bt_matisse = findViewById(R.id.button_matisse);
        bt_matisse.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PhotoMainActivity.this,PhotoActivity.class);
                startActivity(intent);
            }
        });

        bt_camera = findViewById(R.id.button_camera);
        bt_camera.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PhotoMainActivity.this,PhotoCameraActivity.class);
                startActivity(intent);
            }
        });
    }
}
