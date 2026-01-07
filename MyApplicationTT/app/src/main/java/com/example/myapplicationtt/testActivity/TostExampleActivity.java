package com.example.myapplicationtt.testActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplicationtt.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.snackbar.Snackbar;

public class TostExampleActivity extends AppCompatActivity {

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tost_example);

        View main = findViewById(R.id.main);
        progressBar = findViewById(R.id.progressBar);

        ViewCompat.setOnApplyWindowInsetsListener(main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Tooltip
        findViewById(R.id.btnToast).setTooltipText("Show Toast");
    }

    // 1️⃣ Toast
    public void showToast(View view) {
        Toast.makeText(this, "This is Toast", Toast.LENGTH_SHORT).show();
    }

    // 2️⃣ Snackbar
    public void showSnackbar(View view) {
        Snackbar snackbar = Snackbar.make(view, "This is Snackbar", Snackbar.LENGTH_LONG);
        snackbar.setAction("UNDO", v ->
                Toast.makeText(this, "Undo Clicked", Toast.LENGTH_SHORT).show()
        );
        snackbar.setBackgroundTint(Color.BLACK);
        snackbar.setTextColor(Color.WHITE);
        snackbar.show();
    }

    // 3️⃣ AlertDialog (Modal)
    public void showAlert(View view) {
        new AlertDialog.Builder(this)
                .setTitle("Delete Item")
                .setMessage("Are you sure?")
                .setPositiveButton("Yes", (d, w) ->
                        Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show()
                )
                .setNegativeButton("No", null)
                .show();
    }

    // 4️⃣ Custom Dialog
    public void showCustomDialog(View view) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_custom);
        dialog.setCancelable(false);

        Button close = dialog.findViewById(R.id.btnClose);
        close.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }

    // 5️⃣ Bottom Sheet
    public void showBottomSheet(View view) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        View sheetView = LayoutInflater.from(this)
                .inflate(R.layout.bottom_sheet_layout, null);
        bottomSheetDialog.setContentView(sheetView);
        bottomSheetDialog.show();
    }

    // 6️⃣ ProgressBar
    public void showProgress(View view) {
        progressBar.setVisibility(View.VISIBLE);

        new Handler().postDelayed(() ->
                progressBar.setVisibility(View.GONE), 2000);
    }
}
