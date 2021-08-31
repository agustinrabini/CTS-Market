package com.example.ctsmarket05.retrofit.productsOrderRetrofit;

import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ctsmarket05.activities.OrderExpandedActivity;
import com.example.ctsmarket05.activities.userActivities.UserPurchaseActivity;
import com.example.ctsmarket05.entities.Product;
import com.example.ctsmarket05.entities.ProductsOrder;
import com.example.ctsmarket05.entities.User;
import com.example.ctsmarket05.retrofit.productRetrofit.ProductInterface;
import com.example.ctsmarket05.retrofit.productRetrofit.ProductsGET;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductsOrdersGET extends AppCompatActivity {

private DataInterfaceProductsOrders mListener;

    public void getProductsOrders(Integer id_order){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(User.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ProductsOrderInterface productsOrderInterface = retrofit.create(ProductsOrderInterface.class);

        Call<List<ProductsOrder>> call = productsOrderInterface.showProductsOrders(id_order);
        call.enqueue(new Callback<List<ProductsOrder>>() {
            @Override
            public void onResponse(Call<List<ProductsOrder>> call, Response<List<ProductsOrder>> response) {

                if(response.isSuccessful() && response.body()!=null){
                    mListener.responseProductsOrders(response.body());
                }
                else{
                    Toast.makeText(ProductsOrdersGET.this, "Error:" + response.code(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<ProductsOrder>> call, Throwable t) {
                Toast.makeText(ProductsOrdersGET.this, "Error:" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void SetOnDataListenerProductsOrders(DataInterfaceProductsOrders listener){
        mListener = listener;
    }

    public interface DataInterfaceProductsOrders {
        void responseProductsOrders(List<ProductsOrder> productsOrders);
    }
}
