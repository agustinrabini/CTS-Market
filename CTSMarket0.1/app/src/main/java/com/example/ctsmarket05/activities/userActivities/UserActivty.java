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

    private TextView tvQuestion;
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

        tvQuestion.setOnClickListener(v -> {

            String telefono = "+54 1132424233";
            String url = "https://api.whatsapp.com/send?phone=";
            Intent llamada = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:" + telefono));
            llamada.setData(Uri.parse(url + telefono));
            startActivity(llamada);
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

        String genericMesagge = getColoredSpanned("¿Alguna consulta? Habla con un respresentante vía WhatsApp haciendo", "#B8C6CD");
        String whatsApp = getColoredSpanned("click aquí.","#2e7d32");
        tvQuestion.setText(Html.fromHtml(genericMesagge+" "+whatsApp));

        tvUserName.setText("Usuario: " + User.gmail);
    }

    private void findViews() {

        tvQuestion = findViewById(R.id.tv_questionU);
        tvUserName = findViewById(R.id.tv_user_nameU);
        tvUserPurchases = findViewById(R.id.tv_user_purchases);
        tvUserInfo = findViewById(R.id.tv_user_infoU);
        tvUserInfo = findViewById(R.id.tv_user_infoU);
        tvUserLocation= findViewById(R.id.tv_user_locationU);
        tvLogoutU = findViewById(R.id.tv_logoutU);
    }

    private String getColoredSpanned(String text, String color) {
        String input = "<font color=" + color + ">" + text + "</font>";
        return input;
    }
}