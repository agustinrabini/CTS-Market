package com.example.ctsmarket05.model;

import android.widget.Toast;

import com.example.ctsmarket05.entities.Orders;
import com.example.ctsmarket05.entities.Product;
import com.example.ctsmarket05.entities.ProductsOrder;
import com.example.ctsmarket05.entities.User;
import com.example.ctsmarket05.model.orders.OrderCartBoughtPUT;
import com.example.ctsmarket05.model.orders.OrderInterface;
import com.example.ctsmarket05.model.product.ProductInterface;
import com.example.ctsmarket05.model.productsOrder.ProductsOrderInterface;
import com.example.ctsmarket05.model.user.UserInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OPS5Interactor {

    public interface OPSInteractor {
        void opsOnSucces(User user, Product product);
        void opsOnFailure();
    }

    public interface OnConfirmedOrder {
        void onOPSSucces();
        void onOPSFailure();
    }

    public interface OnUserFetched {
        void onUserSucces(User user);
        void onUserFailure();
    }

    public interface OnCartBougth{
        void onBougthSucces();
        void onBougthFailure();
    }

    public void getProduct(Integer idProduct, Integer idUser, final OPSInteractor listener){
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
                    listener.opsOnFailure();
                    return;
                }

                Product product = response.body();

                if (product !=null){
                    getUser(idUser, product, listener);
                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                listener.opsOnFailure();
            }
        });
    }

    public void getUser(Integer idUser,Product product, final OPSInteractor listener){
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
                    listener.opsOnFailure();
                    return;
                }

                User user = response.body();

                if (user !=null){
                    listener.opsOnSucces(user, product);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                listener.opsOnFailure();
            }
        });
    }

    public void oneProductBougth(Integer id_user, Integer id_product,  Orders orders, final OnConfirmedOrder listener){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(User.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        OrderInterface orderInterface = retrofit.create(OrderInterface.class);

        Call<Void> call = orderInterface.oneProductBougth(id_user, id_product, orders);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(!response.isSuccessful()){
                    listener.onOPSFailure();
                    return;
                }else {
                    listener.onOPSSucces();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                listener.onOPSFailure();
            }
        });
    }

    public void fetchUser(Integer idUser, final OnUserFetched listener){
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
                    listener.onUserFailure();
                    return;
                }

                User user = response.body();

                if (user !=null){
                    listener.onUserSucces(user);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                listener.onUserFailure();
            }
        });
    }

    public void boughtCart(Orders orders, Integer idUser, final OnCartBougth listener) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(User.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        OrderInterface orderInterface = retrofit.create(OrderInterface.class);

        Call<Void> call = orderInterface.updateOrderCartBought(orders, idUser);

        call.enqueue(new Callback<Void>() {

            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                if(!response.isSuccessful()){
                    listener.onBougthFailure();
                    return;
                }else{

                    listener.onBougthSucces();
                }
            }

            public void onFailure(Call<Void> call, Throwable t) {
                listener.onBougthFailure();
            }
        });
    }
}
