package com.anuj.mlkitdemo.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextDetector;

/**
 * Created by Anuj on 05-Aug-18.
 * @author Anuj
 * This class handles the Optical Character Recognition.
 * A basic wrapper over the Firebase OCR library.
 */
public class TextDetector {

    private FirebaseVisionImage image;
    private Activity activity;

    public TextDetector(@NonNull Activity activity, @NonNull Bitmap bitmap) {
        this.image = FirebaseVisionImage.fromBitmap(bitmap);
        this.activity = activity;
    }

    /**
     * This function detects the characters from the bitmap
     * @param onSuccessListener: callback which is called on Successful Character Recognition.
     */
    public void detectText(OnSuccessListener<FirebaseVisionText> onSuccessListener) {
        FirebaseApp.initializeApp(this.activity);
        FirebaseVisionTextDetector detector = FirebaseVision.getInstance()
                .getVisionTextDetector();
        detector.detectInImage(this.image)
                .addOnSuccessListener(onSuccessListener)
                .addOnFailureListener(
                        new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(activity, "Couldn't detect Text", Toast.LENGTH_SHORT).show();
                            }
                        });
    }

}
