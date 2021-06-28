package com.example.ctsmarket05.retrofit.ordersRetrofit;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ctsmarket05.entities.Orders;
import com.example.ctsmarket05.entities.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OrderGetByIdUser extends AppCompatActivity {

    //Devuelve un Id_order
    public void OrderGetByIdUser() {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(User.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    OrderInterface orderInterface = retrofit.create(OrderInterface.class);

    Call<Orders> call = orderInterface.getOrderIdForProductsOrder(User.IDUSER);

    call.enqueue(new Callback<Orders>() {

            @Override
            public void onResponse(Call<Orders> call, Response<Orders> response) {
            }

            @Override
            public void onFailure(Call<Orders> call, Throwable t) {
                Toast.makeText(OrderGetByIdUser.this, "Error:" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
            });
    }

}
