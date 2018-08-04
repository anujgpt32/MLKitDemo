package com.anuj.mlkitdemo.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.ByteArrayOutputStream;

/**
 * Created by Anuj on 04-Aug-18.
 */
public class Utilities {

    /*
     *   converts bytes to Bitmap
     */
    public static Bitmap convertBytesToBitmap(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

    public static byte[] compressImage(byte[] input, int quality) {
        Bitmap uncompressedImg = convertBytesToBitmap(input);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        uncompressedImg.compress(Bitmap.CompressFormat.JPEG, quality, out);
        byte[] result = out.toByteArray();
        uncompressedImg.recycle();
        return result;
    }

    public static String generateImagePath() {
        String base = Environment.getExternalStorageDirectory().getPath();
        String dir = "MlKit";
        return base.concat("/").concat(dir).concat("/").concat(String.valueOf(System.currentTimeMillis())).concat(".jpg");
    }

}
