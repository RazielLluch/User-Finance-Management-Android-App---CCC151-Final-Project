package com.example.ccc151finalproject.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ccc151finalproject.R;
import com.example.ccc151finalproject.database.MyAppDatabase;
import com.example.ccc151finalproject.database.dao.ExpenseDao;
import com.example.ccc151finalproject.database.models.ExpenseModel;

import java.util.List;

public class AnalyticsFragment extends Fragment {

    private final MyAppDatabase db = MyAppDatabase.getMyAppDatabase(getContext());
    private final ExpenseDao expenseDao = db.expenseDao();

    private String[] types = {"other", "food", "transportation", "entertainment", "shopping/grocery", "health/cosmetics", "education"};

    private void init(View view){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_analytics, container, false);
        init(view);
        return view;
    }


    protected int[] countTypesOfExpense(){
        List<ExpenseModel> allExpenses = expenseDao.getAllExpenses();

        int[] count = {0, 0, 0, 0, 0, 0, 0};

        for(ExpenseModel expense : allExpenses){
            switch (expense.getType().toLowerCase()){
                case "other" : count[0]++; break;
                case "food" : count[1]++; break;
                case "transportation" : count[2]++; break;
                case "entertainment" : count[3]++; break;
                case "shopping/grocery" : count[4]++; break;
                case "health/cosmetics" : count[5]++; break;
                case "education" : count[6]++; break;
            }
        }

        return count;
    }
}