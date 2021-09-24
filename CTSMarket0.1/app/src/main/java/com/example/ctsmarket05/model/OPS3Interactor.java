package com.example.ctsmarket05.model;

import android.util.Log;

import com.example.ctsmarket05.entities.Location;
import com.example.ctsmarket05.entities.User;
import com.example.ctsmarket05.model.location.LocationInterface;
import com.example.ctsmarket05.model.user.UserInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OPS3Interactor {

    public interface Interactor {
        void onSucces(Location location);
        void onFailure();
        void nullLocation();
    }

    public void getUser(Integer idUser, final Interactor listener){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(User.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserInterface userInterface= retrofit.create(UserInterface.class);

        Call<User> call = userInterface.getUser(idUser);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if(!response.isSuccessful()){
                    listener.onFailure();
                    return;
                }

                User user = response.body();

                if (user !=null && user.getId_location()!= null){

                    getLocation(user.getId_location(),listener);

                }else if(user.getId_location()== null){

                    listener.nullLocation();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                listener.onFailure();
            }
        });
    }

    public void getLocation(Integer idLocation, final Interactor listener) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(User.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        LocationInterface locationInterface = retrofit.create(LocationInterface.class);

        Call<Location> call = locationInterface.locationId(idLocation);

        call.enqueue(new Callback<Location>() {
            @Override
            public void onResponse(Call<Location> call, Response<Location> response) {

                if(!response.isSuccessful()){
                    listener.onFailure();
                    return;
                }

                Location location = response.body();

                if (location !=null){
                    listener.onSucces(location);
                }
            }

            @Override
            public void onFailure(Call<Location> call, Throwable t) {
                listener.onFailure();
            }
        });
    }
}
