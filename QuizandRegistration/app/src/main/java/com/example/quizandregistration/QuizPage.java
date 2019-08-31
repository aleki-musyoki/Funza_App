package com.example.quizandregistration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class QuizPage extends AppCompatActivity {
    private TextView tvEnglish, tvMaths, tvScience, tvHome;
    private ImageView imgHome, imgMaths, imgEnglish, imgScience;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_page);
        tvMaths = findViewById(R.id.tvMaths);
        tvEnglish = findViewById(R.id.tvEnglish);
        tvScience = findViewById(R.id.tvScience);
        tvHome = findViewById(R.id.tvHome);
        imgHome = findViewById(R.id.img_home);
        imgMaths = findViewById(R.id.img_maths);
        imgEnglish = findViewById(R.id.img_english);
        imgScience = findViewById(R.id.img_science);


        tvEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(QuizPage.this,English.class));
            }
        });

        imgEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(QuizPage.this,English.class));
            }
        });

        tvMaths.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(QuizPage.this,MainActivity.class));

            }
        });

        imgMaths.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(QuizPage.this,MainActivity.class));

            }
        });

        tvScience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(QuizPage.this,Science.class));
            }
        });

        imgScience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(QuizPage.this,Science.class));
            }
        });

        tvHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(QuizPage.this,HomeActivity.class));
            }
        });

        imgHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(QuizPage.this,HomeActivity.class));
            }
        });
    }
}
