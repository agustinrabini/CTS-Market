package com.example.ctsmarket05.presenter;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.ctsmarket05.base.BasePresenterActivities;
import com.example.ctsmarket05.entities.User;
import com.example.ctsmarket05.interfaces.UserInfoEditInterface;
import com.example.ctsmarket05.model.UserInfoEditInteractor;

public class UserInfoEditPresenter extends BasePresenterActivities implements UserInfoEditInteractor.onUserUpdated{

    private UserInfoEditInterface view;
    private UserInfoEditInteractor interactor;

    public UserInfoEditPresenter(@NonNull UserInfoEditInterface view, @NonNull UserInfoEditInteractor interactor){
        this.view = view;
        this.interactor = interactor;
    }

    public void updateInfo(String name, Integer phone, Integer dni){
        interactor.putInfo(User.IDUSER, name, phone, dni, this);
    }

    @Override
    public void onSucces() {
        view.backTo();
        view.showProgressbar();
        view.hideLayout();
    }

    @Override
    public void onFailure() {
        view.onError();
        view.backTo();
    }
}
