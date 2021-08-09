package com.example.ctsmarket05.retrofit.productsOrderRetrofit;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ctsmarket05.entities.ProductsOrder;
import com.example.ctsmarket05.entities.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductsOrderPOST extends AppCompatActivity {

    public void addCart(Integer id_product, Integer id_user, Integer quantity){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(User.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ProductsOrderInterface productsOrderInterface = retrofit.create(ProductsOrderInterface.class);

        ProductsOrder productsOrder = new ProductsOrder(id_product, id_user, quantity);

        Call<ProductsOrder> call = productsOrderInterface.addCart(productsOrder);

        call.enqueue(new Callback<ProductsOrder>() {
            @Override
            public void onResponse(Call<ProductsOrder> call, Response<ProductsOrder> response) {
            }

            @Override
            public void onFailure(Call<ProductsOrder> call, Throwable t) {
            }
        });
    }
}

