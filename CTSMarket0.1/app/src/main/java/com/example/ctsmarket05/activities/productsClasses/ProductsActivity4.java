package com.example.ctsmarket05.activities.productsClasses;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ctsmarket05.R;
import com.example.ctsmarket05.activities.LocationAddActivity;
import com.example.ctsmarket05.activities.bottomSheets.InfoProdActv3BottomSheet;
import com.example.ctsmarket05.entities.Orders;
import com.example.ctsmarket05.entities.User;
import com.example.ctsmarket05.retrofit.locationRetrofit.LocationGET;
import com.example.ctsmarket05.retrofit.userRetrofit.UserGET;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.ThreeBounce;

public class ProductsActivity4 extends AppCompatActivity {

    private TextView tvLocation;
    private TextView tvChangeLocation;
    private TextView tvNeedLocation;
    private TextView tvContinue;
    private TextView tvFinalPrice;
    private ImageView ivNeedLocation;
    private ImageView ivHome;
    private ImageView ivSellerHouse;
    private ImageView ivTallerInfo;
    private ProgressBar progressBarProdActv4;
    private int ligthBlueColor = Color.parseColor("#75AADB");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products4);

        findViews();
        showInfo();
        changeLocation();
        sendToHome();
        sendByMail();
        retireAtSellerHome();
    }

    private void changeLocation(){
        tvChangeLocation.setOnClickListener(v -> {

            Intent toLocationAdd = new Intent(this, LocationAddActivity.class);
            toLocationAdd.putExtra("locationAdd","fromPA3");
            startActivity(toLocationAdd);
            finish();
        });
    }

    private void showInfo() {

        Sprite pb = new ThreeBounce();
        pb.setColor(ligthBlueColor);
        progressBarProdActv4.setIndeterminateDrawable(pb);
        ivSellerHouse.setEnabled(false);

        UserGET userGET = new UserGET();
        userGET.SetOnDataListenerUser(user -> {

            Integer idlocation = user.getId_location();

            if (idlocation==null) {

                tvNeedLocation.setVisibility(View.VISIBLE);
                ivNeedLocation.setVisibility(View.VISIBLE);
                tvChangeLocation.setText("Agregar ubicación");

            }else{
               tvContinue.setVisibility(View.VISIBLE);

                LocationGET locationGET = new LocationGET();
                locationGET.SetOnDataListener(location -> {

                    String province = location.getProvince();
                    String city = location.getCity();
                    String district = location.getDistrict();
                    String street = location.getStreet();
                    String street_number = location.getStreet_number().toString();
                    String floor = location.getFloor();
                    String postal_code = location.getPostal_code().toString();

                    tvLocation.setText("Provincia: " + province + "\n"+ "\n"+ "Ciudad: " + city + "\n"+ "\n"+
                             "Localidad: " + district + "\n"+ "\n"+ "Calle: " +street + " " + street_number+". "+floor + "\n" + "\n"+  "Código postal: "+postal_code);
                });
                locationGET.getLocation(User.IDUSER);
            }
        });
        userGET.getUserByGmail();

        new CountDownTimer(1500, 1000) {

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                progressBarProdActv4.setVisibility(View.INVISIBLE);
                tvLocation.setVisibility(View.VISIBLE);
                tvFinalPrice.setVisibility(View.VISIBLE);
                ivSellerHouse.setEnabled(true);
            }
        }.start();

        tvFinalPrice.setText("Precio: "+ Orders.ORDER_PRICE + "$ARS");
    }

    private void sendToHome(){}

    private void sendByMail(){}

    private void retireAtSellerHome(){

        ivTallerInfo.setOnClickListener(v -> {

            InfoProdActv3BottomSheet infoProdActv3BottomSheet = new InfoProdActv3BottomSheet();
            infoProdActv3BottomSheet.show(getSupportFragmentManager(), "");
        });

        ivSellerHouse.setOnClickListener(v -> {

            Orders.ORDER_SHIPPING = 1;
            Intent sellerHome = new Intent(this, ProductsActivity5.class);
            startActivity(sellerHome);
        });
    }

    private void findViews(){

        tvFinalPrice = findViewById(R.id.tv_final_price3);
        tvChangeLocation = findViewById(R.id.tv_change_location3);
        tvLocation = findViewById(R.id.tv_location3);
        tvNeedLocation = findViewById(R.id.tv_need_to_ad_location3);
        ivNeedLocation = findViewById(R.id.iv_need_to_add_location3);
        ivHome = findViewById(R.id.iv_home);
        ivSellerHouse = findViewById(R.id.iv_seller_house);
        ivTallerInfo = findViewById(R.id.iv_taller_info3);
        tvContinue = findViewById(R.id.tv_continue3);
        progressBarProdActv4 = findViewById(R.id.pb_prod_actv4);
    }
}
