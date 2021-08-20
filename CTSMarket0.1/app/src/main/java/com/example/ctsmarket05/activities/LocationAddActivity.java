package com.example.ctsmarket05.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ctsmarket05.R;
import com.example.ctsmarket05.activities.productsClasses.ProductsActivity4;
import com.example.ctsmarket05.activities.userActivities.LocationInfoActivity;
import com.example.ctsmarket05.entities.Location;
import com.example.ctsmarket05.entities.User;
import com.example.ctsmarket05.retrofit.locationRetrofit.LocationAddPOST;
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
        btnCancelar();
        provinces();
    }

    private void btnCancelar() {

        tvCancelar.setOnClickListener(v -> {
            backTo();
        });
    }

    private void provinces() {

        prueba = findViewById(R.id.pruebaLayout);
        atProvince = findViewById(R.id.at_province_add);

        arrayListProvinces = new ArrayList<>();
        arrayListProvinces.add("CABA");
        arrayListProvinces.add("Buenos Aires");
        arrayListProvinces.add("Catamarca");
        arrayListProvinces.add("Chaco");
        arrayListProvinces.add("Chubut");
        arrayListProvinces.add("Córdoba");
        arrayListProvinces.add("Corrientes");
        arrayListProvinces.add("Entre Ríos");
        arrayListProvinces.add("Formosa");
        arrayListProvinces.add("Jujuy");
        arrayListProvinces.add("La Pampa");
        arrayListProvinces.add("La Rioja");
        arrayListProvinces.add("Mendoza");
        arrayListProvinces.add("Misiones");
        arrayListProvinces.add("Neuquén");
        arrayListProvinces.add("Río Negro");
        arrayListProvinces.add("Salta");
        arrayListProvinces.add("San Juan");
        arrayListProvinces.add("San Luis");
        arrayListProvinces.add("Santa Cruz");
        arrayListProvinces.add("Santa Fe");
        arrayListProvinces.add("Santiago del Estero");
        arrayListProvinces.add("Tierra del Fuego");
        arrayListProvinces.add("Tucumán");

        arrayAdapterProvinces = new ArrayAdapter<>(getApplicationContext(), R.layout.dropdown_provinces,arrayListProvinces);
        atProvince.setAdapter(arrayAdapterProvinces);

        atProvince.setThreshold(1);
    }

    //checkea la info del usurio, si ya existe la actualiza y sino la crea.
    public void btnGuardar(){

        btnSave.setOnClickListener(v -> {

            if(     //Chequea que todos los campos esten completos
                    atProvince.getText().toString().equals("") || etCity.getText().toString().equals("")
                            || etDistrict.getText().toString().equals("") || etStreet.getText().toString().equals("")
                            || etStreetNumber.getText().toString().equals("") || etFloor.getText().toString().equals("")
                            || etPostalCode.getText().toString().equals("")
            ){
                Toast.makeText(this, "Verifique que todos los campos estén completos", Toast.LENGTH_LONG).show();
            }else{


            Location location = new Location(
                    User.IDUSER,
                    atProvince.getText().toString(),
                    etCity.getText().toString(),
                    etDistrict.getText().toString(),
                    etStreet.getText().toString(),
                    Integer.parseInt(etStreetNumber.getText().toString()),
                    etFloor.getText().toString(),
                    Integer.parseInt(etPostalCode.getText().toString()));

            LocationAddPOST locationAddPOST = new LocationAddPOST();
            locationAddPOST.locationPost(location);

            backTo();
            }
        });
    }

    private void backTo(){

        //Si viene desde ProductsActivity3 vuelve ahí
        Intent toLocationAdd = getIntent();
        String backTo = toLocationAdd.getStringExtra("locationAdd");

        switch (backTo){

            case "fromPA3":{
                Intent from = new Intent(this, ProductsActivity4.class);
                startActivity(from);
                finish();
            }break;

            case "location_info":{
                Intent from = new Intent(this, LocationInfoActivity.class);
                startActivity(from);
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
