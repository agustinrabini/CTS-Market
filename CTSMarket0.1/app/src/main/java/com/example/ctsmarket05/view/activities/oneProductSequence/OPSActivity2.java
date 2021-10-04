package com.example.ctsmarket05.view.activities.oneProductSequence;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.ctsmarket05.R;
import com.example.ctsmarket05.base.BaseActivity;
import com.example.ctsmarket05.entities.User;
import com.example.ctsmarket05.interfaces.OPS2ActivityInterface;
import com.example.ctsmarket05.model.OPS2Interactor;
import com.example.ctsmarket05.presenter.OPS2ActivityPresenter;
import com.example.ctsmarket05.view.activities.userActivities.UserInfoEditActivity;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.ThreeBounce;

import org.jetbrains.annotations.NotNull;

public class OPSActivity2 extends BaseActivity<OPS2ActivityPresenter> implements OPS2ActivityInterface {

    private TextView tvUser;
    private TextView tvChangeUserInfo;
    private TextView tvAlert;
    private TextView tvError;
    private TextView tvGeneric;
    private TextView tvGeneric2;
    private TextView tvGeneric3;
    private ImageView ivGeneric;
    private ImageView ivGeneric2;
    private Button btnBuy;
    private ProgressBar progressBarPA3;
    private int ligthBlueColor = Color.parseColor("#75AADB");

    @NotNull
    @Override
    protected OPS2ActivityPresenter createPresenter(@NotNull Context context) {
        return new OPS2ActivityPresenter(this, new OPS2Interactor());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ops2);

        findViews();
        changeUserInfo();
        next();
        fetchUserData();
    }

    private void changeUserInfo(){
        tvChangeUserInfo.setOnClickListener(v -> {
            changeClicked();
        });
    }

    private void next(){
        btnBuy.setOnClickListener(v -> {
          nextActivity();
        });
    }

    private void findViews(){
        tvChangeUserInfo = findViewById(R.id.tv_change_user_info_ops2);
        tvUser = findViewById(R.id.tv_user_info_ops2);
        tvAlert = findViewById(R.id.tv_add_user_info_ops2);
        tvError = findViewById(R.id.tv_error_ops2);
        btnBuy = findViewById(R.id.btn_buy_ops2);
        progressBarPA3 = findViewById(R.id.pb_ops2);
        tvError= findViewById(R.id.tv_error_ops2);;
        tvGeneric= findViewById(R.id.tv_generic_ops2);;
        tvGeneric2= findViewById(R.id.tv_generic2_ops2);;
        tvGeneric3= findViewById(R.id.tv_generic3_ops2);;
        ivGeneric= findViewById(R.id.iv_generic_ops2);;
        ivGeneric2= findViewById(R.id.iv_generic2_ops2);;
    }

    @Override
    public void setLayoutVisible() {
        tvUser.setVisibility(View.VISIBLE);
        tvChangeUserInfo.setVisibility(View.VISIBLE);
        tvGeneric.setVisibility(View.VISIBLE);
        tvGeneric2.setVisibility(View.VISIBLE);
        ivGeneric.setVisibility(View.VISIBLE);
        ivGeneric2.setVisibility(View.VISIBLE);
        tvGeneric3.setVisibility(View.VISIBLE);
    }

    @Override
    public void showProgressBar() {
        Sprite pb = new ThreeBounce();
        pb.setColor(ligthBlueColor);
        progressBarPA3.setIndeterminateDrawable(pb);
        progressBarPA3.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBarPA3.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setUserData(User user) {
        tvUser.setText(
                "Entrega a nombre de: " + user.getName_lastname() + "\n" + "\n" + "\n" +"Número de contacto:  "+ user.getPhone().toString() + "\n" + "\n" + "\n"+ "Documento de quien recibe: " + user.getDni().toString()
        );
    }

    @Override
    public void fetchUserData() {
        presenterActivity.fetchUserData();
    }

    @Override
    public void changeClicked() {
        Intent from = new Intent(this, UserInfoEditActivity.class);
        from.putExtra("from", "OPS");
        startActivity(from);
        finish();
    }

    @Override
    public void dataFounded() {
        btnBuy.setVisibility(View.VISIBLE);
        tvChangeUserInfo.setText("Editar información");
    }

    @Override
    public void dataNotFounded() {
        tvAlert.setVisibility(View.VISIBLE);
        tvChangeUserInfo.setText("Añadir información");
    }

    @Override
    public void nextActivity() {
        Intent toProdActv5 = new Intent(this, OPSActivity3.class);
        startActivity(toProdActv5);
    }

    @Override
    public void onError() {
        tvError.setVisibility(View.VISIBLE);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}