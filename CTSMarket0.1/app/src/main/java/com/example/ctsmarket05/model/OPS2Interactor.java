package com.example.ctsmarket05.model;

import com.example.ctsmarket05.entities.User;
import com.example.ctsmarket05.model.user.UserInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OPS2Interactor {

    public interface Interactor {
        void onSucces(User user);
        void onFailure();
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

                if (user !=null){
                    listener.onSucces(user);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                listener.onFailure();
            }
        });
    }
}
