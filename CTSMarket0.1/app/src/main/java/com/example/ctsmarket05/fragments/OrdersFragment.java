package com.example.ctsmarket05.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.ctsmarket05.R;
import com.example.ctsmarket05.activities.CartExpandedActivity;
import com.example.ctsmarket05.adapters.OrdersAdapter;
import com.example.ctsmarket05.clickListeners.OrdersOnCustomClickListener;
import com.example.ctsmarket05.entities.Orders;
import com.example.ctsmarket05.retrofit.ordersRetrofit.OrdersCartGET;
import com.example.ctsmarket05.retrofit.ordersRetrofit.OrdersGET;

public class OrdersFragment extends Fragment implements OrdersOnCustomClickListener {

    private OrdersAdapter ordersAdapter = new OrdersAdapter(this);
    private TextView tvCart;
    private TextView tvDetailsO;
    private Button btnCart;
    private RecyclerView rvOrders;

    public OrdersFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getData();
    }

    private void rvOrders() {

        LinearLayoutManager layoutManager  = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        rvOrders.setLayoutManager(layoutManager);
        rvOrders.setAdapter(ordersAdapter);
    }

    private void getData() {

        //muestra el carrito activo
        OrdersCartGET ordersCartGET = new OrdersCartGET();
        ordersCartGET.SetOnDataListenerOrderCart(order -> {

            if (order.getId_order() != null){
                Integer price = order.getOrder_price();
                Integer quantity_products = order.getQuantity_products();
                Integer state = order.getOrder_state();

                tvCart.setText("El carrito activo cuenta con un total de: " + quantity_products.toString() +
                        "objetos, por un valor tota de" + price.toString()+"$ARS en estos momentos.");
                btnCart.setVisibility(View.VISIBLE);
            }else{
                tvCart.setText("No hay productos en el carrito");
            }

        });
        ordersCartGET.getOrderCart();

        //muestra las ordenes ya terminadas
        OrdersGET ordersGET = new OrdersGET();
        ordersGET.SetOnDataListenerOrders(orders -> {
            ordersAdapter.setOrders(orders);
        });
        ordersGET.getOrders();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v =  inflater.inflate(R.layout.fragment_orders, container, false);

        tvCart = v.findViewById(R.id.tv_cartO);
        tvDetailsO = v.findViewById(R.id.tv_detailsO);
        btnCart = v.findViewById(R.id.btn_buy_cartO);
        rvOrders = v.findViewById(R.id.rv_ordersO);

        rvOrders();

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
    public void onItemClick(Orders order, int position) { }
}