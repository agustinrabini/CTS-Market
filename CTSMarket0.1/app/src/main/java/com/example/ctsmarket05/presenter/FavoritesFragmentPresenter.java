package com.example.ctsmarket05.presenter;

import androidx.annotation.NonNull;

import com.example.ctsmarket05.adapters.FavAdapter;
import com.example.ctsmarket05.base.BasePresenterFragments;
import com.example.ctsmarket05.entities.Favourite;
import com.example.ctsmarket05.entities.User;
import com.example.ctsmarket05.interfaces.FavoritesFragmentInterface;
import com.example.ctsmarket05.interfaces.clickListeners.FavOnCustomClickListener;
import com.example.ctsmarket05.model.favourite.FavsGET;

import java.util.List;

public class FavoritesFragmentPresenter extends BasePresenterFragments implements FavsGET.onFavsFetched, FavOnCustomClickListener {

    private FavoritesFragmentInterface view;
    private FavsGET favsInteractor;
    private FavAdapter favAdapter = new FavAdapter(this);

    public FavoritesFragmentPresenter(@NonNull FavoritesFragmentInterface view, @NonNull FavsGET favsInteractor){
        this.view = view;
        this.favsInteractor = favsInteractor;
    }

    public void fetchFavs() {

        view.showProgressBar();
        favsInteractor.getFavs(User.IDUSER, this);
    }

    @Override
    public void onSucces(List<Favourite> favouriteList) {
        view.setFavsList(favAdapter);
        favAdapter.setFavs(favouriteList);
        view.hideProgressBar();
        view.setLayoutVisible();
    }


    @Override
    public void onFailure() {
        view.onError();
    }

    @Override
    public void nullFavs(String message) {
        view.favoritesNull(message);
        view.hideProgressBar();
    }

    @Override
    public void onItemClick(Favourite favourite, int position) {
    }

}
