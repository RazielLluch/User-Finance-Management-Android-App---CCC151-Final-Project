package com.example.ccc151finalproject.models.relationships;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.ccc151finalproject.models.BudgetModel;
import com.example.ccc151finalproject.models.SavingsModel;

public class BudgetAndSavings {
    @Embedded
    BudgetModel budgetModel;
    @Relation(
            parentColumn = "id",
            entityColumn = "budgetId"
    )
    SavingsModel savingsModel;

    @Override
    public String toString() {
        return "BudgetAndSavings{" +
                "budgetModel=" + budgetModel +
                ", savingsModel=" + savingsModel +
                '}';
    }

    public BudgetModel getBudgetModel() {
        return budgetModel;
    }

    public void setBudgetModel(BudgetModel budgetModel) {
        this.budgetModel = budgetModel;
    }

    public SavingsModel getSavingsModel() {
        return savingsModel;
    }

    public void setSavingsModel(SavingsModel savingsModel) {
        this.savingsModel = savingsModel;
    }
}
