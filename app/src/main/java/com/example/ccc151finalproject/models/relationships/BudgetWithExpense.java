package com.example.ccc151finalproject.models.relationships;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.ccc151finalproject.models.BudgetModel;
import com.example.ccc151finalproject.models.ExpenseModel;

import java.util.List;

public class BudgetWithExpense {
    @Embedded
    BudgetModel budgetModel;
    @Relation(
            parentColumn = "id",
            entityColumn = "budgetId"
    )
    List<ExpenseModel> expenseModel;

    @Override
    public String toString() {
        return "BudgetWithExpense{" +
                "budgetModel=" + budgetModel +
                ", expenseModel=" + expenseModel +
                '}';
    }

    public BudgetModel getBudgetModel() {
        return budgetModel;
    }

    public void setBudgetModel(BudgetModel budgetModel) {
        this.budgetModel = budgetModel;
    }

    public List<ExpenseModel> getExpenseModel() {
        return expenseModel;
    }

    public void setExpenseModel(List<ExpenseModel> expenseModel) {
        this.expenseModel = expenseModel;
    }
}
