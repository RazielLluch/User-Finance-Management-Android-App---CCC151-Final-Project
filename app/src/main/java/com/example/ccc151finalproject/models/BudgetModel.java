package com.example.ccc151finalproject.models;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "Budget",
        foreignKeys = @ForeignKey(entity = UserModel.class,
                parentColumns = "id",
                childColumns = "userId",
                onDelete = ForeignKey.CASCADE,
                onUpdate = ForeignKey.CASCADE))
public class BudgetModel {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String budgetName;
    private String timeframe;
    private int amount;
    private String startDate;
    private String endDate;
    private int userId;

    // Constructor
    public BudgetModel(String budgetName, String timeframe, int amount, String startDate, String endDate, int userId) {
        this.budgetName = budgetName;
        this.timeframe = timeframe;
        this.amount = amount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.userId = userId;
    }

//    public BudgetModel(String budgetName, String timeframe, int amount, String startDate, int userId) {
//        this.budgetName = budgetName;
//        this.timeframe = timeframe;
//        this.amount = amount;
//        this.startDate = startDate;
//        this.endDate = endDate;
//        this.userId = userId;
//    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBudgetName() {
        return budgetName;
    }

    public void setBudgetName(String budgetName) {
        this.budgetName = budgetName;
    }

    public String getTimeframe() {
        return timeframe;
    }

    public void setTimeframe(String timeframe) {
        this.timeframe = timeframe;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
