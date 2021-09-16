package com.example.ctsmarket05.base;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public abstract class BasePresenterActivities {
    //constructor nulo
    protected BasePresenterActivities(){
    }

    @CallSuper
    void onCreate(@Nullable final Bundle savedInstance){
    }

    @CallSuper
    void onResume(){
    }

    @CallSuper
    void onPause(){
    }

    @CallSuper
    void onSaveInstance(@NonNull final Bundle outState){
    }

    @CallSuper
    void onDestroy(){
    }

    @CallSuper
    void onActivityResult(final int requestCode, final int resultCode, @Nullable final Intent data){
    }

    @CallSuper
    void onRequestPermissionsResult(final int requestCode, @NonNull final String[] permissions, @NonNull final int[] grantResults){
    }
}
