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

public class OrderAddPricePUT extends AppCompatActivity {

    //actualiza el carrito con el valor y la cantidad que corresponda al añadir un objeto
    public void orderPricePut(Integer product_price, Integer quantity_product, Integer id_order) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(User.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

       OrderInterface orderInterface = retrofit.create(OrderInterface.class);

       Orders orders = new Orders(product_price,quantity_product);

       Call<Orders> call = orderInterface.updateOrderPriceAdded(orders, id_order);

        call.enqueue(new Callback<Orders>() {

            @Override
            public void onResponse(Call<Orders> call, Response<Orders> response) {
            Orders orderResponse = response.body();
            orderResponse.setOrder_price(product_price);
            orderResponse.setQuantity_products(quantity_product);
            }

            public void onFailure(Call<Orders> call, Throwable t) {
                Toast.makeText(OrderAddPricePUT.this, "sad", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
