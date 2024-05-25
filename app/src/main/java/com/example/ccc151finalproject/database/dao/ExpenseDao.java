package com.example.ccc151finalproject.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.ccc151finalproject.database.models.ExpenseModel;

import java.util.List;

/**
 *      Data Access Object that allows me to do basic database functionalities for the Expense entity
 */
@Dao
public interface ExpenseDao {
    @Query("SELECT * FROM Expense")
    List<ExpenseModel> getAllExpenses();

    @Query("SELECT * FROM Expense WHERE id = :expenseId")
    ExpenseModel getExpenseById(int expenseId);

    @Query("SELECT * FROM Expense WHERE budgetId = :budgetId")
    List<ExpenseModel> getExpenseByBudget(int budgetId);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertExpense(ExpenseModel expense);

    @Update
    void updateExpense(ExpenseModel expense);

    @Delete
    void deleteExpense(ExpenseModel expense);
}
