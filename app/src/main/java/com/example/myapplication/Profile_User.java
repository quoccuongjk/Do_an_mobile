package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Profile_User extends AppCompatActivity {

    ImageView imageView ;
    Button button , button2;
    String value1;
    ArrayList<User> ListUser;
    User user;
    TextView textView1,textView5;
    int id;
    EditText editText,editText2,editText3;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_user);
//        value1=Login.email_profile;
        imageView= findViewById(R.id.back_user);
        textView1=findViewById(R.id.name_user);
        textView5=findViewById(R.id.update_profile);
        button = findViewById(R.id.out_user);
        button2 = findViewById(R.id.edit_user);
        editText = findViewById(R.id.email_user);
        editText2 = findViewById(R.id.sdt_user);
        editText3 = findViewById(R.id.diachi_user);
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText2.getWindowToken(), 0);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText2.setEnabled(true);
                editText3.setEnabled(true);
                textView5.setVisibility(View.VISIBLE);
                textView5.setEnabled(true);
            }
        });
        textView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference usersRef = database.getReference("User");
                User user1 = new User();
                user1.setId(id);
                user1.setDchi(editText3.getText().toString());
                user1.setEmail(editText.getText().toString());
                user1.setName(textView1.getText().toString());
                user1.setSdt(editText2.getText().toString());
                usersRef.child(id+"").setValue(user1);
                editText2.setEnabled(false);
                editText3.setEnabled(false);
                GetData();
            }
        });
        GetData();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent((getApplicationContext()),Login.class);
                startActivity(intent);
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void GetData() {
        SharedPreferences prefs = getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE);
        value1 = prefs.getString("Email", "default value");
        textView5.setVisibility(View.INVISIBLE);
        ListUser=new ArrayList<>();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("User");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    user = dataSnapshot.getValue(User.class);
                    ListUser.add(user);
                }
                textView1.setText(GetUser(ListUser,value1).getName());
                editText2.setText(GetUser(ListUser,value1).getSdt());
                editText3.setText(GetUser(ListUser,value1).getDchi());
                editText.setText(GetUser(ListUser,value1).getEmail());
                id = GetUser(ListUser,value1).getId();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Profile_User.this, "Fail", Toast.LENGTH_SHORT).show();
            }
            public User GetUser(ArrayList<User> arrayList,String s){
                for (User user : arrayList) {
                    if (user.getEmail().trim().equals(value1.trim()))
                    {
                        return user;
                    }
                }
                return new User(0,"Edit Pls!",value1,"Your Name","Edit Pls!");
            }
        });
    }
}