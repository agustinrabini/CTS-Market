package com.example.ctsmarket05.interfaces;

import com.example.ctsmarket05.entities.User;

public interface OPS2ActivityInterface {

    void setLayoutVisible();
    void showProgressBar();
    void hideProgressBar();


    void setUserData(User user);
    void fetchUserData();

    void changeClicked();

    void dataFounded();
    void dataNotFounded();

    void nextActivity();

    void onError();
}
