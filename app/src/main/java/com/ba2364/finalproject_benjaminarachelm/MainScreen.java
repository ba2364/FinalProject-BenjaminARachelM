package com.ba2364.finalproject_benjaminarachelm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainScreen extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AssignmentAdapter assignmentAdapter;
    private DatabaseReference assignmentRef = FirebaseDatabase.getInstance().getReference("assignment");
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private FirebaseAuth.AuthStateListener authListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        assignmentAdapter = new AssignmentAdapter(assignmentRef); // Stop listening if the activity is destroyed
        recyclerView.setAdapter(assignmentAdapter);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        assignmentAdapter.cleanup();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.mainscreenadd:
                Intent intent = new Intent(this, MakeAssignment.class);
                startActivity(intent);
                return true;
            case R.id.signOutButton:
                auth.signOut();
                auth.removeAuthStateListener(authListener);
                Intent out = new Intent(this, LogInScreen.class);
                startActivity(out);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }
}