package com.example.ctsmarket05.interfaces;

import com.example.ctsmarket05.entities.Product;

public interface OPSActivityInterface {

    void setLayoutVisible();
    void showProgressBar();
    void hideProgressBar();

    void setProduct();
    void getProductState();

    void buy();
    void quantity();

    void activeCart();
    void activeFav();

    void onError();
}
