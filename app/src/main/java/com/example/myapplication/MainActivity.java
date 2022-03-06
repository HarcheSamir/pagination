package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.sql.Time;
import java.util.Date;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    EditText name , age ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.nameid) ;
        age = findViewById(R.id.ageid);
       // SimpleDateFormat sfd = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
      //  sfd.format(new Date(FieldValue.serverTimestamp().toString()))) ;


        FieldValue  a = FieldValue.serverTimestamp();
           a.getClass().



   /*   FirebaseFirestore.getInstance().collection("thing")
                .document("1TlewRenLf4BHaQUJEmi").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
DocumentSnapshot dc = task.getResult();
                            TextView  dat= findViewById(R.id.daaat);
                            dat.setText(dc.getTimestamp("timestamp").toDate().toGMTString());

                        }
                    }
                });*/








     /*  for (int i =0 ; i<100 ; i++){
            HashMap<String , Object> dataaa = new HashMap<>();
            dataaa.put("name" , Integer.toString(i)) ;
            dataaa.put("age" , Integer.toString(i) ) ;
            dataaa.put("timestamp"  , FieldValue.serverTimestamp());
            FirebaseFirestore.getInstance().collection("thing").add(dataaa);
        }*/
/*
        FirebaseDatabase.getInstance().getReference().child("thing").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(!dataSnapshot.hasChildren()){

                    HashMap<String , String> dataa = new HashMap<>();
                    dataa.put("name" , "") ;
                    dataa.put("age" , "") ;
                    FirebaseDatabase.getInstance().getReference().child("thing").push().setValue(dataa) ;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        }) ;*/










        findViewById(R.id.saveid).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( !name.getText().toString().equals("") && !age.getText().toString().equals("") ){
                    HashMap<String , Object> data = new HashMap<>();
                    data.put("name" , name.getText().toString()) ;
                    data.put("age" , age.getText().toString() ) ;
                    data.put("timestamp"  , FieldValue.serverTimestamp());
                    FirebaseFirestore.getInstance().collection("thing").add(data);
                    name.setText("");
                    age.setText("");
                }
            }
        });

        findViewById(R.id.showid).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent(MainActivity.this , MainActivity2.class));
            }
        });


    }
}














































































































          /*   for (int i =0 ; i<100 ; i++){
                 HashMap<String , String> dataaa = new HashMap<>();
                 dataaa.put("name" ,Integer.toString(i)) ;
                 dataaa.put("age" , Integer.toString(i)) ;
                 FirebaseDatabase.getInstance().getReference().child("thing").push().setValue(dataaa) ;
             }

        FirebaseDatabase.getInstance().getReference().child("thing").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(!dataSnapshot.hasChildren()){

                    HashMap<String , String> dataa = new HashMap<>();
                    dataa.put("name" , "") ;
                    dataa.put("age" , "") ;
                    FirebaseDatabase.getInstance().getReference().child("thing").push().setValue(dataa) ;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        }) ;









        findViewById(R.id.saveid).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( !name.getText().toString().equals("") && !age.getText().toString().equals("") ){
                    HashMap<String , String> data = new HashMap<>();
                    data.put("name" , name.getText().toString()) ;
                    data.put("age" , age.getText().toString() ) ;
                    FirebaseDatabase.getInstance().getReference().child("thing").push().setValue(data) ;
                    name.setText("");
                    age.setText("");
                }
            }
        });





        findViewById(R.id.showid).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent(MainActivity.this , MainActivity2.class));
            }
        });


    }
}*/