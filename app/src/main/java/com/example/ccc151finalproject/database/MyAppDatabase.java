package com.example.ccc151finalproject.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.ccc151finalproject.database.dao.BudgetDao;
import com.example.ccc151finalproject.database.dao.ExpenseDao;
import com.example.ccc151finalproject.database.dao.SavingsDao;
import com.example.ccc151finalproject.database.dao.UserDao;
import com.example.ccc151finalproject.database.models.BudgetModel;
import com.example.ccc151finalproject.database.models.ExpenseModel;
import com.example.ccc151finalproject.database.models.SavingsModel;
import com.example.ccc151finalproject.database.models.UserModel;

@Database(entities = {UserModel.class, BudgetModel.class, ExpenseModel.class, SavingsModel.class}, version = 1)
public abstract class MyAppDatabase extends RoomDatabase {

    public static MyAppDatabase db;

    /**
     *      returns a UserDao interface implementation in the form of an object,
     *      assign this to a UserDao variable to call the basic database functions of its respective entity
     * @return UserDao object
     */
    public abstract UserDao userDao();
    /**
     *      returns a BudgetDao interface implementation in the form of an object,
     *      assign this to a UserDao variable to call the basic database functions of its respective entity
     * @return BudgetDao object
     */
    public abstract BudgetDao budgetDao();
    /**
     *      returns a ExpenseDao interface implementation in the form of an object,
     *      assign this to a UserDao variable to call the basic database functions of its respective entity
     * @return ExpenseDao object
     */
    public abstract ExpenseDao expenseDao();
    /**
     *      returns a SavingsDao interface implementation in the form of an object,
     *      assign this to a UserDao variable to call the basic database functions of its respective entity
     * @return SavingsDao object
     */
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
