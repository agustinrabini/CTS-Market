package com.example.ctsmarket05.activities.productsClasses;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ctsmarket05.R;
import com.example.ctsmarket05.activities.QuantityBottomSheet;
import com.example.ctsmarket05.entities.Product;
import com.squareup.picasso.Picasso;

public class ProductsActivity2 extends AppCompatActivity implements QuantityBottomSheet.QuantityListener {

    private ImageView ivProduct2;
    private Button btnQuestion;
    private Button btnBuy;
    private TextView tvName2;
    private TextView tvPrice2;
    private TextView tvBlade2;
    private TextView tvBrand2;
    private TextView tvDescription2;
    private TextView tvLength;
    private TextView tvQuantity;
    private Integer quantityProduct = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products2);

        findViews();
        getProductInfo();
        changeQuantityProduct();
        btnBuy();
        btnQuestion();
    }

    private void getProductInfo() {
        Intent Clicked = getIntent();

        String id_prod = Clicked.getStringExtra("id_product");

        String name = Clicked.getStringExtra("name");
        String blade = Clicked.getStringExtra("blade");
        String brand = Clicked.getStringExtra("brand");
        String description = Clicked.getStringExtra("description");
        String image = Clicked.getStringExtra("image");
        Integer price = Clicked.getIntExtra("price",0);
        Integer length = Clicked.getIntExtra("length",0);

        Product.ID_PRODUCT = Integer.parseInt(id_prod);
        Product.NAME = name;
        Product.PRICE = price;
        Product.IMAGE = image;

        tvName2.setText(Product.NAME);
        tvBlade2.setText(blade);
        tvBrand2.setText(brand);
        tvDescription2.setText(description);
        Picasso.with(this).load(Product.IMAGE).into(ivProduct2);
        tvPrice2.setText(Product.PRICE.toString() + " $ARS");
        tvLength.setText(String.valueOf(length + " cm"));
    }

    private void btnQuestion() {
        btnQuestion.setOnClickListener(v -> {
            //Intent question = new Intent(this,PreguntaActivity3.class);
            //startActivity(question);

            //Integer orderPrice = quantityProduct * price;
            //Toast.makeText(this, orderPrice.toString(), Toast.LENGTH_SHORT).show();
        });
    }

    private void btnBuy() {
        btnBuy.setOnClickListener(v -> {

            Intent Clicked = getIntent();
            Integer price = Clicked.getIntExtra("price",0);

            Product.QUANTITY = quantityProduct;
            Product.PRICE = price * quantityProduct;

            //Se va pasando por los activities toda la informacion del producto a medida que el usuario
            //recorre toda la secuencia de compra
            Intent toProdActv3 = new Intent(this, ProductsActivity3.class);
            startActivity(toProdActv3);
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
        quantityProduct = quantity;
        tvQuantity.setText("Cant. : " + quantityProduct.toString());
    }

    private void findViews(){
        ivProduct2 = findViewById(R.id.iv_product2);
        tvQuantity = findViewById(R.id.tv_quantity2);
        btnBuy = findViewById(R.id.btn_buy);
        btnQuestion = findViewById(R.id.btn_question2);
        tvName2 = findViewById(R.id.tv_name2);
        tvPrice2 = findViewById(R.id.tv_price2);
        tvDescription2 = findViewById(R.id.tv_description2);
        tvBlade2 = findViewById(R.id.tv_blade2);
        tvBrand2 = findViewById(R.id.tv_brand2);
        tvLength = findViewById(R.id.tv_length2);
    }
}