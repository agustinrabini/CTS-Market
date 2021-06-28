package com.example.ctsmarket05.retrofit.productsOrderRetrofit;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ctsmarket05.entities.Orders;
import com.example.ctsmarket05.entities.ProductsOrder;
import com.example.ctsmarket05.entities.User;
import com.example.ctsmarket05.retrofit.ordersRetrofit.OrderInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductsOrderIdGET extends AppCompatActivity {

    private DataInterfacePOID mListener;
    //Devuelve un Id_products_order.
    public void getOrderIdForProductsOrder() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(User.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ProductsOrderInterface productsOrderInterface = retrofit.create(ProductsOrderInterface.class);

        Call<ProductsOrder> call = productsOrderInterface.idProductsOrder(User.IDUSER);

        call.enqueue(new Callback<ProductsOrder>() {
            @Override
            public void onResponse(Call<ProductsOrder> call, Response<ProductsOrder> response) {

                if (response.isSuccessful() && response.body() != null) {
                    mListener.responsePOID(response.body());
                } else {
                    Toast.makeText(ProductsOrderIdGET.this, "Error:" + response.code(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ProductsOrder> call, Throwable t) {
                Toast.makeText(ProductsOrderIdGET.this, "Error:" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void SetOnDataListenerPOID(DataInterfacePOID listener){
        mListener = listener;
    }

    public interface DataInterfacePOID {
        void responsePOID(ProductsOrder productsOrder);
    }
}
