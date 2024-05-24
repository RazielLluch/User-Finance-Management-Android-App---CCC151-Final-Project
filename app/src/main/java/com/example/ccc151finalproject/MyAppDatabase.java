package com.example.ccc151finalproject;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.ccc151finalproject.dao.BudgetDao;
import com.example.ccc151finalproject.dao.ExpenseDao;
import com.example.ccc151finalproject.dao.SavingsDao;
import com.example.ccc151finalproject.dao.UserDao;
import com.example.ccc151finalproject.models.BudgetModel;
import com.example.ccc151finalproject.models.ExpenseModel;
import com.example.ccc151finalproject.models.SavingsModel;
import com.example.ccc151finalproject.models.UserModel;

@Database(entities = {UserModel.class, BudgetModel.class, ExpenseModel.class, SavingsModel.class}, version = 1)
public abstract class MyAppDatabase extends RoomDatabase {

    public static MyAppDatabase db;

    public abstract UserDao userDao();
    public abstract BudgetDao budgetDao();
    public abstract ExpenseDao expenseDao();
    public abstract SavingsDao savingsDao();

    public static MyAppDatabase getMyAppDatabase(Context context){
        if(db == null){
            db = Room.databaseBuilder(context, MyAppDatabase.class, "finalproject")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return db;
    }
}
