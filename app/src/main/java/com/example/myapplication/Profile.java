package com.example.myapplication;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.fragment.CartFragment;
import com.example.myapplication.model.Details;
import com.example.myapplication.model.Food;
import com.example.myapplication.model.FoodType;
import com.example.myapplication.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Profile extends AppCompatActivity {
    int UserId;
//    Button button_add;
    int count;
    ImageView imageView1 , imageView2,imageView3, img_food;
    TextView textView1,textView2,textView3,tv_name, tv_mota;
    Food food;
    String gmail;
    ArrayList<User> ListUser;
    User user1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        count=0;
        setContentView(R.layout.activity_profile);
//        button_add = findViewById(R.id.ok_add);
        textView3=findViewById(R.id.count_profile);
        textView2 = findViewById(R.id.price_profile);
        textView1 = findViewById(R.id.tong_profile);
        tv_name = findViewById(R.id.name_profile);
        tv_mota = findViewById(R.id.tv_mota);
        imageView1 = findViewById(R.id.add_profile);
        imageView2 = findViewById(R.id.sub_profile);
        imageView3 = findViewById(R.id.back_profile);
        imageView2.setVisibility(imageView2.INVISIBLE);
        img_food = findViewById(R.id.image_profile);
        Intent intent = getIntent();
        food = (Food) intent.getSerializableExtra("Food");//////
        tv_name.setText(food.getName());
        textView2.setText(food.getPrice()+"");
        Picasso.with(this).load(food.getImage()).into(img_food);
        tv_mota.setText(food.getDescribe());


        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
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
//        button_add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                FirebaseDatabase database = FirebaseDatabase.getInstance();
//                DatabaseReference myRef = database.getReference("Details");
//                Log.d("123","123123");
//                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//                String gmailUser = user.getEmail();
//                Log.d("Pro",gmailUser);
//                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
//                DatabaseReference databaseReference = firebaseDatabase.getReference("User");
//
//                databaseReference.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        for(DataSnapshot dataSnapshot : snapshot.getChildren()){
//                            user1 = dataSnapshot.getValue(User.class);
//                            ListUser.add(user1);
//                        }
//                        UserId = GetUser(ListUser,gmail).getId();
//                    }
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//                        Toast.makeText(getApplicationContext(), "Fail", Toast.LENGTH_SHORT).show();
//                    }
//                });
//                System.out.print(UserId);
//                int FoodId = food.getId();
//                Details details = new Details(UserId,FoodId,count);
//                String child = String.valueOf(UserId);
//                myRef.child(child).setValue(details, new DatabaseReference.CompletionListener() {
//                    @Override
//                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
//                        Toast.makeText(getApplicationContext(),"Thêm vào giỏ hàng thành công",Toast.LENGTH_SHORT).show();
//                    }
//                });
//                Intent intent = new Intent(getApplicationContext(), CartFragment.class);
//                startActivity(intent);
//
//            }
//        });
    }



    private int TongTien() {
        return Integer.parseInt(textView2.getText().toString())*count;
    }
    public User GetUser(ArrayList<User> arrayList, String s){
        for (User user : arrayList) {
            if (user.getEmail().trim().equals(gmail.trim()))
            {
                return user;
            }
        }
        return new User(0,"Edit Pls!",gmail,"Your Name","Edit Pls!");
    }
}