package com.example.ctsmarket05.retrofit.ordersRetrofit;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.FtsOptions;

import com.example.ctsmarket05.entities.Location;
import com.example.ctsmarket05.entities.Orders;
import com.example.ctsmarket05.entities.User;
import com.example.ctsmarket05.retrofit.locationRetrofit.LocationGET;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OrderGetByIdUser extends AppCompatActivity {

    private DataInterfaceOrderPO mListener;

    //Devuelve un Id_order y ademas settea el valor del id_order_PO en la api en la parte de order.
    public void OrderGetByIdUser() {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(User.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    OrderInterface orderInterface = retrofit.create(OrderInterface.class);

    Call<Orders> call = orderInterface.getOrderIdForProductsOrder(User.IDUSER);

    call.enqueue(new Callback<Orders>() {

            @Override
            public void onResponse(Call<Orders> call, Response<Orders> response) {
                mListener.responseOrder(response.body());
            }

            @Override
            public void onFailure(Call<Orders> call, Throwable t) {
                Toast.makeText(OrderGetByIdUser.this, "Error:" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
            });
    }

    public void SetOnDataListenerOrdersPO(DataInterfaceOrderPO listener){
        mListener = listener;
    }

    public interface DataInterfaceOrderPO {
        void responseOrder(Orders order);
    }
}
