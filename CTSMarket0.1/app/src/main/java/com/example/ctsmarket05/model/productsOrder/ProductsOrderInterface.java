package com.example.ctsmarket05.model.productsOrder;

import com.example.ctsmarket05.entities.Location;
import com.example.ctsmarket05.entities.Orders;
import com.example.ctsmarket05.entities.Product;
import com.example.ctsmarket05.entities.ProductsOrder;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ProductsOrderInterface {

    @GET("/productsOrder/spobio/{id}")
    Call<List<ProductsOrder>> showProductsOrders(@Path("id") int id_order);

    @GET("/productsOrder/cartCheck/{user_id}/{product_id}")
    Call<String> cartCheck(@Path("product_id") int id_product, @Path("user_id") int id_user);
}
