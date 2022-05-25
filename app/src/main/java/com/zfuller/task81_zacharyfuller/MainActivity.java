package com.zfuller.task81_zacharyfuller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zfuller.task81_zacharyfuller.data.UserDatabaseHelper;

public class MainActivity extends AppCompatActivity {
    EditText editTextUsername, editTextPassword;
    Button btnLogin, btnSignup;
    UserDatabaseHelper userDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUsername = (findViewById(R.id.editTextUsername));
        editTextPassword = (findViewById(R.id.editTextPassword));
        btnLogin = (findViewById(R.id.btnLogin));
        btnSignup = (findViewById(R.id.btnSignup));
        userDatabaseHelper = new UserDatabaseHelper(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();

                if (username.trim().length() == 0 || password.trim().length() == 0) {
                    Toast.makeText(MainActivity.this, "Please fill out both boxes before logging in", Toast.LENGTH_SHORT).show();
                } else {
                    AttemptLogin(username, password);
                }
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(MainActivity.this, SignupActivity.class);
                startActivity(a);
            }
        });
    }

    public void AttemptLogin(String username, String password) {
        Integer userId = userDatabaseHelper.GetUser(username, password);
        if (userId == null) {
            Toast.makeText(MainActivity.this, "Username and password combination is incorrect...", Toast.LENGTH_SHORT).show();
            return;
        } else {
            Intent i = new Intent(MainActivity.this, HomeActivity.class);
            i.putExtra("userId", userId);
            startActivity(i);
            finish();
        }
    }
}
