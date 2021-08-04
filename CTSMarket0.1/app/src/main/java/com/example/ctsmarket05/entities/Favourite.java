package com.example.ctsmarket05.entities;

public class Favourite {

    private Integer id_favourite;

    private Integer id_product;

    private Integer id_user;

    private Integer state;

    public Favourite(Integer id_favourite, Integer id_product, Integer id_user, Integer state) {
        this.id_favourite = id_favourite;
        this.id_product = id_product;
        this.id_user = id_user;
        this.state = state;
    }

    public Favourite(Integer id_product, Integer id_user, Integer state) {
        this.id_product = id_product;
        this.id_user = id_user;
        this.state = state;
    }

    public Integer getId_favourite() {
        return id_favourite;
    }

    public void setId_favourite(Integer id_favourite) {
        this.id_favourite = id_favourite;
    }

    public Integer getId_product() {
        return id_product;
    }

    public void setId_product(Integer id_product) {
        this.id_product = id_product;
    }

    public Integer getId_user() {
        return id_user;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
