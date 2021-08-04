package com.example.ctsmarket05.activities.userActivities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ctsmarket05.R;
import com.example.ctsmarket05.adapters.OrdersAdapter;
import com.example.ctsmarket05.clickListeners.OrdersOnCustomClickListener;
import com.example.ctsmarket05.entities.Orders;
import com.example.ctsmarket05.retrofit.ordersRetrofit.OrdersGET;

public class UserPurchaseActivity extends AppCompatActivity implements OrdersOnCustomClickListener {

    private OrdersAdapter ordersAdapter = new OrdersAdapter(this);
    private RecyclerView rvOrders;
    private TextView tvQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_purchase);

       findViews();
       rvOrders();
       getData();
    }

    private void getData() {

        OrdersGET ordersGET = new OrdersGET();
        ordersGET.SetOnDataListenerOrders(orders -> {
            ordersAdapter.setOrders(orders);
        });
        ordersGET.getOrders();

        String genericMesagge = getColoredSpanned("Si necesitas realizar una consulta por una compra", "#B8C6CD");
        String whatsApp = getColoredSpanned("hace click aquí ","#2e7d32");
        tvQuestion.setText(Html.fromHtml(genericMesagge+" "+whatsApp + "para hablar vía WhatsApp con un representante."));

        tvQuestion.setOnClickListener(v -> {

            String telefono = "+54 1132424233";
            String url = "https://api.whatsapp.com/send?phone=";
            Intent llamada = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:" + telefono));
            llamada.setData(Uri.parse(url + telefono));
            startActivity(llamada);
        });
    }

    private void rvOrders() {

        LinearLayoutManager layoutManager  = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        rvOrders.setLayoutManager(layoutManager);
        rvOrders.setAdapter(ordersAdapter);
    }

    private void findViews() {

        rvOrders = findViewById(R.id.rv_ordersUP);
        tvQuestion = findViewById(R.id.tv_questionUP);
    }

    private String getColoredSpanned(String text, String color) {
        String input = "<font color=" + color + ">" + text + "</font>";
        return input;
    }

    @Override
    public void onItemClick(Orders order, int position) {}
}
