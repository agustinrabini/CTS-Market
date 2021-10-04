package com.example.ctsmarket05.presenter;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.example.ctsmarket05.base.BasePresenterActivities;
import com.example.ctsmarket05.entities.Orders;
import com.example.ctsmarket05.entities.Product;
import com.example.ctsmarket05.entities.User;
import com.example.ctsmarket05.interfaces.OPS5ActivityInterface;
import com.example.ctsmarket05.model.OPS5Interactor;
import com.google.gson.Gson;

import java.util.Date;

public class OPS5ActivityPresenter extends BasePresenterActivities implements OPS5Interactor.OPSInteractor, OPS5Interactor.OnConfirmedOrder, OPS5Interactor.OnUserFetched, OPS5Interactor.OnCartBougth{

    private OPS5ActivityInterface view;
    private OPS5Interactor interactor;
    private Orders ordersFinal;
    private User user;
    private Integer idProduct;
    private Integer typeSequence;
    private Integer quantityOrder;

    public OPS5ActivityPresenter(@NonNull OPS5ActivityInterface view, @NonNull OPS5Interactor interactor){
        this.view = view;
        this.interactor = interactor;
    }

    public void sequenceChecker(Context context){

        view.showPB();

        SharedPreferences orderPref = context.getSharedPreferences("sequence", Context.MODE_PRIVATE);
        typeSequence = orderPref.getInt("type",0);
        idProduct = orderPref.getInt("idProduct",0);

        if (typeSequence==1){
            Gson gson = new Gson();
            String jsonOPSOrder = orderPref.getString("ops", "");
            Orders orders = gson.fromJson(jsonOPSOrder, Orders.class);
            ordersFinal = orders;
            opsSequence();

        }else if (typeSequence ==2){
            cartSequence();
            Gson gson = new Gson();
            String jsonOPSOrder = orderPref.getString("cart", "");
            Orders orders = gson.fromJson(jsonOPSOrder, Orders.class);
            ordersFinal = orders;
            cartSequence();
        }
    }

    private void opsSequence(){
        //trae datos del usuario y del producto
        interactor.getProduct( idProduct, User.IDUSER, this);
    }

    private void cartSequence(){
        interactor.fetchUser(User.IDUSER, this);
    }

    public void confirmOrder(){

        if(typeSequence==1){

            String date = java.text.DateFormat.getDateTimeInstance().format(new Date());
            ordersFinal.setDate(date);
            ordersFinal.setOrder_state(0);

            interactor.oneProductBougth(User.IDUSER, idProduct, ordersFinal, this);
        }

        else if(typeSequence==2){

            String date = java.text.DateFormat.getDateTimeInstance().format(new Date());
            ordersFinal.setDate(date);
            ordersFinal.setOrder_state(0);

            interactor.boughtCart(ordersFinal, User.IDUSER,this);
        }
    }

    @Override
    public void opsOnSucces(User user, Product product) {
        view.setOPSValues(user, product, ordersFinal);
        view.setOPSLayoutVisible();
        view.hidePB();
    }

    @Override
    public void opsOnFailure() { view.onError(); }

    @Override
    public void onOPSSucces() {
        view.lastSequenceActivity();
    }

    @Override
    public void onOPSFailure() {
        view.onError();
    }

    @Override
    public void onUserSucces(User user) {
        view.setCartLayoutVisible();
        view.setCartValues(user, ordersFinal);
    }

    @Override
    public void onUserFailure() {
        view.onError();
    }

    @Override
    public void onBougthSucces() {
        view.lastSequenceActivity();
    }

    @Override
    public void onBougthFailure() {
        onOPSFailure();
    }
}
