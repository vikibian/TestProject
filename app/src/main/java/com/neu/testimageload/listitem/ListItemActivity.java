package com.neu.testimageload.listitem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.neu.testimageload.R;

public class ListItemActivity extends AppCompatActivity {
    private static String TAG = "ListItemActivity";
    private ListView listView;
    private ListItemAdapter listItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item);
        listView = findViewById(R.id.listview_test);

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
    }
}
