package com.example.ctsmarket05.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ctsmarket05.R;
import com.example.ctsmarket05.activities.productsClasses.ProductsActivity1;
import com.example.ctsmarket05.clickListeners.OrdersOnCustomClickListener;
import com.example.ctsmarket05.adapters.OrdersAdapter;
import com.example.ctsmarket05.entities.Orders;
import com.example.ctsmarket05.retrofit.ordersRetrofit.OrdersCartGET;
import com.example.ctsmarket05.retrofit.ordersRetrofit.OrdersGET;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class OrdersActivity extends AppCompatActivity implements OrdersOnCustomClickListener {

    private OrdersAdapter ordersAdapter = new OrdersAdapter(this);
    private TextView tvCart;
    private TextView tvOrders;
    private Button btnCart;
    private BottomNavigationView bottomNavigationView ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        findViews();
        navigationView();
        rvOrders();
        getData();
        expandCart();
    }

    private void navigationView() {

        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedLiseter);
        bottomNavigationView.setSelectedItemId(R.id.cart);
    }


    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedLiseter = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()){
                case R.id.home:
                    Intent goHome = new Intent(getApplicationContext(), HomeActivity.class);
                    finish();
                    startActivity(goHome);
                    return true;

                case R.id.favorites:
                    Intent goFavs = new Intent(getApplicationContext(), ProductsActivity1.class);
                    startActivity(goFavs);
                    finish();
                    return true;

                case R.id.info:
                    Intent goInfo = new Intent(getApplicationContext(), InfoActivity.class);
                    startActivity(goInfo);
                    finish();
                    return true;
            }

            return false;
        }
    };

    private void findViews() {

        tvOrders = findViewById(R.id.tv_orders_finalizedO);
        btnCart = findViewById(R.id.btn_buy_cartO);
        bottomNavigationView = findViewById(R.id.bottom_navigation_cart);

    }

    private void expandCart() {

       btnCart.setOnClickListener(v -> {

           Intent expandCart= new Intent(this,CartExpandedActivity.class);
           startActivity(expandCart);
        });

    }

    private void getData() {

        //muestra el carrito activo
        OrdersCartGET ordersCartGET = new OrdersCartGET();
        ordersCartGET.SetOnDataListenerOrderCart(order -> {

            if (order.getId_order() != null){
                Integer price = order.getOrder_price();
                Integer quantity_products = order.getQuantity_products();
                Integer state = order.getOrder_state();

                tvCart.setText(price.toString() + quantity_products.toString() + state.toString());
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
