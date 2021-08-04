package com.example.ctsmarket05.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ctsmarket05.R;
import com.example.ctsmarket05.adapters.FavAdapter;
import com.example.ctsmarket05.clickListeners.FavOnCustomClickListener;
import com.example.ctsmarket05.entities.Favourite;
import com.example.ctsmarket05.retrofit.favouriteRetrofit.FavsGET;

import java.util.List;


public class FavoritesFragment extends Fragment implements FavOnCustomClickListener {

    private RecyclerView rvFavs;
    private FavAdapter favAdapter = new FavAdapter(this);

    public FavoritesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getData();
    }

    private void getData() {

        FavsGET favsGET = new FavsGET();
        favsGET.SetOnDataListenerFavourite(favourites -> {
            favAdapter.setFavs(favourites);
        });
        favsGET.getFavs();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_favorites, container, false);

        rvFavs= v.findViewById(R.id.rv_favs);

        rvFavs();
        return v;
    }

    private void rvFavs() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvFavs.setLayoutManager(layoutManager);
        rvFavs.setAdapter(favAdapter);
    }

    @Override
    public void onItemClick(Favourite favourite, int position) {}
}