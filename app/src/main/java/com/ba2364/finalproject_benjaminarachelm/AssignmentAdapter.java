package com.ba2364.finalproject_benjaminarachelm;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;


public class AssignmentAdapter extends FirebaseRecyclerAdapter<Assignment, AssignmentHolder> {

    public AssignmentAdapter(Query ref) {
        super(Assignment.class, R.layout.card_view_assignments, AssignmentHolder.class, ref);
    }

    @Override
    protected void populateViewHolder(AssignmentHolder viewHolder, Assignment assignment, int position) {
        viewHolder.bind(assignment);
    }
}