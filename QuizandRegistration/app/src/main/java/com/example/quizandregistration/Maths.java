package com.example.quizandregistration;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class Maths extends AppCompatActivity {
    TextView questionsTxtView, timerTxtView;
    Button option1, option2, option3, option4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maths);
        questionsTxtView = findViewById(R.id.questionsTxtView);
        timerTxtView = findViewById(R.id.timerTxtView);
        option1 = findViewById(R.id.buttonOpt1);
        option2 = findViewById(R.id.buttonOpt2);
        option3 = findViewById(R.id.buttonOpt3);
        option4 = findViewById(R.id.buttonOpt4);


    }
}
