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

    /**
     * BudgetModel constructor where every attribute is explicitly cited
     * @param budgetName string value of the budget's name
     * @param timeframe timeframe string value of either "monthly", "weekly", or "daily"
     * @param amount the amount of money that was budgeted for this budget
     * @param startDate the date of when a budget will start in the form of a string with the format of yyyy-mm-dd
     * @param endDate the date of when a budget is set to end in the form of a string with the format of yyyy-mm-dd
     * @param userId a foreign key of the Budget entity that references its owner, a user
     */
    @Ignore
    public BudgetModel(String budgetName, String timeframe, int amount, String startDate, String endDate, int userId) {
        this.budgetName = budgetName;
        this.timeframe = timeframe;
        this.amount = amount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.userId = userId;
    }

    /**
     * BudgetModel constructor that excludes the endDate, the endDate will be automatically determined based on the startDate and timeframe
     * @param budgetName string value of the budget's name
     * @param timeframe timeframe string value of either "monthly", "weekly", or "daily"
     * @param amount the amount of money that was budgeted for this budget
     * @param startDate the date of when a budget will start in the form of a string with the format of yyyy-mm-dd
     * @param userId a foreign key of the Budget entity that represents its owner, a user
     */
    public BudgetModel(String budgetName, String timeframe, int amount, String startDate, int userId) {
        this.budgetName = budgetName;
        this.timeframe = timeframe;
        this.amount = amount;
        this.startDate = startDate;
        setEndDateFromStartDate(timeframe.toLowerCase(), startDate);
        this.userId = userId;
    }

    /**
     * private method within the BudgetModel class that can determine the end date based on the timeframe and startDate
     * @param timeframe timeframe string value of either "monthly", "weekly", or "daily"
     * @param startDate the date in the form of a string with the format of yyyy-mm-dd
     */
    private void setEndDateFromStartDate(String timeframe, String startDate){
        switch (timeframe) {
            case "monthly" -> {
                try {
                    LocalDate date = LocalDate.parse(startDate);
                    LocalDate newDate = date.plusMonths(1);
                    endDate = newDate.toString();
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid date format: " + e.getMessage());
                }
            }
            case "weekly" -> {

                try {
                    LocalDate date = LocalDate.parse(startDate);
                    LocalDate newDate = date.plusDays(7);
                    endDate = newDate.toString();
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid date format: " + e.getMessage());
                }

            }
            case "daily" -> endDate = startDate;
        }
    }

    // Getters and Setters

    /**
     * getter method, used to retrieve the id(primary key) of a budget
     * @return autoincrement integer value of a budget's id
     */
    public int getId() {
        return id;
    }

    /**
     * setter method, used to set the id or primary key of a budget, this method should not be used lightly
     * @param id autoincrement integer value of a budget's id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * getter method, used to retrieve the name of a budget
     * @return budgetName string value of the budget's name
     */
    public String getBudgetName() {
        return budgetName;
    }

    /**
     * setter method, used to set the name of a budget
     * @param budgetName string value of the budget's name
     */
    public void setBudgetName(String budgetName) {
        this.budgetName = budgetName;
    }

    /**
     * getter method, used to get the duration in which a budget is active, i.e. monthly, weekly, daily
     * @return timeframe string value of either "monthly", "weekly", or "daily"
     */
    public String getTimeframe() {
        return timeframe;
    }

    /**
     * setter method, used to set the duration of which a budget is active, i.e. monthly, weekly, daily
     * @param timeframe string value of either "monthly", "weekly", or "daily"
     */
    public void setTimeframe(String timeframe) {
        this.timeframe = timeframe;
    }

    /**
     * getter method, used to get the maximum spending amount of a budget
     * @return the amount of money that was budgeted for this budget
     */
    public int getAmount() {
        return amount;
    }

    /**
     * setter method, used to set the maximum spending amount of a budget
     * @param amount the amount of money that will be budgeted for this budget
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * getter method, used to retrieve the date of when a budget is going to start
     * @return startDate, a date in the form of a string with the format of yyyy-mm-dd
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * setter method, used to set the date of when a budget is to start
     * @param startDate the date in the form of a string with the format of yyyy-mm-dd
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * getter method, used to retrieve the date of when a budget is set to end
     * @return endDate, a date in the form of a string with the format of yyyy-mm-dd
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * setter method, used to set when a budget is to end
     * @param endDate the date in the form of a string with the format of yyyy-mm-dd
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * getter method, used to retrieve the userId foreign key from a certain budget. In other words,
     * it is used to find the owner of a budget
     * @return userId, a foreign key of the Budget entity that represents its owner, a user
     */
    public int getUserId() {
        return userId;
    }

    /**
     * setter method, used to set the userId foreign key of a certain budget. In other words,
     * it is used to set the owner of a budget
     * @param userId is a foreign key of the Budget entity that represents its owner, a user
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }
}
