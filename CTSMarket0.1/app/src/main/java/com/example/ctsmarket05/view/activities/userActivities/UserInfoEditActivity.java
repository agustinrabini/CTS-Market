package com.example.ctsmarket05.view.activities.userActivities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ctsmarket05.R;
import com.example.ctsmarket05.base.BaseActivity;
import com.example.ctsmarket05.interfaces.UserInfoEditInterface;
import com.example.ctsmarket05.presenter.UserInfoEditPresenter;
import com.example.ctsmarket05.view.activities.oneProductSequence.OPSActivity2;
import com.example.ctsmarket05.model.UserInfoEditInteractor;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.ThreeBounce;
import com.google.android.material.textfield.TextInputEditText;

import org.jetbrains.annotations.NotNull;

public class UserInfoEditActivity extends BaseActivity<UserInfoEditPresenter> implements UserInfoEditInterface {

    private TextInputEditText etName;
    private TextInputEditText etDni;
    private TextInputEditText etPhone;
    private Button btnSave;
    private TextView tvCancelar;
    private TextView tvAlert;
    private TextView tvGeneric;
    private ProgressBar progressBarUIE;
    private int ligthBlueColor = Color.parseColor("#75AADB");

    @NotNull
    @Override
    protected UserInfoEditPresenter createPresenter(@NotNull Context context) {
        return new UserInfoEditPresenter(this, new UserInfoEditInteractor());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info_edit);

        findViews();
        btns();
    }

    private void btns() {

        tvCancelar.setOnClickListener(v -> {
            backTo();
        });

        btnSave.setOnClickListener(v -> {

            if(etName.getText().equals("") || etPhone.getText().toString().equals("") || etDni.getText().toString().equals("")){
                alert();
            }else{
                updateInfo(etName.getText().toString(), Integer.parseInt(etPhone.getText().toString()), Integer.parseInt(etDni.getText().toString()));
            }
        });
    }

    @Override
    public void updateInfo(String name, Integer phone, Integer dni) {
        presenterActivity.updateInfo(name, phone, dni);
    }

    @Override
    public void alert() {
        tvAlert.setVisibility(View.VISIBLE);
    }

    @Override
    public void backTo() {
        Intent from = getIntent();
        String f = from.getStringExtra("from");

        switch (f) {

            case "userEdit": {
                Intent back = new Intent(this, UserInfoActivity.class);
                startActivity(back);
                finish();
            }
            break;

            case "OPS":{
                Intent back = new Intent(this, OPSActivity2.class);
                startActivity(back);
                finish();
            }
            break;
        }
    }

    @Override
    public void hideLayout() {
        etName.setVisibility(View.INVISIBLE);
        etDni.setVisibility(View.INVISIBLE);
        etPhone.setVisibility(View.INVISIBLE);
        btnSave.setVisibility(View.INVISIBLE);
        tvCancelar.setVisibility(View.INVISIBLE);
        tvAlert.setVisibility(View.INVISIBLE);
        tvGeneric.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showProgressbar() {
        Sprite pb = new ThreeBounce();
        pb.setColor(ligthBlueColor);
        progressBarUIE.setIndeterminateDrawable(pb);
        progressBarUIE.setVisibility(View.VISIBLE);
    }

    @Override
    public void onError() {
        Toast.makeText(this, "Algo salío mal actualizando la información, por favor intente nuevamente.", Toast.LENGTH_LONG).show();
    }

    private void findViews() {
        etName = findViewById(R.id.et_name_add);
        etDni = findViewById(R.id.et_dni_add);
        etPhone = findViewById(R.id.et_phone_add);
        btnSave = findViewById(R.id.btn_save_add_user);
        tvCancelar = findViewById(R.id.tv_cancelar_add_user);
        tvAlert = findViewById(R.id.tv_alert_UIE);
        tvGeneric = findViewById(R.id.tv_generic_UIE);
        progressBarUIE = findViewById(R.id.pb_UIE);
    }
}
