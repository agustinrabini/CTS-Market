package com.example.ctsmarket05.activities.productsClasses;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ctsmarket05.R;
import com.example.ctsmarket05.activities.userActivities.UserActivty;
import com.example.ctsmarket05.activities.userActivities.UserInfoEditActivity;
import com.example.ctsmarket05.entities.User;
import com.example.ctsmarket05.retrofit.userRetrofit.UserGET;

public class ProductsActivity3 extends AppCompatActivity {

    private TextView tvUser;
    private TextView tvChangeUserInfo;
    private TextView tvNeedEditUser;
    private Button btnBuy;
    private ImageView ivNeedLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products3);

        showUserInfo();
        findViews();
        changeUserInfo();
        next();
    }

    private void changeUserInfo(){

        tvChangeUserInfo.setOnClickListener(v -> {
            UserInfoEditActivity.from = "boughtSequence";
            Intent toUserEdit = new Intent(this, UserInfoEditActivity.class);
            startActivity(toUserEdit);
            finish();
        });
    }

    private void showUserInfo(){

        UserGET userGET = new UserGET();
        userGET.SetOnDataListenerUser(user -> {

            String name = user.getName_lastname();
            Integer dni = user.getDni();
            Integer phone = user.getPhone();

            if (name==null||dni==null||phone==null) {

                tvNeedEditUser.setVisibility(View.VISIBLE);
                ivNeedLocation.setVisibility(View.VISIBLE);
                tvChangeUserInfo.setVisibility(View.VISIBLE);
                tvChangeUserInfo.setText("Agregar información");

            }else{
                btnBuy.setVisibility(View.VISIBLE);
                tvChangeUserInfo.setVisibility(View.VISIBLE);
                tvChangeUserInfo.setText("Editar información");
                tvUser.setText(
                        "Nombre: " + user.getName_lastname()+ "\n" + "\n" +"DNI: " + user.getDni().toString() + "\n" + "\n" + "Celular:" + user.getPhone()
                );
            }
        });
        userGET.getUserByGmail();
    }

    private void next(){

        btnBuy.setOnClickListener(v -> {

          Intent toProdActv5 = new Intent(this, ProductsActivity4.class);
          startActivity(toProdActv5);
        });

    }

    private void findViews(){

        tvChangeUserInfo = findViewById(R.id.tv_change_user_info4);
        tvUser = findViewById(R.id.tv_user_info4);
        tvNeedEditUser = findViewById(R.id.tv_need_to_add_user_info4);
        ivNeedLocation = findViewById(R.id.iv_need_to_add_user_info4);
        btnBuy = findViewById(R.id.btn_buy4);
    }
}