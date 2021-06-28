package com.example.ctsmarket05.retrofit.ordersRetrofit;

import android.widget.Toast;

import com.example.ctsmarket05.entities.Orders;
import com.example.ctsmarket05.entities.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OrderPOST {

    public void orderPost(Integer id_user, Integer order_price, Integer quantity_products, Integer order_state, Integer shipping, String date){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(User.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        OrderInterface orderInterface = retrofit.create(OrderInterface.class);

        Orders order = new Orders(id_user, order_price, quantity_products, order_state, shipping, date);

        Call<Orders> call = orderInterface.orderPost( order);

        call.enqueue(new Callback<Orders>() {
            @Override
            public void onResponse(Call<Orders> call, Response<Orders> response) {
                Orders ordersResponse = response.body();

                ordersResponse.setId_user(id_user);
                ordersResponse.setOrder_price(order_price);
                ordersResponse.setQuantity_products(quantity_products);
                ordersResponse.setOrder_state(order_state);
                ordersResponse.setShipping(shipping);
                ordersResponse.setDate(date);
            }

            @Override
            public void onFailure(Call<Orders> call, Throwable t) {

            }
        });
    }
}
