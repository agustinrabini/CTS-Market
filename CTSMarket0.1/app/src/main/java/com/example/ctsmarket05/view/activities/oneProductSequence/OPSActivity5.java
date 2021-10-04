package com.example.ctsmarket05.view.activities.oneProductSequence;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ctsmarket05.R;
import com.example.ctsmarket05.base.BaseActivity;
import com.example.ctsmarket05.interfaces.OPS5ActivityInterface;
import com.example.ctsmarket05.model.OPS5Interactor;
import com.example.ctsmarket05.presenter.OPS5ActivityPresenter;
import com.example.ctsmarket05.entities.Orders;
import com.example.ctsmarket05.entities.Product;
import com.example.ctsmarket05.entities.User;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.ThreeBounce;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

public class OPSActivity5 extends BaseActivity<OPS5ActivityPresenter> implements OPS5ActivityInterface {

    private ImageView ivImage;
    private ImageView ivGeneric;
    private ImageView ivGeneric2;
    private ImageView ivGeneric3;
    private ImageView ivGeneric4;
    private TextView tvProdName;
    private TextView tvQuantity;
    private TextView tvSendingMethod;
    private TextView tvUserInfo;
    private TextView tvPayment;
    private TextView tvFinalPrice;
    private TextView tvError;
    private TextView tvGeneric;
    private TextView tvGeneric2;
    private TextView tvGeneric3;
    private TextView tvGeneric4;
    private TextView tvGeneric5;
    private TextView tvGeneric6;
    private TextView tvGeneric7;
    private TextView tvCartQuantity;
    private Button btnConfirmOrder;
    private ProgressBar progressBar;
    private int ligthBlueColor = Color.parseColor("#75AADB");

