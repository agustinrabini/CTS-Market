package com.example.ctsmarket05.retrofit.ordersRetrofit;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ctsmarket05.entities.Orders;
import com.example.ctsmarket05.entities.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CartAddPOST extends AppCompatActivity {

    public void addCart(Integer id_product, Integer id_user, Integer order_price, Integer quantity_products){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(User.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        OrderInterface orderInterface = retrofit.create(OrderInterface.class);

        Orders orders = new Orders(id_user,order_price, quantity_products, 10, null,"");

        Call<Orders> call = orderInterface.addCart(id_product, orders);
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
