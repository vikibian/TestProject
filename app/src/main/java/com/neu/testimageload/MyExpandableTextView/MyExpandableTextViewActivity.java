package com.neu.testimageload.MyExpandableTextView;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.neu.testimageload.R;

public class MyExpandableTextViewActivity extends AppCompatActivity {

    private ImageButton law_collapse;
    private TextView law_title;
    private TextView law_content;
    private boolean law_isExpand =false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_expandable_text_view);

        law_title = findViewById(R.id.check_detail_law).findViewById(R.id.check_detail_myexpandview_title);
        law_content = findViewById(R.id.check_detail_law).findViewById(R.id.check_detail_myexpandview_content);
        law_collapse = findViewById(R.id.check_detail_law).findViewById(R.id.check_detail_myexpandview_imagebutton);
        law_title.setText("法律法规：");
        law_content.setText("法律法规法律法规法律法规法律法规法律法规法律法规法律法规法律法规法律法规法律法规法律法规法律法规法律法规法律法规法律法规法律法规");
        law_collapse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!law_isExpand){
                    law_collapse.setImageDrawable(getResources().getDrawable(R.drawable.ic_keyboard_arrow_up_grey_600_24dp));
                    law_content.setVisibility(View.VISIBLE);
                    law_isExpand = true;
                }else if (law_isExpand){
                    law_collapse.setImageDrawable(getResources().getDrawable(R.drawable.ic_keyboard_arrow_down_grey_600_24dp));
                    law_content.setVisibility(View.GONE);
                    law_isExpand = false;
                }
            }
        });
    }
}
