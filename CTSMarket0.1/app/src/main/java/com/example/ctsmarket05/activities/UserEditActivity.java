package com.example.ctsmarket05.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ctsmarket05.R;
import com.example.ctsmarket05.activities.productsClasses.ProductsActivity4;
import com.example.ctsmarket05.retrofit.userRetrofit.UserPUTInfo;

public class UserEditActivity extends AppCompatActivity {

    private EditText etName;
    private EditText etDni;
    private EditText etPhone;
    private Button btnSave;
    private TextView tvCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_edit);

        findViews();
        updateUser();
    }

    private void updateUser() {

        btnSave.setOnClickListener(v -> {

            if(
                    etName.getText().toString().equals("") || etDni.getText().toString().equals("") || etPhone.getText().toString().equals("")
            ){
                Toast.makeText(this, "Verifique que todos los campos estén completos", Toast.LENGTH_LONG).show();
            }else{
                UserPUTInfo userPUTInfo = new UserPUTInfo();
                userPUTInfo.putInfo(
                        etName.getText().toString(),
                        Integer.parseInt(etDni.getText().toString()),
                        Integer.parseInt(etPhone.getText().toString())
                );
            }

            Intent toUserEdit = getIntent();
            String backTo = toUserEdit.getStringExtra("fromPA4");

            if (backTo.equals("fromPA4")) {
                Intent fromUE = new Intent(this, ProductsActivity4.class);
                fromUE.putExtra("fromUE","fromUE");
                startActivity(fromUE);
                finish();
            }
        });
    }

    private void findViews() {
        etName = findViewById(R.id.et_name_add);
        etDni = findViewById(R.id.et_dni_add);
        etPhone = findViewById(R.id.et_phone_add);
        btnSave = findViewById(R.id.btn_save_add_user);
        tvCancelar = findViewById(R.id.tv_cancelar_add_user);
    }

}
