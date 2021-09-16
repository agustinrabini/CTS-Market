package com.example.ctsmarket05.model.user;

import android.content.SharedPreferences;
import android.net.Uri;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import com.example.ctsmarket05.R;
import com.example.ctsmarket05.entities.User;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserGET extends AppCompatActivity {

    public interface onUserFetched{
        void onSucces(User userFetchedData);
        void onFailure();
    }

    public void getUserByGmail(String personEmail, final onUserFetched listener){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(User.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserInterface userInterface= retrofit.create(UserInterface.class);

        Call<User> call = userInterface.getByGmail(personEmail);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if(!response.isSuccessful()){
                    listener.onFailure();
                    return;
                }

                User user = response.body();

                if (user !=null){
                    User.IDUSER=user.getId_user();
                    listener.onSucces(response.body());
                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                listener.onFailure();
            }
        });
    }
}

