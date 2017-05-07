package com.ba2364.finalproject_benjaminarachelm;

import android.content.Intent;
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

    private FirebaseAuth mAuth; // do we need = FirebaseAuth.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);

        usernameEnter = (EditText) findViewById(R.id.usernameEnter);
        pwordEnter = (EditText) findViewById(R.id.pwordEnter);

        mAuth = FirebaseAuth.getInstance(); // oh, its here, i dont know if it matters
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
                            Toast.makeText(LogInScreen.this, R.string.login,
                                    Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LogInScreen.this, SplashActivity.class));
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
                            Toast.makeText(LogInScreen.this, R.string.reminder,
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(LogInScreen.this, R.string.success,
                                    Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LogInScreen.this, SplashActivity.class));
                            finish();
                        }

                    }
                });
    }
}