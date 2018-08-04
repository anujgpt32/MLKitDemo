package com.anuj.mlkitdemo.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.anuj.mlkitdemo.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;

public class AnalysisActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        getImage();
    }

    public void getImage() {
        Intent intent = getIntent();
        String path = intent.getStringExtra("image");
        if (path != null && !path.equals("")) {
            File file = new File(path);
            if (file.exists()) {
                Picasso.get().load(file).into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        useBitmap(bitmap);
                    }

                    @Override
                    public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                        Toast.makeText(AnalysisActivity.this, "File load Failed", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                    }
                });
            } else {
                Toast.makeText(this, "Image doesn't exist", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * Use the loaded bitmap from the storage
     *
     * @param bitmap: picture loaded from the file.
     */
    private void useBitmap(Bitmap bitmap) {
        Log.d("bitmap null??", "" + (bitmap == null));
    }
}
