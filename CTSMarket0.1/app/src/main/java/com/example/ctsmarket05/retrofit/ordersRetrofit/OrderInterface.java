package com.example.ctsmarket05.retrofit.ordersRetrofit;

import com.example.ctsmarket05.entities.Orders;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface OrderInterface {

    @POST("orderAdd")
    Call<Orders> postOrder(@Body Orders order);

    @GET("/order/suo/{id}")
    Call<List<Orders>> showUserOrders(@Path("id") int id_user);
}
