package com.example.myapplication.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Profile;
import com.example.myapplication.R;
import com.example.myapplication.adapter.FoodAdapter;
import com.example.myapplication.model.Details;
import com.example.myapplication.model.Food;
import com.example.myapplication.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FoodFragment extends Fragment {

    RecyclerView recyclerView;
    FoodAdapter foodAdapter;
    List<Food> mlist;
    int foodTypeId;
    String UserIdString;
    ArrayList<User> ListUser;
    String gmail;
    User user1;
    int UserId;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_food, container, false);
        Bundle bundle = getArguments();
        foodTypeId = bundle.getInt("product_id");
        recyclerView = v.findViewById(R.id.rcv_food);
        rcv_Food();;
        return v;

    }
    private void rcv_Food() {
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(),1);
        recyclerView.setLayoutManager(layoutManager);
        mlist = new ArrayList<>();
        getList();
        foodAdapter = new FoodAdapter(getContext(), mlist, new FoodAdapter.IClick() {
            @Override
            public void onClick(Food food) {
                NavController navController = NavHostFragment.findNavController(FoodFragment.this);
                Bundle bundle = new Bundle();
                bundle.putSerializable("Food",food);
                bundle.putInt("food_id", food.getId());
                navController.navigate(R.id.action_food_to_fooddetail, bundle);
            }

            @Override
            public void onClickIcon(Food food) {
                UserIdString = "123";
                ListUser = new ArrayList<>();
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Details");
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String gmailUser = user.getEmail();
                gmail = gmailUser;
                DatabaseReference databaseReference = database.getReference("User");
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                            user1 = dataSnapshot.getValue(User.class);
                            ListUser.add(user1);
                        }
                        UserId = GetUser(ListUser,gmail).getId();
                        UserIdString = String.valueOf(UserId);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getContext(), "Fail", Toast.LENGTH_SHORT).show();
                    }
                });
                int FoodId = food.getId();
                final String[] child = {""};
                final Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Details details = new Details(UserId,FoodId,food.getName(),food.getPrice(),1,food.getImage());
                        child[0] = String.valueOf(UserId);
                        Log.v("UserId3",UserIdString);
                        myRef.child(child[0]).child(String.valueOf(FoodId)).setValue(details, new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                Toast.makeText(getContext(),"Thêm vào giỏ hàng thành công",Toast.LENGTH_SHORT).show();
                            }
                        });
                        //Details details = new Details(UserId,FoodId,food.getName(),food.getPrice(),count,food.getImage());
                    }
                },1000);


            }
        });
        recyclerView.setAdapter(foodAdapter);
    }


    private void getList() {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Food");
        Query query = myRef.orderByChild("id_food_type").equalTo(foodTypeId);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Food food = dataSnapshot.getValue(Food.class);
                    mlist.add(food);
                }
                foodAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "k thanh công", Toast.LENGTH_SHORT).show();
            }
        });


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