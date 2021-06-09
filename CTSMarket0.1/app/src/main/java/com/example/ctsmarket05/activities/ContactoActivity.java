package com.example.ctsmarket05.activities;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ctsmarket05.R;

public class ContactoActivity extends AppCompatActivity {

    private TextView txtViewGmail;
    private TextView txtViewFace;
    private TextView txtViewTel;
    private ImageButton imgBtnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        txtViewGmail = findViewById(R.id.textViewGmail);
        txtViewFace = findViewById(R.id.textViewFace);
        txtViewTel = findViewById(R.id.textViewTel);
        imgBtnBack = findViewById(R.id.imgBtnBack);

        imgBtnBack.setOnClickListener(v -> {
                Intent GoBack = new Intent(ContactoActivity.this, HomeActivity.class);
                startActivity(GoBack);
            });

      txtViewGmail.setOnClickListener(v -> {
          String email = "agustinrabini99@gmail.com";
          Intent intentG = new Intent(Intent.ACTION_SEND, Uri.parse(email));
          intentG.setType("plain/text");
          intentG.putExtra(Intent.EXTRA_EMAIL, new String []{"agustinrabini99@gmail.com"});

          try {
            startActivity(Intent.createChooser(intentG,"Seleccione una app"));
          } catch (android.content.ActivityNotFoundException ex ){
              Toast.makeText(getApplicationContext(),"No tienes aplciaciones de mensajeria por correro instaladas en tu celular",Toast.LENGTH_LONG).show();
          }
      });

      txtViewTel.setOnClickListener(v -> {
          String telefono = "+54 1132424233";
          String url = "https://api.whatsapp.com/send?phone=";
          Intent llamada = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:"+ telefono));
          llamada.setData(Uri.parse(url + telefono));
        startActivity(llamada);
      });

      txtViewFace.setOnClickListener(v -> {

          String url = "https://www.facebook.com/CuchillosTitanDelSur";
          boolean isAppInstalled = appInstalledOrNot("com.facebook.katana");

          if(isAppInstalled) {

              Intent face = new Intent(Intent.ACTION_VIEW);
              face.setData(Uri.parse("fb://facewebmodal/f?href=" + url));
              startActivity(face);

          } else {
                Intent faceweb = new Intent(Intent.ACTION_VIEW);
                faceweb.setData(Uri.parse(url));
                startActivity(faceweb);
          }
      });
    }

    private boolean appInstalledOrNot(String uri) {
        PackageManager pm = getPackageManager();
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
        }

        return false;
    }
}

