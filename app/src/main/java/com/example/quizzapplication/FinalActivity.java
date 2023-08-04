package com.example.quizzapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FinalActivity extends AppCompatActivity {
    TextView scoreTextView, rightscore, wrongscore;
    Button restart;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        scoreTextView = findViewById(R.id.score_text_view);
        rightscore = findViewById(R.id.right_questions_text_view);
        wrongscore = findViewById(R.id.wrong_questions_text_view);
        restart = findViewById(R.id.restart_button);
        Intent intent = getIntent();
        int score = intent.getIntExtra("score", 0);
        scoreTextView.setText("Your Score: " + score);
        int rightanser = score ;
        int wronganser = 5 - rightanser;
        rightscore.setText("Right Questions: " + rightanser);
        wrongscore.setText("Wrong Questions: " + wronganser);
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1new = new Intent(FinalActivity.this, MainActivity.class);
                startActivity(intent1new);
                finish();
            }
        });
    }
}
