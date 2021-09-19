package com.example.ctsmarket05.interfaces;

import android.content.Context;

import com.example.ctsmarket05.adapters.ProductsAdapter;
import com.example.ctsmarket05.entities.Product;

import java.util.List;

public interface ProductsFragmentInterface {

    void showProgressBar();
    void hideProgressBar();

    void showRv();
    void hideRv();

    void onError();

    void getUserData(Context context);
    void showUserData(String userName, String userPhoto, Context context);

    void setProductsList(ProductsAdapter productsAdapter);

    void fetchData(String filter);

    void nullListProducts(String nullListProducts);

    void reload(String filter);
}
