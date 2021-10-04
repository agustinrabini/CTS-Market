package com.example.ctsmarket05.model.location;

import com.example.ctsmarket05.entities.Location;
import com.example.ctsmarket05.entities.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LocationAddPOST{

    public void locationPost(Location location) {

        //Retrofit retrofit = new Retrofit.Builder()
        //        .baseUrl(User.URL)
        //        .addConverterFactory(GsonConverterFactory.create())
        //        .build();
//
        //LocationInterface locationInterface = retrofit.create(LocationInterface.class);
//
        //Call<Location> call = locationInterface.locationInteraction(location);
//
        //call.enqueue(new Callback<Location>() {
//
        //    @Override
        //    public void onResponse(Call<Location> call, retrofit2.Response<Location> response) {
        //    }
//
        //    public void opsOnFailure(Call<Location> call, Throwable t) {
        //    }
        //});
    }
}
