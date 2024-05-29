package com.example.ccc151finalproject.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.example.ccc151finalproject.R;
import com.example.ccc151finalproject.database.MyAppDatabase;
import com.example.ccc151finalproject.database.dao.SavingsDao;
import com.example.ccc151finalproject.database.models.BudgetModel;
import com.example.ccc151finalproject.database.models.SavingsModel;

public class NewSavingDialog extends Dialog {

    private final BudgetModel budgetModel;
    private final NewSavingDialog INSTANCE;

    private EditText savingsValue;
    private Button closeButton, addSavingsBUtton;

    private void init(){
        setContentView(R.layout.new_savings_dialog);

        savingsValue = this.findViewById(R.id.getSavingsField);
        closeButton = this.findViewById(R.id.close_button);
        addSavingsBUtton = this.findViewById(R.id.add_saving_button);

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                INSTANCE.dismiss();
            }
        });

        addSavingsBUtton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyAppDatabase db = MyAppDatabase.getMyAppDatabase(getContext());
                SavingsDao savingsDao = db.savingsDao();




                SavingsModel savingsModel = new SavingsModel(Integer.parseInt(savingsValue.getText().toString()), budgetModel.getEndDate(), budgetModel.getId());

                savingsDao.insertSavings(savingsModel);

                INSTANCE.dismiss();
            }
        });

    }
    public NewSavingDialog(@NonNull Context context, BudgetModel budgetModel) {
        super(context);
        this.budgetModel = budgetModel;
        INSTANCE = this;
        init();
    }
}
