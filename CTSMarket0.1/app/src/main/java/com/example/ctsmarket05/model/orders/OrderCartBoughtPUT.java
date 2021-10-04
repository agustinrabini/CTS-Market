package com.example.ctsmarket05.model.orders;

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

    public void boughtCart(Orders orders) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(User.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        OrderInterface orderInterface = retrofit.create(OrderInterface.class);

        Call<Void> call = orderInterface.updateOrderCartBought(orders, orders.getId_order());

        call.enqueue(new Callback<Void>() {

            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
            }

            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(OrderCartBoughtPUT.this, "sad", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
