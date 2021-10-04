package com.example.ctsmarket05.view.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.ctsmarket05.R;
import com.example.ctsmarket05.base.BaseFragment;
import com.example.ctsmarket05.interfaces.CartFragmentInterface;
import com.example.ctsmarket05.model.CartInteractor;
import com.example.ctsmarket05.presenter.CartFragmentPresenter;
import com.example.ctsmarket05.adapters.ProductsOrdersAdapter;
import com.example.ctsmarket05.view.activities.oneProductSequence.OPSActivity2;
import com.example.ctsmarket05.view.activities.oneProductSequence.OPSActivity6;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.ThreeBounce;

public class CartFragment extends BaseFragment<CartFragmentPresenter> implements CartFragmentInterface {

    private TextView tvCartPrice;
    private TextView tvCart;
    private TextView tvError;
    private TextView tvGeneric;
    private ImageView ivReload;
    private ImageView ivCart;
    private Button btnCart;
    public static RecyclerView rvProductsCart;
    public static ProgressBar progressBarCart;
    private int ligthBlueColor = Color.parseColor("#75AADB");

    protected CartFragmentPresenter createPresenter(@NonNull Context context){
        return new CartFragmentPresenter(this, new CartInteractor());
    }

    public CartFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v =  inflater.inflate(R.layout.fragment_orders, container, false);

        tvCart = v.findViewById(R.id.tv_cart_cf);
        tvGeneric = v.findViewById(R.id.tv_generic_cf);
        tvError = v.findViewById(R.id.tv_error_cf);
        ivCart = v.findViewById(R.id.iv_cart_cf);
        ivReload = v.findViewById(R.id.iv_reload_cf);
        tvCartPrice = v.findViewById(R.id.tv_cart_price_cf);
        btnCart = v.findViewById(R.id.btn_buy_cart_cf);
        rvProductsCart = v.findViewById(R.id.rv_products_order_cf);
        progressBarCart = v.findViewById(R.id.pb_orders);

        btnCart.setOnClickListener(z -> {
            Intent expandCart= new Intent(getContext(), OPSActivity2.class);
            presenterFragment.setSequence(getContext());
            startActivity(expandCart);
        });

        tvCart.setOnClickListener(a -> {
            Intent expandCart= new Intent(getContext(), OPSActivity2.class);
            startActivity(expandCart);
        });

        ivReload.setOnClickListener(a -> {
            reload();
        });

        fetchData();
        return v;
    }

    @Override
    public void fetchData() {
        presenterFragment.fetchCart();
    }

    @Override
    public void showProgressBar() {
        Sprite pb = new ThreeBounce();
        pb.setColor(ligthBlueColor);
        ivCart.setVisibility(View.VISIBLE);
        progressBarCart.setIndeterminateDrawable(pb);
        progressBarCart.setVisibility(View.VISIBLE);
    }

    @Override
    public void setProductsOrderList(ProductsOrdersAdapter productsOrdersAdapter, Integer cartPrice) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvProductsCart.setLayoutManager(layoutManager);
        rvProductsCart.setAdapter(productsOrdersAdapter);
        tvCartPrice.setText("El valor del carrito es de " + cartPrice.toString()+"$ARS");
    }

    @Override
    public void nullChecker(String message){
        tvCart.setText(message);
        tvCart.setVisibility(View.VISIBLE);
        tvGeneric.setVisibility(View.VISIBLE);
    }

    @Override
    public void setLayoutVisible() {
        tvCart.setVisibility(View.VISIBLE);
        tvCart.setText("Estos son los productos en tu carrito: ");
        tvCartPrice.setVisibility(View.VISIBLE);
        btnCart.setVisibility(View.VISIBLE);
        rvProductsCart.setVisibility(View.VISIBLE);
        tvGeneric.setVisibility(View.VISIBLE);
        ivCart.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onError() {
        tvError.setVisibility(View.VISIBLE);
        ivReload.setVisibility(View.VISIBLE);
        ivCart.setVisibility(View.GONE);
    }

    @Override
    public void hideProgressBar() {
        progressBarCart.setVisibility(View.GONE);
        ivCart.setVisibility(View.GONE);
    }

    @Override
    public void reload() {
        presenterFragment.fetchCart();
    }
}