    @NotNull
    @Override
    protected OPS5ActivityPresenter createPresenter(@NotNull Context context) {
        return new OPS5ActivityPresenter(this, new OPS5Interactor());
    }

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ops5);

        findViews();
        sequenceChecker();
        btnConfirm();
    }

    private void btnConfirm() {
        btnConfirmOrder.setOnClickListener(v -> {
            confirmOrder();
        });
    }

    @Override
    public void sequenceChecker() {
        presenterActivity.sequenceChecker(getApplicationContext());
    }

    @Override
    public void showPB() {
        Sprite pb = new ThreeBounce();
        pb.setColor(ligthBlueColor);
        progressBar.setIndeterminateDrawable(pb);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hidePB() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onError() {
        tvError.setVisibility(View.VISIBLE);
    }

    @Override
    public void setOPSLayoutVisible() {
        tvFinalPrice.setVisibility(View.VISIBLE);
        tvQuantity.setVisibility(View.VISIBLE);
        tvSendingMethod.setVisibility(View.VISIBLE);
        tvPayment.setVisibility(View.VISIBLE);
        tvProdName.setVisibility(View.VISIBLE);
        tvUserInfo.setVisibility(View.VISIBLE);
        btnConfirmOrder.setVisibility(View.VISIBLE);
        ivImage.setVisibility(View.VISIBLE);

        tvGeneric.setVisibility(View.VISIBLE);
        tvGeneric2.setVisibility(View.VISIBLE);
        tvGeneric3.setVisibility(View.VISIBLE);
        tvGeneric4.setVisibility(View.VISIBLE);
        tvGeneric5.setVisibility(View.VISIBLE);
        tvGeneric6.setVisibility(View.VISIBLE);
        tvGeneric7.setVisibility(View.VISIBLE);

        ivGeneric.setVisibility(View.VISIBLE);
        ivGeneric2.setVisibility(View.VISIBLE);
        ivGeneric3.setVisibility(View.VISIBLE);
        ivGeneric4.setVisibility(View.VISIBLE);
    }

    @Override
    public void setCartLayoutVisible() {
        tvCartQuantity.setVisibility(View.VISIBLE);
        tvSendingMethod.setVisibility(View.VISIBLE);
        tvPayment.setVisibility(View.VISIBLE);
        tvUserInfo.setVisibility(View.VISIBLE);
        tvFinalPrice.setVisibility(View.VISIBLE);
        btnConfirmOrder.setVisibility(View.VISIBLE);

        tvGeneric.setVisibility(View.VISIBLE);
        tvGeneric3.setVisibility(View.VISIBLE);
        tvGeneric4.setVisibility(View.VISIBLE);
        tvGeneric5.setVisibility(View.VISIBLE);
        tvGeneric6.setVisibility(View.VISIBLE);
        tvGeneric7.setVisibility(View.VISIBLE);

        ivGeneric.setVisibility(View.VISIBLE);
        ivGeneric2.setVisibility(View.VISIBLE);
        ivGeneric3.setVisibility(View.VISIBLE);
        ivGeneric4.setVisibility(View.VISIBLE);
    }

    @Override
    public void setOPSValues(User user, Product product, Orders order) {
        Picasso.with(this).load(product.getImage()).into(ivImage);
        tvProdName.setText(product.getName());
        tvQuantity.setText(order.getQuantity_products().toString());

        if(order.getShipping()==1){
            tvSendingMethod.setText("Retira en taller");
            tvPayment.setText("En efectivo al retirar");
        }else if(order.getShipping()==2){
            tvSendingMethod.setText("Envío a domicilio");
            tvPayment.setText("MercadoPago");
        }

        tvUserInfo.setText(user.getName_lastname() + " - " + user.getDni()+ " - " + user.getPhone());
        tvFinalPrice.setText("$ARS" + order.getOrder_price().toString());
    }

    @Override
    public void setCartValues(User user, Orders cart) {

        tvCartQuantity.setText("La orden es por " + cart.getQuantity_products().toString() + " productos");
        tvFinalPrice.setText("$ARS "+cart.getOrder_price().toString());

        if(cart.getShipping()==1){
            tvSendingMethod.setText("Retira en taller");
            tvPayment.setText("En efectivo al retirar");
        }else if(cart.getShipping()==2){
            tvSendingMethod.setText("Envío a domicilio");
            tvPayment.setText("MercadoPago");
        }

        tvUserInfo.setText(user.getName_lastname() + " - " + user.getDni()+ " - " + user.getPhone());
        tvFinalPrice.setText("$ARS" + cart.getOrder_price().toString());
    }

    @Override
    public void confirmOrder() {
        presenterActivity.confirmOrder();
    }

    @Override
    public void lastSequenceActivity() {
        Intent finishBuySequence = new Intent(this, OPSActivity6.class);
        finishBuySequence.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(finishBuySequence);
        finish();
    }

    private void findViews() {
        ivImage = findViewById(R.id.iv_prod_op5);
        ivGeneric = findViewById(R.id.iv_generic_op5);
        ivGeneric2 = findViewById(R.id.iv_generic2_op5);
        ivGeneric3 = findViewById(R.id.iv_generic3_op5);
        ivGeneric4= findViewById(R.id.iv_generic4_ops5);
        tvProdName = findViewById(R.id.tv_prod_name_op5);
        tvQuantity = findViewById(R.id.tv_quantity_op5);
        tvSendingMethod = findViewById(R.id.tv_shipping_method_op5);
        tvUserInfo = findViewById(R.id.tv_user_info_op5);
        tvPayment = findViewById(R.id.tv_payment_op5);
        tvFinalPrice = findViewById(R.id.tv_final_price_op5);
        tvCartQuantity = findViewById(R.id.tv_cart_quantity_ops5);
        tvGeneric = findViewById(R.id.tv_generic_op5);
        tvGeneric2 = findViewById(R.id.tv_generic2_op5);
        tvGeneric3 = findViewById(R.id.tv_generic3_op5);
        tvGeneric4 = findViewById(R.id.tv_generic4_op5);
        tvGeneric5 = findViewById(R.id.tv_generic5_op5);
        tvGeneric6 = findViewById(R.id.tv_generic6_op5);
        tvGeneric7 = findViewById(R.id.tv_generic7_op5);
        btnConfirmOrder = findViewById(R.id.btn_confirm_order_op5);
        progressBar = findViewById(R.id.pb_op5);
        tvError = findViewById(R.id.tv_error_ops5);
    }
}
