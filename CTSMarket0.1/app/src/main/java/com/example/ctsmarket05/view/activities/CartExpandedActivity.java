package com.example.ctsmarket05.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ctsmarket05.R;
import com.example.ctsmarket05.view.activities.oneProductSequence.OPSActivity3;
import com.example.ctsmarket05.interfaces.clickListeners.ProductsOrdersOnCustomClickListener;
import com.example.ctsmarket05.adapters.ProductsOrdersAdapter;
import com.example.ctsmarket05.entities.Orders;
import com.example.ctsmarket05.entities.ProductsOrder;
import com.example.ctsmarket05.model.orders.OrdersCartGET;
import com.example.ctsmarket05.model.productsOrder.ProductsOrdersGET;

public class CartExpandedActivity extends AppCompatActivity implements ProductsOrdersOnCustomClickListener {

    private ProductsOrdersAdapter productsOrdersAdapter = new ProductsOrdersAdapter(this);
    private TextView tvBuyCart;
    private TextView tvProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_expanded);

        findviews();
        getData();
        buyCart();
    }

    private void buyCart() {

        tvBuyCart.setOnClickListener(v -> {

            Orders.ORDER_SEQUENCE = "cartSequence";

            Intent from= new Intent(this, OPSActivity3.class);
            startActivity(from);
        });

    }

    private void getData() {

        //Intent fromOrdersActivity = getIntent();
        //Integer id_order = fromOrdersActivity.getIntExtra("id_order",0);

        OrdersCartGET ordersCartGET = new OrdersCartGET();
        ordersCartGET.SetOnDataListenerOrderCart(order -> {

            Integer id_order = order.getId_order();
            Integer price = order.getOrder_price();
            Integer quantity_products = order.getQuantity_products();
            Integer state = order.getOrder_state();

            Orders.ORDER_PRICE = price;

            ProductsOrdersGET productsOrdersGET = new ProductsOrdersGET();
            productsOrdersGET.SetOnDataListenerProductsOrders(productsOrders -> {
                productsOrdersAdapter.setProductsOrders(productsOrders);
            });
            productsOrdersGET.getProductsOrders(id_order);

            rvProductsOrder();
        });
        ordersCartGET.getOrderCart();
    }

    private void rvProductsOrder() {

        RecyclerView rvProductsOrder = findViewById(R.id.rv_products_orderCE);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvProductsOrder.setLayoutManager(layoutManager);
        rvProductsOrder.setAdapter(productsOrdersAdapter);
    }

    private void findviews() {

        tvBuyCart = findViewById(R.id.btn_buy_cartCE);
    }

    @Override
    public void onItemClick(ProductsOrder productsOrder, int position) {
    }
}
