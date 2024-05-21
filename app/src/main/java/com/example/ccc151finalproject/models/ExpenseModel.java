package com.example.ccc151finalproject.models;

import java.util.ArrayList;
import java.util.List;

public class ExpenseModel {
    private int id;
    private String name;
    private String description;
    private String type;
    private int price;
    private String date;

    public ExpenseModel(){}

    public ExpenseModel(String name, String description, String type, int price, String date) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.price = price;
        this.date = date;
    }

    @Override
    public String toString() {
        return "ExpenseModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", date='" + date + '\'' +
                '}';
    }

//    public static ArrayList<ExpenseModel> getAllTransactions(){
//        ArrayList<ExpenseModel> list = new ArrayList<>();
//
//    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
