package com.example.quizandregistration;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import io.paperdb.Paper;

public class ResultActivity extends AppCompatActivity {
    TextView tvCorrect, tvWrong, tvCorrectAnswer, tvWrongAnswer, tvCompleted, tvCompletedAnswer, tvQuiz;
    private Button btn_get_report;
    String percentage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        tvCompleted = findViewById(R.id.textAnswersCompleted);
        tvCompletedAnswer = findViewById(R.id.tvResultsAnswer);
        tvCorrect = findViewById(R.id.tvCorrect);
        tvCorrectAnswer = findViewById(R.id.tvCorrectAnswer);
        tvWrong = findViewById(R.id.tvWrong);
        tvWrongAnswer = findViewById(R.id.tvWrongAnswer);
        tvQuiz = findViewById(R.id.tvTakeDifferentQuiz);
        btn_get_report = findViewById(R.id.btnReport);


        Intent myIntent = getIntent();

        final String questions = myIntent.getStringExtra("total");
        final String correct = myIntent.getStringExtra("correct");
        final String incorrect = myIntent.getStringExtra("incorrect");


        tvCompletedAnswer.setText(questions);
        tvCorrectAnswer.setText(correct);
        tvWrongAnswer.setText(incorrect);

        tvQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ResultActivity.this,QuizPage.class));
            }
        });

        btn_get_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(ResultActivity.this);
                builder.setMessage(R.string.dialogue_message)
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto","musyoks645@gmail.com", null));
                                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "REPORT OF RESULTS");
                                emailIntent.putExtra(Intent.EXTRA_TEXT, "Please kindly find attached to this email a a report of your recently taken quiz\n"+ questions + "- total questions answered\n"+ correct +"- correct answers\n"+ incorrect +"- wrong answers\n");
                                startActivity(Intent.createChooser(emailIntent, "Send email..."));


                                Toast.makeText(ResultActivity.this, "Sending email...", Toast.LENGTH_SHORT).show();
              //                  Paper.book().destroy();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                                Toast.makeText(ResultActivity.this, "Email not sent ", Toast.LENGTH_SHORT).show();
                            }
                        });

                AlertDialog alertDialog = builder.create();
                alertDialog.setTitle(R.string.dialogue_title);
                alertDialog.show();



            }
        });

    }

}
