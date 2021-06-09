package com.example.ctsmarket05.room.dao;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.ctsmarket05.entities.Product;

import java.util.List;

@Dao
public interface ProductoDAO {

    //Infiere segun el tipo de retorno y la anotacion//

    @Query("SELECT * FROM Product")
    LiveData<List<Product>> getLiveData();

    @Query("SELECT * FROM Product")
    List<Product> getAll();

    @Insert
    Long insert(Product product);

    @Update
    void update(Product product);

    @Delete
    void delete(Product product);

    @Query("DELETE FROM Product")
    void deleteAll();
}
