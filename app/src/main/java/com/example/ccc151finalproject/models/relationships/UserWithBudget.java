package com.example.ccc151finalproject.models.relationships;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.ccc151finalproject.models.BudgetModel;
import com.example.ccc151finalproject.models.UserModel;

import java.util.List;

public class UserWithBudget {
    @Embedded
    UserModel userModel;
    @Relation(
            parentColumn = "id",
            entityColumn = "userId"
    )
    List<BudgetModel> budgetModel;

    @Override
    public String toString() {
        return "UserWithBudget{" +
                "userModel=" + userModel +
                ", budgetModel=" + budgetModel +
                '}';
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public List<BudgetModel> getBudgetModel() {
        return budgetModel;
    }

    public void setBudgetModel(List<BudgetModel> budgetModel) {
        this.budgetModel = budgetModel;
    }
}
