package com.example.ctsmarket05.retrofit.locationRetrofit;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ctsmarket05.entities.Location;
import com.example.ctsmarket05.entities.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LocationGET extends AppCompatActivity{

    private DataInterface mListener;

    public void getLocation(){
        //si el valor es null no ocurre nada, ya que no hay info sobre el usuario en la DB.
        //al ser != null obtiene un objeto Location de la DB.
        if (Location.idLocation !=null){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(User.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        LocationInterface locationInterface = retrofit.create(LocationInterface.class);

        Call<Location> call = locationInterface.locationId(Location.idLocation);

        call.enqueue(new Callback<Location>() {
            @Override
            public void onResponse(Call<Location> call, Response<Location> response) {

                if(response.isSuccessful() && response.body()!=null){
                    mListener.responseLocation(response.body());
                }
                else{
                    Toast.makeText(LocationGET.this, "Error:" + response.code(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Location> call, Throwable t) {
                Toast.makeText(LocationGET.this, "Error:" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

     }else {
            //acá no va nada relacionado con el id, crashea con un {null pointer exception}.
            //afecta a ProductsActivity3.
        }
    }

    public void SetOnDataListener(DataInterface listener){
        mListener = listener;
    }

    public interface DataInterface {
        void responseLocation(Location location);
    }

}