package com.example.ctsmarket05.interfaces;

import com.example.ctsmarket05.adapters.ProductsAdapter;
import com.example.ctsmarket05.adapters.ProductsOrdersAdapter;
import com.example.ctsmarket05.entities.Product;
import com.example.ctsmarket05.entities.ProductsOrder;

import java.util.List;

public interface ProductsOrderInterface {

    void onError();

    void hideProgressBar();

    void setProductsOrderList(List<ProductsOrder> productsOrdersList, ProductsOrdersAdapter productsOrdersAdapter);
}
