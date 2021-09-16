package com.example.ctsmarket05.interfaces;

import com.example.ctsmarket05.adapters.FavAdapter;
import com.example.ctsmarket05.adapters.ProductsOrdersAdapter;
import com.example.ctsmarket05.entities.Favourite;
import com.example.ctsmarket05.entities.ProductsOrder;

import java.util.List;

public interface FavoritesFragmentInterface {

    void fetchData();
    void favoritesNull(String message);
    void setFavsList(List<Favourite> favouriteList, FavAdapter favAdapter);

    void showProgressBar();
    void hideProgressBar();
    void setLayoutVisible();

    void onError();
    void reload();
}
