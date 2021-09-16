package com.example.ctsmarket05.model.orders;

import androidx.room.FtsOptions;

import com.example.ctsmarket05.entities.Orders;
import com.example.ctsmarket05.entities.ProductsOrder;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface OrderInterface {

    @POST("/order/opb/{user_id}/{product_id}")//sequencia de compra para un producto
    Call<Orders> oneProductBougth(@Path("user_id") int id_user,@Path("product_id") int id_product, @Body Orders order);

    @GET("/order/suo/{id}")
    Call<List<Orders>> showUserOrders(@Path("id") int id_user);

    @GET("/order/cno/{id_user}")//checkea si existe ordenes para mostrar
    Call<String> checkNullOrder(@Path("id_user") int id_user);

    @GET("/order/cnc/{id_user}")//checkea si existe carritos para mostrar
    Call<String> checkNullCart(@Path("id_user") int id_user);

    @GET("/order/showCart/{id}")//devuelve el carrito activo
    Call<Orders> getCart(@Path("id") int id_user);

    @PUT("/order/uobc/{id}")//'update order cart bought' ejecuta una orden de compra al carrito y lo convierte en orden terminada
    Call<Orders> updateOrderCartBought(@Body Orders order, @Path("id") int id_order);

    @POST("/order/cartAdd/{id_product}")
    Call<Orders> addCart(@Path("id_product") int id_product, @Body Orders order );

    @DELETE("/order/cartRemove/{user_id}/{product_id}")
    Call<Orders> removeCart(@Path("user_id") int id_user,@Path("product_id") int product_id);
}
