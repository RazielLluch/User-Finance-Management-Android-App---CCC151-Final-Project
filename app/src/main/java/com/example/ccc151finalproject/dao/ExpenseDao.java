package com.example.ccc151finalproject.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.ccc151finalproject.models.ExpenseModel;

import java.util.List;

@Dao
public interface ExpenseDao {
    @Query("SELECT * FROM Expense")
    List<ExpenseModel> getAllExpenses();

    @Query("SELECT * FROM Expense WHERE id = :expenseId")
    ExpenseModel getExpenseById(int expenseId);

    @Insert
    void insertExpense(ExpenseModel expense);

    @Update
    void updateExpense(ExpenseModel expense);

    @Delete
    void deleteExpense(ExpenseModel expense);
}
