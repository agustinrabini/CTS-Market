package com.example.ctsmarket05.retrofit.productsOrderRetrofit;

import com.example.ctsmarket05.entities.ProductsOrder;
import com.example.ctsmarket05.entities.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductsOrderWhenAddPUT  {

    public void putProductsOrder(Integer id_order) {

        setIdProductsOrder();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(User.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ProductsOrderInterface productsOrderInterface = retrofit.create(ProductsOrderInterface.class);

        ProductsOrder productsOrder = new ProductsOrder(id_order);

        Call<ProductsOrder> call = productsOrderInterface.updateWhenAddPO(productsOrder, ProductsOrder.IDPRODUCTSORDER );

        call.enqueue(new Callback<ProductsOrder>() {

             @Override
             public void onResponse(Call<ProductsOrder> call, Response<ProductsOrder> response) {

                 ProductsOrder productsOrder1 = response.body();
                 productsOrder1.setId_order(id_order);
             }

            @Override
            public void onFailure(Call<ProductsOrder> call, Throwable t) {
            }
        });
    }

    private void setIdProductsOrder() {

        ProductsOrderIdGET productsOrderIdGET = new ProductsOrderIdGET();
        productsOrderIdGET.SetOnDataListenerPOID(productsOrder -> {
            Integer id_products_order = productsOrder.getId_products_order();
            ProductsOrder.IDPRODUCTSORDER = id_products_order;
        });
        productsOrderIdGET.getOrderIdForProductsOrder();
    }

}

