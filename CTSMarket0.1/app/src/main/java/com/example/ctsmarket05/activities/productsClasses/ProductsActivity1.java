package com.example.ctsmarket05.activities.productsClasses;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ctsmarket05.R;
import com.example.ctsmarket05.activities.CategoriasActivity;
import com.example.ctsmarket05.activities.ConfActivity;
import com.example.ctsmarket05.activities.ContactoActivity;
import com.example.ctsmarket05.activities.ExhibitionActivity;
import com.example.ctsmarket05.activities.HomeActivity;
import com.example.ctsmarket05.activities.clickListeners.ProductsOnCustomClickListener;
import com.example.ctsmarket05.activities.PedidosActivity;
import com.example.ctsmarket05.activities.TalleresActivity;
import com.example.ctsmarket05.activities.UserActivty;
import com.example.ctsmarket05.adapters.ComplementAdapter;
import com.example.ctsmarket05.adapters.ProductsAdapter;
import com.example.ctsmarket05.entities.Product;
import com.example.ctsmarket05.retrofit.productRetrofit.ProductsGET;
import com.google.android.material.navigation.NavigationView;

public class ProductsActivity1 extends AppCompatActivity implements ProductsOnCustomClickListener {

    private ProductsAdapter productsAdapter = new ProductsAdapter(this);
    private ComplementAdapter complementAdapter = new ComplementAdapter(this);
    private ImageButton ivMenuProdex;
    private NavigationView nvProd;
    private DrawerLayout dlProd ;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products1);

        nvProd = findViewById(R.id.nav_view_prod);
        dlProd = findViewById(R.id.dl_prod);
        ivMenuProdex = findViewById(R.id.iv_menu_prodex);

        getData();
        menu();
    }

    private void getData(){
        rvcomplementario();
        rvproducts();

        ProductsGET productsGET = new ProductsGET();
        productsGET.SetOnDataListenerProd(products -> {
            productsAdapter.setProducts(products);
            complementAdapter.setComplement(products);
        });
        productsGET.getProducts();
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

    private void menu() {
        ivMenuProdex.setOnClickListener(v -> {
            dlProd.openDrawer(GravityCompat.START);

            nvProd.setNavigationItemSelectedListener(item -> {

                switch (item.getItemId()) {
                    case R.id.nav_mi_usuario:
                        Intent home = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(home);
                    case R.id.nav_home:
                        Intent MiUsuario = new Intent(getApplicationContext(), UserActivty.class);
                        startActivity(MiUsuario);
                    case R.id.nav_pedidos:
                        Intent pedidos = new Intent(getApplicationContext(), PedidosActivity.class);
                        startActivity(pedidos);
                    case R.id.nav_logout:
                      // Intent logout = new Intent(getApplicationContext(), LogoutFragment.class);
                      // startActivity(logout);
                    case R.id.nav_productos:
                        Intent productos = new Intent(getApplicationContext(), ProductsActivity1.class);
                        startActivity(productos);
                        break;
                    case R.id.nav_contacto:
                        Intent contacto = new Intent(getApplicationContext(), ContactoActivity.class);
                        startActivity(contacto);
                        break;
                    case R.id.nav_exhibicion:
                        Intent ex = new Intent(getApplicationContext(), ExhibitionActivity.class);
                        startActivity(ex);
                        break;
                    case R.id.nav_talleres:
                        Intent talleres = new Intent(getApplicationContext(), TalleresActivity.class);
                        startActivity(talleres);
                        break;
                    case R.id.nav_categorias:
                        Intent cat = new Intent(getApplicationContext(), CategoriasActivity.class);
                        startActivity(cat);
                        break;
                    case R.id.nav_comp:
                     // Intent comp = new Intent(getApplicationContext(), CompartirFragment.class);
                     // startActivity(comp);
                        break;
                    case R.id.nav_puntuar:
                    case R.id.nav_conf:
                        Intent conf = new Intent(getApplicationContext(), ConfActivity.class);
                        startActivity(conf);
                }
                return true;
            });
        });
    }

    public void onBackPressed() {
        if(dlProd.isDrawerOpen(GravityCompat.START)){
            dlProd.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public void onItemClick (Product product, int position) {
    }
}