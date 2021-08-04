package com.example.ctsmarket05.retrofit.favouriteRetrofit;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ctsmarket05.entities.Favourite;
import com.example.ctsmarket05.entities.Orders;
import com.example.ctsmarket05.entities.User;
import com.example.ctsmarket05.retrofit.ordersRetrofit.OrderInterface;
import com.example.ctsmarket05.retrofit.ordersRetrofit.OrdersGET;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FavsGET extends AppCompatActivity {

    private DataInterfaceFavourite mListener;

    public void getFavs() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(User.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FavouriteInterface favouriteInterface = retrofit.create(FavouriteInterface.class);

        Call<List<Favourite>> call = favouriteInterface.showUserFavs(User.IDUSER);
        call.enqueue(new Callback<List<Favourite>>() {
            @Override
            public void onResponse(Call<List<Favourite>> call, Response<List<Favourite>> response) {

                if (response.isSuccessful() && response.body() != null) {
                    mListener.responseFavourite(response.body());
                } else {
                    Toast.makeText(FavsGET.this, "Error:" + response.code(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Favourite>> call, Throwable t) {
                Toast.makeText(FavsGET.this, "Error:" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void SetOnDataListenerFavourite(DataInterfaceFavourite listener) {
        mListener = listener;
    }

    public interface DataInterfaceFavourite {
        void responseFavourite(List<Favourite> favourites);
    }
}
