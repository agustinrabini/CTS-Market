package com.example.ctsmarket05.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.ctsmarket05.entities.User;

import java.util.List;

@Dao
public interface UsuariosDAO {

    //Infiere segun el tipo de retorno y la anotacion//

    @Query("SELECT * FROM Usuario")
    LiveData<List<User>> getLiveData();

    @Query("SELECT * FROM Usuario")
    List<User> getAll();

    @Insert
    Long insert(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);

    @Query("DELETE FROM Usuario")
    void deleteAll();

}

