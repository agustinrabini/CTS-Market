package com.example.ctsmarket05.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.LayoutTransition;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ctsmarket05.R;

public class InfoActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView iv;
    private TextView tvFirst;
    private TextView tvSecond;
    private TextView tvThird;
    private TextView tvFourth;
    private TextView tvFifth;
    private TextView tvResponse;
    private TextView tvResponse2;
    private TextView tvResponse3;
    private TextView tvResponse4;
    private TextView tvResponse5;
    private final static int MAX_LINES_COLLAPSED = 0;
    private final boolean INITIAL_IS_COLLAPSED = true;
    private final boolean INITIAL_IS_COLLAPSED2 = true;
    private final boolean INITIAL_IS_COLLAPSED3 = true;
    private final boolean INITIAL_IS_COLLAPSED4 = true;
    private final boolean INITIAL_IS_COLLAPSED5 = true;
    private boolean isCollapsed = INITIAL_IS_COLLAPSED;
    private boolean isCollapsed2 = INITIAL_IS_COLLAPSED2;
    private boolean isCollapsed3 = INITIAL_IS_COLLAPSED3;
    private boolean isCollapsed4 = INITIAL_IS_COLLAPSED4;
    private boolean isCollapsed5 = INITIAL_IS_COLLAPSED5;
    private ConstraintLayout clInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        clInfo = findViewById(R.id.cl_info);
        tvResponse = findViewById(R.id.tv_responseI);
        tvFirst = findViewById(R.id.tv_firstI);
        tvSecond = findViewById(R.id.tv_secondI);
        tvThird = findViewById(R.id.tv_thirdI);
        tvFourth = findViewById(R.id.tv_fourthI);
        tvFifth = findViewById(R.id.tv_fifthI);
        tvResponse = findViewById(R.id.tv_responseI);
        tvResponse2 = findViewById(R.id.tv_response2I);
        tvResponse3 = findViewById(R.id.tv_response3I);
        tvResponse4 = findViewById(R.id.tv_response4I);
        tvResponse5 = findViewById(R.id.tv_response5I);

        tvFirst.setOnClickListener(this);
        tvSecond.setOnClickListener(this);
        tvThird.setOnClickListener(this);
        tvFourth.setOnClickListener(this);
        tvFifth.setOnClickListener(this);

        tvResponse.setMaxLines(MAX_LINES_COLLAPSED);
        tvResponse2.setMaxLines(MAX_LINES_COLLAPSED);
        tvResponse3.setMaxLines(MAX_LINES_COLLAPSED);
        tvResponse4.setMaxLines(MAX_LINES_COLLAPSED);
        tvResponse5.setMaxLines(MAX_LINES_COLLAPSED);

        applyLayoutTransition();
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.tv_firstI:

                tvResponse2.setMaxLines(MAX_LINES_COLLAPSED);
                tvResponse3.setMaxLines(MAX_LINES_COLLAPSED);
                tvResponse4.setMaxLines(MAX_LINES_COLLAPSED);
                tvResponse5.setMaxLines(MAX_LINES_COLLAPSED);

                if (isCollapsed) {
                    tvResponse.setMaxLines(Integer.MAX_VALUE);
                    tvResponse.setText(R.string.info1);

                } else {
                    tvResponse.setMaxLines(MAX_LINES_COLLAPSED);
                }
                isCollapsed = INITIAL_IS_COLLAPSED;
                break;

            case R.id.tv_secondI:

                tvResponse.setMaxLines(MAX_LINES_COLLAPSED);
                tvResponse3.setMaxLines(MAX_LINES_COLLAPSED);
                tvResponse4.setMaxLines(MAX_LINES_COLLAPSED);
                tvResponse5.setMaxLines(MAX_LINES_COLLAPSED);

                if (isCollapsed2) {
                    tvResponse2.setMaxLines(Integer.MAX_VALUE);
                    tvResponse2.setText(R.string.info2);

                } else {
                    tvResponse2.setMaxLines(MAX_LINES_COLLAPSED);
                }
                isCollapsed2 = INITIAL_IS_COLLAPSED2;
                break;

            case R.id.tv_thirdI:

                tvResponse.setMaxLines(MAX_LINES_COLLAPSED);
                tvResponse2.setMaxLines(MAX_LINES_COLLAPSED);
                tvResponse4.setMaxLines(MAX_LINES_COLLAPSED);
                tvResponse5.setMaxLines(MAX_LINES_COLLAPSED);

                if (isCollapsed3) {
                    tvResponse3.setMaxLines(Integer.MAX_VALUE);
                    tvResponse3.setText(R.string.info3);

                } else {
                    tvResponse3.setMaxLines(MAX_LINES_COLLAPSED);
                }
                isCollapsed3 = INITIAL_IS_COLLAPSED3;
                break;

            case R.id.tv_fourthI:

                tvResponse.setMaxLines(MAX_LINES_COLLAPSED);
                tvResponse2.setMaxLines(MAX_LINES_COLLAPSED);
                tvResponse3.setMaxLines(MAX_LINES_COLLAPSED);
                tvResponse5.setMaxLines(MAX_LINES_COLLAPSED);

                if (isCollapsed4) {
                    tvResponse4.setMaxLines(Integer.MAX_VALUE);
                    tvResponse4.setText(R.string.info4);

                } else {
                    tvResponse4.setMaxLines(MAX_LINES_COLLAPSED);
                }
                isCollapsed4 = INITIAL_IS_COLLAPSED4;
                break;

            case R.id.tv_fifthI:

                tvResponse.setMaxLines(MAX_LINES_COLLAPSED);
                tvResponse2.setMaxLines(MAX_LINES_COLLAPSED);
                tvResponse3.setMaxLines(MAX_LINES_COLLAPSED);
                tvResponse4.setMaxLines(MAX_LINES_COLLAPSED);

                if (isCollapsed5) {
                    tvResponse5.setMaxLines(Integer.MAX_VALUE);
                    tvResponse5.setText(R.string.info5);

                } else {
                    tvResponse5.setMaxLines(MAX_LINES_COLLAPSED);
                }
                isCollapsed5 = INITIAL_IS_COLLAPSED5;
                break;
        }
    }

    private void applyLayoutTransition() {

        LayoutTransition transition = new LayoutTransition();
        transition.setDuration(300);
        transition.enableTransitionType(LayoutTransition.CHANGING);
        clInfo.setLayoutTransition(transition);
    }
}