package com.example.ccc151finalproject.database.models;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

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
    @Ignore
    public BudgetModel(String budgetName, String timeframe, int amount, String startDate, String endDate, int userId) {
        this.budgetName = budgetName;
        this.timeframe = timeframe;
        this.amount = amount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.userId = userId;
    }

    public BudgetModel(String budgetName, String timeframe, int amount, String startDate, int userId) {
        this.budgetName = budgetName;
        this.timeframe = timeframe;
        this.amount = amount;
        this.startDate = startDate;
        setEndDateFromStartDate();
        this.userId = userId;
    }

    private void setEndDateFromStartDate(){
        char[] startDateCharArray = startDate.toCharArray();
        int year = Integer.parseInt(startDate.substring(0, 4));
        int month = Integer.parseInt(startDate.substring(5, 7));
        int day = Integer.parseInt(startDate.substring(8, 10));

        switch(timeframe){
            case "monthly" : {
                try {
                    LocalDate date = LocalDate.parse(startDate);
                    LocalDate newDate = date.plusMonths(1);
                    endDate = newDate.toString();
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid date format: " + e.getMessage());
                }
            }break;
            case "weekly" : {

                try {
                    LocalDate date = LocalDate.parse(startDate);
                    LocalDate newDate = date.plusDays(7);
                    endDate = newDate.toString();
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid date format: " + e.getMessage());
                }

            }break;
            case "daily" :{
                endDate = startDate;
            }break;
        }
        String monthStr;
        String dayStr;
        if (month < 10) monthStr = "0" + month;
        else monthStr = String.valueOf(month);
        if (day < 10) dayStr = "0" + day;
        else dayStr = String.valueOf(day);
        endDate = year + "-" + monthStr + "-" + dayStr;
    }

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
