package com.example.ccc151finalproject.fragments;

import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.ccc151finalproject.R;
import com.example.ccc151finalproject.database.MyAppDatabase;
import com.example.ccc151finalproject.database.dao.ExpenseDao;
import com.example.ccc151finalproject.database.models.ExpenseModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

public class AnalyticsFragment extends Fragment {
    private BarChart barGraph;
    private final MyAppDatabase db = MyAppDatabase.getMyAppDatabase(getContext());
    private final ExpenseDao expenseDao = db.expenseDao();

    private List<String> types = Arrays.asList("othr", "food", "trnspo", "entr", "shop/grcry", "hlth", "edu");

    private void init(View view){
        barGraph = view.findViewById(R.id.barchart);

        barGraph.getAxisRight().setDrawLabels(false);

        List<ExpenseModel> expenses = expenseDao.getAllExpenses();

        ArrayList<BarEntry> entries = new ArrayList<>();

        float[] values = new float[7];
        for(int i = 0; i < expenses.size(); i++){
            switch(expenses.get(i).getType().toLowerCase()){
                case "other": values[0] += expenses.get(i).getPrice(); break;
                case "food": values[1] += expenses.get(i).getPrice(); break;
                case "transportation": values[2] += expenses.get(i).getPrice(); break;
                case "entertainment": values[3] += expenses.get(i).getPrice(); break;
                case "shopping/grocery": values[4] += expenses.get(i).getPrice(); break;
                case "health/cosmetics": values[5] += expenses.get(i).getPrice(); break;
                case "education": values[6] += expenses.get(i).getPrice(); break;
            }
        }

        for (int i = 0; i < 7; i++){
            System.out.println("printing values " + values[i]);
            entries.add(new BarEntry(i, values[i]));
        }

        YAxis yAxis = barGraph.getAxisLeft();
        yAxis.setAxisMinimum(0f);
        yAxis.setAxisMaximum(1000f);
        yAxis.setAxisLineWidth(2f);
        yAxis.setAxisLineColor(Color.BLACK);
        yAxis.setLabelCount(10);

        BarDataSet dataSet = new BarDataSet(entries, "types");
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);

        BarData barData = new BarData(dataSet);
        barGraph.setData(barData);

        barGraph.getDescription().setEnabled(false);
        barGraph.invalidate();

        barGraph.getXAxis().setValueFormatter(new IndexAxisValueFormatter(types));
        barGraph.getXAxis().setPosition(XAxis.XAxisPosition.BOTH_SIDED);
        barGraph.getXAxis().setGranularity(1f);
        barGraph.getXAxis().setGranularityEnabled(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_analytics, container, false);
        init(view);
        return view;
    }

    protected int[] countTypesOfExpense(){
        List<ExpenseModel> allExpenses = expenseDao.getAllExpenses();

        int[] count = {0, 0, 0, 0, 0, 0, 0};

        for(ExpenseModel expense : allExpenses){
            switch (expense.getType().toLowerCase()){
                case "other" : count[0]++; break;
                case "food" : count[1]++; break;
                case "transportation" : count[2]++; break;
                case "entertainment" : count[3]++; break;
                case "shopping/grocery" : count[4]++; break;
                case "health/cosmetics" : count[5]++; break;
                case "education" : count[6]++; break;
            }
        }

        return count;
    }
}