package com.example.ctsmarket05.view.fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.ctsmarket05.R;
import com.example.ctsmarket05.adapters.FavAdapter;
import com.example.ctsmarket05.interfaces.clickListeners.FavOnCustomClickListener;
import com.example.ctsmarket05.entities.Favourite;
import com.example.ctsmarket05.entities.User;
import com.example.ctsmarket05.model.favourite.FavNullCheckGET;
import com.example.ctsmarket05.model.favourite.FavsGET;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.ThreeBounce;


public class FavoritesFragment extends Fragment implements FavOnCustomClickListener {

    public static RecyclerView rvFavs;
    private FavAdapter favAdapter = new FavAdapter(this);
    private TextView tvFav;
    public static ProgressBar progressBarFav;
    private int ligthBlueColor = Color.parseColor("#75AADB");

    public FavoritesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void nullChecker(){

        Sprite pb = new ThreeBounce();
        pb.setColor(ligthBlueColor);
        progressBarFav.setIndeterminateDrawable(pb);

        new CountDownTimer(1000, 1000) {

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                progressBarFav.setVisibility(View.INVISIBLE);

            }
        }.start();

        FavNullCheckGET favNullCheckGET = new FavNullCheckGET();
        favNullCheckGET.SetOnDataInterfaceFavCheck (check -> {

            String c =  new String(check.getBytes());

            Integer e = Integer.parseInt(c);

            switch (e) {

                case 0: {

                    tvFav.setText("Esta es tu lista de favoritos. Cuando guardes productos en favoritos aparecerán aquí.");
                }
                break;

                case 1:{

                    rvFavs();
                    getData();
                    tvFav.setText("Esta es tu lista de favoritos:");
                }
                break;
            }

        });
        favNullCheckGET.check(User.IDUSER);
    }

    private void getData() {

        int ligthBlueColor = Color.parseColor("#75AADB");
        Sprite doubleBounce = new ThreeBounce();
        doubleBounce.setColor(ligthBlueColor);
        progressBarFav.setIndeterminateDrawable(doubleBounce);

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
        progressBarFav = v.findViewById(R.id.pb_fav);
        tvFav = v.findViewById(R.id.tv_fav);

        nullChecker();
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