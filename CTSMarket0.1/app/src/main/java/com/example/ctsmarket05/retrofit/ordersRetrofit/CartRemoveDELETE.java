package com.example.ctsmarket05.retrofit.ordersRetrofit;

import com.example.ctsmarket05.entities.Orders;
import com.example.ctsmarket05.entities.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CartRemoveDELETE {

    public void deleteCart(Integer id_order, Integer id_user, Integer id_product, Integer quantity_products){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(User.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        OrderInterface orderInterface = retrofit.create(OrderInterface.class);

        Call<Orders> call = orderInterface.removeCart(id_order, id_user, id_product, quantity_products);
        call.enqueue(new Callback<Orders>() {
            @Override
            public void onResponse(Call<Orders> call, Response<Orders> response) {
            }

            @Override
            public void onFailure(Call<Orders> call, Throwable t) {

            }
        });
    }
}
