package com.example.ctsmarket05.retrofit.favouriteRetrofit;

import com.example.ctsmarket05.entities.User;
import com.example.ctsmarket05.retrofit.ordersRetrofit.OrderCheckNullGET;
import com.example.ctsmarket05.retrofit.ordersRetrofit.OrderInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FavNullCheckGET {

    private DataInterfaceFavCheck mListener;

    public void check(Integer id_user){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(User.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FavouriteInterface favouriteInterface = retrofit.create(FavouriteInterface.class);

        Call<String> call = favouriteInterface.nullFavCheck(id_user);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                mListener.responseCheck(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
            }
        });
    }

    public void SetOnDataInterfaceFavCheck(DataInterfaceFavCheck listener){
        mListener = listener;
    }

    public interface DataInterfaceFavCheck {
        void responseCheck(String check);
    }
}
