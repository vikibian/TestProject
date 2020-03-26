package com.neu.testimageload.rectifyresult;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.neu.testimageload.R;
import com.neu.testimageload.listitem.zhenggai.PhotoVideoActivity;
import com.neu.testimageload.listitem.zhenggai.SuggestionGridViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class RectifyResultActivity extends AppCompatActivity implements View.OnClickListener{
    private GridView gridView;
    private SuggestionGridViewAdapter suggestionGridViewAdapter;
    public List<String> pathlistOfPhoto = new ArrayList<>();
    private int deleteIndex;
    private CheckBox recify_result_qualified;
    private CheckBox recify_result_unqualified;
    private TextView recify_result_way_textview;

    private CheckBox recify_result_way_limit;
    private CheckBox recify_result_way_stop;
    private LinearLayout recify_result_way_checkbox_lin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rectify_result);
        deleteIndex = -1;

        recify_result_qualified = findViewById(R.id.rectify_result_qualified);
        recify_result_unqualified = findViewById(R.id.rectify_result_unqualified);
        recify_result_way_textview = findViewById(R.id.rectify_result_item_way_textview);
        recify_result_way_limit = findViewById(R.id.rectify_result_way_limit);
        recify_result_way_stop = findViewById(R.id.rectify_result_way_stop);
        recify_result_way_checkbox_lin = findViewById(R.id.rectify_result_way_checkbox_lin);

        //默认合格
        recify_result_qualified.setChecked(true);

        recify_result_qualified.setOnClickListener(this);
        recify_result_unqualified.setOnClickListener(this);
        recify_result_way_limit.setOnClickListener(this);
        recify_result_way_stop.setOnClickListener(this);

        gridView = findViewById(R.id.suggestion_gridview);
        suggestionGridViewAdapter = new SuggestionGridViewAdapter(getApplicationContext(), pathlistOfPhoto,0);
        gridView.setAdapter(suggestionGridViewAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                deleteIndex = position;
                if (pathlistOfPhoto.size()==position){
                    view.showContextMenu();
                }else {
                    view.showContextMenu();
                }


            }
        });

        gridView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//                menu.add(0,1,0,"相册");
                menu.add(0,2,0,"相机");
                menu.add(0,3,0,"取消");
                menu.add(0,4,0,"删除");
            }
        });
    }


    /**
     * 虽然menu选项在SuggestionGridViewAdapter.java文件中但是一样可以使用这里的选择事件按钮
     * @param item
     * @return
     */
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
//            case 1:
//                Log.e(TAG," item  "+item.getTitle());
//                startPhotoGallery();
//                break;
            case 2:
                Intent intentall = new Intent(RectifyResultActivity.this, PhotoVideoActivity.class);

                startActivity(intentall);
                break;
            case 3:
                break;
            case 4:
                if (deleteIndex>=0&&deleteIndex<pathlistOfPhoto.size()){
                    pathlistOfPhoto.remove(deleteIndex);
                    suggestionGridViewAdapter = new SuggestionGridViewAdapter(getApplicationContext(), pathlistOfPhoto,1);
                    gridView.setAdapter(suggestionGridViewAdapter);
                }else {
                    Toast.makeText(getApplicationContext(),"此图片不能删除",Toast.LENGTH_SHORT).show();
                }
                //pathlistOfPhoto
                break;


        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rectify_result_qualified:
                recify_result_unqualified.setChecked(false);
                break;
            case R.id.rectify_result_unqualified:
                recify_result_qualified.setChecked(false);
                recify_result_way_textview.setVisibility(View.INVISIBLE);
                recify_result_way_checkbox_lin.setVisibility(View.VISIBLE);
                break;
            case R.id.rectify_result_way_limit:
                recify_result_way_stop.setChecked(false);
                Toast.makeText(getApplicationContext(),recify_result_way_limit.getText(),Toast.LENGTH_SHORT).show();
                break;
            case R.id.rectify_result_way_stop:
                recify_result_way_limit.setChecked(false);
                Toast.makeText(getApplicationContext(),recify_result_way_stop.getText(),Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
