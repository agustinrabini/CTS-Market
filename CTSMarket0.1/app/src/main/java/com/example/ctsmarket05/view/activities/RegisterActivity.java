package com.example.ctsmarket05.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.ctsmarket05.R;

public class RegisterActivity extends AppCompatActivity {
private View imgBtnBack;
private View btnJoin;
private EditText editTextNombre;
private EditText editTextEmail;
private EditText editTextPassword1;
private EditText editTextPassword2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);



        imgBtnBack =(ImageButton) findViewById(R.id.imgBtnBack);
        btnJoin=(Button) findViewById(R.id.btnJoin);
        editTextNombre = (EditText) findViewById(R.id.editTextNombre);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword1 = (EditText) findViewById(R.id.editTextPassword1);
        editTextPassword2 = (EditText) findViewById(R.id.editTextPassword2);


        imgBtnBack.setOnClickListener(v -> {
            Intent GoBack = new Intent(RegisterActivity.this, MainActivity.class);
            startActivity(GoBack);
        });

        btnJoin.setOnClickListener(v -> {

            if (editTextPassword1!=editTextPassword2) {
                Toast.makeText(getApplicationContext(),"Las contraseñas deben coincidir",Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(getApplicationContext(),"Usuario registrado con exito",Toast.LENGTH_SHORT).show();
            }


            Intent i = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(i);
        });


    }


}