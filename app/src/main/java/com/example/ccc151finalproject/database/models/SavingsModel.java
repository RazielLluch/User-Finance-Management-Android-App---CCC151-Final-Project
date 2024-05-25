package com.example.ccc151finalproject.database.models;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "Savings",
        foreignKeys = @ForeignKey(entity = BudgetModel.class,
                parentColumns = "id",
                childColumns = "budgetId",
                onDelete = ForeignKey.CASCADE,
                onUpdate = ForeignKey.CASCADE))
public class SavingsModel {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int amount;
    private String date;
    private int budgetId;

    // Constructor
    public SavingsModel(int amount, String date, int budgetId) {
        this.amount = amount;
        this.date = date;
        this.budgetId = budgetId;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(int budgetId) {
        this.budgetId = budgetId;
    }
}
