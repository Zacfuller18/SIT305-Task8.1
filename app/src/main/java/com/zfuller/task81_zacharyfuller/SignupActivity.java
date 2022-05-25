package com.zfuller.task81_zacharyfuller;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.zfuller.task81_zacharyfuller.data.UserDatabaseHelper;
import com.zfuller.task81_zacharyfuller.model.User;

public class SignupActivity extends AppCompatActivity {
    EditText editTextFullName, editTextSignupUsername, editTextSignupPassword, editTextConfirm;
    Button btnCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        editTextFullName = (findViewById(R.id.editTextFullName));
        editTextSignupUsername = (findViewById(R.id.editTextSignupUsername));
        editTextSignupPassword = (findViewById(R.id.editTextSignupPassword));
        editTextConfirm = (findViewById(R.id.editTextConfirm));
        btnCreate = (findViewById(R.id.btnCreate));

        UserDatabaseHelper db = new UserDatabaseHelper(this);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullName = editTextFullName.getText().toString();
                String username = editTextSignupUsername.getText().toString();
                String password = editTextSignupPassword.getText().toString();
                String confirm = editTextConfirm.getText().toString();

                if (!fullName.isEmpty() && !username.isEmpty() && !password.isEmpty() && !confirm.isEmpty() && password.equals(confirm)) {
                    long check = db.InsertUser(new User(fullName, username, password));
                    if (check > 0) {
                        Toast.makeText(SignupActivity.this, "Account created successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(SignupActivity.this, "Submission error...", Toast.LENGTH_SHORT).show();
                    }
                } else if (!password.equals(confirm)) {
                    Toast.makeText(SignupActivity.this, "Passwords need to match", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SignupActivity.this, "Please fill in all entries before creating account!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
