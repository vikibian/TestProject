package com.neu.testimageload.DragFloatActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.neu.testimageload.R;

public class DragFloatActionButtonActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private RelativeLayout searchRightDrawer;
    //    private FloatingNavigationView mFloatingNavigationView;
    private DragFloatActionButton mFloatingNavigationView;
    private TextView searchTtoolbarTextview;
    private boolean isDirection_right =false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_float_action_button);

        mDrawerLayout = findViewById(R.id.search_drawerLayout);
        searchRightDrawer = findViewById(R.id.search_right_drawer);
//        mFloatingNavigationView = view.findViewById(R.id.search_fb);
        mFloatingNavigationView = findViewById(R.id.search_fb);

        mFloatingNavigationView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mFloatingNavigationView.isDrag()){
                    if(!isDirection_right){
                        mDrawerLayout.openDrawer(searchRightDrawer);
                        isDirection_right = true;
                    }else {
                        mDrawerLayout.closeDrawer(searchRightDrawer);
                        isDirection_right = false;
                    }
                }
            }
        });
    }
}
