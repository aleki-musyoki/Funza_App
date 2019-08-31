package com.example.quizandregistration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizandregistration.Modal.Users;
import com.example.quizandregistration.Prevalent.Prevalent;
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

import io.paperdb.Paper;

public class Login extends AppCompatActivity {
    private EditText userPhone, userPassword;
    private Button btnLogin;
    private CheckBox checkRememberMe;
    private TextView tvSignup, tvForgotPass, tvInfo;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private String parentDbName = "Users";
    int counter = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setupUIViews();

        Paper.init(this);
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
//        progressDialog.setCancelable(false);
//        progressDialog.setCanceledOnTouchOutside(false);
//        progressDialog.setMessage("Please Wait...");
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null) {
            finish();
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
        }

        tvSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Registration.class));
            }
        });



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String phone = userPhone.getText().toString();
                String password = userPassword.getText().toString();

                if (TextUtils.isEmpty(phone)) {
                    Toast.makeText(Login.this, "Please key in your Phone Number", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(password)) {
                    Toast.makeText(Login.this, "Please write your password...", Toast.LENGTH_SHORT).show();
                } else {
                    progressDialog.setTitle("Login Account");
                    progressDialog.setMessage("Please wait, while we are checking the credentials.");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();


                    AllowAccessToAccount(phone, password);
                }
            }
        });

    }

    private void AllowAccessToAccount(final String phone, final String password) {

        if (checkRememberMe.isChecked()) {
            Paper.book().write(Prevalent.userPhoneKey, phone);
            Paper.book().write(Prevalent.userPasswordKey, password);
        }


        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();


        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(parentDbName).child(phone).exists()) {
                    Users usersData = dataSnapshot.child(parentDbName).child(phone).getValue(Users.class);

                    if (usersData.getPhone().equals(phone)) {
                        if (usersData.getPassword().equals(password)) {
                            if (parentDbName.equals("Admins")) {
                                Toast.makeText(Login.this, "Welcome Admin, you are logged in Successfully...", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();

                                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                startActivity(intent);
                            } else if (parentDbName.equals("Users")) {
                                Toast.makeText(Login.this, "logged in Successfully...", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();

                                Intent intent = new Intent(Login.this, HomeActivity.class);
                                Prevalent.currentOnlineUser = usersData;
                                startActivity(intent);
                            }
                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(Login.this, "Password is incorrect.", Toast.LENGTH_SHORT).show();
                            denyAccess();
                        }
                    }
                } else {
                    Toast.makeText(Login.this, "Account with this " + phone + " number does not exists.", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    denyAccess();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void denyAccess() {
        counter--;
        tvInfo.setText("Number of attempts remaining: " + counter);
        if (counter == 0) {
            btnLogin.setEnabled(false);
        }


    }
    private void setupUIViews() {
        userPhone = findViewById(R.id.userPhone);
        userPassword = findViewById(R.id.edtLogPass);
        btnLogin = findViewById(R.id.btnSignIn);
        tvSignup = findViewById(R.id.tvGoSignup);
        tvInfo = findViewById(R.id.tvInfo);
        tvInfo.setText("Number of attempts remaining: " + 5);
        checkRememberMe = findViewById(R.id.checkRemember);
    }

}
//    private void validate(String userName, String userPassword){
//        progressDialog.show();
//        firebaseAuth.signInWithEmailAndPassword(userName,userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                progressDialog.dismiss();
//                if (task.isSuccessful()){
//
//                    checkEmailVerification();
//
//                }else {
//                    Toast.makeText(Login.this, "Login Failed", Toast.LENGTH_SHORT).show();
//                    counter--;
//                    tvInfo.setText("Number of attempts remaining: "+counter);
//                    if (counter==0){
//                        btnLogin.setEnabled(false);
//                    }
//                }
//            }
//        });
//    }

//    private void  checkEmailVerification(){
//        FirebaseUser firebaseUser = firebaseAuth.getInstance().getCurrentUser();
//        Boolean emailflag = firebaseUser.isEmailVerified();
//        if (emailflag){
//            finish();
//            startActivity(new Intent(getApplicationContext(),HomeActivity.class));
//        }else {
//            Toast.makeText(this, "Verify your email", Toast.LENGTH_SHORT).show();
//            firebaseAuth.signOut();
//        }
//    }



