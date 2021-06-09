package com.example.ctsmarket05.retrofit.locationRetrofit;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ctsmarket05.entities.Location;
import com.example.ctsmarket05.entities.User;
import com.example.ctsmarket05.retrofit.userRetrofit.UserGET;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LocationGETbyIdUser extends AppCompatActivity  {

    private DataInterfaceLocGetById mListener;
    //call a la tabla Location
    public void getLocationByIdUser(){
        setIdUser();
    }

    private void get(Integer IdUser){

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(User.URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            LocationInterface locationInterface = retrofit.create(LocationInterface.class);

            Call<Location> call = locationInterface.locationByIdUser(IdUser);

            call.enqueue(new Callback<Location>() {
                @Override
                public void onResponse(Call<Location> call, Response<Location> response) {

                    if(response.isSuccessful() && response.body()!=null){
                        mListener.responseLocGetById(response.body());
                    }
                    else{
                        Toast.makeText(LocationGETbyIdUser.this, "Error:" + response.code(), Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<Location> call, Throwable t) {
                    Toast.makeText(LocationGETbyIdUser.this, "Error:" + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
    }

    public void SetOnDataListenerLocGetById(DataInterfaceLocGetById listener){
        mListener = listener;
    }

    public interface DataInterfaceLocGetById {
        void responseLocGetById(Location location);
    }

    private void setIdUser(){
        UserGET userGET = new UserGET();
        userGET.SetOnDataListenerUser(user -> {
            Integer IdUser = user.getId_user();
            get(IdUser);
        });
        userGET.getUserByGmail();
    }

}

