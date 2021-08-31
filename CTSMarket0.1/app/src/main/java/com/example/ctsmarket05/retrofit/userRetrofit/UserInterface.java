package com.example.ctsmarket05.retrofit.userRetrofit;

import com.example.ctsmarket05.entities.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserInterface {

    @GET("/userCheck/{email}")//en el mainAcivity
    Call<Void> checkUser(@Path("email") String email);

    @GET("/subg/{email}")// 'show user by gmail' busca User con gmail como parametro.
    Call<User> getByGmail(@Path("email") String email);

    @PUT("/user/uuoal/{id}")//'update user on add lcoation'
    Call<User> userPUTIdLoc(@Body User user, @Path("id") int id_user);

    @PUT("/user/uuinfo/{id}")//'update user info'
    Call<User> userPUTInfo(@Body User user, @Path("id") int id_user);
}
