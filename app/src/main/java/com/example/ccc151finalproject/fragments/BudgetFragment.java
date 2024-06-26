package com.example.ccc151finalproject.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.ccc151finalproject.R;
import com.example.ccc151finalproject.database.MyAppDatabase;
import com.example.ccc151finalproject.database.dao.BudgetDao;
import com.example.ccc151finalproject.database.dao.ExpenseDao;
import com.example.ccc151finalproject.database.models.BudgetModel;
import com.example.ccc151finalproject.database.models.ExpenseModel;
import com.example.ccc151finalproject.views.BudgetView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class BudgetFragment extends Fragment implements AdapterView.OnItemSelectedListener{
    private LinearLayout transactionsLinearLayout;
    private Dialog dialog;
    private String timeFrame;
    private final MyAppDatabase db = MyAppDatabase.getMyAppDatabase(getContext());
    private final BudgetDao budgetDao = db.budgetDao();

    private void initViews(View view){

        transactionsLinearLayout = view.findViewById(R.id.transactions_linear_layout);
        FloatingActionButton button = view.findViewById(R.id.add_transaction_button);

        button.setOnClickListener(v -> addBudget());

        getDataFromDatabase();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_budget, container, false);

        // Initialize views using the inflated view
        initViews(view);
        //retrieve all budgets

        return view;
    }

    private void getDataFromDatabase(){

        SharedPreferences sharedPreferences;
        sharedPreferences = requireContext().getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        int userId = sharedPreferences.getInt("user_id", 0);

        List<BudgetModel> budgets = budgetDao.getAllBudgetsOfUser(userId);

        //add all budgets into the list view of budgets
        for(BudgetModel budget : budgets){

            ExpenseDao expenseDao = db.expenseDao();

            List<ExpenseModel> expensesOfThisBudget = expenseDao.getExpenseByBudget(budget.getId());

            double sum = 0;
            for(ExpenseModel expense : expensesOfThisBudget){
                sum += expense.getPrice();
            }

            BudgetView newBudgetView = new BudgetView(getContext(), budget, transactionsLinearLayout);
            newBudgetView.setId(View.generateViewId());
            newBudgetView.setBudgetName(budget.getBudgetName());
            newBudgetView.setProgress((int)sum);
            newBudgetView.setProgressTxt(sum + " / " + budget.getAmount());


            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            int leftMarginInDp = 10;
            int topMarginInDp = 10;
            int rightMarginInDp = 10;
            int bottomMarginInDp = 0;

            float scale = getResources().getDisplayMetrics().density;
            int leftMarginInPixels = (int) (leftMarginInDp * scale + 0.5f);
            int topMarginInPixels = (int) (topMarginInDp * scale + 0.5f);
            int rightMarginInPixels = (int) (rightMarginInDp * scale + 0.5f);
            int bottomMarginInPixels = (int) (bottomMarginInDp * scale + 0.5f);

            layoutParams.setMargins(leftMarginInPixels, topMarginInPixels, rightMarginInPixels, bottomMarginInPixels);

            newBudgetView.setLayoutParams(layoutParams);

            transactionsLinearLayout.addView(newBudgetView);
        }
    }

    /**
     *      Opens a new activity that will allows the user to customize and add their own new budget
     */

    public void addBudget() {
        Toast.makeText(getActivity(), "Creating new budget", Toast.LENGTH_SHORT).show();
        // Create a new Dialog instance
        dialog = new Dialog(getContext());

        // Set the custom layout for the Dialog
        dialog.setContentView(R.layout.new_budget_dialog);

        // Initialize the EditText and Spinner variables by finding them in the Dialog layout
        EditText newBudgetName = dialog.findViewById(R.id.budget_name_input);

        Spinner timeframeDropdown = dialog.findViewById(R.id.timeframe_dropdown);
        String[] items = new String[]{"Monthly", "Weekly", "Daily"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, items);
        timeframeDropdown.setAdapter(adapter);
//        timeframeDropdown.setBackgroundResource(R.drawable.dropdown_background);
        timeframeDropdown.setOnItemSelectedListener(this);


        EditText maxSpendingAmount = dialog.findViewById(R.id.max_spending_amount);
        Button startDate = dialog.findViewById(R.id.start_date_btn);
        TextView addNewBudget = dialog.findViewById(R.id.add_new_budget);

        startDate.setOnClickListener(v -> createCalendarDialog(startDate));

        addNewBudget.setOnClickListener(v -> {
            try {
                System.out.println("adding your new budget");

                BudgetModel newBudget = new BudgetModel(
                        newBudgetName.getText().toString(),
                        timeFrame,
                        Integer.parseInt(maxSpendingAmount.getText().toString()),
                        startDate.getText().toString(),
                        1
                );

                budgetDao.insertBudget(newBudget);


//                    Toast.makeText(getContext(), newBudget.toString(), Toast.LENGTH_SHORT).show();
                System.out.println(newBudget);

                BudgetView newBudgetView = new BudgetView(getContext(), newBudget, transactionsLinearLayout);
                newBudgetView.setId(View.generateViewId());
                newBudgetView.setBudgetName(newBudgetName.getText().toString());

                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );

                int leftMarginInDp = 10;
                int topMarginInDp = 10;
                int rightMarginInDp = 10;
                int bottomMarginInDp = 0;

                float scale = getResources().getDisplayMetrics().density;
                int leftMarginInPixels = (int) (leftMarginInDp * scale + 0.5f);
                int topMarginInPixels = (int) (topMarginInDp * scale + 0.5f);
                int rightMarginInPixels = (int) (rightMarginInDp * scale + 0.5f);
                int bottomMarginInPixels = (int) (bottomMarginInDp * scale + 0.5f);

                layoutParams.setMargins(leftMarginInPixels, topMarginInPixels, rightMarginInPixels, bottomMarginInPixels);

                newBudgetView.setLayoutParams(layoutParams); // Apply layout parameters to newBudgetView
                newBudgetView.setProgressTxt("0.0 / " + maxSpendingAmount.getText().toString());
                transactionsLinearLayout.addView(newBudgetView);

                dialog.dismiss();
            } catch (Exception e) {
                System.out.println("Add new budget debug");
                e.printStackTrace();
                Toast.makeText(getContext(), "Don't leave the info blank!", Toast.LENGTH_SHORT).show();
            }
        });

        // Show the Dialog
        dialog.show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id){
        String[] items = new String[]{"Monthly", "Weekly", "Daily"};
        timeFrame = items[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void createCalendarDialog(TextView date) {
        // Get the current date
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Create the DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getContext(),
                (view, year1, month1, dayOfMonth) -> {
                    // Update the TextView with the selected date
                    Calendar selectedDate = Calendar.getInstance();
                    selectedDate.set(year1, month1, dayOfMonth);

                    // Format the date as year-month-dayOfMonth
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                    String formattedDate = sdf.format(selectedDate.getTime());

                    date.setText(formattedDate);
                },
                year, month, day
        );

        datePickerDialog.show();
    }
}
