package com.example.ctsmarket05.presenter;

import androidx.annotation.NonNull;

import com.example.ctsmarket05.base.BasePresenterActivities;
import com.example.ctsmarket05.entities.User;
import com.example.ctsmarket05.interfaces.OPS2ActivityInterface;
import com.example.ctsmarket05.model.OPS2Interactor;

public class OPS2ActivityPresenter extends BasePresenterActivities implements OPS2Interactor.Interactor {

    private OPS2ActivityInterface view;
    private OPS2Interactor interactor;

    public OPS2ActivityPresenter(@NonNull OPS2ActivityInterface view, @NonNull OPS2Interactor interactor){
        this.view = view;
        this.interactor = interactor;
    }

    public void fetchUserData(){
        view.showProgressBar();
        interactor.getUser(User.IDUSER, this);
    }

    @Override
    public void onSucces(User user) {

        if ((user.getName_lastname()!=null) || (user.getDni()!=null) || (user.getPhone()!=null))
        {
            view.setUserData(user);
            view.hideProgressBar();
            view.dataFounded();

        }else{
            view.hideProgressBar();
            view.dataNotFounded();
        }

        view.setLayoutVisible();
    }

    @Override
    public void onFailure() {
        view.onError();
        view.hideProgressBar();
    }
}
