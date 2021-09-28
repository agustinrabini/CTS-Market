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

public class OPS5ActivityPresenter extends BasePresenterActivities implements OPS5Interactor.Interactor, OPS5Interactor.OnConfirmedOrder {

    private OPS5ActivityInterface view;
    private OPS5Interactor interactor;
    private Orders ordersFinal;
    private Integer idProduct;
    private Integer typeSequence;

    public OPS5ActivityPresenter(@NonNull OPS5ActivityInterface view, @NonNull OPS5Interactor interactor){
        this.view = view;
        this.interactor = interactor;
    }

    public void sequenceChecker(Context context){

        view.showPB();

        SharedPreferences orderPref = context.getSharedPreferences("sequence", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String jsonOPSOrder = orderPref.getString("ops", "");
        idProduct = orderPref.getInt("idProduct",0);
        Orders orders = gson.fromJson(jsonOPSOrder, Orders.class);

        ordersFinal = orders;
        typeSequence = orderPref.getInt("type",0);

        if (typeSequence==1){
            opsSequence();
        }else if (typeSequence ==2){
            cartSequence();
        }
    }

    private void opsSequence(){
        //trae datos del usuario y del producto
        interactor.getProduct( idProduct, User.IDUSER, this);
    }

    private void cartSequence(){}

    public void confirmOrder(){

        if(typeSequence==1){

            String date = java.text.DateFormat.getDateTimeInstance().format(new Date());
            ordersFinal.setDate(date);
            ordersFinal.setOrder_state(0);

            interactor.oneProductBougth(User.IDUSER, idProduct, ordersFinal, this);
        }
        else if(typeSequence==2){

        }

    }

    @Override
    public void onSucces(User user, Product product) {
        view.setOPSValues(user, product, ordersFinal);
        view.setLayoutVisible();
        view.hidePB();
    }

    @Override
    public void onSucces() {

    }

    @Override
    public void onFailure() {
        view.onError();
    }
}
