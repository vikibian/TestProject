package com.neu.testimageload.listitem.zhenggai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.neu.testimageload.R;

public class RectifyActivity extends AppCompatActivity {
    private static final String TAG = "RectifyActivity";

    private ListView listView;
    private RectifyListItemAdapter rectifyListItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rectify);
        listView = findViewById(R.id.rectify_listview);

        String[] num = {"1","2","3","4","5"};

        rectifyListItemAdapter = new RectifyListItemAdapter(getApplicationContext(),num);
        listView.setAdapter(rectifyListItemAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),position+" ",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RectifyActivity.this,RectifyItemActivity.class);
                startActivity(intent);
            }
        });
    }
}
