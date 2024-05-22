package com.example.ccc151finalproject.models;

public class BudgetModel {

    private int id;
    private String name;
    private String timeframe;
    private int amount;
    private String start_date, end_date;

    public BudgetModel() {}

    public BudgetModel(String name, String timeframe, int amount, String start_date, String end_date) {
        this.name = name;
        this.timeframe = timeframe;
        this.amount = amount;
        this.start_date = start_date;
        this.end_date = end_date;
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
