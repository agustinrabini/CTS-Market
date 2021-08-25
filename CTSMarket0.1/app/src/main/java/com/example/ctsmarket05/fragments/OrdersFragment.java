package com.example.ctsmarket05.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.ctsmarket05.R;
import com.example.ctsmarket05.activities.CartExpandedActivity;
import com.example.ctsmarket05.adapters.OrdersAdapter;
import com.example.ctsmarket05.adapters.ProductsOrdersAdapter;
import com.example.ctsmarket05.clickListeners.OrdersOnCustomClickListener;
import com.example.ctsmarket05.clickListeners.ProductsOrdersOnCustomClickListener;
import com.example.ctsmarket05.entities.Orders;
import com.example.ctsmarket05.entities.ProductsOrder;
import com.example.ctsmarket05.retrofit.ordersRetrofit.OrdersCartGET;
import com.example.ctsmarket05.retrofit.ordersRetrofit.OrdersGET;
import com.example.ctsmarket05.retrofit.productsOrderRetrofit.ProductsOrdersGET;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.ThreeBounce;

public class OrdersFragment extends Fragment implements ProductsOrdersOnCustomClickListener {

    private ProductsOrdersAdapter productsOrdersAdapter = new ProductsOrdersAdapter(this);
    private TextView tvCartPriceOF;
    private TextView tvDetailsO;
    private Button btnCart;
    public static RecyclerView rvProductsOrder;
    public static ProgressBar progressBarOrders;
    private int ligthBlueColor = Color.parseColor("#75AADB");

    public OrdersFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void rvProductsOrder() {


        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvProductsOrder.setLayoutManager(layoutManager);
        rvProductsOrder.setAdapter(productsOrdersAdapter);
    }

    private void getData() {

        Sprite pb = new ThreeBounce();
        pb.setColor(ligthBlueColor);
        progressBarOrders.setIndeterminateDrawable(pb);

        OrdersCartGET ordersCartGET = new OrdersCartGET();
        ordersCartGET.SetOnDataListenerOrderCart(order -> {

            Integer id_order = order.getId_order();
            Integer price = order.getOrder_price();
            Integer quantity_products = order.getQuantity_products();
            Integer state = order.getOrder_state();

            Orders.ORDER_PRICE = price;
            tvCartPriceOF.setText("El valor del carrito es de $ARS " + order.getOrder_price().toString() + ".");

            ProductsOrdersGET productsOrdersGET = new ProductsOrdersGET();
            productsOrdersGET.SetOnDataListenerProductsOrders(productsOrders -> {
                productsOrdersAdapter.setProductsOrders(productsOrders);
            });
            productsOrdersGET.getProductsOrders(id_order);
        });
        ordersCartGET.getOrderCart();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v =  inflater.inflate(R.layout.fragment_orders, container, false);

        tvDetailsO = v.findViewById(R.id.tv_detailsO);
        tvCartPriceOF = v.findViewById(R.id.tv_cart_price_OF);
        btnCart = v.findViewById(R.id.btn_buy_cartO);
        rvProductsOrder = v.findViewById(R.id.rv_products_orderOF);
        progressBarOrders = v.findViewById(R.id.pb_orders);

        rvProductsOrder();
        getData();

        btnCart.setOnClickListener(z -> {

            Intent expandCart= new Intent(getContext(), CartExpandedActivity.class);
            startActivity(expandCart);
        });

        tvDetailsO.setOnClickListener(a -> {

            Intent expandCart= new Intent(getContext(), CartExpandedActivity.class);
            startActivity(expandCart);
        });

        return v;
    }

    @Override
    public void onItemClick(ProductsOrder productsOrder, int position) {}
}