package com.example.ctsmarket05.view.activities.oneProductSequence;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ctsmarket05.R;
import com.example.ctsmarket05.entities.Orders;
import com.example.ctsmarket05.view.fragments.bottomSheets.InfoOPS4BottomSheet;
import com.google.gson.Gson;

public class OPSActivity4 extends AppCompatActivity {

    private TextView tvFinalPrice;
    private TextView tvTypePayment;
    private TextView tvGeneric4;
    private ImageView ivPayment;
    private ImageView ivInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ops4);

        findviews();
        getData();
        payment();
        infoButton();
    }

    private void infoButton() {
        ivInfo.setOnClickListener(v -> {
            InfoOPS4BottomSheet infoOPS4BottomSheet = new InfoOPS4BottomSheet();
            infoOPS4BottomSheet.show(getSupportFragmentManager(), "");
        });
    }

    private void getData() {

        SharedPreferences orderPref = getSharedPreferences("sequence", Context.MODE_PRIVATE);

        Integer typeSequence = orderPref.getInt("type",0);
        if (typeSequence == 1){
            Gson gson = new Gson();
            String jsonOPSOrder = orderPref.getString("ops", "");
            Orders orders = gson.fromJson(jsonOPSOrder, Orders.class);
            tvFinalPrice.setText("Precio del pedido: $ARS" + orders.getOrder_price().toString());

            if (orders.getShipping() == 1){
                tvTypePayment.setText("En efectivo al retirar");
                tvGeneric4.setVisibility(View.INVISIBLE);
            }else if(orders.getShipping()==2){
                tvTypePayment.setText("Mercadopago");
                tvGeneric4.setVisibility(View.VISIBLE);
                ivInfo.setVisibility(View.VISIBLE);
            }

        }else if (typeSequence == 2) {
            Gson gson = new Gson();
            String jsonOPSOrder = orderPref.getString("cart", "");
            Orders orders = gson.fromJson(jsonOPSOrder, Orders.class);
            tvFinalPrice.setText("Precio del pedido: $ARS" + orders.getOrder_price().toString());

            if (orders.getShipping() == 1){
                tvTypePayment.setText("En efectivo al retirar");
                tvGeneric4.setVisibility(View.INVISIBLE);
            }else if(orders.getShipping()==2){
                tvTypePayment.setText("Mercadopago");
                tvGeneric4.setVisibility(View.VISIBLE);
                ivInfo.setVisibility(View.VISIBLE);
            }
        }
    }

    private void payment() {
        ivPayment.setOnClickListener(v -> {
            Intent ops5 = new Intent(this, OPSActivity5.class);
            startActivity(ops5);
        });
    }

    private void findviews() {
       tvFinalPrice = findViewById(R.id.tv_final_price_ops4);
       tvTypePayment = findViewById(R.id.tv_type_payment_ops4);
       tvGeneric4 = findViewById(R.id.tv_generic4_ops4);
       ivPayment = findViewById(R.id.iv_payment_ops4);
       ivInfo = findViewById(R.id.iv_info_ops4);
    }
}
