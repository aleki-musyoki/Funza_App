package com.example.quizandregistration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.quizandregistration.Modal.Questions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Science extends AppCompatActivity {

    Button b1, b2, b3, b4;
    TextView t1_question, timerTxt;
    int total = 1;
    int correct = 0;
    int wrong = 0;
    DatabaseReference databaseReference;
    ArrayList<Questions> Q_and_A;
    CustomAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_english);
        t1_question = findViewById(R.id.questionsTxt);
        timerTxt = findViewById(R.id.timerTxt);

        b1 = findViewById(R.id.button1);
        b2 = findViewById(R.id.button2);
        b3 = findViewById(R.id.button3);
        b4 = findViewById(R.id.button4);

        updateQuestion();
        reverseTimer(45,timerTxt);
        Q_and_A = new ArrayList<>();
        adapter = new CustomAdapter(this,Q_and_A);





    }

    private void updateQuestion() {

        if (total > 10){
            //Open the result activity

            Intent myIntent =new Intent(Science.this,ResultActivity.class);
            myIntent.putExtra("Total",String.valueOf(total-1));
            myIntent.putExtra("Correct",String.valueOf(correct));
            myIntent.putExtra("Incorrect",String.valueOf(wrong));
            startActivity(myIntent);


        }else {
            databaseReference = FirebaseDatabase.getInstance().getReference().child("Science").child(String.valueOf(total));
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    final  Questions questions = dataSnapshot.getValue(Questions.class);
                    try{
                        t1_question.setText("Q . "+questions.getQuestion());
                        b1.setText("A . "+questions.getA());
                        b2.setText("B . "+questions.getB());
                        b3.setText("C . "+questions.getC());
                        b4.setText("D . "+questions.getD());

                    }catch (NullPointerException ignore){

                    }




                    b1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (b1.getText().toString().equals("A . "+questions.getAnswer())){
                                b1.setBackgroundColor(Color.GREEN);
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        total = total+1;
                                        correct = correct+1;
                                        b1.setBackgroundColor(Color.parseColor("#FF6F22"));
                                        updateQuestion();
                                    }
                                },1500);
                            }else {
                                //answer is wrong.... find correct answer and make it green
                                total = total+1;
                                wrong = wrong+1;
                                b1.setBackgroundColor(Color.RED);

                                if (b2.getText().toString().equals(questions.getAnswer())){
                                    b2.setBackgroundColor(Color.GREEN);
                                    b1.setBackgroundColor(Color.RED);


                                }else if (b3.getText().toString().equals(questions.getAnswer())){
                                    b3.setBackgroundColor(Color.GREEN);
                                    b1.setBackgroundColor(Color.RED);


                                }else if (b4.getText().toString().equals(questions.getAnswer())){
                                    b4.setBackgroundColor(Color.GREEN);
                                    b1.setBackgroundColor(Color.RED);


                                }


                                //This replaces all the buttons to their old color after displaying the correct answer
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        b1.setBackgroundColor(Color.parseColor("#FF6F22"));
                                        b2.setBackgroundColor(Color.parseColor("#FF6F22"));
                                        b3.setBackgroundColor(Color.parseColor("#FF6F22"));
                                        b4.setBackgroundColor(Color.parseColor("#FF6F22"));
                                        updateQuestion();
                                    }
                                },1500);


                            }
                        }
                    });

                    b2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (b2.getText().toString().equals("B . "+questions.getAnswer())){
                                b2.setBackgroundColor(Color.GREEN);
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        total = total+1;
                                        correct = correct+1;
                                        b2.setBackgroundColor(Color.parseColor("#FF6F22"));
                                        updateQuestion();
                                    }
                                },1500);
                            }else {
                                //answer is wrong.... find correct answer and make it green
                                total = total+1;
                                wrong = wrong+1;
                                b2.setBackgroundColor(Color.RED);

                                if (b1.getText().toString().equals(questions.getAnswer())){
                                    b1.setBackgroundColor(Color.GREEN);
                                    b2.setBackgroundColor(Color.RED);


                                }else if (b3.getText().toString().equals(questions.getAnswer())){
                                    b3.setBackgroundColor(Color.GREEN);
                                    b2.setBackgroundColor(Color.RED);


                                }else if (b4.getText().toString().equals(questions.getAnswer())){
                                    b4.setBackgroundColor(Color.GREEN);
                                    b2.setBackgroundColor(Color.RED);


                                }


                                //This replaces all the buttons to their old color after displaying the correct answer
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        b1.setBackgroundColor(Color.parseColor("#FF6F22"));
                                        b2.setBackgroundColor(Color.parseColor("#FF6F22"));
                                        b3.setBackgroundColor(Color.parseColor("#FF6F22"));
                                        b4.setBackgroundColor(Color.parseColor("#FF6F22"));
                                        updateQuestion();
                                    }
                                },1500);


                            }
                        }
                    });
                    b3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (b3.getText().toString().equals("C . "+questions.getAnswer())){
                                b3.setBackgroundColor(Color.GREEN);
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        total = total+1;
                                        correct++;
                                        b3.setBackgroundColor(Color.parseColor("#FF6F22"));
                                        updateQuestion();
                                    }
                                },1500);
                            }else {
                                //answer is wrong.... find correct answer and make it green
                                total = total+1;
                                wrong = wrong+1;
                                b3.setBackgroundColor(Color.RED);

                                if (b1.getText().toString().equals(questions.getAnswer())){
                                    b1.setBackgroundColor(Color.GREEN);
                                }else if (b2.getText().toString().equals(questions.getAnswer())){
                                    b2.setBackgroundColor(Color.GREEN);
                                }else if (b4.getText().toString().equals(questions.getAnswer())){
                                    b4.setBackgroundColor(Color.GREEN);
                                }

                                //Returns buttons to their original color
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        b1.setBackgroundColor(Color.parseColor("#FF6F22"));
                                        b2.setBackgroundColor(Color.parseColor("#FF6F22"));
                                        b3.setBackgroundColor(Color.parseColor("#FF6F22"));
                                        b4.setBackgroundColor(Color.parseColor("#FF6F22"));
                                        updateQuestion();
                                    }
                                },1500);


                            }
                        }
                    });

                    b4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (b4.getText().toString().equals("D . "+questions.getAnswer())){
                                b4.setBackgroundColor(Color.GREEN);
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        total = total+1;
                                        correct = correct+1;
                                        b4.setBackgroundColor(Color.parseColor("#FF6F22"));
                                        updateQuestion();
                                    }
                                },1500);
                            }else {
                                //answer is wrong.... find correct answer and make it green
                                total = total+1;
                                wrong = wrong+1;
                                b4.setBackgroundColor(Color.RED);

                                if (b1.getText().toString().equals(questions.getAnswer())){
                                    b1.setBackgroundColor(Color.GREEN);
                                }else if (b2.getText().toString().equals(questions.getAnswer())){
                                    b2.setBackgroundColor(Color.GREEN);
                                }else if (b3.getText().toString().equals(questions.getAnswer())){
                                    b3.setBackgroundColor(Color.GREEN);
                                }

                                //Returns buttons to their original color
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        b1.setBackgroundColor(Color.parseColor("#FF6F22"));
                                        b2.setBackgroundColor(Color.parseColor("#FF6F22"));
                                        b3.setBackgroundColor(Color.parseColor("#FF6F22"));
                                        b4.setBackgroundColor(Color.parseColor("#FF6F22"));
                                        updateQuestion();
                                    }
                                },1500);


                            }
                        }
                    });




                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }

    }

    public void reverseTimer(int seconds, final TextView tv){
        new CountDownTimer(seconds * 1000 + 1000, 1000) {
            @Override
            public void onTick(long millsUntilFinished) {
                int seconds = (int) (millsUntilFinished /1000);
                int minutes = seconds / 60;
                seconds = seconds % 60;
                tv.setText(String.format("%02d",minutes)
                        + ":" + String.format("%02d",seconds));
            }

            @Override
            public void onFinish() {
                tv.setText("Completed");
                Intent myIntent = new Intent(Science.this,ResultActivity.class);
                myIntent.putExtra("total",String.valueOf(total-1));
                myIntent.putExtra("correct",String.valueOf(correct));
                myIntent.putExtra("incorrect",String.valueOf(wrong));
                startActivity(myIntent);
            }
        }.start();


    }

}
