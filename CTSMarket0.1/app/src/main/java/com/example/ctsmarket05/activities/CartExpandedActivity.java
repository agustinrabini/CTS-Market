package com.example.ctsmarket05.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ctsmarket05.R;
import com.example.ctsmarket05.activities.productsClasses.ProductsActivity2;
import com.example.ctsmarket05.activities.productsClasses.ProductsActivity3;
import com.example.ctsmarket05.clickListeners.ProductsOrdersOnCustomClickListener;
import com.example.ctsmarket05.adapters.ProductsOrdersAdapter;
import com.example.ctsmarket05.entities.Orders;
import com.example.ctsmarket05.entities.ProductsOrder;
import com.example.ctsmarket05.retrofit.ordersRetrofit.OrdersCartGET;
import com.example.ctsmarket05.retrofit.productsOrderRetrofit.ProductsOrdersGET;

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

            Intent from= new Intent(this, ProductsActivity3.class);
            from.putExtra("from", "cartSequence");
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

            tvProducts.setText(price.toString() + quantity_products.toString() + state.toString());
            tvProducts.setText(price.toString() + quantity_products.toString() + state.toString());

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

        tvProducts = findViewById(R.id.tv_cart_productsCE);
        tvBuyCart = findViewById(R.id.tv_buy_cartCE);
    }

    @Override
    public void onItemClick(ProductsOrder productsOrder, int position) {
    }
}
