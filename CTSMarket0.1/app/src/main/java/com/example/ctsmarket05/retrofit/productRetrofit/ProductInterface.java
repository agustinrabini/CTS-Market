package com.example.ctsmarket05.retrofit.productRetrofit;

import com.example.ctsmarket05.entities.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductInterface {

    @GET("products")
    Call<List<Product>> productsList();
}
