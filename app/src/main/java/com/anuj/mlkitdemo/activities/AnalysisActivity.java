package com.anuj.mlkitdemo.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.anuj.mlkitdemo.R;
import com.anuj.mlkitdemo.utils.TextDetector;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AnalysisActivity extends AppCompatActivity {

    private TextView detectedTextTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        detectedTextTV = findViewById(R.id.detectedText);
        getImage();
    }

    /**
     * Function to fetch the recently captured image.
     */
    public void getImage() {
        Intent intent = getIntent();
        String path = intent.getStringExtra("image");
        if (path != null && !path.equals("")) {
            final File file = new File(path);
            if (file.exists()) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            final Bitmap bitmap = Picasso.get().load(file).get();
                            AnalysisActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    onBitmapLoaded(bitmap);
                                }
                            });
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            } else {
                Log.d("image exists", "false");
                Toast.makeText(this, "Image doesn't exist", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * This function is called when the bitmap is fetched from the storage.
     *
     * @param bitmap: The bitmap which was recently captured.
     */
    private void onBitmapLoaded(Bitmap bitmap) {
        final List<String> texts = new ArrayList<>();
        if (bitmap != null) {
            TextDetector detector = new TextDetector(this, bitmap);
            detector.detectText(new OnSuccessListener<FirebaseVisionText>() {
                @Override
                public void onSuccess(FirebaseVisionText firebaseVisionText) {
                    for (FirebaseVisionText.Block block : firebaseVisionText.getBlocks()) {
                        for (FirebaseVisionText.Line line : block.getLines()) {
                            StringBuilder builder = new StringBuilder();
                            for (FirebaseVisionText.Element element : line.getElements()) {
                                builder.append(element.getText().concat(" "));
                            }
                            texts.add(builder.toString());
                        }
                    }
                    String finalText = "Detected Texts: " + texts.toString().replace("[", "")
                            .replace("]", "").replace(", ", "\n");
                    detectedTextTV.setText(finalText);
                }
            });
        } else {
            Log.d("bitmap null", "true");
        }
    }
}
