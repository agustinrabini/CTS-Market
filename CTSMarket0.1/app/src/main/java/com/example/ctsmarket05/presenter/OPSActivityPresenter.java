package com.example.ctsmarket05.presenter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;

import com.example.ctsmarket05.base.BasePresenterActivities;
import com.example.ctsmarket05.entities.Orders;
import com.example.ctsmarket05.entities.User;
import com.example.ctsmarket05.interfaces.OPSActivityInterface;
import com.example.ctsmarket05.model.OPSInteractor;
import com.example.ctsmarket05.view.fragments.bottomSheets.QuantityBottomSheet;

public class OPSActivityPresenter extends BasePresenterActivities implements OPSInteractor.opsInteractor {

    private OPSActivityInterface view;
    private OPSInteractor interactor;
    private Integer f;
    private Integer c;

    public OPSActivityPresenter(@NonNull OPSActivityInterface view, @NonNull OPSInteractor interactor){
        this.view = view;
        this.interactor = interactor;
    }

    public void getProductState(Integer idProduct){
        view.showProgressBar();
        interactor.checkCart(idProduct, User.IDUSER, this);
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

            interactor.deleteCart(User.IDUSER, idProd, this);
            view.cartRemove();
            c=0;

        }else if(c==0){

            Orders order = new Orders(User.IDUSER,price*quantityProduct,quantityProduct,10,null,"");
            interactor.addCart(idProd, order, this);
            view.activeCart();
            c=10;
        }

    }

    //añade o quita un objeto de la lista de favoritos segun corresponda.
    public void favClicked(Integer idProd){

        if (f==10){
            view.favRemove();
            f=1;
        }else if(f == 1){
            view.activeFav();
            f=10;
        }

        interactor.favInteraction(User.IDUSER, idProd, this);
    }

    @Override
    public void onSucces(String cartState, String favState) {

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
    public void onFailure() {
        view.onError();
        view.hideProgressBar();
    }

}
