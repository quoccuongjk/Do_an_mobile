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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;


public class FoodDetailFragment extends Fragment {
    Button button_add_cart;
    int count;
    FirebaseAuth mAuth;
    ImageView imageView1 , img_add,img_sub, img_food;
    TextView tv_price,tv_sum_price,tv_count,tv_name, tv_mota;
    Food food;

    View mView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_food_detail, container, false);
        init();
        onClickSubAdd();
        addCart();
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
        //imageView2.setVisibility(imageView2.INVISIBLE);
        //img_food = findViewById(R.id.image_profile);
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
//        button_add_cart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                FirebaseDatabase database = FirebaseDatabase.getInstance();
//                DatabaseReference myRef = database.getReference("Details");
//
//                //String gmailUser = (mAuth.getCurrentUser().getEmail());
//                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//                String gmailUser = user.getEmail();
//
//                Log.d("CUONGVIPPRO",gmailUser);
//                int FoodId = food.getId();
//                Details details = new Details("lekhoa734@gmail.com",FoodId,count);
//                myRef.child("4").setValue(details, new DatabaseReference.CompletionListener() {
//                    @Override
//                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
//                        Toast.makeText(getContext(),"Thêm vào giỏ hàng thành công",Toast.LENGTH_SHORT).show();
//                    }
//                });
//                Intent intent = new Intent(getApplicationContext(), CartFragment.class);
//                startActivity(intent);
//            }
//        });
    }

    private int TongTien() {
        return Integer.parseInt(tv_price.getText().toString())*count;
    }
}