package com.example.ctsmarket05.model;

import com.example.ctsmarket05.entities.Orders;
import com.example.ctsmarket05.entities.Product;
import com.example.ctsmarket05.entities.User;
import com.example.ctsmarket05.model.orders.OrderInterface;
import com.example.ctsmarket05.model.product.ProductInterface;
import com.example.ctsmarket05.model.user.UserInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OPS5Interactor {

    public interface Interactor {
        void onSucces(User user, Product product);
        void onFailure();
    }

    public interface OnConfirmedOrder {
        void onSucces();
        void onFailure();
    }

    public void getProduct(Integer idProduct, Integer idUser, final Interactor listener){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(User.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ProductInterface productInterface = retrofit.create(ProductInterface.class);

        Call<Product> call = productInterface.showProduct(idProduct);
        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {

                if(!response.isSuccessful()){
                    listener.onFailure();
                    return;
                }

                Product product = response.body();

                if (product !=null){
                    getUser(idUser, product, listener);
                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                listener.onFailure();
            }
        });
    }

    public void getUser(Integer idUser,Product product, final Interactor listener){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(User.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserInterface userInterface= retrofit.create(UserInterface.class);

        Call<User> call = userInterface.getUser(idUser);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if(!response.isSuccessful()){
                    listener.onFailure();
                    return;
                }

                User user = response.body();

                if (user !=null){
                    listener.onSucces(user, product);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                listener.onFailure();
            }
        });
    }

    public void oneProductBougth(Integer id_user, Integer id_product,  Orders orders, final OnConfirmedOrder onConfirmedOrder){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(User.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        OrderInterface orderInterface = retrofit.create(OrderInterface.class);

        Call<Void> call = orderInterface.oneProductBougth(id_user, id_product, orders);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
            }
        });
    }
}
