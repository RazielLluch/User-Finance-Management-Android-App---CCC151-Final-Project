package com.example.ccc151finalproject.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ccc151finalproject.R;
import com.example.ccc151finalproject.database.models.ExpenseModel;

public class TransactionView extends LinearLayout {

    private TextView transactionName;
    private TextView expenseDate;
    private TextView expenseText;
    ExpenseModel expenseModel;

    public TransactionView(Context context, ExpenseModel expenseModel) {
        super(context);
        this.expenseModel = expenseModel;
        init(context);
    }

    public TransactionView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TransactionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        // Inflate the XML layout
        LayoutInflater.from(context).inflate(R.layout.transaction_view, this, true);

        // Get references to the child views
        transactionName = findViewById(R.id.transaction_name);
        expenseDate = findViewById(R.id.expense_date);
        expenseText = findViewById(R.id.expense_text);

        transactionName.setText(expenseModel.getName());
        expenseDate.setText(expenseModel.getDate());
        expenseText.setText(String.valueOf(expenseModel.getPrice()));
    }

    // Add setters for the text views to customize the view
    public void setTransactionName(String name) {
        transactionName.setText(name);
    }

    public void setExpenseDate(String date) {
        expenseDate.setText(date);
    }

    public void setExpenseText(String text) {
        expenseText.setText(text);
    }
}
