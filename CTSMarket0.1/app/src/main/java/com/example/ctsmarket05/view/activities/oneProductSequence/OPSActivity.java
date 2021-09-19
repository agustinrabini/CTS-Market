package com.example.ctsmarket05.view.activities.oneProductSequence;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.ctsmarket05.R;
import com.example.ctsmarket05.base.BaseActivity;
import com.example.ctsmarket05.interfaces.OPSActivityInterface;
import com.example.ctsmarket05.model.ProductChecker;
import com.example.ctsmarket05.model.product.ProductsGET;
import com.example.ctsmarket05.presenter.OPSActivityPresenter;
import com.example.ctsmarket05.view.fragments.bottomSheets.QuantityBottomSheet;
import com.example.ctsmarket05.entities.Orders;
import com.example.ctsmarket05.entities.Product;
import com.example.ctsmarket05.entities.User;
import com.example.ctsmarket05.model.favourite.FavCheckGET;
import com.example.ctsmarket05.model.favourite.FavInteractionPOST;
import com.example.ctsmarket05.model.orders.CartAddPOST;
import com.example.ctsmarket05.model.orders.CartRemoveDELETE;
import com.example.ctsmarket05.model.productsOrder.CheckCartPOST;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.ThreeBounce;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

public class OPSActivity extends BaseActivity<OPSActivityPresenter> implements QuantityBottomSheet.QuantityListener, OPSActivityInterface {

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
    private Integer a = 0;
    private Integer f = 0;
    private ProgressBar progressBarPA2;
    private int ligthBlueColor = Color.parseColor("#75AADB");

    @NotNull
    @Override
    protected OPSActivityPresenter createPresenter(@NotNull Context context) {
        return new OPSActivityPresenter(this, new ProductChecker());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products2);

        findViews();
        changeQuantityProduct();
        btnBuy();
        btnQuestion();
        getProductState();
    }

    private void btnQuestion() {
       tvQuestion.setOnClickListener(v -> {
       });
    }

    private void btnBuy() {
        btnBuy.setOnClickListener(v -> {

            Intent Clicked = getIntent();
            Integer price = Clicked.getIntExtra("price",0);

            Orders.ORDER_PRICE = price * quantityProduct;
            Orders.ORDER_SEQUENCE = "oneProductSequence";

            Intent from = new Intent(this, OPSActivity2.class);
            startActivity(from);
        });
    }

    private void changeQuantityProduct() {

        tvQuantity.setOnClickListener(v -> {
            QuantityBottomSheet quantityBottomSheet = new QuantityBottomSheet();
            quantityBottomSheet.show(getSupportFragmentManager(), "quantityBottomSheet");
        });
    }

    @Override
    public void onButtonClicked(Integer quantity) {

        Intent Clicked = getIntent();
        Integer price = Clicked.getIntExtra("price",0);

        quantityProduct = quantity;
        Orders.ORDER_QUANTITY = quantity;
        tvQuantity.setText("Cant. " + quantityProduct.toString());
        Orders.ORDER_PRICE = (price * quantityProduct);
        tvPrice2.setText("$ARS " + Orders.ORDER_PRICE.toString());
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
        tvPrice2.setText(String.valueOf(Clicked.getIntExtra("price",0)));
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
    public void quantity() {

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
}