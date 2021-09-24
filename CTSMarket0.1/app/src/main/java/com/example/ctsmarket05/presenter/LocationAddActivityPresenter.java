package com.example.ctsmarket05.presenter;

import androidx.annotation.NonNull;

import com.example.ctsmarket05.base.BasePresenterActivities;
import com.example.ctsmarket05.entities.Location;
import com.example.ctsmarket05.interfaces.LocationAddActivityInterface;
import com.example.ctsmarket05.interfaces.UserInfoEditInterface;
import com.example.ctsmarket05.model.LocationAddInteractor;
import com.example.ctsmarket05.model.UserInfoEditInteractor;

public class LocationAddActivityPresenter extends BasePresenterActivities implements LocationAddInteractor.onLocationUpdated {

    private LocationAddActivityInterface view;
    private LocationAddInteractor interactor;

    public LocationAddActivityPresenter(@NonNull LocationAddActivityInterface view, @NonNull LocationAddInteractor interactor){
        this.view = view;
        this.interactor = interactor;
    }

    public void interaction (Location location){
        interactor.locationInteraction(location, this);
    }

    @Override
    public void onSucces() {
        view.showProgressbar();
        view.backTo();
    }

    @Override
    public void onFailure() {
       view.onError();
       view.showProgressbar();
       view.hideLayout();
       view.backTo();
    }
}
