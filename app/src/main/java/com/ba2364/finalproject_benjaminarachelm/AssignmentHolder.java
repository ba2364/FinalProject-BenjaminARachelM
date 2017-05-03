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
    private ImageView assignmentImage;// added to make the image appear with the Assignment

    // add image view here for pictures
    public AssignmentHolder(View itemView) {
        super(itemView);
        cardView = (CardView) itemView.findViewById(R.id.card_view);
        assignmentNameText = (TextView) itemView.findViewById(R.id.topic_card_name);
        topicNameText = (TextView) itemView.findViewById(R.id.assignment_card_name);
        dueDateText = (TextView) itemView.findViewById(R.id.duedate_card_name);
        doneText = (TextView) itemView.findViewById(R.id.done_card_name);
        assignmentImage = (ImageView) itemView.findViewById(R.id.image); /// assigned the ImageView
        // assign image view
        this.context = itemView.getContext();
    }

    public void bind(final Assignment assignment) {
        assignmentNameText.setText(assignment.topic);
        topicNameText.setText(assignment.yourHomework);

        dueDateText.setText(context.getString(R.string.dueText) + " " +assignment.dateMonth + "/" + assignment.dateDay + "/" + assignment.dateYear);
        if (assignment.done) {
            doneText.setTextColor(ContextCompat.getColor(context, R.color.doneColor));
            doneText.setText(R.string.isDone);
        } else {
            doneText.setTextColor(ContextCompat.getColor(context, R.color.notDoneYetColor));
            doneText.setText(R.string.isNotDone);
        }

// this is where we are working on assigning the image


        switch (assignment.topic) {
            case "science":
                assignmentImage.setImageResource(R.drawable.science_icon);
            case "english":
                assignmentImage.setImageResource(R.drawable.english_icon);
            case "math":
                assignmentImage.setImageResource(R.drawable.math_icon);
            case "american history":
                assignmentImage.setImageResource(R.drawable.americanhistory_icon);
            case "programming":
                assignmentImage.setImageResource(R.drawable.programming);


                //R.id.maker_delete:
                //  return true;
                // default:
                //   return true;
        }






        // switch case here and that sets the image based on the name of the assignment
        // need a default image
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