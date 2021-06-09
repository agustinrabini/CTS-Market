package com.example.ctsmarket05.retrofit.locationRetrofit;

import com.example.ctsmarket05.entities.Location;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface LocationInterface {

    @PUT("location/{id}")
    Call<Location> locationPUT(@Body Location location, @Path("id") int id_location);

    @GET("location/{id}")
    Call<Location> locationId(@Path("id") int id_location);

    @GET("location/liu/{id}")
    Call<Location> locationByIdUser(@Path("id") int id_user);

    @POST("locationAdd")
    Call<Location> postLocation(@Body Location location);

}
