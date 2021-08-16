package com.example.ctsmarket05.retrofit.productsOrderRetrofit;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ctsmarket05.entities.ProductsOrder;
import com.example.ctsmarket05.entities.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CheckCartPOST extends AppCompatActivity {

    private DataInterfaceCheck mListener;

    public void check(Integer id_product, Integer id_user){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(User.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ProductsOrderInterface productsOrderInterface = retrofit.create(ProductsOrderInterface.class);


        Call<String> call = productsOrderInterface.cartCheck(id_product, id_user);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                    mListener.responseCheck(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
            }
        });
    }

    public void SetOnDataListenerCheck(DataInterfaceCheck listener){
        mListener = listener;
    }

    public interface DataInterfaceCheck {
        void responseCheck(String check);
    }
}

