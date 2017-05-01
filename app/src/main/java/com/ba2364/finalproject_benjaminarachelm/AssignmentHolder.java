package com.ba2364.finalproject_benjaminarachelm;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class AssignmentHolder extends RecyclerView.ViewHolder {

    private CardView cardView;
    private TextView assignmentNameText;
    private TextView topicNameText;
    private TextView dueDateText;
    private TextView doneText;
    private Context context;

    public AssignmentHolder(View itemView) {
        super(itemView);
        cardView = (CardView) itemView.findViewById(R.id.card_view);
        assignmentNameText = (TextView) itemView.findViewById(R.id.topic_card_name);
        topicNameText = (TextView) itemView.findViewById(R.id.assignment_card_name);
        dueDateText = (TextView) itemView.findViewById(R.id.duedate_card_name);
        doneText = (TextView) itemView.findViewById(R.id.done_card_name);

        this.context = itemView.getContext();
    }

    public void bind(final Assignment assignment) {
        assignmentNameText.setText(assignment.topic);
        topicNameText.setText(assignment.yourHomework);
        dueDateText.setText("Due: " + assignment.dueDate);
        doneText.setText("Done : " + assignment.done);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MakeAssignment.class);
                intent.putExtra(Keys.ASSIGNMENT, assignment);
                context.startActivity(intent);
            }
        });

    }
}