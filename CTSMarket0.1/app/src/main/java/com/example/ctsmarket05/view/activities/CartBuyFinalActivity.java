package com.example.ctsmarket05.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ctsmarket05.R;
import com.example.ctsmarket05.entities.Orders;
import com.example.ctsmarket05.entities.Product;
import com.example.ctsmarket05.model.orders.OrderCartBoughtPUT;
import com.example.ctsmarket05.model.orders.OrdersCartGET;
import com.example.ctsmarket05.model.user.UserGET;

import java.text.DateFormat;
import java.util.Calendar;

public class CartBuyFinalActivity extends AppCompatActivity {

    private TextView tvQuantity;
    private TextView tvSendingMethod;
    private TextView tvUserInfo;
    private TextView tvPayment;
    private TextView tvFinalPrice;
    private Button btnConfirmOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_buy_final);

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
        tvFinalPrice.setText(Orders.ORDER_PRICE +  "$ARS");

        //UserGET userGET = new UserGET();
        //userGET.SetOnDataListenerUser(user ->
        //        tvUserInfo.setText(user.getName_lastname() + " - " + "DNI: " + user.getDni().toString() + " - " + "\n" +"Contacto: " + user.getPhone().toString()));
        //userGET.getUserByGmail();

        tvQuantity.setText(Product.QUANTITY.toString());
    }

    private void confirmOrder() {

        btnConfirmOrder.setOnClickListener(v -> {

            Calendar calendar = Calendar.getInstance();
            String date = DateFormat.getDateInstance().format(calendar.getTime());

            OrdersCartGET ordersCartGET = new OrdersCartGET();
            ordersCartGET.SetOnDataListenerOrderCart(order -> {

                Integer id_order = order.getId_order();

                OrderCartBoughtPUT orderCartBoughtPUT = new OrderCartBoughtPUT();
                orderCartBoughtPUT.boughtCart(id_order,1,1,date);
            });
            ordersCartGET.getOrderCart();


            Intent finishBuySequence = new Intent(this, HomeActivity.class);
            startActivity(finishBuySequence);
            finish();
        });
    }

    private void findViews() {

        tvQuantity = findViewById(R.id.tv_quantityCF);
        tvSendingMethod = findViewById(R.id.tv_shipping_methodCF);
        tvUserInfo = findViewById(R.id.tv_user_infoCF);
        tvPayment = findViewById(R.id.tv_paymentCF);
        tvFinalPrice = findViewById(R.id.tv_final_priceCF);
        btnConfirmOrder = findViewById(R.id.btn_confirm_orderCF);
    }
}
