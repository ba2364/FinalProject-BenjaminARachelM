package com.ba2364.finalproject_benjaminarachelm;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LogInScreen extends AppCompatActivity {

    private EditText emailBox;
    private EditText passwordBox;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_screen);

        emailBox = (EditText) findViewById(R.id.usernameEnter);
        passwordBox = (EditText) findViewById(R.id.pwordEnter);

        mAuth = FirebaseAuth.getInstance();
    }

    public void logIn(View view) {
        String email = emailBox.getText().toString();
        String password = passwordBox.getText().toString();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(LogInScreen.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(LogInScreen.this, task.getResult().getUser().getEmail() + " log-in successful",
                                    Toast.LENGTH_SHORT).show();
                            finish();
                        }

                    }
                });
    }

    public void signUp(View view) {
        String email = emailBox.getText().toString();
        String password = passwordBox.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(LogInScreen.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(LogInScreen.this, task.getResult().getUser().getEmail() + " sign-up successful",
                                    Toast.LENGTH_SHORT).show();
                            finish();
                        }

                    }
                });
    }
}