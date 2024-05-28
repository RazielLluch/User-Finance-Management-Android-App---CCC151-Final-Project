package com.example.ccc151finalproject.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.example.ccc151finalproject.R;
import com.example.ccc151finalproject.activities.LoginActivity;
import com.example.ccc151finalproject.activities.MainActivity;
import com.example.ccc151finalproject.fragments.AccountFragment;

public class ConfirmLogoutDialog extends Dialog {

    FragmentActivity accountActivity;
    ConfirmLogoutDialog INSTANCE;
    private LinearLayout layout;
    private Button noButton, yesButton;
    private void init(){
        layout = findViewById(R.id.confirm_logout_layout);
        noButton = findViewById(R.id.no_button);
        yesButton = findViewById(R.id.yes_button);

        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                INSTANCE.dismiss();
            }
        });

        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logOut();
            }
        });

    }

    public ConfirmLogoutDialog(@NonNull Context context, FragmentActivity mainActivity) {
        super(context);
        INSTANCE = this;
        setContentView(R.layout.confirm_logout_dialog);
        this.accountActivity = mainActivity;

        init();
    }

    private void logOut(){
        Intent backToLogin = new Intent(INSTANCE.getContext(), LoginActivity.class);
        backToLogin.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        accountActivity.startActivity(backToLogin);
        accountActivity.finish();
    }
}
