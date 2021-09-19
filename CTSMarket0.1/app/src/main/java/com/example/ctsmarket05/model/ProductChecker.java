package com.example.ctsmarket05.model;

import com.example.ctsmarket05.entities.User;
import com.example.ctsmarket05.model.favourite.FavouriteInterface;
import com.example.ctsmarket05.model.productsOrder.ProductsOrderInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductChecker {

    public interface onProductState{
        void onSucces(String cartState, String favState);
        void onFailure();
    }

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
                    listener.onFailure();
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
                listener.onFailure();
            }
        });
    }

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
                    listener.onFailure();
                    return;
                }

                String favState = response.body();

                if (favState !=null){
                    listener.onSucces(cartState, favState);

                }else if(favState == null){
                    listener.onSucces(cartState, null);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                listener.onFailure();
            }
        });
    }

}
