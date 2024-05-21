package com.example.ccc151finalproject;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

public class BudgetFragment extends Fragment {

    private ProgressBar monthlyProgressBar, weeklyProgessBar, dailyProgressBar;
    private TextView monthlyProgressTxt, weeklyProgressTxt, dailyProgressTxt;

    private void initViews(View view){
        monthlyProgressBar = view.findViewById(R.id.monthly_budget_progress_bar);
        weeklyProgessBar = view.findViewById(R.id.weekly_budget_progress_bar);
        dailyProgressBar = view.findViewById(R.id.daily_budget_progress_bar);

        monthlyProgressTxt = view.findViewById(R.id.monthly_budget_progress_value);
        weeklyProgressTxt = view.findViewById(R.id.weekly_budget_progress_value);
        dailyProgressTxt = view.findViewById(R.id.daily_budget_progress_value);

        setMonthlyProgress(100);
        setWeeklyProgress(100);
        setDailyProgress(100);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_budget, container, false);

        // Initialize views using the inflated view
        initViews(view);

        return view;
    }

    public void setMonthlyProgress(double expense) {

        double max_spending = 3000;
        double progressRaw = max_spending - expense;
        double progress = max_spending / progressRaw;

        String text = expense + " / " + max_spending;

        if (monthlyProgressBar != null && monthlyProgressTxt != null) {
            monthlyProgressBar.setProgress((int)progress);
            monthlyProgressTxt.setText(text);
        }
    }

    public void setWeeklyProgress(double expense) {

        double max_spending = 750;
        double progressRaw = max_spending - expense;
        double progress = max_spending / progressRaw;

        String text = expense + " / " + max_spending;

        if (weeklyProgessBar != null && weeklyProgressTxt != null) {
            weeklyProgessBar.setProgress((int)progress);
            weeklyProgressTxt.setText(text);
        }
    }

    public void setDailyProgress(double expense) {

        double max_spending = 100;
        double progressRaw = max_spending - expense;
        double progress = max_spending / progressRaw;

        String text = expense + " / " + max_spending;

        if (dailyProgressBar != null && dailyProgressTxt != null) {
            dailyProgressBar.setProgress((int)progress);
            dailyProgressTxt.setText(text);
        }
    }
}
