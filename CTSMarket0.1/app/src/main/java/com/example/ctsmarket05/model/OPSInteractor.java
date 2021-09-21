package com.example.ctsmarket05.model;

import com.example.ctsmarket05.entities.Orders;
import com.example.ctsmarket05.entities.User;
import com.example.ctsmarket05.model.favourite.FavouriteInterface;
import com.example.ctsmarket05.model.orders.OrderInterface;
import com.example.ctsmarket05.model.productsOrder.ProductsOrderInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OPSInteractor {

    public interface onProductState {
        void onSuccesProductState(String cartState, String favState);

        void onFailureProductState();
    }

    public interface onCartInteraction {
        void onSuccesCartInteraction(String cartState);
        void onFailureCartInteraction();
    }

    //checkea el estado del producto en relacion con el carrito.
    public void checkCart(Integer idProduct, Integer idUser, final onProductState listener){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(User.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ProductsOrderInterface productsOrderInterface = retrofit.create(ProductsOrderInterface.class);

        Call<String> call = productsOrderInterface.cartCheck(idProduct, idUser);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                if(!response.isSuccessful()){
                    listener.onFailureProductState();
                    return;
                }

                String cartState = response.body();

                if (cartState !=null){

                    favState(idUser, idProduct, cartState, listener);

                }else if(cartState == null){
                    favState(idUser, idProduct, null, listener);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                listener.onFailureProductState();
            }
        });
    }

    //checkea el estado del producto en relacion con la lista de favoritos.
    public void favState(Integer id_user, Integer id_product, String cartState, final onProductState listener){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(User.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FavouriteInterface favouriteInterface = retrofit.create(FavouriteInterface.class);

        Call<String> call = favouriteInterface.favCheck(id_user,id_product);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                if(!response.isSuccessful()){
                    listener.onFailureProductState();
                    return;
                }

                String favState = response.body();

                if (favState !=null){
                    listener.onSuccesProductState(cartState, favState);

                }else if(favState == null){
                    listener.onSuccesProductState(cartState, null);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                listener.onFailureProductState();
            }
        });
    }

    //agrega el prodcuto al carrito.
    public void addCart(Integer id_product, Orders orders){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(User.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        OrderInterface orderInterface = retrofit.create(OrderInterface.class);

        Call<Orders> call = orderInterface.addCart(id_product, orders);
        call.enqueue(new Callback<Orders>() {
            @Override
            public void onResponse(Call<Orders> call, Response<Orders> response) {
            }

            @Override
            public void onFailure(Call<Orders> call, Throwable t) {

            }
        });
    }

    public void deleteCart(Integer id_user, Integer id_product){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(User.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        OrderInterface orderInterface = retrofit.create(OrderInterface.class);

        Call<Orders> call = orderInterface.removeCart(id_user, id_product);
        call.enqueue(new Callback<Orders>() {
            @Override
            public void onResponse(Call<Orders> call, Response<Orders> response) {
            }

            @Override
            public void onFailure(Call<Orders> call, Throwable t) {

            }
        });
    }

    public void favInteraction(Integer id_user, Integer id_product){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(User.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FavouriteInterface favouriteInterface = retrofit.create(FavouriteInterface.class);

        Call<Void> call = favouriteInterface.favInteraction(id_user,id_product);
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

