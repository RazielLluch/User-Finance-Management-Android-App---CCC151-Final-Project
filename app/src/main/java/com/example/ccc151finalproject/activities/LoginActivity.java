package com.example.ccc151finalproject.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ccc151finalproject.R;
import com.example.ccc151finalproject.database.MyAppDatabase;
import com.example.ccc151finalproject.database.dao.UserDao;
import com.example.ccc151finalproject.database.models.UserModel;

public class LoginActivity extends AppCompatActivity {

    UserModel loggedInUser;
    private EditText getUsername;
    private EditText getPassword;
    private Button btnSignIn;
    private Button btnCreateAccount;
    private boolean successfulLogin = false;

    private void init(){
        getUsername = findViewById(R.id.getUsername);
        getPassword = findViewById(R.id.getPassword);
        btnSignIn = findViewById(R.id.btnSignIn);
        btnCreateAccount = findViewById(R.id.btnCreateAccount);

        btnSignIn.setOnClickListener(v -> {
            String username = getUsername.getText().toString();
            String password = getPassword.getText().toString();

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter both username and password", Toast.LENGTH_SHORT).show();
            } else {
                signIn(username, password);
            }
        });

        btnCreateAccount.setOnClickListener(v -> {
            Intent intent = new Intent(this, SignupActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void signIn(String username, String password){
        successfulLogin = false;
        MyAppDatabase db = MyAppDatabase.getMyAppDatabase(this);
        UserDao userDao = db.userDao();

        loggedInUser = userDao.signIn(username, password);

        if(loggedInUser != null){
            successfulLogin = true;
            saveUserData(loggedInUser);

            Intent startApp = new Intent(this, MainActivity.class);
            startApp.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(startApp);
            finish();
        } else {
            Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveUserData(UserModel user) {
        SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("user_id", user.getId());
        editor.putBoolean("is_logged_in", true);
        editor.apply();
    }
}
