package com.example.ctsmarket05.model;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ctsmarket05.entities.User;
import com.example.ctsmarket05.model.user.UserGET;
import com.example.ctsmarket05.model.user.UserInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserInfoEditInteractor{

    public interface onUserUpdated{
        void onSucces();
        void onFailure();
    }

    public void putInfo(Integer idUser, String name, Integer dni, Integer phone, final onUserUpdated listener){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(User.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserInterface userInterface = retrofit.create(UserInterface.class);

        User user = new User(name, dni, phone);

        Call<User> call = userInterface.userPUTInfo(user, idUser);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User userResponse = response.body();
                userResponse.setName_lastname(name);
                userResponse.setDni(dni);
                userResponse.setPhone(phone);

                if(!response.isSuccessful()){
                    listener.onFailure();
                    return;
                }else if (response.isSuccessful()){
                    listener.onSucces();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
               listener.onFailure();
            }
        });
    }
}
