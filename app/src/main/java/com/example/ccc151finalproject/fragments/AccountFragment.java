package com.example.ccc151finalproject.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.example.ccc151finalproject.R;
import com.example.ccc151finalproject.database.MyAppDatabase;
import com.example.ccc151finalproject.database.dao.UserDao;
import com.example.ccc151finalproject.database.models.UserModel;

public class AccountFragment extends Fragment {

    private final MyAppDatabase db = MyAppDatabase.getMyAppDatabase(getContext());
    private final UserDao userDao = db.userDao();

    private void init(View view){
        TextView userName = view.findViewById(R.id.user_name);
        TextView accountName = view.findViewById(R.id.account_name);
        Button logoutButton = view.findViewById(R.id.logout_button);

        UserModel user = userDao.getUserById(1);
        String username = user.getFirstName() + " " + user.getMiddleName() + " " + user.getLastName();
        userName.setText(username);
        accountName.setText(user.getUsername());

        logoutButton.setOnClickListener(v -> logout());
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