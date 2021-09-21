package com.example.ctsmarket05.interfaces;

import android.support.v4.app.INotificationSideChannel;

import com.example.ctsmarket05.entities.Product;

public interface OPSActivityInterface {

    void setLayoutVisible();
    void showProgressBar();
    void hideProgressBar();

    void setProduct();
    void getProductState();//checkea si el producto esta en carrito y lista de favoritos.

    void buy();
    void cartClicked();
    void favClicked();

    void cartRemove();
    void favRemove();

    void quantityUpdate(Integer quantity);
    void changeQuantity();

    void activeCart();
    void activeFav();

    void onError();
}
