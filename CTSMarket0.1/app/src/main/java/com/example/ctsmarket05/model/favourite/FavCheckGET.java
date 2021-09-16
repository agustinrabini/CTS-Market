package com.example.ctsmarket05.model.favourite;

import com.example.ctsmarket05.entities.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FavCheckGET {

    private DataInterfaceFavCheck mListener;

    public void check(Integer id_user, Integer id_product){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(User.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FavouriteInterface favouriteInterface = retrofit.create(FavouriteInterface.class);

        Call<String> call = favouriteInterface.favCheck(id_user,id_product);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                mListener.responseFavCheck(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
            }
        });
    }

    public void SetOnDataListenerFavCheck(DataInterfaceFavCheck listener){
        mListener = listener;
    }

    public interface DataInterfaceFavCheck {
        void responseFavCheck(String check);
    }

}
