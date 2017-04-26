package com.ba2364.finalproject_benjaminarachelm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainUserScreen extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MainUserScreenAdapter mainUserScreenAdapter;

    private DatabaseReference assignmentRef = FirebaseDatabase.getInstance().getReference("assignment");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_user_screen);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mainUserScreenAdapter = new MainUserScreenAdapter(assignmentRef);

        recyclerView.setAdapter(mainUserScreenAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainUserScreenAdapter.cleanup();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);//calling from my main menu object takes menu xml and inflates it
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mainscreenadd:
                Intent intent = new Intent(this, MakeAssignment.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
}