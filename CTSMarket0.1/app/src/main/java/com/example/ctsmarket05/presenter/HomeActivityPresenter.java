package com.example.ctsmarket05.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ctsmarket05.base.BasePresenterActivities;
import com.example.ctsmarket05.entities.User;
import com.example.ctsmarket05.interfaces.HomeActivityInterface;
import com.example.ctsmarket05.model.user.UserGET;
import com.example.ctsmarket05.view.activities.HomeActivity;
import com.example.ctsmarket05.view.fragments.ProductsFragment;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public class HomeActivityPresenter extends BasePresenterActivities implements UserGET.onUserFetched {

    private HomeActivityInterface view;
    private UserGET userInteractor;

    public HomeActivityPresenter(@NonNull HomeActivityInterface view, @NonNull UserGET userInteractor){
        this.view = view;
        this.userInteractor = userInteractor;
    }

    public void fetchData(Context context){

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(context);
        if (acct != null) {
            String personName = acct.getDisplayName();
            String personEmail = acct.getEmail();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();

            userInteractor.getUserByGmail(personEmail,this);
        }
    }

    @Override
    public void onSucces(User userFetchedData) {
        view.setFragment();
    }

    @Override
    public void onFailure() {

    }
}
