package com.example.ctsmarket05.retrofit.favouriteRetrofit;

import com.example.ctsmarket05.entities.Favourite;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface FavouriteInterface {

    @GET("/favs/flbu/{id}")//'favs list by user'
    Call<List<Favourite>> showUserFavs(@Path("id") int id_user);

    @GET("/favourite/favCheck/{user_id}/{product_id}")
    Call<String> favCheck(@Path("user_id") int id_user, @Path("product_id") int id_product);

    @POST("fav/favInteracion/{user_id}/{product_id}")
    Call<Void> favInteraction(@Path("user_id") int id_user, @Path("product_id") int id_product);
}