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

public class OrderCartBoughtPUT extends AppCompatActivity {

    public void boughtCart(Integer id_order, Integer order_state, Integer shipping, String date) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(User.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        OrderInterface orderInterface = retrofit.create(OrderInterface.class);

        Orders orders = new Orders(order_state,shipping,date);

        Call<Orders> call = orderInterface.updateOrderCartBought(orders, id_order);

        call.enqueue(new Callback<Orders>() {

            @Override
            public void onResponse(Call<Orders> call, Response<Orders> response) {
                Orders orderResponse = response.body();
                orderResponse.setOrder_state(order_state);
                orderResponse.setShipping(shipping);
                orderResponse.setDate(date);
            }

            public void onFailure(Call<Orders> call, Throwable t) {
                Toast.makeText(OrderCartBoughtPUT.this, "sad", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
