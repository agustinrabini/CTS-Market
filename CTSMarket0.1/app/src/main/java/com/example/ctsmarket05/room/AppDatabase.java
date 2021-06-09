package com.example.ctsmarket05.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.ctsmarket05.entities.Product;
import com.example.ctsmarket05.room.dao.ProductoDAO;

@Database(entities = {Product.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    //DECLARACION DE LOS DAO
    public abstract ProductoDAO productoDAO();

    //CREACION DE LA BASE
    private static AppDatabase db = null;

    //Traigo la base de datos
    public static AppDatabase getDatabaseP(Context context) {
        if (db == null){
            db = Room.databaseBuilder(context, AppDatabase.class, "room_database").fallbackToDestructiveMigration().build();
        }
        return db;
    }
}