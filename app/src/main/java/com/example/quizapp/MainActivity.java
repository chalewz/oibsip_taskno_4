package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView questionTex;
    private RadioGroup choicesRadioGroup;
    private RadioButton choice1, choice2, choice3, choice4;
    private Button submit;
    private String[] questions = {
            "Which of the following is the first callback method that is invoked by the system during an activity life-cycle?",
            "What is an activity in android?",
            "Which of the following android component displays the part of an activity on screen?",
            "What is an activity in android?"
    };
    private String[][] choices = {{"onClick() method", "onCreate() method", "onStart() method", "onRestart() method"},
            {"android class", "A single screen in an application with supporting java code", "android package", "None of the above"},
            {"Fragment", "View", "Fragment", "All"},
            {"Application Programming Interface", "Android Programming Interface", "Android Page Interface", "Application Page Interface\n"}};
    private int[] answers = {1,0,0,0};

    private int currentQuestionIndex = 0;
    private int score = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        questionTex = findViewById(R.id.questionTex);
        choicesRadioGroup = findViewById(R.id.choicesRadioGroup);
        choice1 = findViewById(R.id.choice1);
        choice2 = findViewById(R.id.choice2);
        choice3 = findViewById(R.id.choice3);
        choice4 = findViewById(R.id.choice4);
        submit = findViewById(R.id.submit);
        setQuestion();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedChoice = choicesRadioGroup.getCheckedRadioButtonId();
                if (selectedChoice == -1) {
                    Toast.makeText(MainActivity.this, "Please select a choice", Toast.LENGTH_SHORT).show();
                } else {
                    RadioButton selectedRadioButton = findViewById(selectedChoice);
                    int selectedAnswer = choicesRadioGroup.indexOfChild(selectedRadioButton);
                    if (selectedAnswer == answers[currentQuestionIndex]) {
                        score++;
                    }
                    currentQuestionIndex++;
                    if (currentQuestionIndex < questions.length) {
                        setQuestion();
                    } else {
                        Toast.makeText(MainActivity.this, "Quiz completed. Your score is " + score, Toast.LENGTH_SHORT).show();
                        submit.setEnabled(false);
                    }
                }
            }
        });
    }
    private void setQuestion() {
        questionTex.setText(questions[currentQuestionIndex]);
        choice1.setText(choices[currentQuestionIndex][0]);
        choice2.setText(choices[currentQuestionIndex][1]);
        choice3.setText(choices[currentQuestionIndex][2]);
        choice4.setText(choices[currentQuestionIndex][3]);
        choicesRadioGroup.clearCheck();
    }
}