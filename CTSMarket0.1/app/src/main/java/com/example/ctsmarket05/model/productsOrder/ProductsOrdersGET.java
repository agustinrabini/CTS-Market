package com.example.ctsmarket05.model.productsOrder;

import com.example.ctsmarket05.entities.ProductsOrder;
import com.example.ctsmarket05.entities.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductsOrdersGET{

    public interface onProductsOrdersFetched{
        void onSucces(List<ProductsOrder> productsOrdersFetchedData);
        void onFailure();
    }

    public void getProductsOrders(Integer id_order, final onProductsOrdersFetched listener){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(User.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ProductsOrderInterface productsOrderInterface = retrofit.create(ProductsOrderInterface.class);

        Call<List<ProductsOrder>> call = productsOrderInterface.showProductsOrders(id_order);
        call.enqueue(new Callback<List<ProductsOrder>>() {
            @Override
            public void onResponse(Call<List<ProductsOrder>> call, Response<List<ProductsOrder>> response) {

                if(!response.isSuccessful()){
                    listener.onFailure();
                    return;
                }

                List<ProductsOrder> productsOrdersList = response.body();

                if (productsOrdersList !=null){
                    listener.onSucces(response.body());

                }else if(productsOrdersList ==null){
                    listener.onFailure();
                }

            }

            @Override
            public void onFailure(Call<List<ProductsOrder>> call, Throwable t) {
                listener.onFailure();
            }
        });
    }
}
