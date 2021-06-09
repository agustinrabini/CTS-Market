package com.example.ctsmarket05.activities.productsClasses;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ctsmarket05.R;
import com.squareup.picasso.Picasso;

public class ProductoPersonalizado extends AppCompatActivity {

    private ImageView ivProductoFinal;
    private ImageView ivDetalles;
    private TextView tvNombreFinal;
    private TextView tvValorBase;
    private TextView tvCostosDetalles;
    private TextView tvPrecioProducto;
    private TextView tvPrecioBronce;
    private TextView tvPrecioAlpaca;
    private TextView tvPrecioMadera;
    private TextView tvDetallesino;
    private TextView tvOpcionesDetalles;
    private CheckBox cbBronce;
    private CheckBox cbMadera;
    private CheckBox cbAlpaca;
    private ImageButton ibAñadir;
    private ImageButton ibMenos;
    private Button btnContinuar;
    private int detalleBronce = 0;
    private int detalleAlpaca = 0;                                    
    private int detalleMadera = 0;
    private int detallePrecio = 0;
    private String detalles = "No";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido3);

        ivProductoFinal = findViewById(R.id.iv_producto_final);
        ivDetalles = findViewById(R.id.iv_detalles);
        ibMenos = findViewById(R.id.iv_menos);
        tvNombreFinal = findViewById(R.id.tv_nombre_pedido3);
        tvValorBase = findViewById(R.id.tv_valor_base);
        tvCostosDetalles = findViewById(R.id.tv_costo_detalles);
        tvPrecioProducto = findViewById(R.id.tv_precio_producto);
        tvPrecioBronce = findViewById(R.id.tv_precio_bronce);
        tvPrecioAlpaca = findViewById(R.id.tv_precio_alpaca);
        tvPrecioMadera = findViewById(R.id.tv_precio_madera);
        tvDetallesino = findViewById(R.id.tv_detallesino);
        tvOpcionesDetalles = findViewById(R.id.tv_opciones_detalles);
        tvOpcionesDetalles = findViewById(R.id.tv_opciones_detalles);
        cbBronce = findViewById(R.id.cb_bronce);
        cbAlpaca = findViewById(R.id.cb_alpaca);
        cbMadera = findViewById(R.id.cb_madera);
        ibAñadir = findViewById(R.id.btn_añadir);
        btnContinuar = findViewById(R.id.btm_continuar);

        CargarDatos();
        Detalles();

        btnContinuar.setOnClickListener(v -> {
            Intent pasofinal = new Intent(ProductoPersonalizado.this, Personalizado2.class);

            pasofinal.putExtra("nombreFinal",tvNombreFinal.getText());
            pasofinal.putExtra("precioFinal",tvPrecioProducto.getText());
            pasofinal.putExtra("detalle",detalles);
            startActivity(pasofinal);

        });
    }

    private void CargarDatos(){
        Intent pedido = getIntent();
        String nombre = pedido.getStringExtra("nombre");
        String precio = pedido.getStringExtra("precio");
        String foto = pedido.getStringExtra("foto");

        tvNombreFinal.setText(nombre);
        tvValorBase.setText(String.valueOf(precio));
        Picasso.with(this).load(foto).into(ivProductoFinal);
        tvPrecioProducto.setText(String.valueOf(precio));
    }

    private void Detalles(){

        ibAñadir.setOnClickListener(v ->{
            ivDetalles.setVisibility(View.VISIBLE);
            ibMenos.setVisibility(View.VISIBLE);
            ibAñadir.setVisibility(View.INVISIBLE);
            tvPrecioMadera.setVisibility(View.VISIBLE);
            tvPrecioAlpaca.setVisibility(View.VISIBLE);
            tvPrecioBronce.setVisibility(View.VISIBLE);
            cbMadera.setVisibility(View.VISIBLE);
            cbAlpaca.setVisibility(View.VISIBLE);
            cbBronce.setVisibility(View.VISIBLE);
            tvOpcionesDetalles.setVisibility(View.VISIBLE);

            Intent pedido = getIntent();
            String precio = pedido.getStringExtra("precio");


                cbBronce.setOnClickListener(v1 -> {

                    if (cbBronce.isChecked()== true) {
                        cbBronce.setChecked(true);
                        detalleBronce = 800;
                        tvDetallesino.setText("Con detalles");
                        tvDetallesino.setText("Eliminar detalles añadidos");
                        tvDetallesino.setTextColor(Color.RED);
                        detalles = "Si";
                    }

                    if (cbBronce.isChecked()== false) {
                        detalleBronce = 0;
                        detalles = "No";
                    }

                    if (cbMadera.isChecked()==false && cbAlpaca.isChecked()==false && cbBronce.isChecked()==false){
                        tvDetallesino.setText("No se han añadido detalles");
                        tvDetallesino.setTextColor(Color.BLACK);
                    }
                    detallePrecio = detalleBronce + detalleAlpaca + detalleMadera;
                    tvCostosDetalles.setText(String.valueOf(detallePrecio));
                    tvPrecioProducto.setText(String.valueOf(detallePrecio + Integer.parseInt(precio)));

                });

                cbAlpaca.setOnClickListener(v2 -> {
                    if (cbAlpaca.isChecked()== true) {
                        cbAlpaca.setChecked(true);
                        detalleAlpaca = 800;
                        tvDetallesino.setText("Con detalles");
                        tvDetallesino.setText("Eliminar detalles añadidos");
                        tvDetallesino.setTextColor(Color.RED);
                        detalles = "Si";
                    }

                    if (cbAlpaca.isChecked()== false) {
                        detalleAlpaca = 0;
                        detalles = "No";
                    }

                    if (cbMadera.isChecked()==false && cbAlpaca.isChecked()==false && cbBronce.isChecked()==false){
                        tvDetallesino.setText("No se han añadido detalles");
                        tvDetallesino.setTextColor(Color.BLACK);
                    }
                    detallePrecio = detalleBronce + detalleAlpaca + detalleMadera;
                    tvCostosDetalles.setText(String.valueOf(detallePrecio));
                    tvPrecioProducto.setText(String.valueOf((detallePrecio + Integer.parseInt(precio))));
                });

                cbMadera.setOnClickListener(v2 -> {
                    if (cbMadera.isChecked()== true) {
                        cbMadera.setChecked(true);
                        detalleMadera = 800;
                        tvDetallesino.setText("Con detalles");
                        tvDetallesino.setText("Eliminar detalles añadidos");
                        tvDetallesino.setTextColor(Color.RED);
                        detalles = "Si";
                    }

                    if (cbMadera.isChecked()== false) {
                        detalleMadera = 0;
                        detalles = "No";
                    }

                    if (cbMadera.isChecked()==false && cbAlpaca.isChecked()==false && cbBronce.isChecked()==false){
                        tvDetallesino.setText("No se han añadido detalles");
                        tvDetallesino.setTextColor(Color.BLACK);
                    }

                    detallePrecio = detalleBronce + detalleAlpaca + detalleMadera;
                    tvCostosDetalles.setText(String.valueOf(detallePrecio));
                    tvPrecioProducto.setText(String.valueOf((detallePrecio + Integer.parseInt(precio))));
                });
        });

        ibMenos.setOnClickListener(v -> {

            ivDetalles.setVisibility(View.INVISIBLE);
            ibMenos.setVisibility(View.INVISIBLE);
            tvPrecioMadera.setVisibility(View.INVISIBLE);
            tvPrecioAlpaca.setVisibility(View.INVISIBLE);
            tvPrecioBronce.setVisibility(View.INVISIBLE);
            cbMadera.setVisibility(View.INVISIBLE);
            cbAlpaca.setVisibility(View.INVISIBLE);
            cbBronce.setVisibility(View.INVISIBLE);
            tvOpcionesDetalles.setVisibility(View.VISIBLE);
            tvOpcionesDetalles.setVisibility(View.INVISIBLE);
            ibAñadir.setVisibility(View.VISIBLE);
        });

        tvDetallesino.setOnClickListener(v -> {

            ivDetalles.setVisibility(View.INVISIBLE);
            ibMenos.setVisibility(View.INVISIBLE);
            tvPrecioMadera.setVisibility(View.INVISIBLE);
            tvPrecioAlpaca.setVisibility(View.INVISIBLE);
            tvPrecioBronce.setVisibility(View.INVISIBLE);
            cbMadera.setVisibility(View.INVISIBLE);
            cbAlpaca.setVisibility(View.INVISIBLE);
            cbBronce.setVisibility(View.INVISIBLE);
            tvOpcionesDetalles.setVisibility(View.VISIBLE);
            tvOpcionesDetalles.setVisibility(View.INVISIBLE);
            ibAñadir.setVisibility(View.VISIBLE);
            tvDetallesino.setText("No se han añadido detalles");
            tvDetallesino.setTextColor(Color.BLACK);
            detalles = "No";

            Intent pedido = getIntent();
            String precio = pedido.getStringExtra("precio");

            tvCostosDetalles.setText("0");
            cbBronce.setChecked(false);
            cbMadera.setChecked(false);
            cbAlpaca.setChecked(false);
            Toast.makeText(this,"Se han eliminado los detalles",Toast.LENGTH_LONG).show();
            tvPrecioProducto.setText(String.valueOf((Integer.parseInt(precio))));
        });
    }
}