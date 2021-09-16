package com.example.ctsmarket05.view.activities;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.ctsmarket05.R;
import com.example.ctsmarket05.base.BaseActivity;
import com.example.ctsmarket05.interfaces.HomeActivityInterface;
import com.example.ctsmarket05.presenter.HomeActivityPresenter;
import com.example.ctsmarket05.view.fragments.FavoritesFragment;
import com.example.ctsmarket05.view.fragments.ProductsFragment;
import com.example.ctsmarket05.view.fragments.InfoFragment;
import com.example.ctsmarket05.view.fragments.CartFragment;
import com.example.ctsmarket05.model.user.UserGET;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class HomeActivity extends BaseActivity<HomeActivityPresenter> implements HomeActivityInterface {

    private BottomNavigationView bottomNavigationView ;

    FavoritesFragment favoritesFragment = new FavoritesFragment();
    CartFragment cartFragment = new CartFragment();
    InfoFragment infoFragment = new InfoFragment();
    ProductsFragment productsFragment = new ProductsFragment();

    protected HomeActivityPresenter createPresenter(@NonNull Context context){
        return new HomeActivityPresenter(this, new UserGET());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        findViews();
        getUserId();
    }

    private void navigationView() {
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedLiseter);
        loadFragment(productsFragment);
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedLiseter = item -> {

        switch (item.getItemId()){

            case R.id.home:
                loadFragment(productsFragment);
                return true;

            case R.id.cart:
                loadFragment(cartFragment);
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

    public void loadFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }

    private void findViews(){ bottomNavigationView = findViewById(R.id.bottom_navigation_home);}

    @Override
    public void getUserId() {
        presenterActivity.fetchData(getApplicationContext());
    }

    @Override
    public void setFragment() {
        navigationView();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
    }
}