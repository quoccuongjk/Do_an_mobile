package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.fragment.CartFragment;
import com.example.myapplication.fragment.FoodFragment;

public class Pay extends AppCompatActivity {

    Button button, buttonback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        button=findViewById(R.id.ok_pay);
        buttonback = findViewById(R.id.back_pay);
        buttonback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CartFragment.class);
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
}