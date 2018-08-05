package com.anuj.mlkitdemo.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import com.anuj.mlkitdemo.R;

import java.io.ByteArrayOutputStream;

/**
 * Created by Anuj on 04-Aug-18.
 */
public class Utilities {

    /**
     * Function to convert byte[] to Bitmap
     * @param image: byte[] of the image captured from the camera.
     * @return Bitmap: The bitmap of the captured image.
     */
    public static Bitmap convertBytesToBitmap(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

    /**
     * Function to generate file name for the image.
     * @param activity: Activity context for generation of directory name.
     * @return path: The path where the image is to be saved.
     */
    public static String generateImagePath(Activity activity) {
        String base = Environment.getExternalStorageDirectory().getPath();
        String dir = activity.getString(R.string.app_name);
        return base.concat("/").concat(dir).concat("/").concat(String.valueOf(System.currentTimeMillis())).concat(".jpg");
    }

}
