package com.example.ctsmarket05.view.activities.oneProductSequence;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.ctsmarket05.R;
import com.example.ctsmarket05.base.BaseActivity;
import com.example.ctsmarket05.entities.Location;
import com.example.ctsmarket05.entities.Orders;
import com.example.ctsmarket05.interfaces.OPS3ActivityInterface;
import com.example.ctsmarket05.model.OPS3Interactor;
import com.example.ctsmarket05.presenter.OPS3ActivityPresenter;
import com.example.ctsmarket05.view.activities.LocationAddActivity;
import com.example.ctsmarket05.view.fragments.bottomSheets.InfoOPS3BottomSheet;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.ThreeBounce;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

public class OPSActivity3 extends BaseActivity<OPS3ActivityPresenter> implements OPS3ActivityInterface {

    private TextView tvLocation;
    private TextView tvChangeLocation;
    private TextView tvNeedLocation;
    private TextView tvSendCost;
    private TextView tvContinue;
    private TextView tvFinalPrice;
    private TextView tvGeneric;
    private TextView tvGeneric2;
    private TextView tvGeneric3;
    private TextView tvGeneric4;
    private TextView tvGeneric5;
    private TextView tvError;
    private ImageView ivSend;
    private ImageView ivTaller;
    private ImageView ivTallerInfo;
    private ImageView ivGeneric;
    private ImageView ivGeneric2;
    private ImageView ivGeneric3;
    private ImageView ivGeneric4;
    private ProgressBar progressBar;
    private int ligthBlueColor = Color.parseColor("#75AADB");

    @NotNull
    @Override
    protected OPS3ActivityPresenter createPresenter(@NotNull Context context) {
        return new OPS3ActivityPresenter(this, new OPS3Interactor());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ops3);

        findViews();
        fetchLocation();
        clickListeners();
    }

    private void clickListeners() {

        ivTallerInfo.setOnClickListener(v -> {
          info();
        });

        tvChangeLocation.setOnClickListener(v -> {
            changeLocation();
        });

        ivTaller.setOnClickListener(v -> {
            retireAtTaller();
            Intent ops4 = new Intent(this, OPSActivity4.class);
            startActivity(ops4);
        });

        ivSend.setOnClickListener(v -> {
            sendToCustomer();
            Intent ops4 = new Intent(this, OPSActivity4.class);
            startActivity(ops4);
        });
    }

    @Override
    public void fetchLocation() {
        presenterActivity.fetchUserData(getBaseContext());
    }

    @Override
    public void setLocation(Location location) {
        tvLocation.setText(
                location.getProvince() + " " +
                location.getCity() + "\n"+"\n"+
                location.getStreet() +
                location.getStreet_number() + "," + "\n" +"\n"+
                "CP: " + location.getPostal_code() + ", " +
                location.getDistrict()+ "\n"+"\n"+
                location.getFloor());
    }

    @Override
    public void noLocation() {
        tvNeedLocation.setVisibility(View.VISIBLE);
        tvLocation.setText("No hay información de envío");
        ivSend.setVisibility(View.VISIBLE);
        ivSend.setEnabled(false);
    }

    @Override
    public void changeLocation(){
        Intent toLocationAdd = new Intent(this, LocationAddActivity.class);
        toLocationAdd.putExtra("locationAdd","fromPA3");
        startActivity(toLocationAdd);
        finish();
    }

    @Override
    public void orderPrice(Integer price) {
        tvFinalPrice.setText("Precio del pedido: $ARS" + price.toString());
    }

    @Override
    public void info() {
        InfoOPS3BottomSheet infoOPS3BottomSheet = new InfoOPS3BottomSheet();
        infoOPS3BottomSheet.show(getSupportFragmentManager(), "");
    }

    @Override
    public void retireAtTaller() {
        presenterActivity.retireTaller();
    }

    @Override
    public void sendToCustomer() {
        presenterActivity.sendToCustomer();
    }

    @Override
    public void setShippingCost(String priceShipping) {
       tvSendCost.setText(priceShipping);
    }

    @Override
    public void showPb() {
        Sprite pb = new ThreeBounce();
        pb.setColor(ligthBlueColor);
        progressBar.setIndeterminateDrawable(pb);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hidePb() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setLayoutVisible() {
        ivTallerInfo.setVisibility(View.VISIBLE);
        tvFinalPrice.setVisibility(View.VISIBLE);
        tvContinue.setVisibility(View.VISIBLE);
        tvLocation.setVisibility(View.VISIBLE);
        tvChangeLocation.setVisibility(View.VISIBLE);
        tvSendCost.setVisibility(View.VISIBLE);
        ivTaller.setVisibility(View.VISIBLE);
        ivSend.setVisibility(View.VISIBLE);
        ivTallerInfo.setVisibility(View.VISIBLE);
        ivGeneric.setVisibility(View.VISIBLE);
        ivGeneric2.setVisibility(View.VISIBLE);
        ivGeneric3.setVisibility(View.VISIBLE);
        ivGeneric4.setVisibility(View.VISIBLE);
        tvGeneric.setVisibility(View.VISIBLE);
        tvGeneric2.setVisibility(View.VISIBLE);
        tvGeneric3.setVisibility(View.VISIBLE);
        tvGeneric4.setVisibility(View.VISIBLE);
        tvGeneric5.setVisibility(View.VISIBLE);
    }

    @Override
    public void nextActivity() {

    }

    @Override
    public void onError() {
        tvError.setVisibility(View.VISIBLE);
    }

    private void findViews(){
        tvFinalPrice = findViewById(R.id.tv_final_price_ops3);
        tvChangeLocation = findViewById(R.id.tv_change_location_ops3);
        tvSendCost = findViewById(R.id.tv_send_cost_ops3);
        tvLocation = findViewById(R.id.tv_location_ops3);
        tvNeedLocation = findViewById(R.id.tv_add_location_ops3);
        ivTaller = findViewById(R.id.iv_taller_ops3);
        ivTallerInfo = findViewById(R.id.iv_info_ops3);
        ivSend = findViewById(R.id.iv_send_ops3);
        tvContinue = findViewById(R.id.tv_continue3);
        tvError = findViewById(R.id.tv_error_ops3);
        progressBar = findViewById(R.id.pb_ops3);

        ivGeneric = findViewById(R.id.iv_generic_ops3);
        ivGeneric2 = findViewById(R.id.iv_generic2_ops3);
        ivGeneric3 = findViewById(R.id.iv_generic3_ops3);
        ivGeneric4 = findViewById(R.id.iv_generic4_ops3);

        tvGeneric = findViewById(R.id.tv_generic_ops3);
        tvGeneric2 = findViewById(R.id.tv_generic2_ops3);
        tvGeneric3 = findViewById(R.id.tv_generic3_ops3);
        tvGeneric4= findViewById(R.id.tv_generic4_ops3);
        tvGeneric5= findViewById(R.id.tv_generic5_ops3);
    }
}
