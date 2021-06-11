package com.example.ctsmarket05.activities.productsClasses;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ctsmarket05.R;
import com.example.ctsmarket05.entities.Product;
import com.example.ctsmarket05.retrofit.userRetrofit.UserGET;
import com.squareup.picasso.Picasso;

public class ProductsActivity6 extends AppCompatActivity {

    private ImageView ivImage;
    private TextView tvProdName;
    private TextView tvQuantity;
    private TextView tvSendingMethod;
    private TextView tvUserInfo;
    private TextView tvPayment;
    private TextView tvFinalPrice;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products6);

        findViews();
        setOrderValues();
        makeOrder();
    }

    private void setOrderValues() {

        Intent payment = getIntent();

        String sendingMethod = payment.getStringExtra("sendingMethod");
        String payMethod = payment.getStringExtra("payMethod");

        tvProdName.setText(Product.NAME);
        Picasso.with(this).load(Product.IMAGE).into(ivImage);
        //tvQuantity.setText();

        if (sendingMethod.equals("sellerHome")){
            tvSendingMethod.setText("Retira en el domicilio del vendedor");
        }

        if (payMethod.equals("payWithCash")){
            tvPayment.setText("En efectivo al retirar el producto");
        }
        tvFinalPrice.setText(Product.PRICE + " " +  "$ARS");

        UserGET userGET = new UserGET();
        userGET.SetOnDataListenerUser(user ->
                tvUserInfo.setText(user.getName_lastname() + " - " + "DNI: " + user.getDni().toString() + " - " + "\n" +"Contacto: " + user.getPhone().toString()));
        userGET.getUserByGmail();

        tvQuantity.setText(Product.QUANTITY);

    }

    private void makeOrder() {

    }

    private void findViews() {

        ivImage = findViewById(R.id.iv_prod_image6);
        tvProdName = findViewById(R.id.tv_prod_name6);
        tvQuantity = findViewById(R.id.tv_quantity6);
        tvSendingMethod = findViewById(R.id.tv_shipping_method6);
        tvUserInfo = findViewById(R.id.tv_user_info6);
        tvPayment = findViewById(R.id.tv_payment6);
        tvFinalPrice = findViewById(R.id.tv_final_price6);
    }
}
