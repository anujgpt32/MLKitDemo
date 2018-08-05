package com.anuj.mlkitdemo.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import com.anuj.mlkitdemo.R;

/**
 * Created by Anuj on 04-Aug-18.
 */
public class Utilities {

    /**
     * Converts byte[] to Bitmap.
     *
     * @param image: byte[]
     * @return Bitmap
     */
    public static Bitmap convertBytesToBitmap(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }


    /**
     * Function to generate image name while saving.
     *
     * @param activity: for getting the app name.
     * @return path: path of the image.
     */
    public static String generateImagePath(Activity activity) {
        String base = Environment.getExternalStorageDirectory().getPath();
        String dir = activity.getString(R.string.app_name);
        return base.concat("/").concat(dir).concat("/").concat(String.valueOf(System.currentTimeMillis())).concat(".jpg");
    }

}
