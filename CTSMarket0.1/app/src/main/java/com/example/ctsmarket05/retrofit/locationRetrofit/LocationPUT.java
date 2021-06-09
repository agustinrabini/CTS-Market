package com.example.ctsmarket05.retrofit.locationRetrofit;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ctsmarket05.entities.Location;
import com.example.ctsmarket05.entities.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LocationPUT extends AppCompatActivity {
    //se le pasan los valores tomados de los editText en LocationAdd.
    //se crea un nuevo objeto que se envia por la call.
    public void locationPUT(String province, String city, String district, String street, Integer street_number, String floor, Integer postal_code) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(User.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        LocationInterface locationInterface = retrofit.create(LocationInterface.class);

        Location location = new Location(province, city, district, street, street_number, floor,postal_code);

        Call<Location> call = locationInterface.locationPUT(location,Location.idLocation);

        call.enqueue(new Callback<Location>() {

            @Override
            public void onResponse(Call<Location> call, Response<Location> response) {
                Location locationResponse = response.body();
                locationResponse.setProvince(province);
                locationResponse.setStreet(street);
                locationResponse.setStreet_number(street_number);
                locationResponse.setCity(city);
                locationResponse.setPostal_code(postal_code);
                locationResponse.setFloor(floor);
                locationResponse.setDistrict(district);
            }

            public void onFailure(Call<Location> call, Throwable t) {
                Toast.makeText(LocationPUT.this, "Error:" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
