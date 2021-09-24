package com.example.ctsmarket05.model;

import com.example.ctsmarket05.entities.Location;
import com.example.ctsmarket05.entities.User;
import com.example.ctsmarket05.model.location.LocationInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LocationAddInteractor {

    public interface onLocationUpdated{
        void onSucces();
        void onFailure();
    }

    public void locationInteraction(Location location, final onLocationUpdated listener) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(User.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        LocationInterface locationInterface = retrofit.create(LocationInterface.class);

        Call<Void> call = locationInterface.locationInteraction(location);

        call.enqueue(new Callback<Void>() {

            @Override
            public void onResponse(Call<Void> call, retrofit2.Response<Void> response) {

                if (response.isSuccessful()){
                    listener.onSucces();
                }
            }

            public void onFailure(Call<Void> call, Throwable t) {
                listener.onFailure();
            }
        });
    }
}
