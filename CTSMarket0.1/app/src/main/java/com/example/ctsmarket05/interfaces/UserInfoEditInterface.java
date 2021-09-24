package com.example.ctsmarket05.interfaces;

public interface UserInfoEditInterface {

    void updateInfo(String name, Integer phone, Integer dni);
    void alert();

    void backTo();

    void hideLayout();
    void showProgressbar();

    void onError();
}
