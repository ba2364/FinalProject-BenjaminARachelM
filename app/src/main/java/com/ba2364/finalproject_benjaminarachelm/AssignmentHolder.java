package com.ba2364.finalproject_benjaminarachelm;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class AssignmentHolder extends RecyclerView.ViewHolder {

    private CardView cardView;
    private TextView assignmentNameText;
    private TextView topicNameText;
    private TextView dueDateText;
    private TextView doneText;
    private Context context;
    private ImageView assignmentImage;

    public AssignmentHolder(View itemView) {
        super(itemView);
        cardView = (CardView) itemView.findViewById(R.id.card_view);
        assignmentNameText = (TextView) itemView.findViewById(R.id.topic_card_name);
        topicNameText = (TextView) itemView.findViewById(R.id.assignment_card_name);
        dueDateText = (TextView) itemView.findViewById(R.id.duedate_card_name);
        doneText = (TextView) itemView.findViewById(R.id.done_card_name);
        assignmentImage = (ImageView) itemView.findViewById(R.id.image);
        this.context = itemView.getContext();
    }

    public void bind(final Assignment assignment) {
        assignmentNameText.setText(assignment.topic);
        topicNameText.setText(assignment.yourHomework);

        dueDateText.setText(context.getString(R.string.dueText) + " " + assignment.dateMonth + "/" + assignment.dateDay + "/" + assignment.dateYear);
        if (assignment.done) {
            doneText.setTextColor(ContextCompat.getColor(context, R.color.doneColor));
            doneText.setText(R.string.isDone);
        } else {
            doneText.setTextColor(ContextCompat.getColor(context, R.color.notDoneYetColor));
            doneText.setText(R.string.isNotDone);
        }

        switch (assignment.topic.toLowerCase()) {
            case "science":
                assignmentImage.setImageResource(R.drawable.science_icon);
                break;
            case "english":
                assignmentImage.setImageResource(R.drawable.english_icon);
                break;
            case "math":
                assignmentImage.setImageResource(R.drawable.math_icon);
                break;
            case "american history":
                assignmentImage.setImageResource(R.drawable.americanhistory_icon);
                break;
            case "programming":
                assignmentImage.setImageResource(R.drawable.programming);
                break;
            default:
                assignmentImage.setImageResource(R.drawable.specialassignment_icon);
                break;
        }

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