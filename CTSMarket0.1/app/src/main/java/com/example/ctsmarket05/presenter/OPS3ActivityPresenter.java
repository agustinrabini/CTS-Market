package com.example.ctsmarket05.presenter;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.example.ctsmarket05.base.BasePresenterActivities;
import com.example.ctsmarket05.entities.Location;
import com.example.ctsmarket05.entities.Orders;
import com.example.ctsmarket05.entities.User;
import com.example.ctsmarket05.interfaces.OPS2ActivityInterface;
import com.example.ctsmarket05.interfaces.OPS3ActivityInterface;
import com.example.ctsmarket05.model.OPS2Interactor;
import com.example.ctsmarket05.model.OPS3Interactor;
import com.google.gson.Gson;

public class OPS3ActivityPresenter extends BasePresenterActivities implements OPS3Interactor.Interactor {

    private OPS3ActivityInterface view;
    private OPS3Interactor interactor;
    private Integer priceShipping;
    private Integer orderPrice;
    private Integer typeSequence;
    private SharedPreferences orderPref;

    public OPS3ActivityPresenter(@NonNull OPS3ActivityInterface view, @NonNull OPS3Interactor interactor){
        this.view = view;
        this.interactor = interactor;
    }

    public void fetchUserData(Context context){
        view.showPb();
        interactor.getUser(User.IDUSER, this);
        sequence(context);
    }

    public void sequence(Context context){
        orderPref = context.getSharedPreferences("sequence", Context.MODE_PRIVATE);
        typeSequence = orderPref.getInt("type",0);
    }

    public void retireTaller(){
        if(typeSequence==1){//sequencia para un solo producto

            Gson gson = new Gson();
            String jsonOPSOrder = orderPref.getString("ops", "");
            Orders orders = gson.fromJson(jsonOPSOrder, Orders.class);

            orders.setShipping(1);

            SharedPreferences.Editor editor = orderPref.edit();
            Gson gson1 = new Gson();
            String json = gson1.toJson(orders);
            editor.putString("ops", json);
            editor.commit();

        }else if (typeSequence==2){//sequencia para carrito

            Gson gson = new Gson();
            String jsonOPSOrder = orderPref.getString("cart", "");
            Orders orders = gson.fromJson(jsonOPSOrder, Orders.class);

            orders.setShipping(1);

            SharedPreferences.Editor editor = orderPref.edit();
            Gson gson1 = new Gson();
            String json = gson1.toJson(orders);
            editor.putString("cart", json);
            editor.commit();
        }
    }

    public void sendToCustomer(){
        if(typeSequence==1){//sequencia para un solo producto

            Gson gson = new Gson();
            String jsonOPSOrder = orderPref.getString("ops", "");
            Orders orders = gson.fromJson(jsonOPSOrder, Orders.class);

            Integer oldOrderPrice = orders.getOrder_price();
            Integer newOrderPrice = oldOrderPrice + priceShipping;

            orders.setShipping(2);
            orders.setOrder_price(newOrderPrice);

            SharedPreferences.Editor editor = orderPref.edit();
            Gson gson1 = new Gson();
            String json = gson1.toJson(orders);
            editor.putString("ops", json);
            editor.commit();

        }else if (typeSequence==2){//sequencia para carrito

            Gson gson = new Gson();
            String jsonOPSOrder = orderPref.getString("cart", "");
            Orders orders = gson.fromJson(jsonOPSOrder, Orders.class);

            Integer oldOrderPrice = orders.getOrder_price();
            Integer newOrderPrice = oldOrderPrice + priceShipping;

            orders.setShipping(2);
            orders.setOrder_price(newOrderPrice);

            SharedPreferences.Editor editor = orderPref.edit();
            Gson gson1 = new Gson();
            String json = gson1.toJson(orders);
            editor.putString("cart", json);
            editor.commit();
        }
    }

    @Override
    public void onSucces(Location locationFechted) {
        view.setLocation(locationFechted);
        view.hidePb();
        view.setLayoutVisible();

        if(locationFechted.getProvince().equals("CABA")){
            view.setShippingCost("¡Gratis!");
            priceShipping = 0;
        }else {
            view.setShippingCost("$ARS 1000");
            priceShipping = 1000;
        }

        if(typeSequence==1){//precio producto
            Gson gson = new Gson();
            String jsonOPSOrder = orderPref.getString("ops", "");
            Orders orders = gson.fromJson(jsonOPSOrder, Orders.class);

            view.orderPrice(orders.getOrder_price());

        }else if (typeSequence==2){//precio carrito

            Gson gson = new Gson();
            String jsonOPSOrder = orderPref.getString("cart", "");
            Orders orders = gson.fromJson(jsonOPSOrder, Orders.class);

            view.orderPrice(orders.getOrder_price());
        }
    }

    @Override
    public void onFailure() {
        view.hidePb();
        view.onError();
    }

    @Override
    public void nullLocation() {
        view.setLayoutVisible();
        view.hidePb();
        view.noLocation();
    }
}
