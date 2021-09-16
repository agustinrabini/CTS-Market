package com.example.ctsmarket05.view.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.ctsmarket05.R;
import com.example.ctsmarket05.base.BaseFragment;
import com.example.ctsmarket05.interfaces.ProductsFragmentInterface;
import com.example.ctsmarket05.presenter.ProductsFragmentPresenter;
import com.example.ctsmarket05.view.fragments.bottomSheets.FilterBottomSheet;
import com.example.ctsmarket05.view.activities.userActivities.UserActivty;
import com.example.ctsmarket05.adapters.ProductsAdapter;
import com.example.ctsmarket05.interfaces.clickListeners.ProductsOnCustomClickListener;
import com.example.ctsmarket05.entities.Product;
import com.example.ctsmarket05.model.product.ProductsGET;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.ThreeBounce;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductsFragment extends BaseFragment<ProductsFragmentPresenter> implements ProductsOnCustomClickListener, FilterBottomSheet.FilterListenerBottomSheet, ProductsFragmentInterface {

    public static RecyclerView rvProducts;
    private ImageView ivUserPhoto;
    private ImageView ivGeneric;
    private ImageView ivReload;
    private ImageView ivHome;
    private TextView tvFilter;
    private TextView tvUserName;
    private TextView tvGeneric;
    private TextView tvError;
    public static ProgressBar progressBarHome;
    private int ligthBlueColor = Color.parseColor("#75AADB");

    protected ProductsFragmentPresenter createPresenter(@NonNull Context context){
        return new ProductsFragmentPresenter(this, new ProductsGET());
    }

    public ProductsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home, container, false);

        rvProducts = v.findViewById(R.id.rv_products);
        ivUserPhoto = v.findViewById(R.id.iv_user_photo);
        ivGeneric = v.findViewById(R.id.iv_generic_pf);
        ivHome = v.findViewById(R.id.iv_home_pf);
        ivReload = v.findViewById(R.id.iv_reload_pf);
        tvFilter = v.findViewById(R.id.tv_filter);
        tvUserName = v.findViewById(R.id.tv_user_name);
        tvGeneric = v.findViewById(R.id.tv_generic_pf);
        tvError = v.findViewById(R.id.tv_error_pf);
        progressBarHome = v.findViewById(R.id.pb_home);

        tvFilter.setOnClickListener(v1 -> {

            FilterBottomSheet filterBottomSheet = new FilterBottomSheet();
            filterBottomSheet.show(getChildFragmentManager(),"filterBottomSheet");
        });

        ivUserPhoto.setOnClickListener(v12 -> {

            Intent userActv = new Intent(getContext(), UserActivty.class);
            startActivity(userActv);
        });

        ivReload.setOnClickListener(v12 -> {
            fetchData("*");
        });

        getUserData(getContext());
        fetchData("*");//el filtro '*' indica que es para todos los productos.

        return v;
    }

    @Override
    public void onItemClick (Product product, int position) {}

    @Override
    public void onButtonClickedFilter(String filterProduct) {

        if (filterProduct.equals("*")){
            tvFilter.setText("Todos");
        }else{
            tvFilter.setText(filterProduct+"s");
        }

        fetchData(filterProduct);
    }

    @Override
    public void showProgressBar() {
        Sprite pb = new ThreeBounce();
        pb.setColor(ligthBlueColor);
        progressBarHome.setIndeterminateDrawable(pb);
        progressBarHome.setVisibility(View.VISIBLE);
        ivHome.setVisibility(View.VISIBLE);
        ivUserPhoto.setVisibility(View.GONE);
        ivGeneric.setVisibility(View.GONE);
        ivReload.setVisibility(View.GONE);
        tvUserName.setVisibility(View.GONE);
        tvGeneric.setVisibility(View.GONE);
        tvFilter.setVisibility(View.GONE);
        tvError.setVisibility(View.GONE);
    }

    @Override
    public void hideProgressBar() {
        progressBarHome.setVisibility(View.GONE);
        ivHome.setVisibility(View.GONE);
        ivUserPhoto.setVisibility(View.VISIBLE);
        ivGeneric.setVisibility(View.VISIBLE);
        tvGeneric.setVisibility(View.VISIBLE);
        tvUserName.setVisibility(View.VISIBLE);
        tvFilter.setVisibility(View.VISIBLE);
    }

    @Override
    public void showRv() {
        rvProducts.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideRv() {
        rvProducts.setVisibility(View.GONE);
    }

    @Override
    public void onError() {
        tvFilter.setVisibility(View.VISIBLE);
        progressBarHome.setVisibility(View.GONE);
        tvError.setVisibility(View.VISIBLE);
        ivReload.setVisibility(View.VISIBLE);
    }

    @Override
    public void getUserData(Context context) {
        presenterFragment.getUserData(context.getApplicationContext());
    }

    @Override
    public void showUserData(String userName, String userPhoto, Context context) {

        tvUserName.setText("Bienvenido! " + userName);
        Picasso.with(context).load(userPhoto).into(ivUserPhoto);
    }

    @Override
    public void setProductsList(List<Product> products, ProductsAdapter productsAdapter) {

        rvProducts.setLayoutManager(new GridLayoutManager(getContext(), 2));
        rvProducts.setAdapter(productsAdapter);
        rvProducts.setVisibility(View.VISIBLE);
        productsAdapter.setProducts(products);
    }

    @Override
    public void fetchData(String filter) {
        presenterFragment.fetchData(filter);
        tvFilter.setText("Todos");
    }

    @Override
    public void nullListProducts(String nullListProducts) {
        tvError.setVisibility(View.VISIBLE);
        tvError.setText("No hay " + nullListProducts + "s disponibles en este momento.");
        ivReload.setVisibility(View.VISIBLE);
    }

    @Override
    public void reload(String filter) {
        presenterFragment.fetchData(filter);
    }


}