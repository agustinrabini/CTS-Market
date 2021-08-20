package com.example.ctsmarket05.activities.userActivities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ctsmarket05.R;
import com.example.ctsmarket05.activities.MainActivity;
import com.example.ctsmarket05.entities.User;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

//el usuario termina de agregar sus datos
public class UserActivty extends AppCompatActivity {

    private TextView tvUserName;
    private TextView tvUserPurchases;
    private TextView tvUserInfo;
    private TextView tvUserLocation;
    private TextView tvLogoutU;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        findViews();
        setData();
        clickListeners();
        logout();
    }

    private void logout() {

        tvLogoutU.setOnClickListener(v -> {
            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .build();
            GoogleSignInClient googleSignInClient= GoogleSignIn.getClient(this,gso);
            googleSignInClient.signOut();

            Intent logout = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(logout);
        });
    }

    private void clickListeners() {

        tvUserPurchases.setOnClickListener(v -> {
            Intent purchase = new Intent(this, UserPurchaseActivity.class);
            startActivity(purchase);
        });

        tvUserInfo.setOnClickListener(v -> {
            Intent location = new Intent(this, UserInfoActivity.class);
            startActivity(location);
        });

        tvUserLocation.setOnClickListener(v -> {
            Intent location = new Intent(this, LocationInfoActivity.class);
            startActivity(location);
        });
    }

    private void setData() {

        tvUserName.setText("Usuario: " + User.gmail);
    }

    private void findViews() {

        tvUserName = findViewById(R.id.tv_user_nameU);
        tvUserPurchases = findViewById(R.id.tv_user_purchases);
        tvUserInfo = findViewById(R.id.tv_user_infoU);
        tvUserInfo = findViewById(R.id.tv_user_infoU);
        tvUserLocation= findViewById(R.id.tv_user_locationU);
        tvLogoutU = findViewById(R.id.tv_logoutU);
    }
}