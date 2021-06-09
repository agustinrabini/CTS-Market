package com.example.ctsmarket05.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ctsmarket05.R;

public class LogginActivity extends AppCompatActivity {
    private View imgBtnBack;
    private View btnJoin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loggin);

        imgBtnBack = findViewById(R.id.imgBtnBack);
        btnJoin= findViewById(R.id.btnJoin);

        imgBtnBack.setOnClickListener(v -> {
            Intent GoBack = new Intent(LogginActivity.this, MainActivity.class);
            startActivity(GoBack);
        });

        btnJoin.setOnClickListener(v -> {

            Intent Logg = new Intent(LogginActivity.this, HomeActivity.class);
            startActivity(Logg);
        });
    }
}