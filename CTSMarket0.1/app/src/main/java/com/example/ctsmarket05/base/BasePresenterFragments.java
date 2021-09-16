package com.example.ctsmarket05.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.CallSuper;

public abstract class BasePresenterFragments {

    protected BasePresenterFragments(){
    }

    @CallSuper
    public void onAttach(Activity activity) {
    }

    @CallSuper
    public void onCreate(Bundle savedInstanceState) {
    }

    @CallSuper
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = null;
        return v;
    }

    @CallSuper
    public void onViewCreated(View view, Bundle savedInstanceState) {
    }

    @CallSuper
    public void onActivityCreated(Bundle savedInstanceState) {
    }

    @CallSuper
    public void onStart() {
    }

    @CallSuper
    public void onResume() {
    }

    @CallSuper
    public void onPause() {
    }

    @CallSuper
    public void onStop() {
    }

    @CallSuper
    public void onDestroyView() {
    }

    @CallSuper
    public void onDestroy() {
    }

    @CallSuper
    public void onDetach() {
    }
}
