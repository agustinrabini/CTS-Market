package com.example.ctsmarket05.model.user;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ctsmarket05.entities.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserPUTInfo extends AppCompatActivity {
    //se le pasan los valores tomados de los editText en UserAddActivity.
    //se crea un nuevo objeto que se envia por la call.
    //el usuario solo puede editar cierta info.
    public void putInfo(String name, Integer dni, Integer phone){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(User.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserInterface userInterface = retrofit.create(UserInterface.class);

        User user = new User(name, dni, phone);

        Call<User> call = userInterface.userPUTInfo(user, User.IDUSER);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User userResponse = response.body();
                userResponse.setName_lastname(name);
                userResponse.setDni(dni);
                userResponse.setPhone(phone);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(UserPUTInfo.this, "Error:" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
