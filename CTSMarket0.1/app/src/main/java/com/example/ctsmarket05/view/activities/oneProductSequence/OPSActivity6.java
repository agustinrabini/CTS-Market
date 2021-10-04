package com.example.ctsmarket05.view.activities.oneProductSequence;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ctsmarket05.R;
import com.example.ctsmarket05.view.activities.HomeActivity;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK;

public class OPSActivity6 extends AppCompatActivity {

    private TextView tvHome;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ops6);

        tvHome = findViewById(R.id.tv_home_ops6);

        tvHome.setOnClickListener(v -> {
            Intent finishBuySequence = new Intent(this, HomeActivity.class);
            finishBuySequence.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(finishBuySequence);
            finish();
        });
    }
}