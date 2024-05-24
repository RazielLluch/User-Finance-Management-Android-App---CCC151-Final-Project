package com.example.ccc151finalproject.models;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.example.ccc151finalproject.models.BudgetModel;

@Entity(tableName = "Expense",
        foreignKeys = @ForeignKey(entity = BudgetModel.class,
                parentColumns = "id",
                childColumns = "budgetId",
                onDelete = ForeignKey.CASCADE,
                onUpdate = ForeignKey.CASCADE))
public class ExpenseModel {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String description;
    private String type;
    private String date;
    private double price;
    private int budgetId;

    // Constructor
    public ExpenseModel(String name, String description, String type, String date, double price, int budgetId) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.date = date;
        this.price = price;
        this.budgetId = budgetId;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(int budgetId) {
        this.budgetId = budgetId;
    }
}
