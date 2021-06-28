package com.example.ctsmarket05.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ctsmarket05.R;
import com.example.ctsmarket05.retrofit.ordersRetrofit.OrderGET;

public class CartExpandedActivity extends AppCompatActivity {

    private TextView tvOptions;
    private TextView tvProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_expanded);

        findviews();
        getData();
    }

    private void getData() {

        Intent fromOrdersActivity = getIntent();
        Integer id_order = fromOrdersActivity.getIntExtra("id_order",0);

        OrderGET orderGET = new OrderGET();
        orderGET.SetOnDataListenerOrder(order -> {

        });
        orderGET.getOrder(id_order);
    }

    private void findviews() {

        tvProducts = findViewById(R.id.tv_cart_products);
    }
}
