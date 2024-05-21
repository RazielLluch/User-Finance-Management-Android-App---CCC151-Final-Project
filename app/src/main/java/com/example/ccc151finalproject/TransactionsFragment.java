package com.example.ccc151finalproject;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ccc151finalproject.models.TestData;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TransactionsFragment extends Fragment {

    private View view;
    private FloatingActionButton button;
    private void initViews(View view){
        button = view.findViewById(R.id.add_transaction_button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                addTransaction();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_transactions, container, false);

        initViews(view);

        return view;
    }

    public void addTransaction(){
        TestData.addMoney(100);
    }
}