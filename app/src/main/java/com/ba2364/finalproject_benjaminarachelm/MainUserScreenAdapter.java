package com.ba2364.finalproject_benjaminarachelm;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;


public class MainUserScreenAdapter extends FirebaseRecyclerAdapter<Assignment, MainUserScreenHolder> {

    public MainUserScreenAdapter(Query ref) {
        super(Assignment.class, R.layout.card_view_assignments, MainUserScreenHolder.class, ref);
    }

    @Override
    protected void populateViewHolder(MainUserScreenHolder viewHolder, Assignment assignment, int position) {
        viewHolder.bind(assignment);
    }
}