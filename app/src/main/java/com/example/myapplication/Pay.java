package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.fragment.CartFragment;
import com.example.myapplication.fragment.FoodFragment;
import com.example.myapplication.model.FoodCart;
import com.example.myapplication.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Pay extends AppCompatActivity {
    TextView textView;

    Button button, buttonback;
    String string;
    User user;
    ArrayList<User> ListUser;
    EditText editText1,editText2,editText3,editText4;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences prefs = getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE);
        string = prefs.getString("Email", "default value");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        button=findViewById(R.id.ok_pay);
        editText1=findViewById(R.id.edittextname_pay);
        editText2=findViewById(R.id.edittextsdt_pay);
        editText3=findViewById(R.id.edittextemail_pay);
        editText4=findViewById(R.id.edittextdc_pay);
        textView = findViewById(R.id.update_pay);
        buttonback = findViewById(R.id.back_pay);
        GetInfo(string);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText1.setEnabled(true);
                editText2.setEnabled(true);
                editText3.setEnabled(true);
                editText4.setEnabled(true);

            }
        });
        buttonback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Paydone.class);
                startActivity(intent);
            }
        });
    }

    private void GetInfo(String string) {
        ListUser=new ArrayList<>();
        editText1.setEnabled(false);
        editText2.setEnabled(false);
        editText3.setEnabled(false);
        editText4.setEnabled(false);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("User");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    user = dataSnapshot.getValue(User.class);
                    ListUser.add(user);
                }
                editText1.setText(GetUser(ListUser,string).getName());
                editText2.setText(GetUser(ListUser,string).getSdt());
                editText3.setText(GetUser(ListUser,string).getEmail());
                editText4.setText(GetUser(ListUser,string).getDchi());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
            public User GetUser(ArrayList<User> arrayList,String s){
                for (User user : arrayList) {
                    if (user.getEmail().trim().equals(string.trim()))
                    {
                        return user;
                    }
                }
                return new User(0,"Edit Pls!",string,"Your Name","Edit Pls!");
            }
        });
    }
}
// Bấm ok chưa push hoạc update lên firebase sẽ làm nếu còn thời gian