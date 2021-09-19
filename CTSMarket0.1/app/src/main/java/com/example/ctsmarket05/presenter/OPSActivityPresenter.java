package com.example.ctsmarket05.presenter;

import androidx.annotation.NonNull;

import com.example.ctsmarket05.base.BasePresenterActivities;
import com.example.ctsmarket05.entities.Product;
import com.example.ctsmarket05.entities.User;
import com.example.ctsmarket05.interfaces.HomeActivityInterface;
import com.example.ctsmarket05.interfaces.OPSActivityInterface;
import com.example.ctsmarket05.model.ProductChecker;
import com.example.ctsmarket05.model.product.ProductsGET;
import com.example.ctsmarket05.model.user.UserGET;

public class OPSActivityPresenter extends BasePresenterActivities implements ProductChecker.onProductState {

    private OPSActivityInterface view;
    private ProductChecker productsInteractor;

    public OPSActivityPresenter(@NonNull OPSActivityInterface view, @NonNull ProductChecker productsInteractor){
        this.view = view;
        this.productsInteractor = productsInteractor;
    }

    public void getProductState(Integer idProduct){
        view.showProgressBar();
        productsInteractor.checkCart(idProduct, User.IDUSER, this);
    }

    @Override
    public void onSucces(String cartState, String favState) {

        String a = cartState;
        String b = favState;

        if(cartState.equals("10")){
            view.activeCart();
        }

        if(favState.equals("10")){
            view.activeFav();
        }

        view.setProduct();
        view.setLayoutVisible();
        view.hideProgressBar();
    }

    @Override
    public void onFailure() {
        view.onError();
    }
}
