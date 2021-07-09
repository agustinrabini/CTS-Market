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

public class ProductsOrderDELETE extends AppCompatActivity {

    public void removeProductOrder(Integer id_product_order) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(User.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ProductsOrderInterface productsOrderInterface = retrofit.create(ProductsOrderInterface.class);

        Call<ProductsOrder> call = productsOrderInterface.removeProductsOrders(id_product_order);

        call.enqueue(new Callback<ProductsOrder>() {
            @Override
            public void onResponse(Call<ProductsOrder> call, Response<ProductsOrder> response) {

                if (response.isSuccessful() && response.body() != null) {
                } else {
                    Toast.makeText(ProductsOrderDELETE.this, "Error:" + response.code(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ProductsOrder> call, Throwable t) {
                Toast.makeText(ProductsOrderDELETE.this, "Error:" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
