package com.example.myapplication.fragment;

import static android.content.Intent.getIntent;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.model.Details;
import com.example.myapplication.model.Food;
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
import java.util.Locale;


public class FoodDetailFragment extends Fragment {
    int UserId;
    Button button_add_cart;
    int count;
    FirebaseAuth mAuth;
    ImageView imageView1 , img_add,img_sub, img_food;
    TextView tv_price,tv_sum_price,tv_count,tv_name, tv_mota;
    Food food;
    User user1;
    ArrayList<User> ListUser;
    String gmail;
    View mView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_food_detail, container, false);
        init();
        onClickSubAdd();
//        addCart();
        return mView;
    }
    private void init() {
        button_add_cart = mView.findViewById(R.id.ok_add);
        tv_name=mView.findViewById(R.id.name_food_detail);
        tv_price = mView.findViewById(R.id.price_food_detail);
        tv_sum_price = mView.findViewById(R.id.sum_price);
        tv_count = mView.findViewById(R.id.count_food_detail);
        tv_mota = mView.findViewById(R.id.tv_mota);
        img_sub = mView.findViewById(R.id.sub_food);
        img_add = mView.findViewById(R.id.add_food);

        img_food = mView.findViewById(R.id.image_food_detail);
        count = 1;
        tv_count.setText(count+"");
//        imageView2.setVisibility(imageView2.INVISIBLE);
//        img_food = findViewById(R.id.image_profile);
        Bundle bundle = getArguments();

        food = (Food) bundle.get("Food");//

        tv_name.setText(food.getName());
        tv_price.setText(food.getPrice()+"");
        Picasso.with(getContext()).load(food.getImage()).into(img_food);
        tv_mota.setText(food.getDescribe());
    }
    private void onClickSubAdd(){
        img_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                count=count+1;
                tv_count.setText(count+"");
                tv_sum_price.setText(TongTien()+"");
                if(count>0)img_sub.setVisibility(img_sub.VISIBLE);
            }
        });
        img_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count--;
                tv_count.setText(count+"");
                tv_sum_price.setText(TongTien()+"");
                if(count==0){        img_sub.setVisibility(img_sub.INVISIBLE);
                }
            }
        });

    }
    private void addCart() {
        button_add_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Details");
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String gmailUser = user.getEmail();
                gmail = gmailUser;
                Log.d("123",gmailUser);
                ListUser=new ArrayList<>();
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference databaseReference = firebaseDatabase.getReference("User");

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                            user1 = dataSnapshot.getValue(User.class);
                            ListUser.add(user1);
                        }
                        UserId = GetUser(ListUser,gmail).getId();
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getContext(), "Fail", Toast.LENGTH_SHORT).show();
                    }
                });
                Log.d("UserId",String.valueOf(UserId));
                int FoodId = food.getId();
                Details details = new Details(UserId,FoodId,food.getName(),food.getPrice(),count,food.getImage());
                String child = String.valueOf(UserId);
                myRef.child(child).setValue(details, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                        Toast.makeText(getContext(),"Thêm vào giỏ hàng thành công",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }


    private int TongTien() {
        return Integer.parseInt(tv_price.getText().toString())*count;
    }
    public User GetUser(ArrayList<User> arrayList, String s){
        for (User user : arrayList) {
            if (user.getEmail().toLowerCase(Locale.ROOT).trim().equals(gmail.trim()))
            {
                return user;
            }
        }
        return new User(0,"Edit Pls!",gmail,"Your Name","Edit Pls!");
    }
}