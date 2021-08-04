package com.example.ctsmarket05.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ctsmarket05.R;
import com.example.ctsmarket05.activities.productsClasses.ProductsActivity3;
import com.example.ctsmarket05.entities.Location;
import com.example.ctsmarket05.entities.User;
import com.example.ctsmarket05.retrofit.locationRetrofit.LocationPOST;
import com.example.ctsmarket05.retrofit.locationRetrofit.LocationPUT;
import com.example.ctsmarket05.retrofit.userRetrofit.UserPUTIdLoc;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class LocationAddActivity extends AppCompatActivity {

    private TextInputEditText etStreet;
    private TextInputEditText etStreetNumber;
    private TextInputEditText etCity;
    private TextInputEditText etPostalCode;
    private TextInputEditText etFloor;
    private TextInputEditText etDistrict;
    private TextView tvCancelar;
    private Button btnSave;
    private AutoCompleteTextView atProvince;
    private TextInputLayout prueba;
    private ArrayList<String> arrayListProvinces;
    private ArrayAdapter<String> arrayAdapterProvinces;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_add);

        findViews();
        btnGuardar();
        provinces();
    }

    private void provinces() {

        prueba = (TextInputLayout) findViewById(R.id.pruebaLayout);
        atProvince = (AutoCompleteTextView) findViewById(R.id.at_province_add);

        arrayListProvinces = new ArrayList<>();
        arrayListProvinces.add("CABA");
        arrayListProvinces.add("Tucumán");
        arrayListProvinces.add("Córdoba");
        arrayListProvinces.add("Jujuy");
        arrayListProvinces.add("Salta");

        arrayAdapterProvinces = new ArrayAdapter<>(getApplicationContext(), R.layout.dropdown_provinces,arrayListProvinces);
        atProvince.setAdapter(arrayAdapterProvinces);

        atProvince.setThreshold(1);
    }


    //si el usuario ya esta registrado se le hace un UPDATE a la DB updateLocation();
    //si es la primera vez que se registra se hace un POST con firstTimePost();
    public void btnGuardar(){
        btnSave.setOnClickListener(v -> {
            if (Location.idLocation != null) {

                updateLocation();
            }else{

                firstTimePost();
            }
        });

        //tvCancelar.setOnClickListener(v -> {
        //    backTo();
        //});
    }

    private void updateLocation() {

        if(     //Chequea que todos los campos esten completos
                atProvince.getText().toString().equals("") || etCity.getText().toString().equals("")
                        || etDistrict.getText().toString().equals("") || etStreet.getText().toString().equals("")
                        || etStreetNumber.getText().toString().equals("") || etFloor.getText().toString().equals("")
                        || etPostalCode.getText().toString().equals("")
        ){
            Toast.makeText(this, "Verifique que todos los campos estén completos", Toast.LENGTH_LONG).show();
        }else{

            //UPDATE a la tabla location.
            LocationPUT locationPUT = new LocationPUT();
            locationPUT.locationPUT(
                    atProvince.getText().toString(),
                    etCity.getText().toString(),
                    etDistrict.getText().toString(),
                    etStreet.getText().toString(),
                    Integer.parseInt(etStreetNumber.getText().toString()),
                    etFloor.getText().toString(),
                    Integer.parseInt(etPostalCode.getText().toString()));

            backTo();
        }
    }

    //se ejecuta un INSERT a en la tabla location de la DB. Tambien se hace un UPDATE, a la tabla
    //user donde se actualiza su idLocation que era nulo.
    private void firstTimePost(){

        if(
                atProvince.getText().toString().equals("") || etCity.getText().toString().equals("")
                        || etDistrict.getText().toString().equals("") || etStreet.getText().toString().equals("")
                        || etStreetNumber.getText().toString().equals("") || etFloor.getText().toString().equals("")
                        || etPostalCode.getText().toString().equals("")
        ){
            Toast.makeText(this, "Verifique que todos los campos estén completos", Toast.LENGTH_LONG).show();
        }else{

            postL();

            backTo();
        }
    }

    private void postL(){
        LocationPOST locationPOST = new LocationPOST();
        locationPOST.locationPost(
                User.IDUSER,
                atProvince.getText().toString(),
                etCity.getText().toString(),
                etDistrict.getText().toString(),
                etStreet.getText().toString(),
                Integer.parseInt(etStreetNumber.getText().toString()),
                etFloor.getText().toString(),
                Integer.parseInt(etPostalCode.getText().toString())
        );

        updatedb();
    }

    private void updatedb(){
        UserPUTIdLoc userPUTIdLoc = new UserPUTIdLoc();
        userPUTIdLoc.userPut();
    }

    private void backTo(){

        //Si viene desde ProductsActivity3 vuelve ahí
        Intent toLocationAdd = getIntent();
        String backTo = toLocationAdd.getStringExtra("locationAdd");

        switch (backTo){

            case "fromPA3":{
                Intent fromLAA = new Intent(this, ProductsActivity3.class);
                fromLAA.putExtra("fromLAA","fromLAA");
                startActivity(fromLAA);
                finish();
            }break;

            case "location_info":{
                Intent fromLAA = new Intent(this, LocationInfoActivity.class);
                startActivity(fromLAA);
                finish();
            }break;
        }
    }

    private void findViews(){
        etStreet = findViewById(R.id.et_street_add);
        etStreetNumber = findViewById(R.id.et_street_number_add);
        etCity = findViewById(R.id.et_city_add);
        etPostalCode = findViewById(R.id.et_postal_code_add);
        etFloor = findViewById(R.id.et_floor_add);
        etDistrict = findViewById(R.id.et_district_add);
        atProvince = findViewById(R.id.at_province_add);
        btnSave = findViewById(R.id.btn_save_add);
        tvCancelar = findViewById(R.id.tv_cancelar_add);
    }
}
