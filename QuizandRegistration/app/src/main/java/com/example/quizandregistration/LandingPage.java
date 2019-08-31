package com.example.quizandregistration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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

public class LandingPage extends AppCompatActivity {
    private Button btn_join_now, btn_login_now;
    private ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
        btn_join_now = findViewById(R.id.btn_join_now);
        btn_login_now = findViewById(R.id.btn_login_now);
        dialog = new ProgressDialog(this);


        Paper.init(this);


        btn_join_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Registration.class));
            }
        });

        btn_login_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });

        String UserPhoneKey = Paper.book().read(Prevalent.userPhoneKey);
        String UserPasswordKey = Paper.book().read(Prevalent.userPasswordKey);

        if (UserPhoneKey != "" && UserPasswordKey != "")
        {
            if (!TextUtils.isEmpty(UserPhoneKey)  &&  !TextUtils.isEmpty(UserPasswordKey))
            {
                AllowAccess(UserPhoneKey, UserPasswordKey);

                dialog.setTitle("Already Logged in");
                dialog.setMessage("Please wait.....");
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
            }
        }
    }



    private void AllowAccess(final String phone, final String password)
    {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();


        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if (dataSnapshot.child("Users").child(phone).exists())
                {
                    Users usersData = dataSnapshot.child("Users").child(phone).getValue(Users.class);

                    if (usersData.getPhone().equals(phone))
                    {
                        if (usersData.getPassword().equals(password))
                        {
                            Toast.makeText(LandingPage.this, "Please wait, you are already logged in...", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();

                            Intent intent = new Intent(LandingPage.this, HomeActivity.class);
                            Prevalent.currentOnlineUser = usersData;
                            startActivity(intent);
                        }
                        else
                        {
                            dialog.dismiss();
                            Toast.makeText(LandingPage.this, "Password is incorrect.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                else
                {
                    Toast.makeText(LandingPage.this, "Account with this " + phone + " number do not exists.", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
