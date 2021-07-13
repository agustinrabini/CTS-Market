package com.example.ctsmarket05.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ctsmarket05.R;
import com.example.ctsmarket05.retrofit.locationRetrofit.LocationGET;
import com.example.ctsmarket05.retrofit.userRetrofit.UserGET;

public class LocationInfoActivity extends AppCompatActivity {

    private TextView tvChangeLocation;
    private TextView tvLocation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_info);

        findViews();
        showInfo();
        changeLocation();
    }

    private void changeLocation(){
        tvChangeLocation.setOnClickListener(v -> {
            Intent toLocationAdd = new Intent(this, LocationAddActivity.class);
            toLocationAdd.putExtra("locationAdd","location_info");
            startActivity(toLocationAdd);
            finish();
        });
    }

    private void findViews() {

    tvChangeLocation = findViewById(R.id.tv_change_location_info);
    tvLocation = findViewById(R.id.tv_location_info);
    }

    private void showInfo() {

        UserGET userGET = new UserGET();
        userGET.SetOnDataListenerUser(user -> {

            Integer idlocation = user.getId_location();

            if (idlocation==null) {

                tvChangeLocation.setText("Agregar ubicación");

            }else{

                tvChangeLocation.setText("Editar ubicación");

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

    }

}
