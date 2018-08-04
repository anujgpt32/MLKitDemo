package com.anuj.mlkitdemo.utils;

import android.graphics.Bitmap;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anuj on 2/8/18.
 */

//class to handle file operations
public class FileUtils {

    private static void mkdir(String fullPath) {
        new File(fullPath).mkdir();
    }

    /**
     * First, it checks whether the user has permitted file storage or not.
     * if not, the file will not be written, the user has to explicitly ask for the permission.
     */
    public static boolean writeFile(File file, String contents) {
        try {
            mkdir(file.getParent());
            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write(contents.getBytes());
            outputStream.close();
            return file.exists();
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return false;
        }
    }

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

    public static String readFile(File file) {
        try {
            InputStream stream = new FileInputStream(file);
            String result = "";
            int i;
            while ((i = stream.read()) != -1) {
                result = result.concat(String.valueOf((char) i));
            }
            stream.close();
            return result;
        } catch (IOException ioe) {
            return "";
        }
    }

    //Gives the count of files only
    public static int countFiles(String path) {
        return countFiles(new File(path));
    }

    public static int countFiles(File dir) {
        String[] fileNames = dir.list();
        int count = 0;
        if (fileNames != null && fileNames.length > 0) {
            for (String f : fileNames) {
                if (new File(dir.getPath() + "/" + f).isFile())
                    count++;
            }
        }
        return count;
    }

    //gives the list of directories only.
    public static List<File> getDirList(String path) {
        return getDirList(new File(path));
    }

    //gives the list of directories only.
    public static List<File> getDirList(File file) {
        List<File> dirs = new ArrayList<>();
        String[] fileNames = file.list();
        for (String f : fileNames) {
            File f2 = new File(file.getPath() + "/" + f);
            if (f2.isDirectory())
                dirs.add(f2);
        }
        return dirs;
    }

    //gives the list of files only.
    public static List<File> getFilesList(String path) {
        return getFilesList(new File(path));
    }

    public static List<File> getFilesList(File file) {
        List<File> dirs = new ArrayList<>();
        String[] fileNames = file.list();
        for (String f : fileNames) {
            File f2 = new File(file.getPath() + "/" + f);
            if (f2.isFile())
                dirs.add(f2);
        }
        return dirs;
    }

}
