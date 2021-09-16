package com.example.ctsmarket05.view.activities.oneProductSequence;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ctsmarket05.R;
import com.example.ctsmarket05.view.activities.CartBuyFinalActivity;
import com.example.ctsmarket05.entities.Orders;

public class OPSActivity4 extends AppCompatActivity {

    private TextView tvFinalPrice;
    private ImageView cashContinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products5);

        findviews();
        payWithCash();
        price();
    }

    private void price() {

        switch (Orders.ORDER_SEQUENCE){

            case "cartSequence":{
                tvFinalPrice.setText("Precio final de la orden: "+ Orders.ORDER_PRICE.toString() + "$ARS");
            }break;

            case "oneProductSequence":{
                tvFinalPrice.setText( "Precio final: " + Orders.ORDER_PRICE.toString() + "$ARS");
            }break;
        }
    }

    private void payWithCash() {

        cashContinue.setOnClickListener(v -> {

            Orders.ORDER_PAYMENT  = "atRetire";

            switch (Orders.ORDER_SEQUENCE){

                case "cartSequence":{

                    Intent payment = new Intent(this, CartBuyFinalActivity.class);
                    startActivity(payment);
                }break;

                case "oneProductSequence":{

                    Intent payment = new Intent(this, OPSActivity5.class);
                    startActivity(payment);
                }break;
            }
        });
    }

    private void findviews() {

       tvFinalPrice = findViewById(R.id.tv_final_price5);
       cashContinue = findViewById(R.id.iv_cash_continue5);
    }
}
