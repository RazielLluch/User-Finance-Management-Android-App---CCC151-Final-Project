package com.example.ccc151finalproject.models;

import java.io.CharArrayWriter;

public class BudgetModel {

    private int id;
    private String name;
    private String timeframe;
    private int amount;
    private String start_date, end_date;
    private int userId;

    public BudgetModel() {}

    public BudgetModel(String name, String timeframe, int amount, String start_date, String end_date, int userId) {
        this.name = name;
        this.timeframe = timeframe;
        this.amount = amount;
        this.start_date = start_date;
        this.end_date = end_date;
        this.userId = userId;
    }

    public BudgetModel(String name, String timeframe, int amount, String start_date, int userId) {
        this.name = name;
        this.timeframe = timeframe;
        this.amount = amount;
        this.start_date = start_date;
        this.userId = userId;
        end_date = findEnddateFromStartdate(start_date);
    }

    @Override
    public String toString() {
        return "BudgetModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", timeframe='" + timeframe + '\'' +
                ", amount=" + amount +
                ", start_date='" + start_date + '\'' +
                ", end_date='" + end_date + '\'' +
                '}';
    }

    private String findEnddateFromStartdate(String startDate){
        char[] startDateCharArray = startDate.toCharArray();

        int year = Integer.parseInt(startDate.substring(0,4));
        int month = Integer.parseInt(startDate.substring(5,7));
//        Integer.parseInt(String.valueOf(startDateCharArray[5]) + String.valueOf(startDateCharArray[6]));
        int day = Integer.parseInt(startDate.substring(8,10));

        if(month == 12){
            year += 1;
            month = 1;
        } else if(month == 2 && day > 28){
            month = 4;
            day = day - 28;
        } else if(month == 2 && day == 1){
            month = 3;
        }else if(day == 31){
            month += 1;
            day = 30;
        } else {
            month += 1;
        }

        String monthStr;
        String dayStr;
        if(month < 10) monthStr = "0" + month;
        else monthStr = String.valueOf(month);
        if(day < 10) dayStr = "0" + day;
        else dayStr = String.valueOf(day);

        return year + "-" + monthStr + "-" + dayStr;
    }




    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }
}
