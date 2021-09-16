package com.example.ctsmarket05.view.fragments;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.ctsmarket05.R;
import com.example.ctsmarket05.adapters.FavAdapter;
import com.example.ctsmarket05.base.BaseFragment;
import com.example.ctsmarket05.interfaces.FavoritesFragmentInterface;
import com.example.ctsmarket05.entities.Favourite;
import com.example.ctsmarket05.model.favourite.FavsGET;
import com.example.ctsmarket05.presenter.FavoritesFragmentPresenter;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.ThreeBounce;

import org.jetbrains.annotations.NotNull;

import java.util.List;


public class FavoritesFragment extends BaseFragment<FavoritesFragmentPresenter> implements FavoritesFragmentInterface{

    public static RecyclerView rvFavs;
    private TextView tvFav;
    private TextView tvGeneric;
    private TextView tvError;
    private ImageView ivFav;
    private ImageView ivReload;
    public static ProgressBar progressBarFav;
    private int ligthBlueColor = Color.parseColor("#75AADB");

    public FavoritesFragment() {
    }

    @NotNull
    @Override
    protected FavoritesFragmentPresenter createPresenter(@NotNull Context context) {
        return new FavoritesFragmentPresenter(this, new FavsGET());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_favorites, container, false);

        rvFavs= v.findViewById(R.id.rv_favs);
        progressBarFav = v.findViewById(R.id.pb_fav);
        tvFav = v.findViewById(R.id.tv_fav);
        tvGeneric = v.findViewById(R.id.tv_generic_fav);
        tvError = v.findViewById(R.id.tv_error_fav);
        ivFav = v.findViewById(R.id.iv_fav);
        ivReload = v.findViewById(R.id.iv_reload_fav);

        fetchData();
        return v;
    }

    @Override
    public void fetchData() {
        presenterFragment.fetchFavs();
    }

    @Override
    public void favoritesNull(String message) {
        tvFav.setText(message);
        tvFav.setVisibility(View.VISIBLE);
        tvGeneric.setVisibility(View.VISIBLE);
    }

    @Override
    public void setFavsList(List<Favourite> favouriteList, FavAdapter favAdapter) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvFavs.setLayoutManager(layoutManager);
        rvFavs.setAdapter(favAdapter);
        favAdapter.setFavs(favouriteList);
    }

    @Override
    public void showProgressBar() {
        Sprite threeBounce = new ThreeBounce();
        threeBounce.setColor(ligthBlueColor);
        progressBarFav.setIndeterminateDrawable(threeBounce);
        progressBarFav.setVisibility(View.VISIBLE);
        ivFav.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBarFav.setVisibility(View.INVISIBLE);
        ivFav.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setLayoutVisible() {
        tvFav.setText("Esta es tu lista de productos favoritos:");
        tvFav.setVisibility(View.VISIBLE);
        tvGeneric.setVisibility(View.VISIBLE);
        rvFavs.setVisibility(View.VISIBLE);
    }

    @Override
    public void onError() {
        ivReload.setVisibility(View.VISIBLE);
        tvError.setVisibility(View.VISIBLE);
        ivFav.setVisibility(View.GONE);
    }

    @Override
    public void reload() {
        presenterFragment.fetchFavs();
    }
}