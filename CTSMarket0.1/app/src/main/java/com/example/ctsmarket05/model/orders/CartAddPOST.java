package com.example.ctsmarket05.model.orders;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ctsmarket05.entities.Orders;
import com.example.ctsmarket05.entities.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CartAddPOST{

    //public void addCart(Integer id_product, Orders orders){
//
    //    Retrofit retrofit = new Retrofit.Builder()
    //            .baseUrl(User.URL)
    //            .addConverterFactory(GsonConverterFactory.create())
    //            .build();
//
    //    OrderInterface orderInterface = retrofit.create(OrderInterface.class);
//
    //    Call<Orders> call = orderInterface.addCart(id_product, orders);
    //    call.enqueue(new Callback<Orders>() {
    //        @Override
    //        public void onResponse(Call<Orders> call, Response<Orders> response) {
    //        }
//
    //        @Override
    //        public void opsOnFailure(Call<Orders> call, Throwable t) {
//
    //        }
    //    });
    //}
}
