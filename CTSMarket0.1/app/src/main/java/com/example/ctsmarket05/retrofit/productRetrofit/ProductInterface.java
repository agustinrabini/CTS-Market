package com.example.ctsmarket05.retrofit.productRetrofit;

import com.example.ctsmarket05.entities.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ProductInterface {

    @GET("/products/filter/{filter}")
    Call<List<Product>> productsList(@Path("filter") String filter);

    @GET("/product/{id}")
    Call<Product> showProduct(@Path("id") int id);
}
