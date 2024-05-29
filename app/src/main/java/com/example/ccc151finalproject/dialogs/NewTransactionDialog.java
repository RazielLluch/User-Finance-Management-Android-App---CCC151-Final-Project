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
import java.util.List;
import com.example.ccc151finalproject.R;
import com.example.ccc151finalproject.database.MyAppDatabase;
import com.example.ccc151finalproject.database.dao.BudgetDao;
import com.example.ccc151finalproject.database.dao.ExpenseDao;
import com.example.ccc151finalproject.database.models.BudgetModel;
import com.example.ccc151finalproject.database.models.ExpenseModel;
import com.example.ccc151finalproject.views.TransactionView;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class NewTransactionDialog extends Dialog {

    private final Context context;
    private final LinearLayout transactionsLinearLayout;
    private final ExpenseDao expenseDao;
    private final BudgetDao budgetDao;
    private EditText expenseName, expenseDesc, expensePrice;
    private final String[] types = {"other", "food", "transportation", "entertainment", "shopping/grocery", "health/cosmetics", "education"};
    private String[] budgets;
    private String selectedType;
    private String selectedBudget;
    private int selectedBudgetIndex;

    ArrayAdapter<String> typeAdapter, budgetAdapter;

    private void init(){
        setContentView(R.layout.new_transaction_dialog);

        expenseName = findViewById(R.id.expense_name_field);
        expenseDesc = findViewById(R.id.expense_desc_field);
        Spinner typeDropdown = findViewById(R.id.type_dropdown);
        Spinner budgetDropdown = findViewById(R.id.budget_dropdown);
        expensePrice = findViewById(R.id.expense_price_field);

        // Set up the spinner with an adapter
        typeAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, types);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeDropdown.setAdapter(typeAdapter);

        List<BudgetModel> budgetModels = budgetDao.getAllBudgets();

        budgets = new String[budgetModels.size()];

        for(int i = 0; i < budgetModels.size(); i++){
            System.out.println(budgetModels.get(i).getBudgetName());

            budgets[i] = budgetModels.get(i).getBudgetName();
        }


        budgetAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, budgets);
        budgetAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        budgetDropdown.setAdapter(budgetAdapter);

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

        budgetDropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedBudget = budgets[position];
                selectedBudgetIndex = budgetModels.get(position).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Default to the first item if nothing is selected
                selectedBudget = budgets[0];
                selectedBudgetIndex = budgetModels.get(0).getId();
            }
        });

        Button closeButton = findViewById(R.id.close_button);
        Button addButton = findViewById(R.id.add_button);
        closeButton.setOnClickListener(v -> this.dismiss());
        addButton.setOnClickListener(v -> addNewTransaction());
    }

    public NewTransactionDialog(Context context, LinearLayout transactionsLinearLayout, MyAppDatabase db) {
        super(context);
        this.transactionsLinearLayout = transactionsLinearLayout;
        this.expenseDao = db.expenseDao();
        this.budgetDao = db.budgetDao();
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
        ExpenseModel expenseModel = new ExpenseModel(expenseName.getText().toString(), expenseDesc.getText().toString(), selectedType, formattedDate, Double.parseDouble(expensePrice.getText().toString()), selectedBudgetIndex);

        try{
            expenseDao.insertExpense(expenseModel);

            TransactionView newTransaction = new TransactionView(context, expenseModel);

            transactionsLinearLayout.addView(newTransaction);
        }catch(Exception ignored){

        }
        this.dismiss();
    }
}
