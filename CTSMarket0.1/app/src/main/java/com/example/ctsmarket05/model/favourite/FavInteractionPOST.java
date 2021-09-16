package com.example.ctsmarket05.model.favourite;

import com.example.ctsmarket05.entities.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FavInteractionPOST {

    public void interaction(Integer id_user, Integer id_product){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(User.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FavouriteInterface favouriteInterface = retrofit.create(FavouriteInterface.class);

        Call<Void> call = favouriteInterface.favInteraction(id_user,id_product);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
            }
        });
    }
}
