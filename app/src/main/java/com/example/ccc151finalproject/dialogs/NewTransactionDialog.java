package com.example.ccc151finalproject.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.ccc151finalproject.R;
import com.example.ccc151finalproject.database.dao.ExpenseDao;
import com.example.ccc151finalproject.database.models.ExpenseModel;
import com.example.ccc151finalproject.views.TransactionView;

public class NewTransactionDialog extends Dialog {

    private Context context;
    private LinearLayout transactionsLinearLayout;
    private Button button;
    private ExpenseDao expenseDao;

    private void init(){
        setContentView(R.layout.new_transaction_dialog);
        button = findViewById(R.id.close_button);
        // Initialize views and setup listeners
        Button closeButton = this.findViewById(R.id.close_button);
        closeButton.setOnClickListener(v -> addNewTransaction());
    }

    public NewTransactionDialog(Context context, LinearLayout transactionsLinearLayout, ExpenseDao expenseDao) {
        super(context);
        this.transactionsLinearLayout = transactionsLinearLayout;
        this.expenseDao = expenseDao;
        init();
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void addNewTransaction(){

        //test sample expense
        ExpenseModel expenseModel = new ExpenseModel("kaon", "ni kaon ko", "food", "2024-05-25", 300, 1);

        expenseDao.insertExpense(expenseModel);

        TransactionView newTransaction = new TransactionView(context, expenseModel);

        transactionsLinearLayout.addView(newTransaction);
        this.dismiss();
    }
}
