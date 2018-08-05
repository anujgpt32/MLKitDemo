package com.anuj.mlkitdemo.utils;

import android.graphics.Bitmap;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Anuj on 2/8/18.
 */

//class to handle file operations
public class FileUtils {

    /**
     * Function to save the bitmap. This function needs to be called in a thread.
     *
     * @param path:   Full path file.
     * @param bitmap: The image contents
     * @return boolean: whether the file is saved or not.
     */
    public static boolean saveBitmap(String path, Bitmap bitmap) {
        File file = new File(path);
        try {
            // create parent directory.
            if (!file.getParentFile().exists())
                file.getParentFile().mkdir();
            // create new file.
            boolean created = file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
            return created;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}
