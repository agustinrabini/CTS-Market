package com.example.ctsmarket05.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Product {
    @PrimaryKey
    private int id_product;

    private String name;

    private String blade;

    private String brand;

    private String description;

    private String image;

    private int price;

    private int length;

    private int stock;

    public static Integer ID_PRODUCT;

    public static String NAME;

    public static Integer PRICE;

    public static Integer QUANTITY;

    public static String IMAGE;

    public Product(int id_product, String name, String blade, String brand, String description, String image, int price, int length, int stock) {
        this.id_product = id_product;
        this.name = name;
        this.blade = blade;
        this.brand = brand;
        this.description = description;
        this.image = image;
        this.price = price;
        this.length = length;
        this.stock = stock;
    }

    public int getId_product() {
        return id_product;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBlade() { return blade; }

    public void setBlade(String blade) { this.blade = blade; }

    public String getBrand() { return brand; }

    public void setBrand(String brand) { this.brand = brand; }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getLength() { return length; }

    public void setLength(int length) {
        this.length = length;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
