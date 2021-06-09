package com.example.ctsmarket05.room.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.ctsmarket05.entities.Product;
import com.example.ctsmarket05.room.AppDatabase;
import com.example.ctsmarket05.room.dao.ProductoDAO;

import java.util.List;

public class ProductoRepository {

    //Declaro el dao de autos
    private ProductoDAO productoDAO;

    //Declaro el livedata de autos
    private LiveData<List<Product>> dataList;

    //constructor
    public ProductoRepository(Application application) {
        //Instancio la db
        AppDatabase database = AppDatabase.getDatabaseP(application.getApplicationContext());
        if (database != null) {
            //Instancio el dao de autos
            productoDAO = database.productoDAO();
            //Instancio el livedata
            dataList = productoDAO.getLiveData();
        }
    }

    //Getter de el livedata
    //  public LiveData<List<ClaseProducto>> get(){ return dataList; }

    //Funcion para insertar
    public void insertAuto(Product product) {
        //Ejecuto un hilo en segundo plano
        new InsertAutoAsyncTask(productoDAO).execute(product);
    }

    //Clase que extiende async task <PARAMETRO, Void, RETORNO>
    private static class InsertAutoAsyncTask extends AsyncTask<Product, Void, Void> {

        private final ProductoDAO productoDAO;

        //En el constructor de la clase le pido el DAO de autos
        InsertAutoAsyncTask(ProductoDAO productoDAO) {
            this.productoDAO = productoDAO;
        }

        @Override
        protected Void doInBackground(Product... products) {
            Long id = productoDAO.insert(products[0]);
            return null;
        }
    }
}
