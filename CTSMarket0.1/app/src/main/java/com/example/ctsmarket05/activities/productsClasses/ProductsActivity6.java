package com.example.ctsmarket05.activities.productsClasses;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ctsmarket05.R;
import com.example.ctsmarket05.activities.HomeActivity;
import com.example.ctsmarket05.entities.Orders;
import com.example.ctsmarket05.entities.Product;
import com.example.ctsmarket05.entities.User;
import com.example.ctsmarket05.retrofit.ordersRetrofit.OrderOneProductPOST;
import com.example.ctsmarket05.retrofit.userRetrofit.UserGET;
import com.squareup.picasso.Picasso;

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

       if (sendingMethod.equals("sellerHome")){
           tvSendingMethod.setText("Retira en el domicilio del vendedor");
       }

       if (payMethod.equals("payWithCash")){
           tvPayment.setText("En efectivo al retirar el producto");
       }
       tvFinalPrice.setText(Orders.ORDER_PRICE + " " +  "$ARS");

       UserGET userGET = new UserGET();
       userGET.SetOnDataListenerUser(user ->
               tvUserInfo.setText(user.getName_lastname() + " - " + "DNI: " + user.getDni().toString() + " - " + "\n" +"Contacto: " + user.getPhone().toString()));
       userGET.getUserByGmail();

       tvQuantity.setText(Orders.ORDER_QUANTITY.toString());
       tvProdName.setText(Product.NAME);
       Picasso.with(this).load(Product.IMAGE).into(ivImage);
    }

    private void confirmOrder() {

       btnConfirmOrder.setOnClickListener(v -> {

           String date = java.text.DateFormat.getDateTimeInstance().format(new Date());

           //OrderPOST orderPOST = new OrderPOST();
           //orderPOST.orderPost(
//
           //        User.IDUSER,
           //        Product.PRICE,
           //        Product.QUANTITY,
           //        0,
           //        1,
           //        date
           //);
//
           //ProductsOrderPOST productsOrderPOST = new ProductsOrderPOST();
           //productsOrderPOST.addCart(Product.ID_PRODUCT, User.IDUSER,Product.QUANTITY);

           Orders orders = new Orders(User.IDUSER,Orders.ORDER_PRICE,Orders.ORDER_QUANTITY,0,1, date);

           OrderOneProductPOST orderOneProductPOST = new OrderOneProductPOST();
           orderOneProductPOST.oneProductBougth(User.IDUSER, Product.ID_PRODUCT, orders);

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
