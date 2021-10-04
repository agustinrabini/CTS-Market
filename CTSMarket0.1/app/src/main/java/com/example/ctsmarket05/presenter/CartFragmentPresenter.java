package com.example.ctsmarket05.presenter;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.example.ctsmarket05.adapters.ProductsOrdersAdapter;
import com.example.ctsmarket05.base.BasePresenterFragments;
import com.example.ctsmarket05.entities.Orders;
import com.example.ctsmarket05.entities.ProductsOrder;
import com.example.ctsmarket05.entities.User;
import com.example.ctsmarket05.interfaces.CartFragmentInterface;
import com.example.ctsmarket05.interfaces.clickListeners.ProductsOrdersOnCustomClickListener;
import com.example.ctsmarket05.model.CartInteractor;
import com.google.gson.Gson;

import java.util.List;

public class CartFragmentPresenter extends BasePresenterFragments implements CartInteractor.onCartFetched, ProductsOrdersOnCustomClickListener {

    private CartFragmentInterface view;
    private CartInteractor cartInteractor;
    private ProductsOrdersAdapter productsOrdersAdapter = new ProductsOrdersAdapter(this);
    private Integer quantity;
    private Integer orderPrice;

    public CartFragmentPresenter(@NonNull CartFragmentInterface view, @NonNull CartInteractor cartInteractor){
        this.view = view;
        this.cartInteractor = cartInteractor;
    }

    public void fetchCart() {
        view.showProgressBar();
        cartInteractor.getCart(User.IDUSER, this);
    }

    public void setSequence(Context context){
        Orders orders = new Orders(
                User.IDUSER,
                orderPrice,
                quantity,
                null,
                null,
                null);

        SharedPreferences orderPref = context.getSharedPreferences("sequence", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = orderPref.edit();

        Gson gson = new Gson();
        String orderCart = gson.toJson(orders);
        editor.putString("cart", orderCart);
        editor.putInt("type",2);
        editor.apply();
    }

    @Override
    public void onSucces(List<ProductsOrder> productsOrderList, Integer cartPrice) {
        view.setProductsOrderList(productsOrdersAdapter, cartPrice);
        productsOrdersAdapter.setProductsOrders(productsOrderList);
        view.hideProgressBar();
        view.setLayoutVisible();

        orderPrice = cartPrice;
        quantity = productsOrderList.size();
    }

    @Override
    public void onFailure() {
        view.onError();
    }

    @Override
    public void nullCart(String message) {
        view.nullChecker(message);
        view.hideProgressBar();
    }

    @Override
    public void onItemClick(ProductsOrder productsOrder, int position) {
    }
}
