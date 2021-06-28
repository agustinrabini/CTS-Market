package com.example.ctsmarket05.retrofit.ordersRetrofit;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ctsmarket05.entities.Location;
import com.example.ctsmarket05.entities.Orders;
import com.example.ctsmarket05.entities.User;
import com.example.ctsmarket05.retrofit.locationRetrofit.LocationGET;
import com.example.ctsmarket05.retrofit.locationRetrofit.LocationInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OrderGET extends AppCompatActivity {

    private DataInterfaceOrder mListener;

    public void getOrder(Integer id_order) {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(User.URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            OrderInterface orderInterface = retrofit.create(OrderInterface.class);

            Call<Orders> call = orderInterface.getOrder(id_order);

            call.enqueue(new Callback<Orders>() {
                @Override
                public void onResponse(Call<Orders> call, Response<Orders> response) {

                    if (response.isSuccessful() && response.body() != null) {
                        mListener.responseOrder(response.body());
                    } else {
                        Toast.makeText(OrderGET.this, "Error:" + response.code(), Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<Orders> call, Throwable t) {
                    Toast.makeText(OrderGET.this, "Error:" + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }

    public void SetOnDataListenerOrder(DataInterfaceOrder listener){
        mListener = listener;
    }

    public interface DataInterfaceOrder {
        void responseOrder(Orders order);
    }

}

