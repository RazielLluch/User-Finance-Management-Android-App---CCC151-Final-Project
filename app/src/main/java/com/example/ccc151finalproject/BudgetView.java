package com.example.ccc151finalproject;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

public class BudgetView extends LinearLayout {

    private TextView budgetName;
    private LinearLayout progressLayout;
    private ProgressBar progressBar;
    private TextView progressTxt;

    private void init(Context context){
        budgetName = new TextView(context);
        progressLayout = new LinearLayout(context);
        progressBar = new ProgressBar(context);
        progressTxt = new TextView(context);
    }

    public BudgetView(Context context) {
        super(context);

        init(context);

        float scale = getResources().getDisplayMetrics().density;

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT
        );

        int topMarginInDp = 1; // Replace with your desired top margin in dp
        int leftMarginInDp = 10; // Replace with your desired left margin in dp
        int rightMarginInDp = 10; // Replace with your desired right margin in dp
        int bottomMarginInDp = 0; // Replace with your desired bottom margin in dp

        int leftMarginInPixels = (int) (leftMarginInDp * scale + 0.5f);
        int topMarginInPixels = (int) (topMarginInDp * scale + 0.5f);
        int rightMarginInPixels = (int) (rightMarginInDp * scale + 0.5f);
        int bottomMarginInPixels = (int) (bottomMarginInDp * scale + 0.5f);

        layoutParams.setMargins(leftMarginInPixels, topMarginInPixels, rightMarginInPixels, bottomMarginInPixels);

        budgetName.setLayoutParams(layoutParams);
        Typeface font = Typeface.createFromAsset(context.getAssets(), "@font/inter_bold");
        budgetName.setTypeface(font);
        budgetName.setText("hello");

    }

    //TODO create a method that sets the Budget Name
}
