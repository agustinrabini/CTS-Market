package com.example.ctsmarket05.model.product;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ctsmarket05.R;
import com.example.ctsmarket05.entities.Product;
import com.example.ctsmarket05.entities.User;
import com.example.ctsmarket05.model.user.UserGET;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductsGET extends AppCompatActivity {

    public interface onProductsFetched{
        void onSucces(List<Product> productsFetchedData);
        void onFailure();
        void nullList(String nullFilterProductList);//se pasa el filtro que se solicitó.
    }

    public void getProducts(String filter, final onProductsFetched listener){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(User.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ProductInterface productInterface = retrofit.create(ProductInterface.class);

        Call<List<Product>> call = productInterface.productsList(filter);
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {

                if(!response.isSuccessful()){
                    listener.onFailure();
                    return;
                }

                List<Product> productList = response.body();

                if (productList !=null){

                    listener.onSucces(response.body());
                }else if(productList==null){

                    listener.nullList(filter);
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                listener.onFailure();
            }
        });
    }
}
