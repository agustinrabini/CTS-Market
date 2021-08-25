package com.example.ctsmarket05.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ctsmarket05.R;
import com.example.ctsmarket05.entities.User;
import com.example.ctsmarket05.retrofit.userRetrofit.UserInterface;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Button btnLoggin;
    private ImageButton ibGoogle;
    private String email;
    int RC_SIGN_IN = 0;
    GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findviews();
        clickListeners();
        googleSignIn();
    }

    private void googleSignIn() {

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                //.requestProfile()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    private void clickListeners() {

        //ivLoggin.setOnClickListener(v -> {
        //    Intent openRegister = new Intent(MainActivity.this, RegisterActivity.class);
        //    startActivity(openRegister);
        //});
//
        //ivLoggin.setOnClickListener(v -> {
        //    Intent openLoggin = new Intent(MainActivity.this, LogginActivity.class);
        //    startActivity(openLoggin);
        //});

        ibGoogle.setOnClickListener(v -> {
            signIn();
        });
    }

    private void findviews() {

        btnLoggin = findViewById(R.id.btn_loggin_main);
        ibGoogle= findViewById(R.id.ib_google);
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {

            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            // Signed in successfully, show authenticated UI.
            updateUI(account);
            checkUser();

        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("Error", "signInResult:failed code=" + e.getStatusCode());
            updateUI(null);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        updateUI(account);
    }

    private void updateUI(GoogleSignInAccount account){
        if (account==null){
          // Intent haveToSign = new Intent(MainActivity.this, MainActivity.class);
          // startActivity(haveToSign);
        }else{
            Intent alredySign = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(alredySign);
        }
    }
    //Esta funcion es para checkear (SELECT COUNT(*)) en la DB de la app (MySql) si el usuario se registro alguna vez.
    // El retorno es void porque el response es un texto plano, 0 ó 1, que se leera en la api. En caso de ser
    // 0 (lo cual significa que el usuario se registra por
    // primera vez) se ejecuta un INSERT a la DB.
    //Ese INSERT se hace en la api en la funcion CheckUser.
    private void checkUser(){

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);

        String personEmail = acct.getEmail();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(User.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserInterface userInterface= retrofit.create(UserInterface.class);

        Call<Void> call = userInterface.checkUser(personEmail);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                if(response.isSuccessful()) {
                    return;
                }
            }

            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error:" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}