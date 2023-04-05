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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Signup extends AppCompatActivity {

    private EditText mUsernameField;
    private EditText mFirstnameField;
    private EditText mLastnameField;
    private EditText mEmailField;
    private EditText mPasswordField;
    private Button mSignupButton;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mUsernameField = findViewById(R.id.edit_username);
        mFirstnameField = findViewById(R.id.edit_firstname);
        mLastnameField = findViewById(R.id.edit_lastname);
        mEmailField = findViewById(R.id.edit_email);
        mPasswordField = findViewById(R.id.edit_password);
        mSignupButton = findViewById(R.id.button_signup);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        mSignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signup();
            }
        });
    }

    private void signup() {
        // Get the values from the input fields
        String username = mUsernameField.getText().toString().trim();
        String firstname = mFirstnameField.getText().toString().trim();
        String lastname = mLastnameField.getText().toString().trim();
        String email = mEmailField.getText().toString().trim();
        String password = mPasswordField.getText().toString().trim();

        // Check that all fields are filled out
        if (TextUtils.isEmpty(username) ||
                TextUtils.isEmpty(firstname) ||
                TextUtils.isEmpty(lastname) ||
                TextUtils.isEmpty(email) ||
                TextUtils.isEmpty(password)) {
            // Display an error message if any field is empty
            // You can use a Toast or a Snackbar for this
            return;
        }

        // Check if the username or email already exist in the database
        mDatabase.child("users").orderByChild("username").equalTo(username).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    // Display an error message if the username already exists
                    // You can use a Toast or a Snackbar for this
                    Toast.makeText(getApplicationContext(), "Username already exists. Please choose another username", Toast.LENGTH_LONG).show();


                    return;
                } else {
                    // Perform another query to check if the email already exists
                    mDatabase.child("users").orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                // Display an error message if the email already exists
                                // You can use a Toast or a Snackbar for this
                                Toast.makeText(getApplicationContext(), "Email already used. Please choose another Email", Toast.LENGTH_LONG).show();

                                return;
                            } else {
                                // Create a new User object with the input values
                                User user = new User(username, firstname, lastname, email, password);

                                // Generate a new ID for the user in the database
                                String userId = mDatabase.child("users").push().getKey();

                                // Add the user to the database with the generated ID
                                mDatabase.child("users").child(userId).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            // Display a success message if the user was added to the database
                                            // You can use a Toast or a Snackbar for this
                                            Toast.makeText(getApplicationContext(), username+ " succesfully created", Toast.LENGTH_LONG).show();
                                            startActivity(new Intent(Signup.this,Login.class));


                                        } else {
                                            // Display an error message if the user could not be added to the database
                                            // You can use a Toast or a Snackbar for this
                                            Log.e("Signup", "Error adding user to database", task.getException());
                                        }
                                    }
                                });
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Log.e("Signup", "Error querying database", error.toException());
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Signup", "Error querying database", error.toException());
            }
        });
    }

}
