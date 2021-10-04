package com.example.ctsmarket05.presenter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;

import com.example.ctsmarket05.base.BasePresenterActivities;
import com.example.ctsmarket05.entities.Orders;
import com.example.ctsmarket05.entities.User;
import com.example.ctsmarket05.interfaces.OPSActivityInterface;
import com.example.ctsmarket05.model.OPSInteractor;
import com.example.ctsmarket05.view.fragments.bottomSheets.QuantityBottomSheet;
import com.google.gson.Gson;

public class OPSActivityPresenter extends BasePresenterActivities implements OPSInteractor.opsInteractor {

    private OPSActivityInterface view;
    private OPSInteractor interactor;
    private Integer f;
    private Integer c;
    private Integer idProduct;
    private Integer stock;
    private Integer price;
    private Integer length;
    private String name;
    private String description;
    private String image;

    public OPSActivityPresenter(@NonNull OPSActivityInterface view, @NonNull OPSInteractor interactor){
        this.view = view;
        this.interactor = interactor;
    }

    public void getProductState(Intent intent){

        idProduct = intent.getIntExtra("idProduct",0);
        stock = intent.getIntExtra("stock",0);
        price = intent.getIntExtra("price",0);
        length = intent.getIntExtra("length",0);
        description = intent.getStringExtra("description");
        image = intent.getStringExtra("image");
        name = intent.getStringExtra("name");

        view.showProgressBar();
        interactor.checkCart(idProduct, User.IDUSER, this);
    }

    public void changeQuantity(FragmentManager fragmentManager){

        Bundle args = new Bundle();
        args.putInt("stock", stock);

        QuantityBottomSheet quantityBottomSheet = new QuantityBottomSheet();
        quantityBottomSheet.show(fragmentManager, "quantityBottomSheet");
        quantityBottomSheet.setArguments(args);
    }

    //si el producto está en el carrito lo quita. Si no está en carrito lo agrega.
    public void cartClicked(Integer quantityProduct){

        if (c == 10){

            interactor.deleteCart(User.IDUSER,idProduct , this);
            view.cartRemove();
            c=0;

        }else{

            Orders order = new Orders(User.IDUSER,price*quantityProduct,quantityProduct,10,null,"");
            interactor.addCart(idProduct, order, this);
            view.activeCart();
            c=10;
        }

    }

    //añade o quita un objeto de la lista de favoritos segun corresponda.
    public void favClicked(){

        if (f==10){
            view.favRemove();
            f=1;
        }else{
            view.activeFav();
            f=10;
        }

        interactor.favInteraction(User.IDUSER, idProduct, this);
    }

    public void nullStock(){
        if (stock == 0){
            view.nullStockCheck();
        }
    }

    public void buy(Context context, Integer quantity){
        Orders orders = new Orders(
                User.IDUSER,
                price*quantity,
                quantity,
                null,
                null,
                null);

        SharedPreferences orderPref = context.getSharedPreferences("sequence", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = orderPref.edit();

        Gson gson = new Gson();
        String jsonOPSOrder = gson.toJson(orders);
        editor.putString("ops", jsonOPSOrder);
        editor.putInt("idProduct",idProduct);
        editor.putInt("type",1);//TIPO DE SEQUENCIA
        editor.apply();
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

        view.setProduct(name, description,image,stock,price,length);
        view.setLayoutVisible();
        view.hideProgressBar();
    }

    @Override
    public void onFailure() {
        view.onError();
        view.hideProgressBar();
    }
}
