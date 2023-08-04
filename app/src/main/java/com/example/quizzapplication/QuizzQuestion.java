package com.example.quizzapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class QuizzQuestion extends AppCompatActivity {

    TextView questionTextView,nameset;
    RadioGroup optionsRadioGroup;
    Button nextButton,quitbutton;

    ArrayList<String> questions = new ArrayList<>();
    ArrayList<String> answers = new ArrayList<>();
    ArrayList<ArrayList<String>> optionList = new ArrayList<>();
    int currentQuestionIndex = 0;
    int score = 0;
    TextView scoresetter;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz_question);

        questionTextView = findViewById(R.id.question_text_view);
        optionsRadioGroup = findViewById(R.id.options_radio_group);
        nextButton = findViewById(R.id.next_button);
        quitbutton =findViewById(R.id.quit_button);
        scoresetter = findViewById(R.id.textView4);
        scoresetter.setText(""+ score);
        Intent intent = getIntent();
        String Name  = intent.getStringExtra("name");
        nameset = findViewById(R.id.score_text_view);
        nameset.setText(Name);
        // Add questions and answers
        questions.add("Which method can be defined only once in a program?");
        questions.add("Which keyword is used by methods to refer to the current object that invoked it?");
        questions.add("Which of these access specifiers can be used for an interface?");
        questions.add("Which of the following is the correct way of importing an entire package 'pkg'?");
        questions.add("What is the return type of constructors?");

        answers.add("main method");
        answers.add("this");
        answers.add("public");
        answers.add("import pkg.*");
        answers.add("None of the mentioned");

        ArrayList<String> options1 = new ArrayList<>();
        options1.add("finalize method");
        options1.add("main method");
        options1.add("static method");
        options1.add("private method");

        ArrayList<String> options2 = new ArrayList<>();
        options2.add("import");
        options2.add("this");
        options2.add("catch");
        options2.add("abstract");

        ArrayList<String> options3 = new ArrayList<>();
        options3.add("public");
        options3.add("private");
        options3.add("protected");
        options3.add("All of the above mentioned");

        ArrayList<String> options4 = new ArrayList<>();
        options4.add("Import pkg.");
        options4.add("import pkg.*");
        options4.add("Import pkg.*");
        options4.add("import pkg.");

        ArrayList<String> options5 = new ArrayList<>();
        options5.add("int");
        options5.add("float");
        options5.add("void");
        options5.add("None of the mentioned");

        optionList.add(options1);
        optionList.add(options2);
        optionList.add(options3);
        optionList.add(options4);
        optionList.add(options5);

        // Display the first question
        displayQuestion(currentQuestionIndex);

        quitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to FinalActivity
                Intent intent = new Intent(QuizzQuestion.this, FinalActivity.class);
                intent.putExtra("score", score);
                startActivity(intent);
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (optionsRadioGroup.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(QuizzQuestion.this, "Please select an option", Toast.LENGTH_SHORT).show();
                    return;
                }

                checkAnswer();
                currentQuestionIndex++;
                if (currentQuestionIndex < questions.size()) {
                    displayQuestion(currentQuestionIndex);
                } else {
                    // Navigate to FinalActivity
                    Intent intent = new Intent(QuizzQuestion.this, FinalActivity.class);
                    intent.putExtra("score", score);
                    startActivity(intent);
                    finish();
                }
            }
        });


    }

    private void displayQuestion(int index) {
        String question = questions.get(index);
        questionTextView.setText(question);

        optionsRadioGroup.removeAllViews();

        List<String> options = optionList.get(index);
        for (String option : options) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(option);
            optionsRadioGroup.addView(radioButton);
        }

        optionsRadioGroup.clearCheck();
    }

    private void checkAnswer() {
        int checkedId = optionsRadioGroup.getCheckedRadioButtonId();
        if (checkedId != -1) {
            RadioButton selectedRadioButton = findViewById(checkedId);
            String selectedAnswer = selectedRadioButton.getText().toString();
            String correctAnswer = answers.get(currentQuestionIndex);
            if (selectedAnswer.equals(correctAnswer)) {
                score += 1;
                Toast.makeText(this, "Correct answer!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Wrong answer! Correct answer is: " + correctAnswer, Toast.LENGTH_SHORT).show();
            }
            scoresetter.setText("" + score); // Update the score in the TextView
        }
    }

    private void showQuizCompleted() {
        Toast.makeText(this, "Quiz completed! Your score: " + score, Toast.LENGTH_SHORT).show();
        nextButton.setEnabled(false);
    }
}
