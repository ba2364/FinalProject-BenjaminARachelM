package com.ba2364.finalproject_benjaminarachelm;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

public class ImageUtil {
    // Convert a bitmap to string
    public static String bitmapToByteString(Bitmap bitmap) {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteStream);
        byte[] byteArray = byteStream.toByteArray();
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }

    // Convert string back to a bitmap
    public static Bitmap byteStringToBitmap(String byteString) {
        byte[] imageAsByte = Base64.decode(byteString.getBytes(), Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(imageAsByte, 0, imageAsByte.length);
    }
}
