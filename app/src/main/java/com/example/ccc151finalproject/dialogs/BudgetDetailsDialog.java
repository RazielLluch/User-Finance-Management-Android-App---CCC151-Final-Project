package com.example.ccc151finalproject.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.ccc151finalproject.R;
import com.example.ccc151finalproject.database.MyAppDatabase;
import com.example.ccc151finalproject.database.dao.BudgetDao;
import com.example.ccc151finalproject.database.dao.ExpenseDao;
import com.example.ccc151finalproject.database.models.BudgetModel;
import com.example.ccc151finalproject.database.models.ExpenseModel;
import com.example.ccc151finalproject.views.TransactionView;

import java.util.List;

public class BudgetDetailsDialog extends Dialog {

    private final MyAppDatabase db = MyAppDatabase.getMyAppDatabase(getContext());
    private final LinearLayout transactionsLinearLayout;
    private final ExpenseDao expenseDao;
    private BudgetDao budgetDao;
    private final BudgetModel budgetModel;

    private final BudgetDetailsDialog INSTANCE;
    private void init(){
        setContentView(R.layout.budget_details_dialog);

        TextView budgetName = findViewById(R.id.budget_name_txt);
        TextView budgetTimeframe = findViewById(R.id.timeframe_txt);
        TextView startDate = findViewById(R.id.start_date_txt);
        TextView endDate = findViewById(R.id.end_date_txt);

        budgetName.setText(budgetModel.getBudgetName());
        budgetTimeframe.setText("budget timeframe: " + budgetModel.getTimeframe());
        startDate.setText("from: " + budgetModel.getStartDate());
        endDate.setText("to: " + budgetModel.getEndDate());
        LinearLayout transactionsLinearLayout2 = findViewById(R.id.transactions_linear_layout2);
        List<ExpenseModel> expensesOfThisBudget = expenseDao.getExpenseByBudget(budgetModel.getId());

        for(ExpenseModel expense : expensesOfThisBudget){

            TransactionView transactionView = new TransactionView(getContext(), expense);

            transactionsLinearLayout2.addView(transactionView);
        }


        Button closeButton = findViewById(R.id.close_button);
        Button deleteButton = findViewById(R.id.delete_button);

        closeButton.setOnClickListener(v -> INSTANCE.dismiss());

        deleteButton.setOnClickListener(v -> {
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
        });

    }

    public BudgetDetailsDialog(Context context, BudgetModel budgetModel, LinearLayout transactionsLinearLayout) {
        super(context);
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
