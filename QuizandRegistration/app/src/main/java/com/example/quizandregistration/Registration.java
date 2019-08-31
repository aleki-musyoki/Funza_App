package com.example.quizandregistration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Registration extends AppCompatActivity {
    private EditText edit_name, edit_phone, edit_password;
    private Button btnRegister;
    private TextView tvLogin;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        edit_name = findViewById(R.id.editName);
        edit_phone = findViewById(R.id.editPhone);
        edit_password = findViewById(R.id.editPassword);
        btnRegister = findViewById(R.id.btnRegister);
        tvLogin = findViewById(R.id.tvLogin);


        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog  = new ProgressDialog(this);
//        progressDialog.setMessage("Registering\nPlease wait while we're checking credentials...");
//        progressDialog.setCanceledOnTouchOutside(false);
//        progressDialog.setCancelable(false);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               createAccount();
            }
        });

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });




    }

    private void createAccount() {
        String name = edit_name.getText().toString();
        String phone = edit_phone.getText().toString();
        String password = edit_password.getText().toString();

        if (TextUtils.isEmpty(name))
        {
            Toast.makeText(this, "Please write your name...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(phone))
        {
            Toast.makeText(this, "Please write your phone number...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(password))
        {
            Toast.makeText(this, "Please write your password...", Toast.LENGTH_SHORT).show();
        }
        else
        {
            progressDialog.setTitle("Create Account");
            progressDialog.setMessage("Please wait, while we are checking the credentials.");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            ValidatephoneNumber(name, phone, password);
        }
    }

    private void ValidatephoneNumber(final String name, final String phone, final String password) {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if (!(dataSnapshot.child("Users").child(phone).exists()))
                {
                    HashMap<String, Object> userdataMap = new HashMap<>();
                    userdataMap.put("phone", phone);
                    userdataMap.put("password", password);
                    userdataMap.put("name", name);

                    RootRef.child("Users").child(phone).updateChildren(userdataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task)
                                {
                                    if (task.isSuccessful())
                                    {
                                        Toast.makeText(Registration.this, "Congratulations, your account has been created.", Toast.LENGTH_SHORT).show();
                                        progressDialog.dismiss();

                                        Intent intent = new Intent(getApplicationContext(), Login.class);
                                        startActivity(intent);
                                    }
                                    else
                                    {
                                        progressDialog.dismiss();
                                        Toast.makeText(Registration.this, "Network Error: Please try again after some time...", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
                else
                {
                    Toast.makeText(Registration.this, "This " + phone + " already exists.", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    Toast.makeText(Registration.this, "Please try again using another phone number.", Toast.LENGTH_SHORT).show();
                    //Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    //startActivity(intent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


//    private boolean validate(){
//        boolean result = false;
//        String name = edit_name.getText().toString();
//        String mail = email.getText().toString();
//        String pass = password.getText().toString();
//        if (name.isEmpty()|| mail.isEmpty() || pass.isEmpty()){
//            Toast.makeText(this, "Input all the fields", Toast.LENGTH_SHORT).show();
//        }else {
//            result = true;
//        }
//        return result;
//    }

//    private void sendEmailVerification(){
//        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
//        if (firebaseUser != null){
//            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
//                @Override
//                public void onComplete(@NonNull Task<Void> task) {
//                    if (task.isSuccessful()){
//                        Toast.makeText(Registration.this, "Registered Successfully. Verification mail has been sent to "+email.getText().toString(), Toast.LENGTH_SHORT).show();
//                        firebaseAuth.signOut();
//                        finish();
//                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
//                    }else {
//                        Toast.makeText(Registration.this, "Registration Failed. Please try again. \n Verification mail not sent", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            });
//        }
//    }

}

