package com.example.ccc151finalproject.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import com.example.ccc151finalproject.database.models.ExpenseModel;
import java.util.List;

@Dao
public interface ExpenseDao {
    @Query("SELECT * FROM Expense")
    List<ExpenseModel> getAllExpenses();

    @Query("SELECT * FROM Expense WHERE Expense.budgetId = :budgetId")
    List<ExpenseModel> getAllExpensesOfBudget(int budgetId);

    @Query("SELECT Expense.* FROM Expense JOIN Budget ON Expense.budgetId = Budget.id JOIN User ON Budget.userId = User.id WHERE User.id = :userId")
    List<ExpenseModel> getAllExpensesOfUser(int userId);

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
