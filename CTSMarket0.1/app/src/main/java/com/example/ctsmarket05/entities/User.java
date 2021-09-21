package com.example.ctsmarket05.entities;

import android.net.Uri;

public class User {

    public Integer id_user;

    public Integer id_location;

    private String name_lastname ;

    private String email;

    private Integer dni;

    private Integer phone;

    public static String URL = "https://c359-2800-2141-5400-373-c102-5950-928c-77f4.ngrok.io";

    public static String gmail = "" ;

    public static String PHOTO;

    public static Integer IDUSER;

    public User(Integer id_location, String name_lastname, String email, Integer dni, Integer phone) {
        this.id_location = id_location;
        this.name_lastname = name_lastname;
        this.email = email;
        this.dni = dni;
        this.phone= phone;
    }

    public User(Integer id_location) {
        this.id_location = id_location;
    }

    public User(String name_lastname, Integer dni, Integer phone) {
        this.name_lastname = name_lastname;
        this.dni = dni;
        this.phone = phone;
    }

    public Integer getId_user() {
        return id_user;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    public Integer getId_location() {
        return id_location;
    }

    public void setId_location(Integer id_location) {
        this.id_location = id_location;
    }

    public String getName_lastname() {
        return name_lastname;
    }

    public void setName_lastname(String name_lastname) {
        this.name_lastname = name_lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getDni() {
        return dni;
    }

    public void setDni(Integer dni) {
        this.dni = dni;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }
}
