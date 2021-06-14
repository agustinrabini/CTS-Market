package com.example.ctsmarket05.retrofit.ordersRetrofit;

import com.example.ctsmarket05.entities.Orders;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface OrderInterface {

    @POST("orderAdd")
    Call<Orders> postOrder(@Body Orders order);
}
