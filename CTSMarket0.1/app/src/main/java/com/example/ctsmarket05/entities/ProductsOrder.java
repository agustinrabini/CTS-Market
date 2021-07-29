package com.example.ctsmarket05.entities;

import java.util.List;

public class ProductsOrder {

    public Integer id_products_order;

    public Integer id_product;

    public Integer id_order;

    public Integer id_user;

    public Integer quantity;

    public static Integer IDPRODUCTSORDER;

    public ProductsOrder(Integer id_product, Integer id_user, Integer quantity) {
        this.id_product = id_product;
        this.id_user = id_user;
        this.quantity = quantity;
    }


    public ProductsOrder(Integer id_order) {
        this.id_order = id_order;
    }

    public Integer getId_products_order() {
        return id_products_order;
    }

    public void setId_products_order(Integer id_products_order) {
        this.id_products_order = id_products_order;
    }

    public Integer getId_product() {
        return id_product;
    }

    public void setId_product(Integer id_product) {
        this.id_product = id_product;
    }

    public Integer getId_order() {
        return id_order;
    }

    public void setId_order(Integer id_order) {
        this.id_order = id_order;
    }

    public Integer getId_user() {
        return id_user;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
