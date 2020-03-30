package com.neu.testimageload.answerlist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;

import android.widget.Button;

import com.neu.testimageload.R;

import java.util.ArrayList;
import java.util.List;

public class AnswerListActivity extends AppCompatActivity {
    private static final String TAG = "AnswerListActivity";
    private Button button_left;
    private Button button_right;
    private Button button_middle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_list);
//        setContentView(R.layout.item_anwerslist);

        button_left = findViewById(R.id.button_answerlist_left);
        button_right = findViewById(R.id.button_answerlist_right);
        button_middle = findViewById(R.id.button_answerlist_middle);
        setEnable(button_left);

//        button_left.setBackground(getResources().getDrawable(R.drawable.logincode_left_press));
//        button_left.setTextColor(getResources().getColor(R.color.white));

        button_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setEnable(button_left);
//                button_left.setBackground(getResources().getDrawable(R.drawable.logincode_left_press));
//                button_left.setTextColor(getResources().getColor(R.color.white));
//
//                button_right.setBackground(getResources().getDrawable(R.drawable.logincode_right));
//                button_right.setTextColor(getResources().getColor(R.color.black));
            }
        });

        button_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setEnable(button_right);
//                button_right.setBackground(getResources().getDrawable(R.drawable.logincode_right_press));
//                button_right.setTextColor(getResources().getColor(R.color.white));
//
//                button_left.setBackground(getResources().getDrawable(R.drawable.logincode_left));
//                button_left.setTextColor(getResources().getColor(R.color.black));
            }
        });

        button_middle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setEnable(button_middle);
            }
        });


    }

    private void setEnable(Button btn) {
        List<Button> buttonList=new ArrayList<>();
        if (buttonList.size()==0){
            buttonList.add(button_left);
            buttonList.add(button_right);
            buttonList.add(button_middle);
        }
        for (int i = 0; i <buttonList.size() ; i++) {
            buttonList.get(i).setEnabled(true);
        }
        btn.setEnabled(false);
    }



}
