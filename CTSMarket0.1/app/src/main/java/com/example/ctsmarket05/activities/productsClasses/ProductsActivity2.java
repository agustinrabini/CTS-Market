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
import com.example.ctsmarket05.entities.User;
import com.example.ctsmarket05.retrofit.ordersRetrofit.OrderGetByIdUser;
import com.example.ctsmarket05.retrofit.ordersRetrofit.OrderCartPOST;
import com.example.ctsmarket05.retrofit.ordersRetrofit.OrderAddPricePUT;
import com.example.ctsmarket05.retrofit.productsOrderRetrofit.ProductsOrderPOST;
import com.squareup.picasso.Picasso;

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
        btnCart();
    }

    private void btnCart() {
        //se agrega un ubjeto al carrito activo. El estado 10 indica que el carrito aun esta activo
        //y se le pueden seguir agregando productos.
        ivCart.setOnClickListener(v -> {

            Intent Clicked = getIntent();

            String id_prod = Clicked.getStringExtra("id_product");
            Integer price = Clicked.getIntExtra("price", 0);

            Product.QUANTITY = quantityProduct;
            Product.PRICE = price * quantityProduct;
            Product.ID_PRODUCT = Integer.parseInt(id_prod);

            //Calendar calendar = Calendar.getInstance();
            //String date = DateFormat.getDateInstance().format(calendar.getTime());

            OrderCartPOST orderCartPOST = new OrderCartPOST();
            orderCartPOST.orderCartPOST(
                    User.IDUSER,
                    Product.PRICE,
                    Product.QUANTITY,
                    10,
                    null,
                    ""
            );

            OrderGetByIdUser orderGetByIdUser = new OrderGetByIdUser();
            orderGetByIdUser.SetOnDataListenerOrdersPO(order -> {

                Integer id_order = order.getId_order();
                //si es nulo es porque es la primera vez que se crea el carrito
                if (id_order != null){
                    OrderAddPricePUT orderAddPricePUT = new OrderAddPricePUT();
                    orderAddPricePUT.orderPricePut(Product.PRICE,Product.QUANTITY,id_order);
                }
            });
            orderGetByIdUser.OrderGetByIdUser();

            ProductsOrderPOST productsOrderPOST = new ProductsOrderPOST();
            productsOrderPOST.addCart(Product.ID_PRODUCT, User.IDUSER, Product.QUANTITY);
        });
    }

    private void getProductInfo() {

        Intent Clicked = getIntent();
        String id_prod = Clicked.getStringExtra("id_product");
        Product.ID_PRODUCT = Integer.parseInt(id_prod);

        String name = Clicked.getStringExtra("name");
        String blade = Clicked.getStringExtra("blade");
        String brand = Clicked.getStringExtra("brand");
        String description = Clicked.getStringExtra("description");
        String image = Clicked.getStringExtra("image");
        Integer price = Clicked.getIntExtra("price",0);
        Integer length = Clicked.getIntExtra("length",0);
        Integer stock = Clicked.getIntExtra("stock",0);

        Product.QUANTITY = quantityProduct;
        Product.ID_PRODUCT = Integer.parseInt(id_prod);
        Product.NAME = name;
        Product.PRICE = price;
        Product.IMAGE = image;

        if (stock == 0){
            btnBuy.setText("SIN STOCK");
            btnBuy.setEnabled(false);
        }

        tvName2.setText(Product.NAME);
        tvDescription2.setText(description);
        Picasso.with(this).load(Product.IMAGE).into(ivProduct2);
        tvPrice2.setText(Product.PRICE.toString());
        tvLength.setText(String.valueOf(length + " cm"));
    }

    private void btnQuestion() {
        tvQuestion.setOnClickListener(v -> {
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

            Product.PRICE = price * quantityProduct;

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
        quantityProduct = quantity;
        tvQuantity.setText("Cant. : " + quantityProduct.toString());
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
    }
}