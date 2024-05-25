package com.example.ccc151finalproject.views;

import android.content.Context;
import android.graphics.Typeface;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;

import com.example.ccc151finalproject.R;
import com.example.ccc151finalproject.database.models.BudgetModel;
import com.example.ccc151finalproject.dialogs.BudgetDetailsDialog;

public class BudgetView extends LinearLayout {

    private final float scale = getResources().getDisplayMetrics().density;
    private final BudgetModel budgetModel;
    private TextView budgetName;
    private LinearLayout progressLayout;
    private ProgressBar progressBar;
    private TextView progressTxt;
    private ImageView moreIcon;
    private final LinearLayout transactionsLinearLayout;

    private void init(Context context) {
        budgetName = new TextView(context);
        progressLayout = new LinearLayout(context);
        progressBar = new ProgressBar(context, null, 0, androidx.appcompat.R.style.Widget_AppCompat_ProgressBar_Horizontal);
        progressTxt = new TextView(context);
        moreIcon = new ImageView(context);

        setOrientation(LinearLayout.VERTICAL);

        progressLayout.setOrientation(LinearLayout.HORIZONTAL);

        budgetName.setText(budgetModel.getBudgetName());
        initProgressBar();
        initProgressTxt();
        initIcon();

        addView(budgetName);
        progressLayout.addView(progressBar);
        progressLayout.addView(progressTxt);
        progressLayout.addView(moreIcon);

        addView(progressLayout);
    }
    public BudgetView(Context context, BudgetModel budgetModel, LinearLayout transactionsLinearLayout) {
        super(context);
        this.transactionsLinearLayout = transactionsLinearLayout;
        this.budgetModel = budgetModel;
        init(context);
        setupLayout(context);
    }
    private void viewBudgetDetails(){
        BudgetDetailsDialog budgetDetailsDialog = new BudgetDetailsDialog(getContext(), budgetModel, transactionsLinearLayout);

        budgetDetailsDialog.show();
    }
    private void setupLayout(Context context) {
        float scale = getResources().getDisplayMetrics().density;

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT
        );

        int topMarginInDp = 1;
        int leftMarginInDp = 10;
        int rightMarginInDp = 10;
        int bottomMarginInDp = 0;

        int leftMarginInPixels = (int) (leftMarginInDp * scale + 0.5f);
        int topMarginInPixels = (int) (topMarginInDp * scale + 0.5f);
        int rightMarginInPixels = (int) (rightMarginInDp * scale + 0.5f);
        int bottomMarginInPixels = (int) (bottomMarginInDp * scale + 0.5f);

        layoutParams.setMargins(leftMarginInPixels, topMarginInPixels, rightMarginInPixels, bottomMarginInPixels);

        progressLayout.setLayoutParams(layoutParams);
        budgetName.setLayoutParams(layoutParams);
        Typeface font = ResourcesCompat.getFont(context, R.font.inter_black);
        budgetName.setTypeface(font);
        budgetName.setText("hello");
    }
    private void initProgressBar(){
        LayoutParams progressBarParams = new LayoutParams(
                0,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                1f
        );
        progressBar.setLayoutParams(progressBarParams);
        progressBar.setMax(budgetModel.getAmount());
        progressBar.setProgress(0);
    }
    private void initProgressTxt(){
        int progressTxtWidth = (int)(70 * scale + 0.5f);

        LayoutParams progressTxtParams = new LayoutParams(
                progressTxtWidth,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        int margin = (int)(10 * scale + 0.5f);
        progressTxtParams.setMargins(margin, 0, margin, 0);
        progressTxt.setLayoutParams(progressTxtParams);
        progressTxt.setTextSize(10);
        progressTxt.setText("0.0 / " + budgetModel.getAmount());
    }
    private void initIcon(){
        moreIcon.setImageResource(R.drawable.more_info);

        int iconHeight = (int)(20 * scale + 0.5f);

        LinearLayout.LayoutParams moreIconParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                iconHeight
        );
        moreIcon.setLayoutParams(moreIconParams);

        moreIcon.setOnClickListener(v -> viewBudgetDetails());
    }
    public void setBudgetName(String name) {
        budgetName.setText(name);
    }
    public void setProgressTxt(String text){
        progressTxt.setText(text);
    }
    public void setProgress(int progress){
        progressBar.setProgress(progress);
    }
}
