package com.example.ctsmarket05.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.NotNull;

public abstract class BaseFragment<PresenterFragment extends BasePresenterFragments> extends Fragment {

    protected PresenterFragment presenterFragment;

    public int a;

    @NotNull
    protected abstract PresenterFragment createPresenter(@NotNull final Context context);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenterFragment = createPresenter(getContext());
        presenterFragment.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = null;
        super.onCreateView(inflater, container, savedInstanceState);
        presenterFragment.onCreateView(inflater, container, savedInstanceState);
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenterFragment.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        presenterFragment.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenterFragment.onResume();
    }

    @Override
    public void onPause() {
    super.onPause();
    presenterFragment.onPause();
    }

    @Override
    public void onStop() {
    super.onStop();
    presenterFragment.onStop();
    }

    @Override
    public void onDestroyView() {
    super.onDestroyView();
    presenterFragment.onDestroyView();
    }

    @Override
    public void onDestroy() {
    super.onDestroy();
    presenterFragment.onDestroy();
    }

    @Override
    public void onDetach() {
    super.onDetach();
    presenterFragment.onDetach();
    }
}
