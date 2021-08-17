package com.example.ctsmarket05.activities.productsClasses;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;

import com.example.ctsmarket05.R;
import com.example.ctsmarket05.activities.QuantityBottomSheet;
import com.example.ctsmarket05.entities.Orders;
import com.example.ctsmarket05.entities.Product;
import com.example.ctsmarket05.entities.User;
import com.example.ctsmarket05.retrofit.ordersRetrofit.CartAddPOST;
import com.example.ctsmarket05.retrofit.ordersRetrofit.CartRemoveDELETE;
import com.example.ctsmarket05.retrofit.productRetrofit.ProductInterface;
import com.example.ctsmarket05.retrofit.productsOrderRetrofit.CheckCartPOST;
import com.google.android.gms.common.util.IOUtils;
import com.squareup.picasso.Picasso;

import java.util.Arrays;

public class ProductsActivity2 extends AppCompatActivity implements QuantityBottomSheet.QuantityListener {

    private ImageView ivProduct2;
    private TextView tvQuestion;
    private Button btnBuy;
    private ImageView ivCart;
    private TextView tvName2;
    private TextView tvPrice2;
    private TextView tvDescription2;
    private TextView tvLength;
    private TextView tvQuantity;
    private TextView tvStock;
    private Integer quantityProduct = 1;
    private Integer a = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products2);

        findViews();
        getProductInfo();
        changeQuantityProduct();
        btnBuy();
        btnQuestion();
        cart();
        checkCart();
    }

    private void checkCart() {

        //se checkea el estado del producto en el carrito
        CheckCartPOST checkCartPOST = new CheckCartPOST();
        checkCartPOST.SetOnDataListenerCheck(check -> {

            String c =  new String(check.getBytes());

            Integer e = Integer.parseInt(c);

            if (e == 10){
                a=1;
                ivCart.setImageResource(R.drawable.ic_cart2);
            }
        });
        checkCartPOST.check(Product.ID_PRODUCT, User.IDUSER);
    }

    private void cart() {

        Intent Clicked = getIntent();
        Integer stock = Clicked.getIntExtra("stock",0);
        Integer id_prod = Clicked.getIntExtra("id_product",0);
        Integer price = Clicked.getIntExtra("price",0);
        Product.ID_PRODUCT = id_prod;

        ivCart.setOnClickListener(v -> {

            if(stock !=0){

                switch (a) {

                    case 0: {

                        a=1;
                        CartAddPOST cartAddPOST = new CartAddPOST();
                        Orders order = new Orders(User.IDUSER,price*quantityProduct,quantityProduct,10,null,"");
                        cartAddPOST.addCart(Product.ID_PRODUCT, order);
                        ivCart.setImageResource(R.drawable.ic_cart2);
                    }
                    break;

                    case 1:{

                        CartRemoveDELETE removeDELETE = new CartRemoveDELETE();
                        removeDELETE.deleteCart(User.IDUSER, Product.ID_PRODUCT);
                        ivCart.setImageResource(R.drawable.ic_cart1);
                        a=0;
                    }
                    break;
                }
            }
        });
    }

    private void getProductInfo() {

        //se settea la iformacion en la pantalla
        Intent Clicked = getIntent();
        Integer id_prod = Clicked.getIntExtra("id_product",0);
        String name = Clicked.getStringExtra("name");
        String description = Clicked.getStringExtra("description");
        String image = Clicked.getStringExtra("image");
        Integer price = Clicked.getIntExtra("price",0);
        Integer length = Clicked.getIntExtra("length",0);
        Integer stock = Clicked.getIntExtra("stock",0);


        Product.ID_PRODUCT = id_prod;
        Product.STOCK=stock;
        Orders.ORDER_QUANTITY = quantityProduct;
        Product.NAME = name;
        Product.PRICE = price;
        Orders.ORDER_PRICE = price;
        Product.IMAGE = image;


        if (stock == 0){
            btnBuy.setText("SIN STOCK");
            btnBuy.setEnabled(false);
            ivCart.setEnabled(false);
        }else if(stock<=5 && stock>1){
            tvStock.setText("¡Últimos " + stock.toString() + " en stock!");
        }else if(stock==1){
            tvStock.setText("¡Último en stock!");
        }


        tvName2.setText(Product.NAME);
        tvDescription2.setText(description);
        Picasso.with(this).load(Product.IMAGE).into(ivProduct2);
        tvPrice2.setText("$ARS " + Product.PRICE.toString());
        tvLength.setText(String.valueOf(length + " cm"));
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

            //Se va pasando por los activities toda la informacion del producto a medida que el usuario
            //recorre toda la secuencia de compra
            Intent from = new Intent(this, ProductsActivity3.class);
            from.putExtra("from","oneProductSequence");
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
        tvName2 = findViewById(R.id.tv_name2);
        tvPrice2 = findViewById(R.id.tv_price2);
        tvDescription2 = findViewById(R.id.tv_description2);
        tvLength = findViewById(R.id.tv_length2);
        tvStock = findViewById(R.id.tv_stock2);
    }
}