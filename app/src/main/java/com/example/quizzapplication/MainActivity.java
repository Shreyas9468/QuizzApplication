package com.example.quizzapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText name ;
    Button start , about;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.Name);
        start = findViewById(R.id.buttonStart);
        about = findViewById(R.id.buttonAbout);

        String Name = name.getText().toString();
       about.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intentabout = new Intent(getApplicationContext(), About.class);
               startActivity(intentabout);

           }
       });
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Name = name.getText().toString();
                if (TextUtils.isEmpty(Name)) {
                    name.setError("Please enter your name");
                } else {

                    Intent intent = new Intent(getApplicationContext(), QuizzQuestion.class);
                    intent.putExtra("name",Name);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }
}