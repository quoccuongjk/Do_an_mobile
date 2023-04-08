package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.fragment.CartFragment;

public class Profile extends AppCompatActivity {
    Button button;
    int count;
    ImageView imageView1 , imageView2;
    TextView textView1,textView2,textView3;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        count=0;
        setContentView(R.layout.activity_profile);
        button = findViewById(R.id.ok_add);
        textView3=findViewById(R.id.count_profile);
        textView2 = findViewById(R.id.price_profile);
        textView1 = findViewById(R.id.tong_profile);
        imageView1 = findViewById(R.id.add_profile);
        imageView2 = findViewById(R.id.sub_profile);
        imageView2.setVisibility(imageView2.INVISIBLE);
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                count=count+1;
                textView3.setText(count+"");
                textView1.setText(TongTien()+"");
                if(count>0)imageView2.setVisibility(imageView2.VISIBLE);
            }
        });
            imageView2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    count--;
                    textView3.setText(count+"");
                    textView1.setText(TongTien()+"");
                    if(count==0){        imageView2.setVisibility(imageView2.INVISIBLE);
                    }
                }
            });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CartFragment.class);
                startActivity(intent);
            }
        });
    }

    private int TongTien() {
        return Integer.parseInt(textView2.getText().toString())*count;
    }
}