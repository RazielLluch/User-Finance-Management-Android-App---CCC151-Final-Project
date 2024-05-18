package com.example.ccc151finalproject;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.ccc151finalproject.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        replaceFragment(new AnalyticsFragment());
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            int itemId = item.getItemId();

            if(itemId == R.id.analytics){
                replaceFragment(new AnalyticsFragment());
            }else if(itemId == R.id.budget){
                replaceFragment(new BudgetFragment());
            }else if(itemId == R.id.transactions){
                replaceFragment(new TransactionsFragment());
            } else if(itemId == R.id.account) {
                replaceFragment(new AccountFragment());
            }
//            else replaceFragment(new AnalyticsFragment());

            return true;
        });

    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();
    }
}