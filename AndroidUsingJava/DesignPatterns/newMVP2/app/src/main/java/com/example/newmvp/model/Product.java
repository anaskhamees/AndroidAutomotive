package com.example.newmvp.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "products_table")
public class Product {

    @PrimaryKey
    @NonNull
    private String title;
    private String description;
    private String brand;
    private double price;
    @SerializedName("rating")
    private double rate;
    @SerializedName("thumbnail")
    private String imgUrl ;

    public Product(String title, String description, String brand, double price, double rate, String imgUrl) {
        this.title = title;
        this.description = description;
        this.brand = brand;
        this.price = price;
        this.rate = rate;
        this.imgUrl = imgUrl;
    }

    public String getImgUrl(){
        return imgUrl;
    }
    public String getTitle(){
        return title;
    }
    public String getDescription(){
        return description;
    }
    public String getBrand(){
        return brand;
    }
    public double getPrice(){
        return price;
    }
    public double getRate(){
        return rate;
    }
}
