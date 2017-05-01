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

    private EditText usernameEnter;
    private EditText pwordEnter;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);

        usernameEnter = (EditText) findViewById(R.id.usernameEnter);
        pwordEnter = (EditText) findViewById(R.id.pwordEnter);

        mAuth = FirebaseAuth.getInstance();
    }

    public void logIn(View view) {
        String email = usernameEnter.getText().toString();
        String password = pwordEnter.getText().toString();

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
        String email = usernameEnter.getText().toString();
        String password = pwordEnter.getText().toString();

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