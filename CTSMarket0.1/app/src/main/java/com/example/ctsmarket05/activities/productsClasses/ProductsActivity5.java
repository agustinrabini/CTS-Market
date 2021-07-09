package com.example.ctsmarket05.activities.productsClasses;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ctsmarket05.R;
import com.example.ctsmarket05.activities.CartBuyFinalActivity;
import com.example.ctsmarket05.entities.Orders;
import com.example.ctsmarket05.entities.Product;

public class ProductsActivity5 extends AppCompatActivity {

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

        Intent toProdActv5 = getIntent();
        String sequence = toProdActv5.getStringExtra("sequence");

        switch (sequence){

            case "cartSequence":{
                tvFinalPrice.setText("Precio final de la orden: "+ Orders.ORDER_PRICE.toString() + "$ARS");
            }break;

            case "oneProductSequence":{
                tvFinalPrice.setText( "Precio final: " + Product.PRICE+ "$ARS");
            }break;
        }

    }

    private void payWithCash() {

        cashContinue.setOnClickListener(v -> {

            Intent toProdActv5 = getIntent();
            String image = toProdActv5.getStringExtra("image");
            String name = toProdActv5.getStringExtra("name");
            String price = toProdActv5.getStringExtra("price");
            String sendingMethod = toProdActv5.getStringExtra("sendingMethod");
            String quantity = toProdActv5.getStringExtra("quantity");
            String sequence = toProdActv5.getStringExtra("sequence");

            switch (sequence){

                case "cartSequence":{
                    Intent payment = new Intent(this, CartBuyFinalActivity.class);
                    payment.putExtra("sendingMethod", sendingMethod);
                    payment.putExtra("image", image);
                    payment.putExtra("name", name);
                    payment.putExtra("price", price);
                    payment.putExtra("payMethod", "payWithCash");
                    payment.putExtra("quantity", quantity);
                    startActivity(payment);
                }break;

                case "oneProductSequence":{
                    Intent payment = new Intent(this, ProductsActivity6.class);
                    payment.putExtra("sendingMethod", sendingMethod);
                    payment.putExtra("image", image);
                    payment.putExtra("name", name);
                    payment.putExtra("price", price);
                    payment.putExtra("payMethod", "payWithCash");
                    payment.putExtra("quantity", quantity);
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
