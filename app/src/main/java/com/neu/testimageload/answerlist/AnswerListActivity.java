package com.neu.testimageload.answerlist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;

import android.widget.Button;

import com.neu.testimageload.R;

public class AnswerListActivity extends AppCompatActivity {
    private static final String TAG = "AnswerListActivity";
    private Button button_left;
    private Button button_right;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_list);
//        setContentView(R.layout.item_anwerslist);

        button_left = findViewById(R.id.button_answerlist_left);
        button_right = findViewById(R.id.button_answerlist_right);

        button_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button_left.setBackground(getResources().getDrawable(R.drawable.logincode_left_press));
                button_left.setTextColor(getResources().getColor(R.color.white));

                button_right.setBackground(getResources().getDrawable(R.drawable.logincode_right));
                button_right.setTextColor(getResources().getColor(R.color.black));
            }
        });

        button_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button_right.setBackground(getResources().getDrawable(R.drawable.logincode_right_press));
                button_right.setTextColor(getResources().getColor(R.color.white));

                button_left.setBackground(getResources().getDrawable(R.drawable.logincode_left));
                button_left.setTextColor(getResources().getColor(R.color.black));
            }
        });


    }



}
