package com.example.ctsmarket05.view.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ctsmarket05.R;
import com.example.ctsmarket05.base.BaseActivity;
import com.example.ctsmarket05.interfaces.LocationAddActivityInterface;
import com.example.ctsmarket05.model.LocationAddInteractor;
import com.example.ctsmarket05.model.UserInfoEditInteractor;
import com.example.ctsmarket05.presenter.LocationAddActivityPresenter;
import com.example.ctsmarket05.presenter.UserInfoEditPresenter;
import com.example.ctsmarket05.view.activities.oneProductSequence.OPSActivity3;
import com.example.ctsmarket05.view.activities.userActivities.LocationInfoActivity;
import com.example.ctsmarket05.entities.Location;
import com.example.ctsmarket05.entities.User;
import com.example.ctsmarket05.model.location.LocationAddPOST;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.ThreeBounce;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class LocationAddActivity extends BaseActivity<LocationAddActivityPresenter> implements LocationAddActivityInterface {

    private TextInputEditText etStreet;
    private TextInputEditText etStreetNumber;
    private TextInputEditText etCity;
    private TextInputEditText etPostalCode;
    private TextInputEditText etFloor;
    private TextInputEditText etDistrict;
    private TextView tvCancelar;
    private TextView tvAlert;
    private Button btnSave;
    private AutoCompleteTextView atProvince;
    private ArrayList<String> arrayListProvinces;
    private ArrayAdapter<String> arrayAdapterProvinces;
    private ProgressBar progressBarLAA;
    private int ligthBlueColor = Color.parseColor("#75AADB");

    @NotNull
    @Override
    protected LocationAddActivityPresenter createPresenter(@NotNull Context context) {
        return new LocationAddActivityPresenter(this, new LocationAddInteractor());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_add);

        findViews();
        clickListeners();
        provinces();
    }

    private void clickListeners() {

        tvCancelar.setOnClickListener(v -> {
            backTo();
        });

        btnSave.setOnClickListener(v -> {
            if(
                    atProvince.getText().toString().equals("") || etCity.getText().toString().equals("")
                            || etDistrict.getText().toString().equals("") || etStreet.getText().toString().equals("")
                            || etStreetNumber.getText().toString().equals("") || etFloor.getText().toString().equals("")
                            || etPostalCode.getText().toString().equals("")
            ){
                alert();

            }else{
                updateInfo();
            }
        });
    }

    private void provinces() {
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

    @Override
    public void updateInfo() {
        Location location1 = new Location(User.IDUSER,
                atProvince.getText().toString(),
                etCity.getText().toString(),
                etDistrict.getText().toString(),
                etStreet.getText().toString(),
                etStreetNumber.getText().toString(),
                etFloor.getText().toString(),
                etPostalCode.getText().toString());

        presenterActivity.interaction(location1);
    }

    @Override
    public void alert() {
        tvAlert.setVisibility(View.VISIBLE);
    }

    @Override
    public void backTo(){

        Intent toLocationAdd = getIntent();
        String backTo = toLocationAdd.getStringExtra("locationAdd");

        switch (backTo){

            case "fromPA3":{
                Intent from = new Intent(this, OPSActivity3.class);
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

    @Override
    public void hideLayout() {
        etStreet.setVisibility(View.INVISIBLE);
        etStreetNumber.setVisibility(View.INVISIBLE);
        etCity.setVisibility(View.INVISIBLE);
        etPostalCode.setVisibility(View.INVISIBLE);
        etFloor.setVisibility(View.INVISIBLE);
        etDistrict.setVisibility(View.INVISIBLE);
        atProvince.setVisibility(View.INVISIBLE);
        btnSave.setVisibility(View.INVISIBLE);
        tvCancelar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showProgressbar() {
        Sprite pb = new ThreeBounce();
        pb.setColor(ligthBlueColor);
        progressBarLAA.setIndeterminateDrawable(pb);
        progressBarLAA.setVisibility(View.VISIBLE);
    }

    @Override
    public void onError() {
        Toast.makeText(this, "Algo salío mal actualizando la información, por favor más tarde.", Toast.LENGTH_LONG).show();
    }

    private void findViews(){
        atProvince = findViewById(R.id.at_province_add);
        etStreet = findViewById(R.id.et_street_add);
        etStreetNumber = findViewById(R.id.et_street_number_add);
        etCity = findViewById(R.id.et_city_add);
        etPostalCode = findViewById(R.id.et_postal_code_add);
        etFloor = findViewById(R.id.et_floor_add);
        etDistrict = findViewById(R.id.et_district_add);
        atProvince = findViewById(R.id.at_province_add);
        btnSave = findViewById(R.id.btn_save_add);
        tvCancelar = findViewById(R.id.tv_cancelar_add);
        tvAlert = findViewById(R.id.tv_alert_LAA);
        progressBarLAA = findViewById(R.id.pb_LAA);
    }
}
