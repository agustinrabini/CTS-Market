package com.example.ctsmarket05.activities;

import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.ctsmarket05.R;
import com.example.ctsmarket05.entities.Location;
import com.example.ctsmarket05.entities.User;
import com.example.ctsmarket05.fragments.FavoritesFragment;
import com.example.ctsmarket05.fragments.HomeFragment;
import com.example.ctsmarket05.fragments.InfoFragment;
import com.example.ctsmarket05.fragments.OrdersFragment;
import com.example.ctsmarket05.retrofit.userRetrofit.UserGET;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class HomeActivity extends AppCompatActivity {

    private TextView tvUserName;
    private BottomNavigationView bottomNavigationView ;

    FavoritesFragment favoritesFragment = new FavoritesFragment();
    OrdersFragment ordersFragment = new OrdersFragment();
    InfoFragment infoFragment = new InfoFragment();
    HomeFragment homeFragment = new HomeFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        findViews();
        navigationView();
        userValues();
        setStaticIdsValues();
    }

    private void navigationView() {
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedLiseter);
        loadFragment(homeFragment);
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedLiseter = item -> {

        switch (item.getItemId()){

            case R.id.home:
                loadFragment(homeFragment);
                return true;

            case R.id.cart:
                loadFragment(ordersFragment);
                return true;

            case R.id.favorites:
                loadFragment(favoritesFragment);
                return true;

            case R.id.info:
                loadFragment(infoFragment);
                return true;
        }
        return false;
    };

    private void userValues() {

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            String personName = acct.getDisplayName();
            String personEmail = acct.getEmail();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();

            User.gmail = personEmail;
            //User.PHOTO = personPhoto.toString();
        }
    }

    public void loadFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }

    //Establece el valor de los atributos estaticos idLocation y idUser. Para ser usado en LocationGET
    public void setStaticIdsValues(){
        //para idLocation
        UserGET getId = new UserGET();
        getId.SetOnDataListenerUser(user -> {
            Integer id = user.getId_location();
            if(id!=null) {
                setLocationId(id);
            }else{}
        });
        getId.getUserByGmail();

        UserGET userGET = new UserGET();
        userGET.SetOnDataListenerUser(user -> {
            Integer id = user.getId_user();
            setUserId(id);
        });
        userGET.getUserByGmail();
    }

    private void setLocationId(Integer id){
        Location.idLocation = id;
    }

    private void setUserId(Integer id){
           User.IDUSER= id;
    }

    private void findViews(){ bottomNavigationView = findViewById(R.id.bottom_navigation_home);}
}