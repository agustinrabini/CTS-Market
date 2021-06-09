package com.example.ctsmarket05.retrofit.userRetrofit;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ctsmarket05.entities.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
//Obtener todos los datos del usuario por gmail
public class UserGET extends AppCompatActivity {

    private DataInterfaceUser mListener;

    //hace una llamada a la api con un metodo GET. Como response devuelve un objeto User completo.
    //lo envia al activity donde se solicite y ahi se leen los datos y se le asignan valores.
    //ennvia a la api el gmail como parametro para buscar y traer el objeto.
    public void getUserByGmail(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(User.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserInterface userInterface= retrofit.create(UserInterface.class);

        Call<User> call = userInterface.getByGmail(User.gmail);//se obtiene en el HomeActivity
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if(response.isSuccessful() && response.body()!=null){
                    mListener.responseUser(response.body());
                }
                else{
                    Toast.makeText(UserGET.this, "Error:" + response.code(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(UserGET.this, "Error:" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Interfaces para pasar la response a las demas activities:

    public void SetOnDataListenerUser(DataInterfaceUser listener){
        mListener = listener;
    }

    public interface DataInterfaceUser {
        void responseUser(User user);
    }
}



