package com.example.ctsmarket05.retrofit.productsOrderRetrofit;

import com.example.ctsmarket05.entities.Location;
import com.example.ctsmarket05.entities.Orders;
import com.example.ctsmarket05.entities.Product;
import com.example.ctsmarket05.entities.ProductsOrder;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ProductsOrderInterface {

    //@GET("products")
    //Call<List<Product>> productsList();

    @POST("productsOrder/addCart")
    Call<ProductsOrder> addCart(@Body ProductsOrder productsOrder);

   //@PUT("/productsOrder/uwapo/{id}")//UpdateWhenAddProductsOrder, ultima call de la sequencia de agregado.
   //Call<ProductsOrder> updateWhenAddPO(@Body ProductsOrder productsOrder, @Path("id") int id_products_order);
}
