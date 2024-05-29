package com.example.ccc151finalproject.activities;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.ccc151finalproject.R;
import com.example.ccc151finalproject.database.dao.UserDao;
import com.example.ccc151finalproject.database.MyAppDatabase;
import com.example.ccc151finalproject.databinding.ActivityMainBinding;
import com.example.ccc151finalproject.database.models.UserModel;
import com.example.ccc151finalproject.fragments.AccountFragment;
import com.example.ccc151finalproject.fragments.AnalyticsFragment;
import com.example.ccc151finalproject.fragments.BudgetFragment;
import com.example.ccc151finalproject.fragments.TransactionsFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        com.example.ccc151finalproject.databinding.ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        MyAppDatabase db = MyAppDatabase.getMyAppDatabase(this);

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

    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();
    }
}