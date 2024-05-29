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

public class SignupActivity extends AppCompatActivity {

    private EditText getUsername;
    private EditText getFirstName;
    private EditText getMiddleName;
    private EditText getLastName;
    private EditText getPassword;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        getUsername = findViewById(R.id.getUsername);
        getFirstName = findViewById(R.id.getFirstName);
        getMiddleName = findViewById(R.id.getMiddleName);
        getLastName = findViewById(R.id.getLastName);
        getPassword = findViewById(R.id.getPassword);
        btnSubmit = findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(v -> {
            String username = getUsername.getText().toString();
            String firstName = getFirstName.getText().toString();
            String middleName = getMiddleName.getText().toString();
            String lastName = getLastName.getText().toString();
            String password = getPassword.getText().toString();

            if (firstName.isEmpty() || lastName.isEmpty()) {
                Toast.makeText(this, "Please fill out all required fields", Toast.LENGTH_SHORT).show();
            } else {
                UserModel signUpUser = new UserModel(firstName, middleName, lastName, username, password);
                signUp(signUpUser);
            }
        });
    }

    private void signUp(UserModel userSignup) {
        MyAppDatabase db = MyAppDatabase.getMyAppDatabase(this);
        UserDao userDao = db.userDao();

        long trySignUp = userDao.signUp(userSignup);
        if (trySignUp == -1) {
            Toast.makeText(this, "This user already exists", Toast.LENGTH_SHORT).show();
        } else {
            userSignup.setId((int) trySignUp); // Set the ID of the newly created user
            saveUserData(userSignup);

            Intent startApp = new Intent(this, MainActivity.class);
            startApp.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(startApp);
            finish();
        }
    }

    private void saveUserData(UserModel user) {
        SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("user_id", user.getId());
        editor.putString("username", user.getUsername());
        editor.putBoolean("is_logged_in", true);
        editor.apply();
    }
}
