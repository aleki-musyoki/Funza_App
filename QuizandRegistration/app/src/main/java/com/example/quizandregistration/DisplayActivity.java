package com.example.quizandregistration;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.quizandregistration.Modal.Questions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DisplayActivity extends AppCompatActivity {
    ListView list;
    ArrayList<Questions> maswali;
    //Instantiate Custom adapter
    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        list =findViewById(R.id.listDisplay);
        maswali = new ArrayList<>();
        adapter = new CustomAdapter(this,maswali);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Questions");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snap: dataSnapshot.getChildren()){
                    Questions questions =snap.getValue(Questions.class);
                    maswali.add(questions);
                    adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(DisplayActivity.this, "Sorry, Please Check Internet Connection", Toast.LENGTH_SHORT).show();
            }
        });
        list.setAdapter(adapter);//To display data from the db to the list that we have
    }
}
