package com.example.ctsmarket05.activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ctsmarket05.R;
import com.example.ctsmarket05.activities.clickListeners.OrdersOnCustomClickListener;
import com.example.ctsmarket05.adapters.OrdersAdapter;
import com.example.ctsmarket05.entities.Orders;
import com.example.ctsmarket05.entities.User;
import com.example.ctsmarket05.retrofit.ordersRetrofit.OrdersCartGET;
import com.example.ctsmarket05.retrofit.ordersRetrofit.OrdersGET;

public class OrdersActivity extends AppCompatActivity implements OrdersOnCustomClickListener {

    private OrdersAdapter ordersAdapter = new OrdersAdapter(this);
    private TextView tvCart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        rvOrders();
        getData();
    }

    private void getData() {

        OrdersCartGET ordersCartGET = new OrdersCartGET();
        ordersCartGET.SetOnDataListenerOrderCart(order -> {

           Integer price = order.getOrder_price();
           Integer quantity_products = order.getQuantity_products();
           Integer state = order.getOrder_state();

           tvCart.setText(price.toString() + quantity_products.toString() + state.toString());
        });
        ordersCartGET.getOrderCart();

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
