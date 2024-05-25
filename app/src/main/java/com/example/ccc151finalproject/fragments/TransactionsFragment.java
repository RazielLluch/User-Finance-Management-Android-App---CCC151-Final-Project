package com.example.ccc151finalproject.fragments;

import android.os.Bundle;
import android.app.Dialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.ccc151finalproject.database.dao.BudgetDao;
import com.example.ccc151finalproject.dialogs.NewTransactionDialog;
import com.example.ccc151finalproject.R;
import com.example.ccc151finalproject.database.dao.ExpenseDao;
import com.example.ccc151finalproject.database.MyAppDatabase;
import com.example.ccc151finalproject.database.models.ExpenseModel;
import com.example.ccc151finalproject.views.TransactionView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;

public class TransactionsFragment extends Fragment {

    private static final String TAG = "TransactionsFragment";
    private MyAppDatabase db;
    private ExpenseDao expenseDao;
    private LinearLayout transactionsLinearLayout;
    private ScrollView transactionsScrollView;
    private ConstraintLayout transactionConstraintLayout;


    private void initViews(View view){
        FloatingActionButton button = view.findViewById(R.id.add_transaction_button);
        button.setOnClickListener(v -> {

            BudgetDao budgetDao = db.budgetDao();

            if(budgetDao.getNumberOfBudgets() != 0) openNewTransactionDialog(expenseDao);
            else Toast.makeText(getContext(), "You don't have any budgets!", Toast.LENGTH_SHORT).show();

        });

        transactionsLinearLayout = view.findViewById(R.id.transactions_linear_layout);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_transactions, container, false);
        db = MyAppDatabase.getMyAppDatabase(getContext()); // Initialize db here
        expenseDao = db.expenseDao();
        initViews(view);

        List<ExpenseModel> allExpenses = expenseDao.getAllExpenses();
        for(ExpenseModel expense : allExpenses){
            TransactionView newTransactionView = new TransactionView(getContext(), expense);
            newTransactionView.setId(View.generateViewId());
            transactionsLinearLayout.addView(newTransactionView);
        }

        return view;
    }

    private void openNewTransactionDialog(ExpenseDao expenseDao){
        Dialog dialog = new NewTransactionDialog(getContext(), transactionsLinearLayout, db);
        dialog.show();
    }
}
