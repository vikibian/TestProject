package com.neu.testimageload.listitem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.neu.testimageload.R;
import com.neu.testimageload.listitem.zhenggai.RectifyActivity;

public class ListItemActivity extends AppCompatActivity {
    private static String TAG = "ListItemActivity";
    private ListView listView;
    private ListItemAdapter listItemAdapter;
    private Button button_adjust;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item);
        listView = findViewById(R.id.listview_test);
        button_adjust = findViewById(R.id.button_adjust);

        String[] a = {"1","2","3","4","5","6","7"};
        listItemAdapter = new ListItemAdapter(getApplicationContext(),a);
        listView.setAdapter(listItemAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),position+"",Toast.LENGTH_SHORT).show();
                Log.e(TAG," parent: "+parent.toString()+" size: "+parent.getCount());
                Log.e(TAG," view: "+view.toString()+"id: "+view.getId());
            }
        });

        button_adjust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListItemActivity.this, RectifyActivity.class);
                startActivity(intent);

            }
        });
    }
}
