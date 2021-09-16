package com.example.ctsmarket05.interfaces;

import com.example.ctsmarket05.adapters.ProductsOrdersAdapter;
import com.example.ctsmarket05.entities.ProductsOrder;

import java.util.List;

public interface CartFragmentInterface {

    void fetchData();

    void showProgressBar();
    void hideProgressBar();

    void nullChecker(String message);
    void setLayoutVisible();

    void setProductsOrderList(List<ProductsOrder> productsOrdersList, ProductsOrdersAdapter productsOrdersAdapter, Integer cartPrice);

    void onError();
    void reload();
}
