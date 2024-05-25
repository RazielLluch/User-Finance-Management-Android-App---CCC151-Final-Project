package com.example.ccc151finalproject.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.ccc151finalproject.database.models.BudgetModel;

import java.util.List;

/**
 *      Data Access Object that allows me to do basic database functionalities for the Budget entity
 */
@Dao
public interface BudgetDao {
    @Query("SELECT * FROM Budget")
    List<BudgetModel> getAllBudgets();

    @Query("SELECT * FROM Budget WHERE id = :budgetId")
    BudgetModel getBudgetById(int budgetId);

    @Query("SELECT * FROM Budget WHERE Budget.userId = :userId")
    List<BudgetModel> getAllBudgetsOfUser(int userId);

    @Insert
    void insertBudget(BudgetModel budget);

    @Update
    void updateBudget(BudgetModel budget);

    @Delete
    void deleteBudget(BudgetModel budget);
}
