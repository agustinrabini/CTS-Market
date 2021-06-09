package com.example.ctsmarket05.retrofit.userRetrofit;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ctsmarket05.entities.User;
import com.example.ctsmarket05.retrofit.locationRetrofit.LocationGETbyIdUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserPUTIdLoc extends AppCompatActivity {
    //UPDATE a la DB para asignar idLocation
    public void userPut(){
        getLocationId();
    }
    //
    private void PUT(Integer id_location){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(User.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserInterface userInterface = retrofit.create(UserInterface.class);

        User user = new User(id_location);

        Call<User> call = userInterface.userPUTIdLoc(user, User.IDUSER);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User userResponse = response.body();
                userResponse.setId_location(id_location);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(UserPUTIdLoc.this, "Error:" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    //retrofit call para obtener el id_location que se va a insertar en la tabla User.
    private void getLocationId(){
        LocationGETbyIdUser locationGETbyIdUser = new LocationGETbyIdUser();
        locationGETbyIdUser.SetOnDataListenerLocGetById(location ->{
            Integer id = location.getId_location();
            PUT(id);
        });
        locationGETbyIdUser.getLocationByIdUser();
    }

}
