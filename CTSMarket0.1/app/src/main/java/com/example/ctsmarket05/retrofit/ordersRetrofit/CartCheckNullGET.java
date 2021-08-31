package com.example.ctsmarket05.retrofit.ordersRetrofit;

import com.example.ctsmarket05.entities.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CartCheckNullGET {

    private DataInterfaceCartCheck mListener;

    public void check(Integer id_user){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(User.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        OrderInterface orderInterface = retrofit.create(OrderInterface.class);

        Call<String> call = orderInterface.checkNullCart(id_user);
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

    public void SetOnDataInterfaceCartCheck(DataInterfaceCartCheck listener){
        mListener = listener;
    }

    public interface DataInterfaceCartCheck {
        void responseCheck(String check);
    }
}
