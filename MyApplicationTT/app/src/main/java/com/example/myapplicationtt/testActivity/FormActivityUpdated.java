package com.example.myapplicationtt.testActivity;


import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.myapplicationtt.R;

import java.util.Objects;

public class FormActivityUpdated extends AppCompatActivity {

    ImageView ivProfile;
    Button btnUploadImage;

    ActivityResultLauncher<Intent> cameraLauncher;
    ActivityResultLauncher<Intent> galleryLauncher;
    ActivityResultLauncher<String> cameraPermissionLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_updated);

        ivProfile = findViewById(R.id.ivProfile);
        btnUploadImage = findViewById(R.id.btnUploadImage);

        initLaunchers();

        btnUploadImage.setOnClickListener(v -> showChooser());
    }

    private void showChooser() {
        String[] options = {"Camera", "Gallery"};

        new AlertDialog.Builder(this)
                .setTitle("Select Image")
                .setItems(options, (dialog, which) -> {
                    if (which == 0) {
                        openCameraWithPermission();
                    } else {
                        Intent gallery = new Intent(
                                Intent.ACTION_PICK,
                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                        );
                        galleryLauncher.launch(gallery);
                    }
                })
                .show();
    }

    private void openCameraWithPermission() {
        if (ContextCompat.checkSelfPermission(
                this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED) {

            openCamera();

        } else {
            cameraPermissionLauncher.launch(Manifest.permission.CAMERA);
        }
    }

    private void openCamera() {
        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraLauncher.launch(camera);
    }


    private void initLaunchers() {

        // 📷 Camera result
        cameraLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Bitmap bitmap = (Bitmap) Objects.requireNonNull(result.getData().getExtras()).get("data");
                        ivProfile.setImageBitmap(bitmap);
                    }
                });

        // 🖼 Gallery result
        galleryLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        ivProfile.setImageURI(result.getData().getData());
                    }
                });

        // 🔐 Camera permission
        cameraPermissionLauncher =
                registerForActivityResult(
                        new ActivityResultContracts.RequestPermission(),
                        isGranted -> {
                            if (isGranted) {
                                openCamera();
                            } else {
                                Toast.makeText(
                                        this,
                                        "Camera permission denied",
                                        Toast.LENGTH_SHORT
                                ).show();
                            }
                        });
    }
}
