package com.ba2364.finalproject_benjaminarachelm;

import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ImageActivity extends AppCompatActivity {
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference picRef = database.getReference("picture");
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        image = (ImageView) findViewById(R.id.image);
        picRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String byteString = dataSnapshot.getValue(String.class);
                if (TextUtils.isEmpty(byteString)) return;
                image.setImageBitmap(ImageUtil.byteStringToBitmap(byteString)); // Read image from Firebase
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    public void uploadImage(View view) {
        picRef.setValue(ImageUtil.bitmapToByteString(((BitmapDrawable) image.getDrawable()).getBitmap())); // Save image to Firebase
    }
}

