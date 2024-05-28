package com.example.ccc151finalproject.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.example.ccc151finalproject.R;
import com.example.ccc151finalproject.activities.LoginActivity;
import com.example.ccc151finalproject.activities.MainActivity;
import com.example.ccc151finalproject.database.MyAppDatabase;
import com.example.ccc151finalproject.database.dao.BudgetDao;
import com.example.ccc151finalproject.database.dao.SavingsDao;
import com.example.ccc151finalproject.database.dao.UserDao;
import com.example.ccc151finalproject.database.models.BudgetModel;
import com.example.ccc151finalproject.database.models.ExpenseModel;
import com.example.ccc151finalproject.database.models.SavingsModel;
import com.example.ccc151finalproject.database.models.UserModel;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AccountFragment extends Fragment {

    private SharedPreferences sharedPreferences;
    private TextView savings;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        init(view);

        return view;
    }

    private void init(View view) {
        savings = view.findViewById(R.id.savings);
        TextView userName = view.findViewById(R.id.user_name);
        TextView accountName = view.findViewById(R.id.account_name);
        Button logoutButton = view.findViewById(R.id.logout_button);

        // Retrieve the logged-in user's data from SharedPreferences
        sharedPreferences = requireContext().getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        int userId = sharedPreferences.getInt("user_id", 0);

        MyAppDatabase db = MyAppDatabase.getMyAppDatabase(getContext());
        BudgetDao budgetDao = db.budgetDao();
        SavingsDao savingsDao = db.savingsDao();

        List<BudgetModel> budgetOfThisUser = budgetDao.getAllBudgetsOfUser(userId);

        List<SavingsModel> savingsOfThisUser = new ArrayList<SavingsModel>();

        for(BudgetModel budget : budgetOfThisUser){
            savingsOfThisUser.addAll(savingsDao.getAllSavingsOfBudget(budget.getId()));
        }

        int sum = 0;
        for(SavingsModel savings : savingsOfThisUser) {
            sum += savings.getAmount();
        }

        savings.setText("savings: " + sum);

        UserDao userDao = db.userDao();

        UserModel loggedInUser = userDao.getUserById(userId);

        // Set the userName TextView to display the user's full name
        userName.setText(loggedInUser.getUsername());

        String username = loggedInUser.getFirstName() + " " + loggedInUser.getMiddleName() + " " + loggedInUser.getLastName();

        // Set the accountName TextView to display the user's username
        accountName.setText(username);

        logoutButton.setOnClickListener(v -> logout());
    }

    //TODO continue making the login/register/logout features
    private void logout() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("is_logged_in", false);
        editor.apply();

        Intent backToLogin = new Intent(getActivity(), LoginActivity.class);
        backToLogin.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(backToLogin);
        getActivity().finish();

    }
}
