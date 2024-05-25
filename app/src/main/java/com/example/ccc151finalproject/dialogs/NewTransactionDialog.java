package com.example.ccc151finalproject.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.example.ccc151finalproject.R;
import com.example.ccc151finalproject.database.dao.ExpenseDao;
import com.example.ccc151finalproject.database.models.ExpenseModel;
import com.example.ccc151finalproject.views.TransactionView;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class NewTransactionDialog extends Dialog {

    private Context context;
    private LinearLayout transactionsLinearLayout;
    private Button closeButton, addButton;
    private ExpenseDao expenseDao;
    private EditText expenseName, expenseDesc, expensePrice;
    private Spinner typeDropdown;
    private String[] types = {"other", "food", "transportation", "entertainment", "shopping/grocery", "health/cosmetics", "education"};
    private String selectedType;
    ArrayAdapter<String> adapter;

    private void init(){
        setContentView(R.layout.new_transaction_dialog);

        expenseName = findViewById(R.id.expense_name_field);
        expenseDesc = findViewById(R.id.expense_desc_field);
        typeDropdown = findViewById(R.id.type_dropdown);
        expensePrice = findViewById(R.id.expense_price_field);

        // Set up the spinner with an adapter
        adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, types);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeDropdown.setAdapter(adapter);

        typeDropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedType = types[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Default to the first item if nothing is selected
                selectedType = types[0];
            }
        });

        closeButton = findViewById(R.id.close_button);
        addButton = findViewById(R.id.add_button);
        closeButton.setOnClickListener(v -> this.dismiss());
        addButton.setOnClickListener(v -> addNewTransaction());
    }

    public NewTransactionDialog(Context context, LinearLayout transactionsLinearLayout, ExpenseDao expenseDao) {
        super(context);
        this.transactionsLinearLayout = transactionsLinearLayout;
        this.expenseDao = expenseDao;
        this.context = context;
        init();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void addNewTransaction(){

        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        calendar.set(year, month, day);

        // Format the date as year-month-dayOfMonth
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String formattedDate = sdf.format(calendar.getTime());

        //test sample expense
        ExpenseModel expenseModel = new ExpenseModel(expenseName.getText().toString(), expenseDesc.getText().toString(), "food", formattedDate, Double.parseDouble(expensePrice.getText().toString()), 1);

        try{
            expenseDao.insertExpense(expenseModel);

            TransactionView newTransaction = new TransactionView(context, expenseModel);

            transactionsLinearLayout.addView(newTransaction);
        }catch(Exception e){

        }
        this.dismiss();
    }
}
