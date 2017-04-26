package com.ba2364.finalproject_benjaminarachelm;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainUserScreenHolder extends RecyclerView.ViewHolder {

    private CardView cardView;
    private TextView assignmentNameText;
    private TextView topicNameText;
    private TextView dueDateText;
    private TextView doneText;
    private Context context;

    public MainUserScreenHolder(View itemView) {
        super(itemView);
        cardView = (CardView) itemView.findViewById(R.id.card_view);
        assignmentNameText = (TextView) itemView.findViewById(R.id.topic_card_name);
        topicNameText = (TextView) itemView.findViewById(R.id.assignment_card_name);
        dueDateText = (TextView) itemView.findViewById(R.id.duedate_card_name);
        doneText = (TextView) itemView.findViewById(R.id.done_card_name);

        this.context = itemView.getContext();
    }

    public void bind(final Assignment assignment) {
        assignmentNameText.setText(assignment.getTopic());
        topicNameText.setText(assignment.getYourHomework());
        dueDateText.setText("Due Date: " + assignment.getDueDate());
        doneText.setText("Done: " + assignment.getDone());
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, assignmentNameText.getText(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, MakeAssignment.class);
                intent.putExtra(Keys.ASSIGNMENT, assignment);
                context.startActivity(intent);
            }
        });

    }
}