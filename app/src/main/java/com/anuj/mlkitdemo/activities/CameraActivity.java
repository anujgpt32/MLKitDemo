package com.anuj.mlkitdemo.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.anuj.mlkitdemo.R;
import com.anuj.mlkitdemo.utils.FileUtils;
import com.anuj.mlkitdemo.utils.Utilities;
import com.otaliastudios.cameraview.CameraListener;
import com.otaliastudios.cameraview.CameraView;
import com.otaliastudios.cameraview.Facing;

public class CameraActivity extends AppCompatActivity {

    private CameraView camera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        camera = findViewById(R.id.camera);
    }

    @Override
    protected void onResume() {
        super.onResume();
        camera.addCameraListener(new CameraListener() {
            @Override
            public void onPictureTaken(final byte[] jpeg) {
                super.onPictureTaken(jpeg);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final String path = Utilities.generateImagePath(CameraActivity.this);
                        Bitmap bitmap = Utilities.convertBytesToBitmap(jpeg);
                        final boolean isSaved = FileUtils.saveBitmap(path, bitmap);
                        CameraActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (!isSaved) {
                                    Toast.makeText(CameraActivity.this,
                                            "File Not Saved", Toast.LENGTH_SHORT).show();
                                } else {
                                    Intent intent = new Intent(CameraActivity.this, AnalysisActivity.class);
                                    intent.putExtra("image", path);
                                    startActivity(intent);
                                }
                            }
                        });
                    }
                }).start();
            }
        });
        camera.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        camera.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        camera.destroy();
    }

    public void capture(View view) {
        camera.capturePicture();
    }

    public void switchCamera(View view) {
        if (camera.getFacing() == Facing.FRONT) {
            camera.setFacing(Facing.BACK);
        } else {
            camera.setFacing(Facing.FRONT);
        }
    }
}
