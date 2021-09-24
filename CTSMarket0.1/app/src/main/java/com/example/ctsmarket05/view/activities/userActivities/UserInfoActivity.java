package com.example.ctsmarket05.view.activities.userActivities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ctsmarket05.R;

public class UserInfoActivity extends AppCompatActivity {

    private TextView tvChangeinfo;
    private TextView tvUserInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        findViews();
        changeLocation();
        getData();
    }

    private void changeLocation(){

        tvChangeinfo.setOnClickListener(v -> {
            Intent from = new Intent(this, UserInfoEditActivity.class);
            from.putExtra("from","userEdit");
            startActivity(from);
            finish();
        });
    }

    private void getData(){

        //UserGET userGET = new UserGET();
        //userGET.SetOnDataListenerUser(user -> {
//
        //    String name = user.getName_lastname();
        //    Integer dni = user.getDni();
        //    Integer phone = user.getPhone();
//
        //    if (name==null||dni==null||phone==null) {
//
        //        tvChangeinfo.setText("Agregar información");
//
        //    }else{
//
        //        tvChangeinfo.setText("Editar información");
        //        tvUserInfo.setText(
        //                "Nombre: " + user.getName_lastname()+ "\n" + "\n" +  "DNI: " + user.getDni().toString() + "\n" + "\n" +  "Celular:" + user.getPhone()
        //        );
        //    }
        //});
        //userGET.getUserByGmail();
    }

    private void findViews() {

        tvChangeinfo = findViewById(R.id.tv_change_user_infoUI);
        tvUserInfo = findViewById(R.id.tv_user_infoUI);
    }
}
