package com.example.ccc151finalproject.models;

public class SavingsModel {
    private int amount;
    private String date;

    public SavingsModel(){}

    public SavingsModel(int amount, String date) {
        this.amount = amount;
        this.date = date;
    }

    @Override
    public String toString() {
        return "SavingsModel{" +
                "amount=" + amount +
                ", date='" + date + '\'' +
                '}';
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
}
