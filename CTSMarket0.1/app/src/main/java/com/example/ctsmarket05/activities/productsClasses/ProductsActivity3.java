package com.example.ctsmarket05.activities.productsClasses;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ctsmarket05.R;
import com.example.ctsmarket05.activities.LocationAddActivity;
import com.example.ctsmarket05.entities.Orders;
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
                    String disctrict = location.getDistrict();
                    String street = location.getStreet();
                    String street_number = location.getStreet_number().toString();
                    String floor = location.getFloor();
                    String postal_code = location.getPostal_code().toString();

                    tvLocation.setText(province+" "+city+" "+disctrict+ "\n"+street+" "+street_number+" "+floor+ "\n"+postal_code);
                });
                locationGET.getLocation();
            }
        });
        userGET.getUserByGmail();

        Intent from = getIntent();
        String comingFrom = from.getStringExtra("from");

        //determina el precio segun la secuencia de compra
        switch (comingFrom){
            case "cartSequence":{
                tvFinalPrice.setText("Precio final de la orden: "+ Orders.ORDER_PRICE + "$ARS");
            }break;

            case "oneProductSequence":{
                tvFinalPrice.setText("PRECIO: " + Orders.ORDER_PRICE.toString() + "$ARS");
            }break;
        }
    }

    private void sendToHome(){}

    private void sendByMail(){}

    private void retireAtSellerHome(){

        ivSellerHouse.setOnClickListener(v -> {

            Intent from = getIntent();
            String image = from.getStringExtra("image");
            String name = from.getStringExtra("name");
            String price = from.getStringExtra("price");
            String quantity = from.getStringExtra("quantity");
            String comingFrom = from.getStringExtra("from");

            Intent sellerHome = new Intent(this, ProductsActivity4.class);
            sellerHome.putExtra("sendingMethod", "sellerHome");
            sellerHome.putExtra("image", image);
            sellerHome.putExtra("name", name);
            sellerHome.putExtra("price", price);
            sellerHome.putExtra("quantity", quantity);

            //determina que secuencia se ejecuta, si es la compra de un solo producto ó de un carrito
            String sequence = "";
            switch (comingFrom){

                case "cartSequence":{
                    sequence = "cartSequence";
                }break;

                case "oneProductSequence":{
                    sequence = "oneProductSequence";
                }break;
            }
            sellerHome.putExtra("sequence", sequence);
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
