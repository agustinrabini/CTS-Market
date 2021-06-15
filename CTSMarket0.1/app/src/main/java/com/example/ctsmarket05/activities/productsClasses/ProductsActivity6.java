package com.example.ctsmarket05.activities.productsClasses;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ctsmarket05.R;
import com.example.ctsmarket05.activities.HomeActivity;
import com.example.ctsmarket05.entities.Cart;
import com.example.ctsmarket05.entities.Product;
import com.example.ctsmarket05.entities.User;
import com.example.ctsmarket05.retrofit.ordersRetrofit.OrderPOST;
import com.example.ctsmarket05.retrofit.userRetrofit.UserGET;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.util.Calendar;

public class ProductsActivity6 extends AppCompatActivity {

    private ImageView ivImage;
    private TextView tvProdName;
    private TextView tvQuantity;
    private TextView tvSendingMethod;
    private TextView tvUserInfo;
    private TextView tvPayment;
    private TextView tvFinalPrice;
    private Button btnConfirmOrder;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products6);

        findViews();
        setOrderValues();
        confirmOrder();
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

       tvQuantity.setText(Product.QUANTITY.toString());

    }

    private void confirmOrder() {

       btnConfirmOrder.setOnClickListener(v -> {

           Calendar calendar = Calendar.getInstance();
           String date = DateFormat.getDateInstance().format(calendar.getTime());

           Cart cart = new Cart(Product.QUANTITY,Product.ID_PRODUCT);

           OrderPOST orderPOST = new OrderPOST();
           orderPOST.orderPost(

                   User.IDUSER,
                   Product.PRICE,
                   Product.QUANTITY,
                   0,
                   1,
                   cart,
                   date
           );

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
    }
}
