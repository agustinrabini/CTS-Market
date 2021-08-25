package com.example.ctsmarket05.activities.productsClasses;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ctsmarket05.R;
import com.example.ctsmarket05.activities.HomeActivity;
import com.example.ctsmarket05.entities.Orders;
import com.example.ctsmarket05.entities.Product;
import com.example.ctsmarket05.entities.User;
import com.example.ctsmarket05.retrofit.ordersRetrofit.OrderOneProductPOST;
import com.example.ctsmarket05.retrofit.userRetrofit.UserGET;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.ThreeBounce;
import com.squareup.picasso.Picasso;

import java.net.PortUnreachableException;
import java.util.Date;

public class ProductsActivity6 extends AppCompatActivity {

    private ImageView ivImage;
    private TextView tvProdName;
    private TextView tvQuantity;
    private TextView tvSendingMethod;
    private TextView tvUserInfo;
    private TextView tvPayment;
    private TextView tvFinalPrice;
    private Button btnConfirmOrder;
    private ProgressBar progressBarProdActv6;
    private int ligthBlueColor = Color.parseColor("#75AADB");

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products6);

        findViews();
        setOrderValues();
        confirmOrder();
    }

    private void setOrderValues() {

        Sprite pb = new ThreeBounce();
        pb.setColor(ligthBlueColor);
        progressBarProdActv6.setIndeterminateDrawable(pb);

        new CountDownTimer(1500, 1000) {

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                progressBarProdActv6.setVisibility(View.INVISIBLE);
                tvFinalPrice.setVisibility(View.VISIBLE);
                tvQuantity.setVisibility(View.VISIBLE);
                tvSendingMethod.setVisibility(View.VISIBLE);
                tvPayment.setVisibility(View.VISIBLE);
                tvProdName.setVisibility(View.VISIBLE);
                tvUserInfo.setVisibility(View.VISIBLE);
                btnConfirmOrder.setVisibility(View.VISIBLE);
            }
        }.start();

       UserGET userGET = new UserGET();
       userGET.SetOnDataListenerUser(user ->
               tvUserInfo.setText(user.getName_lastname() + " - " + "DNI: " + user.getDni().toString() + " - " + "\n" +"Contacto: " + user.getPhone().toString()));
       userGET.getUserByGmail();

       tvProdName.setText(Product.NAME);
       Picasso.with(this).load(Product.IMAGE).into(ivImage);

        switch (Orders.ORDER_SEQUENCE){

            case "cartSequence":{
                tvQuantity.setText(Product.QUANTITY.toString());
                tvFinalPrice.setText(Product.PRICE+ " " +  "$ARS");

            }break;

            case "oneProductSequence":{
                tvQuantity.setText(Orders.ORDER_QUANTITY.toString());
                tvFinalPrice.setText(Orders.ORDER_PRICE + " " +  "$ARS");
            }break;
        }

        switch (Orders.ORDER_PAYMENT){

            case "atRetire":{

                tvPayment.setText("En efectivo al retirar");
            }break;

            case "send":{

            }break;
        }

        switch (Orders.ORDER_SHIPPING){

            case 1:{

                tvSendingMethod.setText("Retira en taller");
            }break;

            case 2:{

            }break;
        }
    }

    private void confirmOrder() {

       btnConfirmOrder.setOnClickListener(v -> {

           String date = java.text.DateFormat.getDateTimeInstance().format(new Date());

           switch (Orders.ORDER_SEQUENCE){

               case "cartSequence":{

               }break;

               case "oneProductSequence":{
                   Orders orders = new Orders(User.IDUSER, Product.PRICE,Orders.ORDER_QUANTITY,0,1, date);

                   OrderOneProductPOST orderOneProductPOST = new OrderOneProductPOST();
                   orderOneProductPOST.oneProductBougth(User.IDUSER, Product.ID_PRODUCT, orders);
               }break;
           }

           Intent finishBuySequence = new Intent(this, HomeActivity.class);
           startActivity(finishBuySequence);
           finish();
       });
    }

    private void findViews() {

        ivImage = findViewById(R.id.iv_prod_image6);
        tvProdName = findViewById(R.id.tv_prod_name6);
        tvQuantity = findViewById(R.id.tv_quantity6);
        tvSendingMethod = findViewById(R.id.tv_shipping_method6);
        tvUserInfo = findViewById(R.id.tv_user_info6);
        tvPayment = findViewById(R.id.tv_payment6);
        tvFinalPrice = findViewById(R.id.tv_final_price6);
        btnConfirmOrder = findViewById(R.id.btn_confirm_order6);
        progressBarProdActv6 = findViewById(R.id.pb_prod_actv6);
    }
}
