package com.example.ctsmarket05.retrofit.productRetrofit;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ctsmarket05.entities.Product;
import com.example.ctsmarket05.entities.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductGET extends AppCompatActivity {

    private DataInterfaceProd mListener;

    public void getProduct(Integer id_product){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(User.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ProductInterface productInterface = retrofit.create(ProductInterface.class);

        Call<Product> call = productInterface.showProduct(id_product);
        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {

                if(response.isSuccessful() && response.body()!=null){
                    mListener.responseProd(response.body());
                }
                else{
                    Toast.makeText(ProductGET.this, "Error:" + response.code(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                Toast.makeText(ProductGET.this, "Error:" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void SetOnDataListenerProd(DataInterfaceProd listener){
        mListener = listener;
    }

    public interface DataInterfaceProd {
        void responseProd(Product product);
    }
}