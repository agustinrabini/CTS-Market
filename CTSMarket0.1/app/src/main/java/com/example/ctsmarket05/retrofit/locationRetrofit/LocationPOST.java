package com.example.ctsmarket05.retrofit.locationRetrofit;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ctsmarket05.entities.Location;
import com.example.ctsmarket05.entities.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LocationPOST extends AppCompatActivity {

    public void locationPost(Integer id_user, String province, String city, String district, String street, Integer street_number, String floor, Integer postal_code) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(User.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        LocationInterface locationInterface = retrofit.create(LocationInterface.class);

        Location location = new Location(id_user, province, city, district, street, street_number, floor,postal_code);

        Call<Location> call = locationInterface.postLocation(location);

        call.enqueue(new Callback<Location>() {

            @Override
            public void onResponse(Call<Location> call, retrofit2.Response<Location> response) {
                Location locationResponse = response.body();

                locationResponse.setId_user(id_user);
                locationResponse.setProvince(province);
                locationResponse.setStreet(street);
                locationResponse.setStreet_number(street_number);
                locationResponse.setCity(city);
                locationResponse.setPostal_code(postal_code);
                locationResponse.setFloor(floor);
                locationResponse.setDistrict(district);
            }

            public void onFailure(Call<Location> call, Throwable t) {
                Toast.makeText(LocationPOST.this, "Error:" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
