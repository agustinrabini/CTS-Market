package com.example.ctsmarket05.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ctsmarket05.R;
import com.example.ctsmarket05.entities.Location;
import com.example.ctsmarket05.retrofit.locationRetrofit.LocationGETbyIdUser;

public class TalleresActivity extends AppCompatActivity {

    private Button button;
    private EditText et_email;
    private EditText et_dni;
    private EditText et_name;
    private EditText etPostalCode;
    private EditText etFloor;
    TextView textView56;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talleres);

        button = findViewById(R.id.button);
        textView56 = findViewById(R.id.textView56);

        getLocationId();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView56.setText(Location.idLocation.toString());
            }
        });

    }

    private void getLocationId(){
        LocationGETbyIdUser locationGETbyIdUser = new LocationGETbyIdUser();
        locationGETbyIdUser.SetOnDataListenerLocGetById(location ->{
            Integer id = location.getId_location();
            Integer id_user = location.getId_user();
            Toast.makeText(locationGETbyIdUser, id.toString(), Toast.LENGTH_SHORT).show();
        });
        locationGETbyIdUser.getLocationByIdUser();
    }

}
