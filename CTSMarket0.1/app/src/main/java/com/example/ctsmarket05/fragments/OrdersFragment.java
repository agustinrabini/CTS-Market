package com.example.ctsmarket05.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.ctsmarket05.R;
import com.example.ctsmarket05.activities.CartExpandedActivity;
import com.example.ctsmarket05.adapters.ProductsOrdersAdapter;
import com.example.ctsmarket05.clickListeners.ProductsOrdersOnCustomClickListener;
import com.example.ctsmarket05.entities.Orders;
import com.example.ctsmarket05.entities.ProductsOrder;
import com.example.ctsmarket05.entities.User;
import com.example.ctsmarket05.retrofit.favouriteRetrofit.FavNullCheckGET;
import com.example.ctsmarket05.retrofit.ordersRetrofit.CartCheckNullGET;
import com.example.ctsmarket05.retrofit.ordersRetrofit.OrdersCartGET;
import com.example.ctsmarket05.retrofit.productsOrderRetrofit.ProductsOrdersGET;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.ThreeBounce;

public class OrdersFragment extends Fragment implements ProductsOrdersOnCustomClickListener {

    private ProductsOrdersAdapter productsOrdersAdapter = new ProductsOrdersAdapter(this);
    private TextView tvCartPriceOF;
    private TextView tvCart;
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

    public void nullChecker(){

        Sprite pb = new ThreeBounce();
        pb.setColor(ligthBlueColor);
        progressBarOrders.setIndeterminateDrawable(pb);

        new CountDownTimer(1000, 1000) {

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                progressBarOrders.setVisibility(View.INVISIBLE);

            }
        }.start();

        CartCheckNullGET cartCheckNullGET = new CartCheckNullGET();
        cartCheckNullGET.SetOnDataInterfaceCartCheck (check -> {

            String c =  new String(check.getBytes());

            Integer e = Integer.parseInt(c);

            switch (e) {

                case 0: {

                    tvCart.setText("Este es tu carrito. Cuando añadas productos al carrito aparecerán aquí.");
                }
                break;

                case 1:{

                    rvProductsOrder();
                    getData();
                    tvCart.setText("Este es tu carrito:");
                    btnCart.setVisibility(View.VISIBLE);
                }
                break;
            }
        });
        cartCheckNullGET.check(User.IDUSER);
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

        tvCart = v.findViewById(R.id.tv_cart);
        tvCartPriceOF = v.findViewById(R.id.tv_cart_price_OF);
        btnCart = v.findViewById(R.id.btn_buy_cartO);
        rvProductsOrder = v.findViewById(R.id.rv_products_orderOF);
        progressBarOrders = v.findViewById(R.id.pb_orders);

        btnCart.setOnClickListener(z -> {

            Intent expandCart= new Intent(getContext(), CartExpandedActivity.class);
            startActivity(expandCart);
        });

        tvCart.setOnClickListener(a -> {

            Intent expandCart= new Intent(getContext(), CartExpandedActivity.class);
            startActivity(expandCart);
        });

        nullChecker();
        return v;
    }

    @Override
    public void onItemClick(ProductsOrder productsOrder, int position) {}
}