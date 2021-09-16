package com.example.ctsmarket05.presenter;

import android.content.Context;
import android.net.Uri;

import androidx.annotation.NonNull;

import com.example.ctsmarket05.adapters.ProductsAdapter;
import com.example.ctsmarket05.base.BasePresenterFragments;
import com.example.ctsmarket05.entities.Product;
import com.example.ctsmarket05.interfaces.ProductsFragmentInterface;
import com.example.ctsmarket05.interfaces.clickListeners.ProductsOnCustomClickListener;
import com.example.ctsmarket05.model.product.ProductsGET;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import java.util.List;

public class ProductsFragmentPresenter extends BasePresenterFragments implements ProductsGET.onProductsFetched, ProductsOnCustomClickListener {

    private ProductsFragmentInterface view;
    private ProductsGET productsInteractor;
    private ProductsAdapter productsAdapter = new ProductsAdapter(this);

    public ProductsFragmentPresenter(@NonNull ProductsFragmentInterface view, @NonNull ProductsGET productsInteractor){
        this.view = view;
        this.productsInteractor = productsInteractor;
    }

    public void fetchData(String filter){
        view.hideRv();
        view.showProgressBar();
        productsInteractor.getProducts(filter,this);
    }

    public void getUserData(Context context){
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(context);
        if (acct != null) {
            String personName = acct.getDisplayName();
            Uri personPhoto = acct.getPhotoUrl();

            view.showUserData(personName, personPhoto.toString(), context);
        }
    }

    @Override
    public void onSucces(List<Product> productsFetchedData) {
        view.hideProgressBar();
        view.showRv();
        view.setProductsList(productsFetchedData, productsAdapter);
    }

    @Override
    public void onFailure() {
        view.onError();
    }

    @Override
    public void nullList(String typeOfNullList) {
        view.nullListProducts(typeOfNullList);
        view.hideProgressBar();
    }

    @Override
    public void onItemClick(Product product, int position) {
    }
}
