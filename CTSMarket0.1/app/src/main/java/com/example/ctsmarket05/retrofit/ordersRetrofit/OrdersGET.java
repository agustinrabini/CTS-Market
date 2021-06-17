package com.example.ctsmarket05.retrofit.ordersRetrofit;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ctsmarket05.entities.Orders;
import com.example.ctsmarket05.entities.Product;
import com.example.ctsmarket05.entities.User;
import com.example.ctsmarket05.retrofit.productRetrofit.ProductGET;
import com.example.ctsmarket05.retrofit.productRetrofit.ProductInterface;
import com.example.ctsmarket05.retrofit.productRetrofit.ProductsGET;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OrdersGET extends AppCompatActivity {

    private DataInterfaceOrders mListener;

    public void getOrders(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(User.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        OrderInterface orderInterface = retrofit.create(OrderInterface.class);

        Call<List<Orders>> call = orderInterface.showUserOrders(User.IDUSER);
        call.enqueue(new Callback<List<Orders>>() {
            @Override
            public void onResponse(Call<List<Orders>> call, Response<List<Orders>> response) {

                if(response.isSuccessful() && response.body()!=null){
                    mListener.responseOrder(response.body());
                }
                else{
                    Toast.makeText(OrdersGET.this, "Error:" + response.code(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Orders>> call, Throwable t) {
                Toast.makeText(OrdersGET.this, "Error:" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void SetOnDataListenerOrders(DataInterfaceOrders listener){
        mListener = listener;
    }

    public interface DataInterfaceOrders {
        void responseOrder(List<Orders> orders);
    }
}