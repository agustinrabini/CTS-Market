package com.example.ctsmarket05.activities;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ctsmarket05.R;
import com.example.ctsmarket05.adapters.ProductsOrdersAdapter;
import com.example.ctsmarket05.clickListeners.ProductsOrdersOnCustomClickListener;
import com.example.ctsmarket05.entities.ProductsOrder;
import com.example.ctsmarket05.retrofit.productsOrderRetrofit.ProductsOrdersGET;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.ThreeBounce;

public class OrderExpandedActivity extends AppCompatActivity implements ProductsOrdersOnCustomClickListener {

    private ProductsOrdersAdapter productsOrdersAdapter = new ProductsOrdersAdapter(this);
    private TextView tvQuestion;
    public TextView tvOrderNumber;
    public static ImageView ivBkg;
    public static RecyclerView rvProductsOrder;
    public static ProgressBar progressBarOrderExpanded;
    private int ligthBlueColor = Color.parseColor("#75AADB");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_expanded);

        findViews();
        getData();
        question();
    }

    private void question() {

        tvQuestion.setOnClickListener(v -> {
            String telefono = "+54 1132424233";
            String url = "https://api.whatsapp.com/send?phone=";
            Intent wpp = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:" + telefono));
            wpp.setData(Uri.parse(url + telefono));
            startActivity(wpp);
        });
    }

    private void findViews() {

        tvQuestion = findViewById(R.id.tv_questionOE);
        tvOrderNumber = findViewById(R.id.tv_order_numberOE);
        ivBkg = findViewById(R.id.iv_bkgOE);
        rvProductsOrder = findViewById(R.id.rv_products_orderOE);
        progressBarOrderExpanded = findViewById(R.id.pb_orders_expanded);
    }

    private void getData() {


        Sprite pb = new ThreeBounce();
        pb.setColor(ligthBlueColor);
        progressBarOrderExpanded.setIndeterminateDrawable(pb);

        new CountDownTimer(1000, 1000) {

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {

                tvQuestion.setVisibility(View.VISIBLE);
                progressBarOrderExpanded.setVisibility(View.INVISIBLE);
                ivBkg.setVisibility(View.VISIBLE);
                rvProductsOrder.setVisibility(View.VISIBLE);
            }
        }.start();

        String genericMesagge = getColoredSpanned("¿Alguna consulta? Hablá con un respresentante de nuestro equipo vía WhatsApp haciendo ", "#000000");
        String genericMesagge2 = getColoredSpanned("click aquí.","#2e7d32");
        tvQuestion.setText(Html.fromHtml(genericMesagge +" "+ genericMesagge2));

        Intent fromOrdersActivity = getIntent();

        Integer id_order = fromOrdersActivity.getIntExtra("id_order",0);

        tvOrderNumber.setText("Número de orden: " + id_order.toString());

        ProductsOrdersGET productsOrdersGET = new ProductsOrdersGET();
        productsOrdersGET.SetOnDataListenerProductsOrders(productsOrders -> {
            productsOrdersAdapter.setProductsOrders(productsOrders);
        });
        productsOrdersGET.getProductsOrders(id_order);
        rvProductsOrder();
    }

    private String getColoredSpanned(String text, String color) {
        String input = "<font color=" + color + ">" + text + "</font>";
        return input;
    }

    private void rvProductsOrder() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvProductsOrder.setLayoutManager(layoutManager);
        rvProductsOrder.setAdapter(productsOrdersAdapter);
    }

    @Override
    public void onItemClick(ProductsOrder productsOrder, int position) {}
}
