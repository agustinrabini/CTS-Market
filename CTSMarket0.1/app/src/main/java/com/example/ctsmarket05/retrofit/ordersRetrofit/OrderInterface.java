package com.example.ctsmarket05.retrofit.ordersRetrofit;

import com.example.ctsmarket05.entities.Orders;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface OrderInterface {

    @POST("order/opfc/{id}")
    Call<Orders> cartOrder(@Path("id") int id_user, @Body Orders order);

    @POST("orderAdd")
    Call<Orders> orderPost(@Body Orders order);

    @GET("order/suo/{id}")
    Call<List<Orders>> showUserOrders(@Path("id") int id_user);

    @GET("order/{id}")
    Call<Orders> getOrder(@Path("id") int id_order);

    @GET("order/sopo/{id}")
    Call<Orders> getOrderIdForProductsOrder(@Path("id") int id_user);

    @GET("order/showCart/{id}")
    Call<Orders> getCart(@Path("id") int id_user);

    @PUT("order/uopa/{id}")//'update order product added' actualiza el order_price al añadir un producto.
    Call<Orders> updateOrderPrice(@Body Orders order, @Path("id") int id_order);
}
