package com.example.ctsmarket05.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ctsmarket05.R;
import com.example.ctsmarket05.activities.FilterBottomSheet;
import com.example.ctsmarket05.activities.userActivities.UserActivty;
import com.example.ctsmarket05.adapters.ProductsAdapter;
import com.example.ctsmarket05.clickListeners.ProductsOnCustomClickListener;
import com.example.ctsmarket05.entities.Product;
import com.example.ctsmarket05.entities.User;
import com.example.ctsmarket05.retrofit.productRetrofit.ProductsGET;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.squareup.picasso.Picasso;

public class HomeFragment extends Fragment implements ProductsOnCustomClickListener, FilterBottomSheet.FilterListenerBottomSheet {

    private ProductsAdapter productsAdapter = new ProductsAdapter(this);
    private RecyclerView rvProducts;
    private ImageView ivUserPhoto;
    private String filter;
    private String userName;
    private Uri personPhoto ;
    private TextView tvFilter;
    private TextView tvUserName;
    private TextView tvQuestionsHome;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getData();
    }

    public void getData(){

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

        rvproducts();

        tvFilter.setOnClickListener(v1 -> {
            FilterBottomSheet filterBottomSheet = new FilterBottomSheet();
            filterBottomSheet.show(getChildFragmentManager(),"filterBottomSheet");
        });

        if (personPhoto!= null){
            Picasso.with(v.getContext()).load(personPhoto).into(ivUserPhoto);
        }else{
            Picasso.with(v.getContext()).load(R.drawable.ic_user_ligth_blue).into(ivUserPhoto);
        }
        tvUserName.setText("Bienvenido, " +userName + "." + "\n" +"¿Qué buscás hoy?");

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
        }else{tvFilter.setText(filterProduct+"s");}

        ProductsGET productsGET = new ProductsGET();
        productsGET.SetOnDataListenerProd(products -> {
            productsAdapter.setProducts(products);
        });
        productsGET.getProducts(filterProduct);
    }
}