package com.example.ctsmarket05.model.orders;

import com.example.ctsmarket05.entities.Orders;
import com.example.ctsmarket05.entities.ProductsOrder;
import com.example.ctsmarket05.entities.User;
import com.example.ctsmarket05.model.productsOrder.ProductsOrderInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CartGET {

    public interface onCartFetched{
        void onSucces(List<ProductsOrder> productsOrderList, Integer cartPrice);
        void onFailure();
        void nullCart(String message);
    }

    public void getCart(Integer idUser, final onCartFetched listener) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(User.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        OrderInterface orderInterface = retrofit.create(OrderInterface.class);

        Call<Orders> call = orderInterface.getCart(idUser);

        call.enqueue(new Callback<Orders>() {
            @Override
            public void onResponse(Call<Orders> call, Response<Orders> response) {

                if(!response.isSuccessful()){
                    listener.onFailure();
                    return;
                }

                Orders cart = response.body();

                if (cart.getId_order() != null){
                    getProductsOrders(cart.getId_order(), cart.getOrder_price(),listener);

                }else if(cart.getId_order() ==null){
                    listener.nullCart("No tenés ningún producto en carrito, cuando agregues uno aparecerá en esta sección.");
                }
            }

            @Override
            public void onFailure(Call<Orders> call, Throwable t) {
                listener.onFailure();
            }
        });
    }

    public void getProductsOrders(Integer id_order, Integer cartPrice, final onCartFetched listener){
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
                    listener.onSucces(productsOrdersList, cartPrice);

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
