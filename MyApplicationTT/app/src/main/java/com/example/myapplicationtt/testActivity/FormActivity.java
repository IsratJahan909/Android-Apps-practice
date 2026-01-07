package com.example.myapplicationtt.testActivity;

import static android.app.ProgressDialog.show;

import android.net.Network;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplicationtt.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FormActivity extends AppCompatActivity {


//    EditText name, pass, email, phone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_form3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

         EditText ename = findViewById(R.id.eName);
         EditText etmail = findViewById(R.id.etmail);
        EditText password_toggle = findViewById(R.id.password_toggle);
        RadioGroup Gendar1 = findViewById(R.id.etGendar);
        TextView Phone = findViewById(R.id.phone);

        DatePicker calender = findViewById(R.id.calendar);
        Spinner country = findViewById(R.id.country);
        CheckBox flutter = findViewById(R.id.Flutter);
        CheckBox java = findViewById(R.id.Java);
        CheckBox php = findViewById(R.id.Php);
        CheckBox network = findViewById(R.id.Network);
        View tterm = findViewById(R.id.tterm);


        Button register = findViewById(R.id.register);


        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
             android.R.layout.simple_spinner_dropdown_item,
                Arrays.asList("Bangladesh", "India" ,"USA", "UK")
        );
        country.setAdapter(adapter);

        register.setOnClickListener( view ->{

            String name = ename.getText().toString();
            String mail = etmail.getText().toString();
            String pass =password_toggle.getText().toString();
            String number = Phone.getText().toString();

            int selectedGenderId = Gendar1.getCheckedRadioButtonId();
            String gendar = selectedGenderId ==-1 ? "Not selected" : ((RadioButton)findViewById(selectedGenderId)).getText().toString();

            List<String> skill = new ArrayList<>();
            if (flutter.isChecked()){skill.add("Flutter");}
            if (java.isChecked()){skill.add("Java");}
            if (php.isChecked()){skill.add("Php");};
            if (network.isChecked()){skill.add("Network");};

            boolean ttermChecked = tterm.isChecked();






            String Value = "Name: " + name + ", Email:" + mail + ", Pass: " + password_toggle + ", Phone" + number;

//            Toast.makeText(this, "Button is clicked!" , Toast.LENGTH_LONG).show();

            new AlterDialog.Builder(this)
        });



    }
}