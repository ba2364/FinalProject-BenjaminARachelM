package com.ba2364.finalproject_benjaminarachelm;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class MakeAssignment extends AppCompatActivity {

    TextView userInput;
    TextView displayText;
    private ArrayList<String> messageList = new ArrayList<>();
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private FirebaseAuth.AuthStateListener authListener;
    private DatabaseReference userRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_assignment);

        //displayText = (TextView) findViewById(R.id.textDisplay);
        //userInput = (EditText) findViewById(R.id.messageText);

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user == null) {
                    startActivity(new Intent(MakeAssignment.this, LogInScreen.class));
                } else {
                    userRef = database.getReference(user.getUid());
                    userRef.addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                            messageList.add(dataSnapshot.getValue(String.class));
                            displayText();
                        }

                        @Override
                        public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                            Toast.makeText(MakeAssignment.this, dataSnapshot.getValue(String.class) + " has changed", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onChildRemoved(DataSnapshot dataSnapshot) {
                            Toast.makeText(MakeAssignment.this, dataSnapshot.getValue(String.class) + " is removed", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                        }
                    });
                }

            }
        };


    }

    @Override
    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(authListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        auth.removeAuthStateListener(authListener);
    }

    public void sendText(View view) {
        FirebaseUser user = auth.getCurrentUser();
        userRef = database.getReference(user.getUid());
        String userText = userInput.getText().toString();

        userRef.push().setValue(userText);
    }

    public void scheduleAssignment(View view)
    {
        EditText titleField = (EditText) findViewById(R.id.title);
        String title = titleField.getText().toString();

        EditText messageField = (EditText) findViewById(R.id.assignment);
        String message = messageField.getText().toString();

        Switch onlyTitle = (Switch) findViewById(R.id.finish);
        boolean noMessage = onlyTitle.isChecked();

        if (noMessage)
        {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto: "));
            intent.putExtra(Intent.EXTRA_SUBJECT, title);
            if (intent.resolveActivity(getPackageManager()) != null)
            {
                startActivity(intent);
            }
        }
        else
        {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto: "));
            intent.putExtra(Intent.EXTRA_SUBJECT, title);
            intent.putExtra(Intent.EXTRA_TEXT, message);
            if (intent.resolveActivity(getPackageManager()) != null)
            {
                startActivity(intent);
            }
        }
    }

    private void displayText() {
        String text = "";
        for (String s : messageList) {
            text += s + "\n";

        }
        displayText.setText(text);
    }

    public void signOut(View view) {
        auth.signOut();
        messageList.clear();
        displayText.setText("");
        userInput.setText("");
    }
}