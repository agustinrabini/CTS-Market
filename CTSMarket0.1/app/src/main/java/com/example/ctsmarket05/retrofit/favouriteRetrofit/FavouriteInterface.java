package com.example.ctsmarket05.retrofit.favouriteRetrofit;

import com.example.ctsmarket05.entities.Favourite;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FavouriteInterface {

    @GET("favs/flbu/{id}")//'favs list by user'
    Call<List<Favourite>> showUserFavs(@Path("id") int id_user);
}