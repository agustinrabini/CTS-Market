package com.example.ctsmarket05.interfaces;

import com.example.ctsmarket05.entities.Location;

public interface OPS3ActivityInterface {


    void fetchLocation();

    void setLocation(Location location);
    void noLocation();

    void changeLocation();

    void orderPrice();

    void info();

    void retireAtTaller();
    void sendToCustomer(Integer shippingPrice);

    void setShippingCost(Integer priceShipping);

    void showPb();
    void hidePb();

    void setLayoutVisible();

    void nextActivity();

    void onError();
}
