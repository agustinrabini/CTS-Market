package com.example.ctsmarket05.entities;

import com.google.gson.JsonArray;

import java.util.Date;

public class Orders {

    private Integer id_order;
    private Integer id_user;
    private Integer order_price;
    private Integer quantity_products;
    private Integer order_state;
    private Integer shipping;
    private JsonArray cart;
    private Date date;


    public Orders(Integer id_user, Integer order_price, Integer quantity_products, Integer order_state, Integer shipping, JsonArray cart, Date date) {
        this.id_user = id_user;
        this.order_price = order_price;
        this.quantity_products = quantity_products;
        this.order_state = order_state;
        this.shipping = shipping;
        this.cart = cart;
        this.date = date;
    }

    public Integer getId_user() {
        return id_user;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    public Integer getOrder_price() {
        return order_price;
    }

    public void setOrder_price(Integer order_price) {
        this.order_price = order_price;
    }

    public Integer getQuantity_products() {
        return quantity_products;
    }

    public void setQuantity_products(Integer quantity_products) {
        this.quantity_products = quantity_products;
    }

    public Integer getOrder_state() {
        return order_state;
    }

    public void setOrder_state(Integer order_state) {
        this.order_state = order_state;
    }

    public Integer getShipping() {
        return shipping;
    }

    public void setShipping(Integer shipping) {
        this.shipping = shipping;
    }

    public JsonArray getCart() {
        return cart;
    }

    public void setCart(JsonArray cart) {
        this.cart = cart;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
