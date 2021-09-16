package com.example.ctsmarket05.model.location;

import com.example.ctsmarket05.entities.Location;
import com.example.ctsmarket05.entities.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LocationGET {

    private DataInterface mListener;

    public void getLocation(Integer id_user) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(User.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        LocationInterface locationInterface = retrofit.create(LocationInterface.class);

        Call<Location> call = locationInterface.locationUser(id_user);

        call.enqueue(new Callback<Location>() {
            @Override
            public void onResponse(Call<Location> call, Response<Location> response) {

                mListener.responseLocation(response.body());
            }

            @Override
            public void onFailure(Call<Location> call, Throwable t) {

            }
        });
    }

    public void SetOnDataListener(DataInterface listener) {
        mListener = listener;
    }

    public interface DataInterface {
        void responseLocation(Location location);
    }
}