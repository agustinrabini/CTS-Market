package com.example.ctsmarket05.retrofit.productRetrofit;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ctsmarket05.entities.Product;
import com.example.ctsmarket05.entities.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductsGET extends AppCompatActivity {

    private DataInterfaceProd mListener;

    public void getProducts(String filter){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(User.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ProductInterface productInterface = retrofit.create(ProductInterface.class);

        Call<List<Product>> call = productInterface.productsList(filter);
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {

                if(response.isSuccessful() && response.body()!=null){
                    mListener.responseProd(response.body());
                }
                else{
                    Toast.makeText(ProductsGET.this, "Error:" + response.code(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(ProductsGET.this, "Error:" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void SetOnDataListenerProd(DataInterfaceProd listener){
        mListener = listener;
    }

    public interface DataInterfaceProd {
        void responseProd(List<Product> products);
    }
}
