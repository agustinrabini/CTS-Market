package com.example.ctsmarket05.model.favourite;

import com.example.ctsmarket05.entities.Favourite;
import com.example.ctsmarket05.entities.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FavsGET{

    public interface onFavsFetched{
        void onSucces(List<Favourite> favouriteList);
        void onFailure();
        void nullFavs(String message);
    }

    public void getFavs(Integer idUser, final onFavsFetched listener) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(User.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FavouriteInterface favouriteInterface = retrofit.create(FavouriteInterface.class);

        Call<List<Favourite>> call = favouriteInterface.showUserFavs(idUser);
        call.enqueue(new Callback<List<Favourite>>() {
            @Override
            public void onResponse(Call<List<Favourite>> call, Response<List<Favourite>> response) {

                if(!response.isSuccessful()){
                    listener.onFailure();
                    return;
                }

                List<Favourite> favouriteList = response.body();

                if (favouriteList != null){
                    listener.onSucces(favouriteList);

                }else if(favouriteList ==null){
                    listener.nullFavs("No tenés ningún producto en favoritos, cuando agregues uno aparecerá en esta sección.");
                }
            }

            @Override
            public void onFailure(Call<List<Favourite>> call, Throwable t) {
                listener.onFailure();
            }
        });
    }
}
