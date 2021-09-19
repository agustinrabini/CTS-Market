package com.example.ctsmarket05.presenter;

import androidx.annotation.NonNull;

import com.example.ctsmarket05.adapters.ProductsOrdersAdapter;
import com.example.ctsmarket05.base.BasePresenterFragments;
import com.example.ctsmarket05.entities.ProductsOrder;
import com.example.ctsmarket05.entities.User;
import com.example.ctsmarket05.interfaces.CartFragmentInterface;
import com.example.ctsmarket05.interfaces.ProductsOrderInterface;
import com.example.ctsmarket05.interfaces.clickListeners.ProductsOrdersOnCustomClickListener;
import com.example.ctsmarket05.model.orders.CartGET;

import java.util.List;

public class CartFragmentPresenter extends BasePresenterFragments implements CartGET.onCartFetched, ProductsOrdersOnCustomClickListener {

    private CartFragmentInterface view;
    private CartGET cartInteractor;
    private ProductsOrdersAdapter productsOrdersAdapter = new ProductsOrdersAdapter(this);

    public CartFragmentPresenter(@NonNull CartFragmentInterface view, @NonNull CartGET cartInteractor){
        this.view = view;
        this.cartInteractor = cartInteractor;
    }

    public void fetchCart() {
        view.showProgressBar();
        cartInteractor.getCart(User.IDUSER, this);
    }

    @Override
    public void onSucces(List<ProductsOrder> productsOrderList, Integer cartPrice) {
        view.setProductsOrderList(productsOrdersAdapter, cartPrice);
        productsOrdersAdapter.setProductsOrders(productsOrderList);
        view.hideProgressBar();
        view.setLayoutVisible();
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
