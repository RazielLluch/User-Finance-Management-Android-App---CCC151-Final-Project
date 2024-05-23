package com.example.ccc151finalproject.models;

public class TestData {

    private static double money = 0;

    public static double getMoney(){
        return money;
    }

    public static void addMoney(double money2){
        money += money2;
    }

}
