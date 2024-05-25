package com.example.ccc151finalproject.fragments;

import android.os.Bundle;
import android.app.Dialog;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

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
    private FloatingActionButton button;
    private LinearLayout transactionsLinearLayout;
    private ScrollView transactionsScrollView;
    private ConstraintLayout transactionConstraintLayout;

    private void initViews(View view){
        button = view.findViewById(R.id.add_transaction_button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openNewTransactionDialog(expenseDao);
            }
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

        ExpenseModel sampleExpense = new ExpenseModel("kaon", "ni kaon ko", "food", "2024-05-25", 300, 1);

//        if (getContext() != null)
//        {
//            Log.d(TAG, "Creating TransactionView with context: " + getContext());
//
//            TransactionView sampleTransactionView = new TransactionView(getContext(), sampleExpense);
//            sampleTransactionView.setId(View.generateViewId());
//            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
//                    LinearLayout.LayoutParams.MATCH_PARENT,
//                    LinearLayout.LayoutParams.WRAP_CONTENT
//            );
//            sampleTransactionView.setLayoutParams(layoutParams);
//
//            Log.d(TAG, "Adding TransactionView to transactionsLinearLayout");
//            transactionsLinearLayout.addView(sampleTransactionView);
//
//            // Verify if the TransactionView was added
//            int childCount = transactionsLinearLayout.getChildCount();
//            Log.d(TAG, "Number of child views in transactionsLinearLayout: " + childCount);
//        }

//         converting all the expenses in the database into a list of transaction views
         List<ExpenseModel> allExpenses = expenseDao.getAllExpenses();
         for(ExpenseModel expense : allExpenses){
             TransactionView newTransactionView = new TransactionView(getContext(), expense);
             newTransactionView.setId(View.generateViewId());
             transactionsLinearLayout.addView(newTransactionView);
         }

        return view;
    }

    private void openNewTransactionDialog(ExpenseDao expenseDao){
        Dialog dialog = new NewTransactionDialog(getContext(), transactionsLinearLayout, expenseDao);
        dialog.show();
    }
}
