package com.example.quizandregistration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.quizandregistration.Modal.Users;
import com.example.quizandregistration.Prevalent.Prevalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;

public class HomeActivity extends AppCompatActivity {
    Button buttonLogout,buttonTakeQuiz;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        buttonLogout = findViewById(R.id.btnLogout);
        buttonTakeQuiz = findViewById(R.id.btnQuiz);


        buttonTakeQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,QuizPage.class));
            }
        });

        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
                builder.setMessage(R.string.dialogue_exit)
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //startActivity(new Intent(getApplicationContext(),LandingPage.class));
                                Toast.makeText(HomeActivity.this, "You have closed the app!", Toast.LENGTH_SHORT).show();
                                Paper.book().destroy();
                                finish();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                                Toast.makeText(HomeActivity.this, "Thanks for sticking around", Toast.LENGTH_SHORT).show();
                            }
                        });

                AlertDialog alertDialog = builder.create();
                alertDialog.setTitle(R.string.dialogue_go);
                alertDialog.show();
            }
        });


    }
}
