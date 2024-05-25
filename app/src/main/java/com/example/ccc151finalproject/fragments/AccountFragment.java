package com.example.ccc151finalproject.fragments;

import android.app.Dialog;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.ccc151finalproject.R;
import com.example.ccc151finalproject.database.MyAppDatabase;
import com.example.ccc151finalproject.database.dao.BudgetDao;
import com.example.ccc151finalproject.database.dao.UserDao;
import com.example.ccc151finalproject.database.models.UserModel;

public class AccountFragment extends Fragment {

    private final MyAppDatabase db = MyAppDatabase.getMyAppDatabase(getContext());
    private final UserDao userDao = db.userDao();
    private TextView userName, accountName;
    private UserModel user;
    private Button logoutButton;
    private void init(View view){
        userName = view.findViewById(R.id.user_name);
        accountName = view.findViewById(R.id.account_name);
        logoutButton = view.findViewById(R.id.logout_button);

        user = userDao.getUserById(1);
        userName.setText(user.getFirstName() + " " + user.getMiddleName() + " " + user.getLastName());
        accountName.setText(user.getUsername());

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        init(view);

        return view;
    }

    //TODO continue making the login/register/logout features
    private void logout(){
        Dialog dialog = new Dialog(getContext());
    }
}