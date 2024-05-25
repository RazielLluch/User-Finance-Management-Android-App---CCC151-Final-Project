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
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

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

public class BudgetDetailsDialog extends Dialog {

    private MyAppDatabase db = MyAppDatabase.getMyAppDatabase(getContext());
    private LinearLayout transactionsLinearLayout;
    private Context context;
    private ExpenseDao expenseDao;
    private BudgetDao budgetDao;
    private BudgetModel budgetModel;
    private TextView budgetName, budgetTimeframe, startDate, endDate;
    private ScrollView transactionsScrollView;
    private Button closeButton, deleteButton;

    private BudgetDetailsDialog INSTANCE;
    private void init(){
        setContentView(R.layout.budget_details_dialog);

        budgetName = findViewById(R.id.budget_name_txt);
        budgetTimeframe = findViewById(R.id.timeframe_txt);
        startDate = findViewById(R.id.start_date_txt);
        endDate = findViewById(R.id.end_date_txt);

        transactionsScrollView = findViewById(R.id.transactions_scrollview);

        budgetName.setText(budgetModel.getBudgetName());
        budgetTimeframe.setText("budget timeframe: " + budgetModel.getTimeframe());
        startDate.setText("from: " + budgetModel.getStartDate());
        endDate.setText("to: " + budgetModel.getEndDate());

        List<ExpenseModel> expensesOfThisBudget = expenseDao.getExpenseByBudget(budgetModel.getId());

        for(ExpenseModel expense : expensesOfThisBudget){

            TransactionView transactionView = new TransactionView(getContext(), expense);

            transactionsScrollView.addView(transactionView);
        }

        closeButton = findViewById(R.id.close_button);
        deleteButton = findViewById(R.id.delete_button);

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                INSTANCE.dismiss();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                budgetDao = db.budgetDao();

                List<BudgetModel> budgets = budgetDao.getAllBudgets();

                int index = -1;
                for(int i = 0; i < budgets.size(); i++){
                    if(budgets.get(i).getId() == budgetModel.getId()){
                        index = i;
                        break;
                    }
                }

                if (index >= 0){
                    transactionsLinearLayout.removeView(transactionsLinearLayout.getChildAt(index+1));
                    budgetDao.deleteBudget(budgetModel);
                }

                INSTANCE.dismiss();
            }
        });

    }

    public BudgetDetailsDialog(Context context, BudgetModel budgetModel, LinearLayout transactionsLinearLayout) {
        super(context);
        this.context = context;
        this.budgetModel = budgetModel;
        this.transactionsLinearLayout = transactionsLinearLayout;

        INSTANCE = this;

        expenseDao = db.expenseDao();
        init();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

}
