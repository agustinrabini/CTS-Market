package com.example.ctsmarket05.activities;

import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.FtsOptions;

import com.example.ctsmarket05.R;
import com.example.ctsmarket05.activities.clickListeners.OrdersOnCustomClickListener;
import com.example.ctsmarket05.adapters.OrdersAdapter;
import com.example.ctsmarket05.entities.Orders;
import com.example.ctsmarket05.retrofit.ordersRetrofit.OrdersGET;

import java.util.List;

public class CartActivity extends AppCompatActivity implements OrdersOnCustomClickListener {

    private OrdersAdapter ordersAdapter = new OrdersAdapter(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        rvOrders();
        getData();
    }

    private void getData() {

        OrdersGET ordersGET = new OrdersGET();
        ordersGET.SetOnDataListenerOrders(orders -> {
            ordersAdapter.setOrders(orders);
        });
        ordersGET.getOrders();
    }

    private void rvOrders() {
        RecyclerView rvOrders = findViewById(R.id.rv_orders);
        LinearLayoutManager layoutManager  = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        rvOrders.setLayoutManager(layoutManager);
        rvOrders.setAdapter(ordersAdapter);
    }

    @Override
    public void onItemClick(Orders order, int position) {
    }
}
