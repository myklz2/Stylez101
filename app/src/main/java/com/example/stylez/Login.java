package com.example.stylez;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.Nullable;

public class Login extends AppCompatActivity {

    private EditText loginusername, loginpassword;
    private Button loginbtn, mSignupButton;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        loginusername = findViewById(R.id.loginusername);
        loginpassword = findViewById(R.id.loginpassword);
        loginbtn = findViewById(R.id.btnlogin);
        mSignupButton = findViewById(R.id.btnsignup);
        mSignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Signup.class));
            }
        });
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // method
                if (validUserName() && validPass()) {
                    checkUser();
                }
            }
        });
    }

    public Boolean validUserName() {
        String val = loginusername.getText().toString();
        if (TextUtils.isEmpty(val)) {
            loginusername.setError("Username is empty");
            return false;
        } else {
            loginusername.setError(null);
            return true;
        }
    }

    public Boolean validPass() {
        String val = loginpassword.getText().toString();
        if (TextUtils.isEmpty(val)) {
            loginpassword.setError("Password is empty");
            return false;
        } else {
            loginpassword.setError(null);
            return true;
        }
    }
    public void checkUser() {
        String userUserName = loginusername.getText().toString().trim();
        String userPassword = loginpassword.getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");

        Query checkUserDatabase = reference.orderByChild("username").equalTo(userUserName);
        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                        String password = userSnapshot.child("password").getValue(String.class);
                        if (password != null && password.equals(userPassword)) {
                            Intent intent = new Intent(Login.this, NavigationSection.class);
                            Toast.makeText(Login.this, "Welcome", Toast.LENGTH_SHORT).show();
                            startActivity(intent);
                            return;
                        }
                    }
                    loginpassword.setError("Invalid credential");
                    loginpassword.requestFocus();
                } else {
                    loginusername.setError("User not found");
                    loginusername.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Login.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }


}
