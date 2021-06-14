package com.example.ctsmarket05.activities.productsClasses;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ctsmarket05.R;
import com.example.ctsmarket05.activities.LocationAddActivity;
import com.example.ctsmarket05.entities.Product;
import com.example.ctsmarket05.retrofit.locationRetrofit.LocationGET;
import com.example.ctsmarket05.retrofit.userRetrofit.UserGET;

public class ProductsActivity3 extends AppCompatActivity {

    private TextView tvLocation;
    private TextView tvChangeLocation;
    private TextView tvNeedLocation;
    private TextView tvContinue;
    private TextView tvFinalPrice;
    private ImageView ivNeedLocation;
    private ImageView ivHome;
    private ImageView ivSellerHouse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products3);

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

        tvFinalPrice.setText("PRECIO: " + Product.PRICE.toString() + "$ARS");

        UserGET userGET = new UserGET();
        userGET.SetOnDataListenerUser(user -> {

            Integer idlocation = user.getId_location();

                if (idlocation != null) {

                    LocationGET locationGET = new LocationGET();
                    locationGET.SetOnDataListener(location -> {
                        tvContinue.setVisibility(View.VISIBLE);
                        tvLocation.setText(location.getProvince() + location.getCity() + location.getDistrict() + "\n"
                                + location.getStreet() + location.getStreet_number().toString() + "\n"
                                + location.getFloor() + ""+ location.getPostal_code().toString()
                        );
                    });
                    locationGET.getLocation();

                } else {
                   tvNeedLocation.setVisibility(View.VISIBLE);
                   ivNeedLocation.setVisibility(View.VISIBLE);
                   tvChangeLocation.setText("Agregar ubicación");
                }
        });
        userGET.getUserByGmail();
    }

    private void sendToHome(){
       // ivHome.setOnClickListener(v -> {
       //     //Intent home = new Intent(this, ProductsActivity4.class );
       //     //home.putExtra("method", "home");
       //     //startActivity(home);
       //     Toast.makeText(this, "No disponilbe, seleccione otro método", Toast.LENGTH_SHORT).show();
       // });
    }

    private void sendByMail(){
       // ivMail.setOnClickListener(v -> {
       //     Toast.makeText(this, "No disponilbe, seleccione otro método", Toast.LENGTH_SHORT).show();
       // });
    }

    private void retireAtSellerHome(){
        ivSellerHouse.setOnClickListener(v -> {

            Intent topProdActv3 = getIntent();
            String image = topProdActv3.getStringExtra("image");
            String name = topProdActv3.getStringExtra("name");
            String price = topProdActv3.getStringExtra("price");
            String quantity = topProdActv3.getStringExtra("quantity");

            Intent sellerHome = new Intent(this, ProductsActivity4.class);
            sellerHome.putExtra("sendingMethod", "sellerHome");
            sellerHome.putExtra("image", image);
            sellerHome.putExtra("name", name);
            sellerHome.putExtra("price", price);
            sellerHome.putExtra("quantity", quantity);
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
        tvContinue = findViewById(R.id.tv_continue3);
    }

}
