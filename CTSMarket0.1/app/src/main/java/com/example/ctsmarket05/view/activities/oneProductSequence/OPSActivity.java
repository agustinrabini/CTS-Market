package com.example.ctsmarket05.view.activities.oneProductSequence;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;

import com.example.ctsmarket05.R;
import com.example.ctsmarket05.base.BaseActivity;
import com.example.ctsmarket05.interfaces.OPSActivityInterface;
import com.example.ctsmarket05.model.OPSInteractor;
import com.example.ctsmarket05.presenter.OPSActivityPresenter;
import com.example.ctsmarket05.entities.Orders;
import com.example.ctsmarket05.view.fragments.bottomSheets.QuantityBottomSheet;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.ThreeBounce;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

public class OPSActivity extends BaseActivity<OPSActivityPresenter> implements OPSActivityInterface, QuantityBottomSheet.QuantityListener {

    private ImageView ivProduct2;
    private TextView tvQuestion;
    private Button btnBuy;
    private ImageView ivCart;
    private ImageView ivFav;
    private ImageView ivBkg;
    private TextView tvName2;
    private TextView tvPrice2;
    private TextView tvDescription2;
    private TextView tvLength;
    private TextView tvQuantity;
    private TextView tvStock;
    private TextView tvGeneric;
    private TextView tvGeneric2;
    private TextView tvGeneric3;
    private Integer quantityProduct = 1;
    private ProgressBar progressBarPA2;
    private int ligthBlueColor = Color.parseColor("#75AADB");

    @NotNull
    @Override
    protected OPSActivityPresenter createPresenter(@NotNull Context context) {
        return new OPSActivityPresenter(this, new OPSInteractor());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products2);

        findViews();
        clickListeners();
        getProductState();
    }

    private void clickListeners() {

        btnBuy.setOnClickListener(v -> {

            Intent Clicked = getIntent();
            Integer price = Clicked.getIntExtra("price",0);

            Orders.ORDER_PRICE = price * quantityProduct;
            Orders.ORDER_SEQUENCE = "oneProductSequence";

            Intent from = new Intent(this, OPSActivity2.class);
            startActivity(from);
        });

        tvQuantity.setOnClickListener(v -> {

            changeQuantity();
        });

        ivCart.setOnClickListener(v -> {
            cartClicked();
        });

        ivFav.setOnClickListener(v -> {
            favClicked();
        });

    }

    private void findViews(){
        ivProduct2 = findViewById(R.id.iv_product2);
        tvQuantity = findViewById(R.id.tv_quantity2);
        btnBuy = findViewById(R.id.btn_buy2);
        tvQuestion = findViewById(R.id.tv_question2);
        ivCart = findViewById(R.id.iv_cart2);
        ivFav = findViewById(R.id.iv_fav2);
        ivBkg = findViewById(R.id.iv_bkg_pa2);
        tvName2 = findViewById(R.id.tv_name2);
        tvPrice2 = findViewById(R.id.tv_price2);
        tvDescription2 = findViewById(R.id.tv_description2);
        tvLength = findViewById(R.id.tv_length2);
        tvStock = findViewById(R.id.tv_stock2);
        progressBarPA2 = findViewById(R.id.pb_pa2);
        tvGeneric = findViewById(R.id.tv_generic);
        tvGeneric2 = findViewById(R.id.tv_generic2);
        tvGeneric3 = findViewById(R.id.tv_generic3);
    }

    @Override
    public void onBackPressed() {

        finish();
    }

    @Override
    public void setLayoutVisible() {
        ivProduct2.setVisibility(View.VISIBLE);
        tvQuestion.setVisibility(View.VISIBLE);
        btnBuy.setVisibility(View.VISIBLE);
        ivCart.setVisibility(View.VISIBLE);
        ivFav.setVisibility(View.VISIBLE);
        tvName2.setVisibility(View.VISIBLE);
        tvPrice2.setVisibility(View.VISIBLE);
        tvDescription2.setVisibility(View.VISIBLE);
        tvLength.setVisibility(View.VISIBLE);
        tvQuantity.setVisibility(View.VISIBLE);
        tvStock.setVisibility(View.VISIBLE);
        progressBarPA2.setVisibility(View.INVISIBLE);
        tvGeneric.setVisibility(View.VISIBLE);
        tvGeneric2.setVisibility(View.VISIBLE);
        tvGeneric3.setVisibility(View.VISIBLE);
        ivBkg.setVisibility(View.VISIBLE);
    }

    @Override
    public void showProgressBar() {
        Sprite pb = new ThreeBounce();
        pb.setColor(ligthBlueColor);
        progressBarPA2.setIndeterminateDrawable(pb);
        progressBarPA2.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBarPA2.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setProduct() {

        Intent Clicked = getIntent();

        tvName2.setText(Clicked.getStringExtra("name"));
        tvDescription2.setText(Clicked.getStringExtra("description"));
        tvPrice2.setText( "$ARS " + String.valueOf(Clicked.getIntExtra("price",0)));
        tvLength.setText(String.valueOf(Clicked.getIntExtra("length",0)+ " cm"));
        tvStock.setText("¡Quedan " + String.valueOf(Clicked.getIntExtra("stock",0) + " en stock! "));
        tvQuantity.setText("Cant. : " + quantityProduct.toString());
        Picasso.with(this).load(Clicked.getStringExtra("image")).into(ivProduct2);
    }

    @Override
    public void getProductState() {
        Intent Clicked = getIntent();
        Integer idProd = Clicked.getIntExtra("idProduct", 0);
        presenterActivity.getProductState(idProd);
    }

    @Override
    public void activeCart() {
        ivCart.setImageResource(R.drawable.ic_cart2);
    }

    @Override
    public void buy() {

    }

    @Override
    public void cartClicked() {
        Intent Clicked = getIntent();
        Integer idProd = Clicked.getIntExtra("idProduct",0);
        Integer price = Clicked.getIntExtra("price",0);
        presenterActivity.cartClicked(idProd, price, quantityProduct);
    }

    @Override
    public void favClicked() {
        Intent Clicked = getIntent();
        Integer idProd = Clicked.getIntExtra("idProduct",0);
        presenterActivity.favClicked(idProd);
    }

    @Override
    public void cartRemove() {
        ivCart.setImageResource(R.drawable.ic_cart1);
    }

    @Override
    public void favRemove() {
        ivFav.setImageResource(R.drawable.ic_heart1);
    }

    @Override
    public void quantityUpdate(Integer quantity) {

        Intent Clicked = getIntent();
        Integer price = Clicked.getIntExtra("price", 0);
        Integer p = price*quantity;

        quantityProduct = quantity;

        tvPrice2.setText( "$ARS " + p.toString());
        tvQuantity.setText("Cant. : " + quantity.toString());
    }

    @Override
    public void changeQuantity() {

        Intent Clicked = getIntent();
        Integer stock = Clicked.getIntExtra("stock",0);

        FragmentManager fragmentManager = getSupportFragmentManager();
        presenterActivity.changeQuantity(fragmentManager, stock);
    }

    @Override
    public void activeFav() {
        ivFav.setImageResource(R.drawable.ic_heart2);
    }

    @Override
    public void onError() {
        tvName2.setVisibility(View.VISIBLE);
        tvName2.setText("ERROR");
    }

    @Override
    public void onButtonClicked(Integer quantity) {
        quantityUpdate(quantity);
    }
}