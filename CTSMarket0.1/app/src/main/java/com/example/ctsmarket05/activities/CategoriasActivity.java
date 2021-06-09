package com.example.ctsmarket05.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.ctsmarket05.R;
import com.example.ctsmarket05.activities.productsClasses.ProductsActivity1;
import com.google.android.material.navigation.NavigationView;

public class CategoriasActivity extends AppCompatActivity {

    private ImageButton ivMenuCat;
    private DrawerLayout drawerLayout ;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias);

        ivMenuCat = findViewById(R.id.iv_menu_cat);
        navigationView = findViewById(R.id.nav_view);
        drawerLayout = findViewById(R.id.dl);


        menu();
    }

    private void menu(){
        ivMenuCat.setOnClickListener(v -> {
            drawerLayout.openDrawer(GravityCompat.START);

            navigationView.setNavigationItemSelectedListener(item -> {

                switch (item.getItemId()){
                    case R.id.nav_home:
                        Intent MiUsuario = new Intent(getApplicationContext(), UserActivty.class);
                        startActivity(MiUsuario);
                    case R.id.nav_mi_usuario:
                        Intent home = new Intent(getApplicationContext(),HomeActivity.class);
                        startActivity(home);
                    case R.id.nav_pedidos:
                        Intent pedidos = new Intent(getApplicationContext(),PedidosActivity.class);
                        startActivity(pedidos);
                    case  R.id.nav_logout:
                        Intent logout = new Intent(getApplicationContext(),LogginActivity.class);
                        startActivity(logout);
                    case R.id.nav_productos:
                        Intent productos = new Intent(getApplicationContext(), ProductsActivity1.class);
                        startActivity(productos);
                        break;
                    case R.id.nav_contacto:
                        Intent contacto = new Intent(getApplicationContext(),ContactoActivity.class);
                        startActivity(contacto);
                        break;
                    case R.id.nav_exhibicion:
                        Intent ex = new Intent(getApplicationContext(),ProductsActivity1.class);
                        startActivity(ex);
                        break;
                    case R.id.nav_talleres:
                        Intent talleres = new Intent(getApplicationContext(),TalleresActivity.class);
                        startActivity(talleres);
                        break;
                    case R.id.nav_categorias:
                        Intent cat = new Intent(getApplicationContext(),CategoriasActivity.class);
                        startActivity(cat);
                        break;
                    case R.id.nav_comp:
                    // Intent comp = new Intent(getApplicationContext(),CompartirFragment.class);
                    // startActivity(comp);
                        break;
                    case R.id.nav_puntuar:
                      // Intent puntuar = new Intent(getApplicationContext(),PuntuarFragment.class);
                      // startActivity(puntuar);
                    case  R.id.nav_conf:
                        Intent conf = new Intent(getApplicationContext(),ConfActivity.class);
                        startActivity(conf);
                }
                return true;
            });
        });
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            Intent home = new Intent(this,HomeActivity.class);
            startActivity(home);
            super.onBackPressed();
        }
    }
}