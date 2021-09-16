package com.example.ctsmarket05.view.activities.userActivities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ctsmarket05.R;
import com.example.ctsmarket05.view.activities.oneProductSequence.OPSActivity2;
import com.example.ctsmarket05.model.user.UserPUTInfo;
import com.google.android.material.textfield.TextInputEditText;

public class UserInfoEditActivity extends AppCompatActivity {

    private TextInputEditText etName;
    private TextInputEditText etDni;
    private TextInputEditText etPhone;
    private Button btnSave;
    private TextView tvCancelar;
    public static String from;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info_edit);

        findViews();
        btnSave();
        cancel();
    }

    private void backTo() {

        switch (from) {

            case "UserEditInfo": {
                Intent back = new Intent(this, UserInfoActivity.class);
                startActivity(back);
                finish();
            }
            break;

            case "boughtSequence":{
                Intent back = new Intent(this, OPSActivity2.class);
                startActivity(back);
                finish();
            }
            break;
        }
    }

    private void cancel() {

        tvCancelar.setOnClickListener(v -> {
           backTo();
        });
    }

    private void btnSave() {

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
                backTo();
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
