package com.example.myapplicationtt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplicationtt.layouts.HomeActivity;
import com.example.myapplicationtt.layouts2.HomeActivity2;
import com.example.myapplicationtt.testActivity.TestActivity;
import com.example.myapplicationtt.testActivity.TostExampleActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

//        Button btnLogin = findViewById(R.id.btn1);
//        btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent it = new Intent(MainActivity.this, TestActivity.class);
//                startActivity(it);
//            }
//        });

//        btnLogin.setOnClickListener(v ->
//                 startActivity(new Intent(MainActivity.this, TestActivity.class))
//        );


        findViewById(R.id.btn1).setOnClickListener(v ->
                startActivity(new Intent(this, TostExampleActivity.class)));

        findViewById(R.id.btn2).setOnClickListener(v ->
                startActivity(new Intent(this, TestActivity.class)));

        findViewById(R.id.btn3).setOnClickListener(v ->
                startActivity(new Intent(this, HomeActivity.class)));

        findViewById(R.id.btn4).setOnClickListener(v ->
                startActivity(new Intent(this, HomeActivity2.class)));
//
//        findViewById(R.id.btn5).setOnClickListener(v ->
//                startActivity(new Intent(this, BottomSheetExampleActivity.class)));

//        findViewById(R.id.btn6).setOnClickListener(v ->
//                startActivity(new Intent(this, GridExampleActivity.class)));
//        findViewById(R.id.btn1).setOnClickListener(v -> open(ToastExampleActivity.class));


    }

    private void open(Class<?> activity) {
        startActivity(new Intent(this, activity));
    }
}