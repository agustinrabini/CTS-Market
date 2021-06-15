package com.example.ctsmarket05.entities;

public class Cart {

    public Integer quantity;

    public Integer id_product;

    public Cart(Integer quantity, Integer id_product) {
        this.quantity = quantity;
        this.id_product = id_product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getId_product() {
        return id_product;
    }

    public void setId_product(Integer id_product) {
        this.id_product = id_product;
    }
}
