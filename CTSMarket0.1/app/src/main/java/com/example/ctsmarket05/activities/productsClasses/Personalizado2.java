package com.example.ctsmarket05.activities.productsClasses;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ctsmarket05.R;

public class Personalizado2 extends AppCompatActivity {
    private ImageButton ibMinus;
    private ImageButton ibPlus;
    private TextView tvName;
    private TextView tvQuantity;
    private TextView tvShipping;
    private TextView tvPrice;
    private TextView tvInfo;
    private TextView tvAclaration;
    private CheckBox cbMercadopago;
    private CheckBox cbSeller;
    private Button btnCart;
    private Button bought;
    private int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personalizado2);

        ibPlus=findViewById(R.id.ib_plus3);
        ibMinus=findViewById(R.id.ib_minus3);
        tvName=findViewById(R.id.tv_name3);
        tvQuantity=findViewById(R.id.tv_quantity);
        tvAclaration=findViewById(R.id.tv_aclaration3);
        tvInfo=findViewById(R.id.tv_info3);
        tvShipping=findViewById(R.id.tv_shipping3);
        tvPrice =findViewById(R.id.tv_price3);
        cbMercadopago=findViewById(R.id.cb_mercadopago3);
        cbSeller=findViewById(R.id.cb_seller3);
        btnCart=findViewById(R.id.btn_cart);

        ResumenPedido();
        MetodosPago();
    }

    private void ResumenPedido(){
        Intent prodActv3 = getIntent();
        String price = prodActv3.getStringExtra("price");
        String name = prodActv3.getStringExtra("name");

        ibPlus.setOnClickListener(v -> {
            quantity++;
            tvQuantity.setText(String.valueOf(quantity));
            tvPrice.setText(String.valueOf((quantity *Integer.parseInt(price)))+" $ARS");
        });

        ibMinus.setOnClickListener(v -> {

            if (quantity >=2){
                quantity--;
            }
            tvQuantity.setText(String.valueOf(quantity));
            tvPrice.setText(String.valueOf(quantity *Integer.parseInt(price))+" $ARS");
        });

        tvName.setText(name);
        tvPrice.setText((String.valueOf(Integer.parseInt(price)))+" $ARS");
    }

    private void MetodosPago(){
        cbMercadopago.setOnClickListener(v -> {
            if (cbMercadopago.isChecked()== true) {
                cbMercadopago.setChecked(true);
                cbSeller.setChecked(false);
            }

            if (cbMercadopago.isChecked()== false){
                cbMercadopago.setChecked(false);
                cbSeller.setClickable(true);
            }
        });

        cbSeller.setOnClickListener(v -> {
            if (cbSeller.isChecked()== true) {
                cbSeller.setChecked(true);
                cbMercadopago.setChecked(false);
            }

            if (cbSeller.isChecked()== false){
                cbSeller.setChecked(false);
                cbMercadopago.setClickable(true);
            }
        });
    }

    private void DatosEnvio(){

    }

    private boolean appInstalledOrNot(String uri) {
        PackageManager pm = getPackageManager();
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
        }

        return false;
    }
}