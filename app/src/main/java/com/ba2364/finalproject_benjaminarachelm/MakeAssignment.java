package com.ba2364.finalproject_benjaminarachelm;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;


public class MakeAssignment extends AppCompatActivity {

    private ArrayList<String> messageList = new ArrayList<>();
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private FirebaseAuth.AuthStateListener authListener;
    private DatabaseReference userRef;
    private EditText topic_box;
    private EditText assignment_box;
    private DatePicker datePicker;
    private CheckBox doneChecker;
    private DatabaseReference assignmentRef = FirebaseDatabase.getInstance().getReference("assignment");
    private Assignment assignmentObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.make_assignment);

        if (getIntent() != null)
            assignmentObject = (Assignment) getIntent().getSerializableExtra(Keys.ASSIGNMENT);

        topic_box = (EditText) findViewById(R.id.topic_box);
        assignment_box = (EditText) findViewById(R.id.assignment_box);
        datePicker = (DatePicker) findViewById(R.id.date_picker);
        doneChecker = (CheckBox) findViewById(R.id.finished);

        if (assignmentObject != null) {
            topic_box.setText(assignmentObject.topic);
            assignment_box.setText(assignmentObject.yourHomework);
            doneChecker.setChecked(assignmentObject.done);
        }
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_make_assignment, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.maker_save:
                if (assignmentObject == null) {
                    String topicName = topic_box.getText().toString();
                    String assignmentName = assignment_box.getText().toString();
                    long dueDate = (datePicker.getMaxDate());
                    int dateDay = (datePicker.getDayOfMonth());
                    int dateMonth = (datePicker.getMonth() + 1);
                    int dateYear = (datePicker.getYear());

                    boolean isDone = doneChecker.isChecked();
                    Date date = new Date(dateYear, dateMonth, dateDay);
                    SimpleDateFormat formatter = new SimpleDateFormat("yy-MM-dd");
                    String id = formatter.format(date);

                    assignmentRef.child(id).setValue(new Assignment(id, topicName, assignmentName, date, dateDay, dateMonth, dateYear, isDone));

                    Intent intent = new Intent(this, MainScreen.class);
                    Toast.makeText(this, "Assignment added", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                    return true;
                } else {
                    assignmentObject.topic = topic_box.getText().toString();
                    assignmentObject.yourHomework = assignment_box.getText().toString();
                    assignmentObject.dateDay = (datePicker.getDayOfMonth());
                    assignmentObject.dateMonth = (datePicker.getMonth() + 1);
                    assignmentObject.dateYear = (datePicker.getYear());
                    assignmentObject.done = doneChecker.isChecked();
                    assignmentRef.child(assignmentObject.id).setValue(assignmentObject);
                    Intent intent2 = new Intent(this, MainScreen.class);
                    Toast.makeText(this, "Assignment modified", Toast.LENGTH_SHORT).show();
                    startActivity(intent2);
                    return true;
                }
                //case R.id.make_menu_item_calendar:
                //scheduleAssignment();
                //return true;
            case R.id.maker_delete:
                if (assignmentObject != null)
                    assignmentRef.child(assignmentObject.id).removeValue();
                Intent intent5 = new Intent(this, MainScreen.class);
                Toast.makeText(this, "Assignment removed", Toast.LENGTH_SHORT).show();
                startActivity(intent5);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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

    public void sendAssignment(View view) {
        if (assignmentObject == null) {
            String topicName = topic_box.getText().toString();
            String assignmentName = assignment_box.getText().toString();
            long dueDate = (datePicker.getMaxDate());
            int dateDay = (datePicker.getDayOfMonth());
            int dateMonth = (datePicker.getMonth() + 1);
            int dateYear = (datePicker.getYear());

            boolean isDone = doneChecker.isChecked();
            Date date = new Date(dateYear, dateMonth, dateDay);
            SimpleDateFormat formatter = new SimpleDateFormat("yy-MM-dd");
            String id = formatter.format(date);

            assignmentRef.child(id).setValue(new Assignment(id, topicName, assignmentName, date, dateDay, dateMonth, dateYear, isDone));

            Intent intent3 = new Intent(this, MainScreen.class);
            Toast.makeText(this, "Assignment added", Toast.LENGTH_SHORT).show();
            startActivity(intent3);
        } else {
            assignmentObject.topic = topic_box.getText().toString();
            assignmentObject.yourHomework = assignment_box.getText().toString();
            assignmentObject.dateDay = (datePicker.getDayOfMonth());
            assignmentObject.dateMonth = (datePicker.getMonth() + 1);
            assignmentObject.dateYear = (datePicker.getYear());
            assignmentObject.done = doneChecker.isChecked();
            assignmentRef.child(assignmentObject.id).setValue(assignmentObject);
            Intent intent4 = new Intent(this, MainScreen.class);
            Toast.makeText(this, "Assignment modified", Toast.LENGTH_SHORT).show();
            startActivity(intent4);
        }
    }

    public void scheduleAssignmentOnCalendar() {
        //Intent cal = new Intent(Intent.ACTION_EDIT);
        //cal.setType("vnd.android.cursor.item/event");
        //cal.putExtra(CalendarContract.Events.TITLE, strTitle);
        //cal.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,startDateMillis);
        //cal.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,endDateMillis);
        //cal.putExtra(CalendarContract.Events.ALL_DAY, false);// periodicity
        //cal.putExtra(CalendarContract.Events.DESCRIPTION,strDescription));
    }

    public void addCalendarEvent(String title, String location, long begin, long end) {
        Intent intent = new Intent(Intent.ACTION_INSERT)
                .setData(CalendarContract.Events.CONTENT_URI)
                .putExtra(CalendarContract.Events.TITLE, title)
                .putExtra(CalendarContract.Events.EVENT_LOCATION, location)
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, begin)
                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, end);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}