package com.example.ctsmarket05.presenter;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;

import com.example.ctsmarket05.base.BasePresenterActivities;
import com.example.ctsmarket05.entities.Orders;
import com.example.ctsmarket05.entities.User;
import com.example.ctsmarket05.interfaces.OPSActivityInterface;
import com.example.ctsmarket05.model.OPSInteractor;
import com.example.ctsmarket05.view.fragments.bottomSheets.QuantityBottomSheet;

import java.net.UnknownServiceException;

public class OPSActivityPresenter extends BasePresenterActivities implements OPSInteractor.onProductState, OPSInteractor.onCartInteraction{

    private OPSActivityInterface view;
    private OPSInteractor productsInteractor;
    private Integer f;
    private Integer c;

    public OPSActivityPresenter(@NonNull OPSActivityInterface view, @NonNull OPSInteractor productsInteractor){
        this.view = view;
        this.productsInteractor = productsInteractor;
    }

    public void getProductState(Integer idProduct){
        view.showProgressBar();
        productsInteractor.checkCart(idProduct, User.IDUSER, this);
    }

    public void changeQuantity(FragmentManager fragmentManager, Integer stock){

        Bundle args = new Bundle();
        args.putInt("stock", stock);

        QuantityBottomSheet quantityBottomSheet = new QuantityBottomSheet();
        quantityBottomSheet.show(fragmentManager, "quantityBottomSheet");
        quantityBottomSheet.setArguments(args);

    }

    //si el producto está en el carrito lo quita. Si no está en carrito lo agrega.
    public void cartClicked(Integer idProd, Integer price, Integer quantityProduct){

        if (c == 10){

            productsInteractor.deleteCart(User.IDUSER, idProd);
            view.cartRemove();

        }else{

            Orders order = new Orders(User.IDUSER,price*quantityProduct,quantityProduct,10,null,"");
            productsInteractor.addCart(idProd, order);
            view.activeCart();
        }

    }

    public void favClicked(Integer idProd){

        if (f==10){
            view.favRemove();
        }else{
            view.activeFav();
        }

        productsInteractor.favInteraction(User.IDUSER, idProd);

    }

    @Override
    public void onSuccesProductState(String cartState, String favState) {

        c = Integer.parseInt(cartState);
        f = Integer.parseInt(favState);

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
    public void onFailureProductState() {

    }

    @Override
    public void onSuccesCartInteraction(String cartState) {

    }

    @Override
    public void onFailureCartInteraction() {
        view.onError();
    }

}
