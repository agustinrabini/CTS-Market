package com.example.ctsmarket05.interfaces;

import com.example.ctsmarket05.entities.Orders;
import com.example.ctsmarket05.entities.Product;
import com.example.ctsmarket05.entities.ProductsOrder;
import com.example.ctsmarket05.entities.User;

public interface OPS5ActivityInterface {

    void sequenceChecker();

    void showPB();
    void hidePB();

    void onError();

    void setLayoutVisible();
    void setOPSValues(User user, Product product, Orders order);

    void confirmOrder();
}
