package com.example.ccc151finalproject.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.ccc151finalproject.models.BudgetModel;

import java.util.List;

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
