package com.neu.testimageload.androidtree;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.neu.testimageload.R;
import com.neu.testimageload.androidtree.activity.SingleFragmentActivity;
import com.neu.testimageload.androidtree.fragment.CustomViewHolderFragment;
import com.neu.testimageload.androidtree.fragment.FolderStructureFragment;
import com.neu.testimageload.androidtree.fragment.SelectableTreeFragment;
import com.neu.testimageload.androidtree.fragment.TwoDScrollingArrowExpandFragment;
import com.neu.testimageload.androidtree.fragment.TwoDScrollingFragment;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class AndroidTreeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_tree);

        final LinkedHashMap<String, Class<?>> listItems = new LinkedHashMap<>();
        listItems.put("Folder Structure Example", FolderStructureFragment.class);
        listItems.put("Custom Holder Example", CustomViewHolderFragment.class);
        listItems.put("Selectable Nodes", SelectableTreeFragment.class);
        listItems.put("2d scrolling", TwoDScrollingFragment.class);
        listItems.put("Expand with arrow only", TwoDScrollingArrowExpandFragment.class);


        final List<String> list = new ArrayList(listItems.keySet());
        final ListView listview = (ListView) findViewById(R.id.listview);
        final SimpleArrayAdapter adapter = new SimpleArrayAdapter(this, list);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Class<?> clazz = listItems.values().toArray(new Class<?>[]{})[position];
                Intent i = new Intent(AndroidTreeActivity.this, SingleFragmentActivity.class);
                i.putExtra(SingleFragmentActivity.FRAGMENT_PARAM, clazz);
                AndroidTreeActivity.this.startActivity(i);
            }
        });
    }


    private class SimpleArrayAdapter extends ArrayAdapter<String> {
        public SimpleArrayAdapter(Context context, List<String> objects) {
            super(context, android.R.layout.simple_list_item_1, objects);

        }

        @Override
        public long getItemId(int position) {
            return position;
        }
    }
}
