package com.example.ctsmarket05.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.ctsmarket05.R;
import com.example.ctsmarket05.activities.bottomSheets.FilterBottomSheet;
import com.example.ctsmarket05.activities.userActivities.UserActivty;
import com.example.ctsmarket05.adapters.ProductsAdapter;
import com.example.ctsmarket05.clickListeners.ProductsOnCustomClickListener;
import com.example.ctsmarket05.entities.Product;
import com.example.ctsmarket05.entities.User;
import com.example.ctsmarket05.retrofit.productRetrofit.ProductsGET;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.ThreeBounce;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.squareup.picasso.Picasso;

public class HomeFragment extends Fragment implements ProductsOnCustomClickListener, FilterBottomSheet.FilterListenerBottomSheet {

    private ProductsAdapter productsAdapter = new ProductsAdapter(this);
    public static RecyclerView rvProducts;
    private ImageView ivUserPhoto;
    private String filter;
    private String userName;
    private Uri personPhoto ;
    private TextView tvFilter;
    private TextView tvUserName;
    private TextView tvQuestionsHome;
    public static ProgressBar progressBarHome;
    private int ligthBlueColor = Color.parseColor("#75AADB");

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void getData(){

        Sprite pb = new ThreeBounce();
        pb.setColor(ligthBlueColor);
        progressBarHome.setIndeterminateDrawable(pb);

        ProductsGET productsGET = new ProductsGET();
        productsGET.SetOnDataListenerProd(products -> {
            productsAdapter.setProducts(products);
        });
        productsGET.getProducts("*");

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getContext());
        if (acct != null) {

            String personName = acct.getDisplayName();
            String personEmail = acct.getEmail();
            String personId = acct.getId();
            personPhoto = acct.getPhotoUrl();
            User.gmail = personEmail;
            userName = personName;
        }
    }

    private String getColoredSpanned(String text, String color) {
        String input = "<font color=" + color + ">" + text + "</font>";
        return input;
    }

    private void rvproducts(){

        rvProducts.setLayoutManager(new GridLayoutManager(getContext(), 2));
        rvProducts.setAdapter(productsAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home, container, false);
        rvProducts = v.findViewById(R.id.rv_products);
        ivUserPhoto = v.findViewById(R.id.iv_user_photo);
        tvFilter = v.findViewById(R.id.tv_filter);
        tvUserName = v.findViewById(R.id.tv_user_name);
        tvQuestionsHome = v.findViewById(R.id.tv_questions_home);
        progressBarHome = v.findViewById(R.id.pb_home);

        getData();
        rvproducts();

        tvFilter.setOnClickListener(v1 -> {
            FilterBottomSheet filterBottomSheet = new FilterBottomSheet();
            filterBottomSheet.show(getChildFragmentManager(),"filterBottomSheet");
        });

        //nombre y foto del user
        Picasso.with(v.getContext()).load(personPhoto).into(ivUserPhoto);
        tvUserName.setText("Bienvenido, " +userName);

        ivUserPhoto.setOnClickListener(v12 -> {
            Intent userActv = new Intent(getContext(), UserActivty.class);
            startActivity(userActv);
        });

        tvQuestionsHome.setOnClickListener(v14 -> {

            String telefono = "+54 1132424233";
            String url = "https://api.whatsapp.com/send?phone=";
            Intent llamada = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:" + telefono));
            llamada.setData(Uri.parse(url + telefono));
            startActivity(llamada);
        });

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

        ProductsGET productsGET = new ProductsGET();
        productsGET.SetOnDataListenerProd(products -> {
            productsAdapter.setProducts(products);
        });
        productsGET.getProducts(filterProduct);
    }
}