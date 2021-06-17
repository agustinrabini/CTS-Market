package com.example.ctsmarket05.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ctsmarket05.R;
import com.example.ctsmarket05.activities.clickListeners.ProductsOnCustomClickListener;
import com.example.ctsmarket05.activities.productsClasses.ProductsActivity1;
import com.example.ctsmarket05.adapters.ExhibitionAdapter;
import com.example.ctsmarket05.entities.Product;
import com.example.ctsmarket05.entities.User;
import com.example.ctsmarket05.retrofit.productRetrofit.ProductInterface;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ExhibitionActivity extends AppCompatActivity implements ProductsOnCustomClickListener {

    //Al adapter se le pasa interface onCustomClicklistener
    private ExhibitionAdapter exhibitionAdapter = new ExhibitionAdapter(this);
    private ImageButton ivMenuEx;
    private DrawerLayout drawerLayout ;
    private NavigationView navigationView;
    private ImageButton ibHlleno;
    private ImageButton ibHvacio;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exibicion);

        drawerLayout = findViewById(R.id.dl_ex);
        ivMenuEx= findViewById(R.id.iv_menu_ex);
        navigationView = findViewById(R.id.nav_view_ex);
    // ibHlleno = findViewById(R.id.ib_hlleno);
    // ibHvacio = findViewById(R.id.ib_hvacio);

        getData();
        menu();
        favoritos();
    }

   private void getData(){

       rvExibition();
       Retrofit retrofit = new Retrofit.Builder()
               .baseUrl(User.URL)
               .addConverterFactory(GsonConverterFactory.create())
               .build();

       ProductInterface productInterface = retrofit.create(ProductInterface.class);

       Call<List<Product>> call = productInterface.productsList();

       call.enqueue(new Callback<List<Product>>() {
           @Override
           public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {

               if(response.isSuccessful() && response.body()!=null){
                   exhibitionAdapter.setExhibition(response.body());
               }
               else{
                   Toast.makeText(ExhibitionActivity.this, "Error:" + response.code(), Toast.LENGTH_LONG).show();
               }
           }

           @Override
           public void onFailure(Call<List<Product>> call, Throwable t) {
               Toast.makeText(ExhibitionActivity.this, "Error:" + t.getMessage(), Toast.LENGTH_SHORT).show();
           }
       });
   }

    private void rvExibition(){
        RecyclerView rvExibition = findViewById(R.id.rv_exibition);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rvExibition.setLayoutManager(layoutManager);
        rvExibition.setAdapter(exhibitionAdapter);
    }

    private void menu (){

        ivMenuEx.setOnClickListener(v -> {

            drawerLayout.openDrawer(GravityCompat.START);

            navigationView.setNavigationItemSelectedListener(item -> {

                switch (item.getItemId()){

                    case R.id.nav_productos:
                        Intent productos = new Intent(getApplicationContext(), ProductsActivity1.class);
                        startActivity(productos);
                        break;
                    case R.id.nav_contacto:
                        Intent contacto = new Intent(getApplicationContext(),ContactoActivity.class);
                        startActivity(contacto);
                        break;
                    case R.id.nav_exhibicion:
                        Intent ex = new Intent(getApplicationContext(),ExhibitionActivity.class);
                        startActivity(ex);
                        break;
                    case R.id.nav_talleres:
                        Intent talleres = new Intent(getApplicationContext(),TalleresActivity.class);
                        startActivity(talleres);
                        break;
                    case R.id.nav_home:
                        Intent home = new Intent(getApplicationContext(),HomeActivity.class);
                        startActivity(home);
                        break;
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
            super.onBackPressed();
        }
    }

    @Override
    public void onItemClick(Product product, int position) {
    }

    public void favoritos(){

 //ibHvacio.setOnClickListener(v -> {
 //    ibHvacio.setVisibility(View.INVISIBLE);
 //    ibHlleno.setVisibility(View.VISIBLE);
 //});

 //ibHlleno.setOnClickListener(v -> {
 //    ibHlleno.setVisibility(View.INVISIBLE);
 //    ibHvacio.setVisibility(View.VISIBLE);
 //});
    }
}