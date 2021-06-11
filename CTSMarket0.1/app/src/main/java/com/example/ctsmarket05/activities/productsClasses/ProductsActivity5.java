package com.example.ctsmarket05.activities.productsClasses;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ctsmarket05.R;
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
        String price = toProdActv5.getStringExtra("price");

        tvFinalPrice.setText( "PRECIO FINAL: " + Product.PRICE);
    }

    private void payWithCash() {

        cashContinue.setOnClickListener(v -> {

            Intent toProdActv5 = getIntent();
            String image = toProdActv5.getStringExtra("image");
            String name = toProdActv5.getStringExtra("name");
            String price = toProdActv5.getStringExtra("price");
            String sendingMethod = toProdActv5.getStringExtra("sendingMethod");
            String quantity = toProdActv5.getStringExtra("quantity");

            Intent payment = new Intent(this, ProductsActivity6.class);
            payment.putExtra("sendingMethod", sendingMethod);
            payment.putExtra("image", image);
            payment.putExtra("name", name);
            payment.putExtra("price", price);
            payment.putExtra("payMethod", "payWithCash");
            payment.putExtra("quantity", quantity);
            startActivity(payment);
        });
    }

    private void findviews() {

       tvFinalPrice = findViewById(R.id.tv_final_price5);
        cashContinue = findViewById(R.id.iv_cash_continue5);
    }
}
