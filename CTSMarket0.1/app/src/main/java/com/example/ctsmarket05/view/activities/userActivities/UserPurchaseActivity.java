package com.example.ctsmarket05.view.activities.userActivities;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ctsmarket05.R;
import com.example.ctsmarket05.adapters.OrdersAdapter;
import com.example.ctsmarket05.interfaces.clickListeners.OrdersOnCustomClickListener;
import com.example.ctsmarket05.entities.Orders;
import com.example.ctsmarket05.entities.User;
import com.example.ctsmarket05.model.orders.OrderCheckNullGET;
import com.example.ctsmarket05.model.orders.OrdersGET;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.ThreeBounce;

public class UserPurchaseActivity extends AppCompatActivity implements OrdersOnCustomClickListener {

    private OrdersAdapter ordersAdapter = new OrdersAdapter(this);
    public static RecyclerView rvOrders;
    public static ProgressBar progressBarOrders;
    public static ImageView ivBkg;
    private int ligthBlueColor = Color.parseColor("#75AADB");
    private TextView tvOrderHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_purchase);

       findViews();
       nullChecker();
    }

    private void nullChecker(){

        OrderCheckNullGET orderCheckNullGET = new OrderCheckNullGET();
        orderCheckNullGET.SetOnDataInterfaceOrderCheck(check -> {

            String c =  new String(check.getBytes());

            Integer e = Integer.parseInt(c);

            switch (e) {

                case 0: {

                    tvOrderHistory.setText("Este es el historial de compra. Cuando hagas una compra aparecerá aquí.");
                }
                break;

                case 1:{

                    rvOrders();
                    getData();
                    tvOrderHistory.setText("Este es tu historial de compras:");
                }
                break;
            }

        });
        orderCheckNullGET.check(User.IDUSER);
    }

    private void getData() {

        Sprite pb = new ThreeBounce();
        pb.setColor(ligthBlueColor);
        progressBarOrders.setIndeterminateDrawable(pb);

        OrdersGET ordersGET = new OrdersGET();
        ordersGET.SetOnDataListenerOrders(orders -> {
            ordersAdapter.setOrders(orders);
        });
        ordersGET.getOrders();
    }

    private void rvOrders() {

        LinearLayoutManager layoutManager  = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        rvOrders.setLayoutManager(layoutManager);
        rvOrders.setAdapter(ordersAdapter);
    }

    private void findViews() {

        rvOrders = findViewById(R.id.rv_ordersUP);
        progressBarOrders = findViewById(R.id.pb_ordersP);
        ivBkg = findViewById(R.id.iv_orders_bkgUP);
        tvOrderHistory = findViewById(R.id.tv_order_historyUP);
    }

    @Override
    public void onItemClick(Orders order, int position) {}
}
