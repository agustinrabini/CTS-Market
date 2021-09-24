package com.example.ctsmarket05.presenter;

import androidx.annotation.NonNull;

import com.example.ctsmarket05.base.BasePresenterActivities;
import com.example.ctsmarket05.entities.Location;
import com.example.ctsmarket05.entities.User;
import com.example.ctsmarket05.interfaces.OPS2ActivityInterface;
import com.example.ctsmarket05.interfaces.OPS3ActivityInterface;
import com.example.ctsmarket05.model.OPS2Interactor;
import com.example.ctsmarket05.model.OPS3Interactor;

public class OPS3ActivityPresenter extends BasePresenterActivities implements OPS3Interactor.Interactor {

    private OPS3ActivityInterface view;
    private OPS3Interactor interactor;

    public OPS3ActivityPresenter(@NonNull OPS3ActivityInterface view, @NonNull OPS3Interactor interactor){
        this.view = view;
        this.interactor = interactor;
    }

    public void fetchUserData(){
        view.showPb();
        interactor.getUser(User.IDUSER, this);
    }

    @Override
    public void onSucces(Location locationFechted) {
        view.setLocation(locationFechted);
        view.hidePb();
        view.setLayoutVisible();
        view.orderPrice();

        if(locationFechted.getProvince().equals("CABA")){
            view.setShippingCost(0);
        }else {
            view.setShippingCost(1500);
        }

    }

    @Override
    public void onFailure() {
        view.hidePb();
        view.onError();
    }

    @Override
    public void nullLocation() {
        view.orderPrice();
        view.setLayoutVisible();
        view.hidePb();
        view.noLocation();
    }
}
