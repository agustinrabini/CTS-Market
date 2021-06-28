package com.example.ctsmarket05.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ctsmarket05.R;

public class TalleresActivity extends AppCompatActivity {

    private Button button;
    private EditText et_email;
    private EditText et_dni;
    private EditText et_name;
    private EditText etPostalCode;
    private EditText etFloor;
    TextView textView56;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talleres);

        button = findViewById(R.id.button);
        textView56 = findViewById(R.id.textView56);

    }

}
