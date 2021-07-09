package com.example.ctsmarket05.activities;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;

import com.example.ctsmarket05.R;
import com.example.ctsmarket05.activities.productsClasses.ProductsActivity1;
import com.example.ctsmarket05.entities.Location;
import com.example.ctsmarket05.entities.User;
import com.example.ctsmarket05.retrofit.userRetrofit.UserGET;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.navigation.NavigationView;


public class HomeActivity extends AppCompatActivity {
    private TextView tvUserName;
    private ImageView ivProductos;
    private ImageView ivContacto;
    private ImageView ivExibicion;
    private ImageView ivTalleres;
    private ImageView ivMenu;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout ;
    private Dialog compartir;
    private NavController navController;
    private SearchView searchViewHome;
    private ImageButton ibBackDc;
    private Integer idlocation;
    GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tvUserName = findViewById(R.id.tv_user_name);
        ivProductos = findViewById(R.id.iv_productos);
        ivContacto= findViewById(R.id.iv_contacto);
        ivExibicion=  findViewById(R.id.iv_exhibicion);
        ivTalleres= findViewById(R.id.iv_talleres);
        ivMenu= findViewById(R.id.iv_menu);
        searchViewHome = findViewById(R.id.searchViewHome);
        navigationView = findViewById(R.id.nav_view);
        drawerLayout = findViewById(R.id.dl);
        ibBackDc = findViewById(R.id.ib_back_dc);

       // navController = Navigation.findNavController(this,R.id.nav_home_host);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            String personName = acct.getDisplayName();
            String personEmail = acct.getEmail();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();

            User.gmail = personEmail;
            tvUserName.setText("Hola " +personName);
        }

        menu();
        activities();
        setStaticIdsValues();
    }

    //Activities botones principales
    private void activities (){
        ivContacto.setOnClickListener(v -> {
            Intent contacto = new Intent(HomeActivity.this, ContactoActivity.class);
            startActivity(contacto);
        });

        ivProductos.setOnClickListener(v ->{

            Intent productos = new Intent(HomeActivity.this, ProductsActivity1.class);
            startActivity(productos);
        });

        ivExibicion.setOnClickListener(v -> {
            Intent exibicion = new Intent(HomeActivity.this, ExhibitionActivity.class);
            startActivity(exibicion);
        });

        ivTalleres.setOnClickListener(v -> {
            Intent talleres = new Intent(HomeActivity.this, OrdersActivity.class);
            startActivity(talleres);
        });
    }

    //Lanzamiento de las activities del menú
    private void menu(){
        ivMenu.setOnClickListener(v -> {
            drawerLayout.openDrawer(GravityCompat.START);

            navigationView.setNavigationItemSelectedListener(item -> {

                switch (item.getItemId()){
                    case R.id.nav_mi_usuario:
                        Intent home = new Intent(getApplicationContext(),HomeActivity.class);
                        startActivity(home);
                        break;
                    case R.id.nav_home:
                        Intent MiUsuario = new Intent(getApplicationContext(), UserActivty.class);
                        startActivity(MiUsuario);
                        break;
                    case R.id.nav_pedidos:
                        Intent pedidos = new Intent(getApplicationContext(),PedidosActivity.class);
                        startActivity(pedidos);
                        break;
                    case  R.id.nav_logout:
                        signOut();
                        break;
                    case R.id.nav_productos:
                        Intent productos = new Intent(getApplicationContext(), ProductsActivity1.class);
                        startActivity(productos);
                        break;
                    case R.id.nav_contacto:
                        Intent contacto = new Intent(getApplicationContext(),ContactoActivity.class);
                        startActivity(contacto);
                        break;
                    case R.id.nav_exhibicion:
                        Intent ex = new Intent(getApplicationContext(), ProductsActivity1.class);
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
                        dialogoPuntuar();
                    case  R.id.nav_conf:
                        Intent conf = new Intent(getApplicationContext(),ConfActivity.class);
                        startActivity(conf);
                }
                return true;
            });
        });
    }

    private void signOut() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .build();
        GoogleSignInClient googleSignInClient=GoogleSignIn.getClient(this,gso);
        googleSignInClient.signOut();

        Intent logout = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(logout);
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

    private void dialogoPuntuar(){
        compartir= new Dialog(HomeActivity.this);
        compartir.setContentView(R.layout.compartir_dialogo);
        compartir.getWindow().setBackgroundDrawable(getDrawable(R.drawable.loggin_solapa));
        compartir.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
     //  compartir.setCancelable(true);
        compartir.getWindow().getAttributes().windowAnimations = R.style.animacion;

        ibBackDc.setOnClickListener(v -> {
            compartir.dismiss();
        });

        compartir.show();
    }
    //Establece el valor de los atributos estaticos idLocation y idUser. Para ser usado en LocationGET
    public void setStaticIdsValues(){
        //para idLocation
        UserGET getId = new UserGET();
        getId.SetOnDataListenerUser(user -> {
            Integer id = user.getId_location();
            if(id!=null) {
                setLocationId(id);
            }else{}
        });
        getId.getUserByGmail();

        UserGET userGET = new UserGET();
        userGET.SetOnDataListenerUser(user -> {
            Integer id = user.getId_user();
            setUserId(id);
        });
        userGET.getUserByGmail();
    }

    private void setLocationId(Integer id){
        Location.idLocation = id;
    }

    private void setUserId(Integer id){
           User.IDUSER= id;
    }
}