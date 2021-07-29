package com.example.ctsmarket05.activities.productsClasses;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ctsmarket05.R;
import com.example.ctsmarket05.clickListeners.ProductsOnCustomClickListener;
import com.example.ctsmarket05.adapters.ComplementAdapter;
import com.example.ctsmarket05.adapters.ProductsAdapter;
import com.example.ctsmarket05.entities.Product;
import com.example.ctsmarket05.retrofit.productRetrofit.ProductsGET;
import com.google.android.material.navigation.NavigationView;

public class ProductsActivity1 extends AppCompatActivity implements ProductsOnCustomClickListener {

    private ProductsAdapter productsAdapter = new ProductsAdapter(this);
    private ComplementAdapter complementAdapter = new ComplementAdapter(this);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products1);

        getData();
    }

    private void getData(){
        rvcomplementario();
        rvproducts();

       //ProductsGET productsGET = new ProductsGET();
       //productsGET.SetOnDataListenerProd(products -> {
       //    productsAdapter.setProducts(products);
       //    complementAdapter.setComplement(products);
       //});
       //productsGET.getProducts();
    }

    private void rvproducts(){
       RecyclerView rvProductos = findViewById(R.id.rv_products);
       LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
       rvProductos.setLayoutManager(layoutManager);
       rvProductos.setAdapter(productsAdapter);
    }

   private void rvcomplementario(){
       RecyclerView rvComplementarios = findViewById(R.id.rv_complement);
       LinearLayoutManager layoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
       rvComplementarios.setLayoutManager(layoutManager2);
       rvComplementarios.setAdapter(complementAdapter);
   }

   @Override
   public void onItemClick (Product product, int position) { }
}