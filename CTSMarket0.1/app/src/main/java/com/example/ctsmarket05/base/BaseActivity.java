package com.example.ctsmarket05.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.NotNull;

public abstract class BaseActivity<PresenterActivity extends BasePresenterActivities> extends AppCompatActivity {

    protected PresenterActivity presenterActivity;

    @NotNull
    protected abstract PresenterActivity createPresenter(@NotNull final Context context);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenterActivity = createPresenter(this);
        presenterActivity.onCreate(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(@NonNull final Bundle outState, PersistableBundle outPersistenceState){
        super.onSaveInstanceState(outState, outPersistenceState);
        presenterActivity.onSaveInstance(outState);
    }

    @Override
    protected void onPause(){
        super.onPause();
        presenterActivity.onPause();
    }

    @Override
    protected void onResume(){
        super.onResume();
        presenterActivity.onResume();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        presenterActivity.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        presenterActivity.onActivityResult(requestCode, resultCode, data);
    }

    @CallSuper
    public void onRequestPermissionsResult(final int requestCode, @NonNull final String[] permissions, @NonNull final int[] grantResults){
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        presenterActivity.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
