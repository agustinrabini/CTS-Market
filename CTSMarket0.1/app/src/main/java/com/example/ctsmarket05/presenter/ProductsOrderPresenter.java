package com.example.ctsmarket05.presenter;

import androidx.annotation.NonNull;

import com.example.ctsmarket05.adapters.ProductsOrdersAdapter;
import com.example.ctsmarket05.base.BasePresenterFragments;
import com.example.ctsmarket05.entities.ProductsOrder;
import com.example.ctsmarket05.interfaces.ProductsFragmentInterface;
import com.example.ctsmarket05.interfaces.ProductsOrderInterface;
import com.example.ctsmarket05.interfaces.clickListeners.ProductsOrdersOnCustomClickListener;
import com.example.ctsmarket05.model.product.ProductsGET;
import com.example.ctsmarket05.model.productsOrder.ProductsOrdersGET;

import java.util.List;

public class ProductsOrderPresenter extends BasePresenterFragments implements ProductsOrdersGET.onProductsOrdersFetched, ProductsOrdersOnCustomClickListener {

    private ProductsOrdersAdapter productsOrdersAdapter = new ProductsOrdersAdapter(this);
    private ProductsOrderInterface view;
    private ProductsOrdersGET productsOrderInteractor;

    public ProductsOrderPresenter(@NonNull ProductsOrderInterface view, @NonNull ProductsOrdersGET productsOrderInteractor){
        this.view = view;
        this.productsOrderInteractor = productsOrderInteractor;
    }

    public void fetchProductsOrder(Integer idOrder){

        productsOrderInteractor.getProductsOrders(idOrder, this);
    }

    @Override
    public void onSucces(List<ProductsOrder> productsOrdersFetchedData) {
        view.hideProgressBar();
        view.setProductsOrderList(productsOrdersFetchedData, productsOrdersAdapter);
        productsOrdersAdapter.setProductsOrders(productsOrdersFetchedData);
    }

    @Override
    public void onFailure() {
        view.onError();
    }

    @Override
    public void onItemClick(ProductsOrder productsOrder, int position) {
    }
}
