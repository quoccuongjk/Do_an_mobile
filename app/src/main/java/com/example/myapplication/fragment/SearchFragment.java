package com.example.myapplication.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

public class SearchFragment extends Fragment {

    View mView;
    RecyclerView recyclerView;
    FoodAdapter foodAdapter;
    List<Food> mlist;
    EditText editText;
    int foodTypeId;
    String UserIdString;
    ArrayList<User> ListUser;
    String gmail;
    User user1;
    int UserId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_search, container, false);
        recyclerView = mView.findViewById(R.id.rcv_search);
        editText = mView.findViewById(R.id.edittext);
        rcv_Food();
        search_Food();
        return mView;
    }

    private void search_Food() {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String search = editText.getText().toString();
                search_Food(search);
            }
        });
    }
    private void search_Food(String search) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Food");
        Query query = myRef.orderByChild("name");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mlist.clear();
                foodAdapter.notifyDataSetChanged();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    Food food = dataSnapshot.getValue(Food.class);
                    if (food.getName().toLowerCase().contains(search.toLowerCase())) {
                        mlist.add(food);
                    }

                }
                foodAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "k thanh công", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void rcv_Food() {
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(),1);
        recyclerView.setLayoutManager(layoutManager);
        mlist = new ArrayList<>();
        getList();
        foodAdapter = new FoodAdapter(getContext(), mlist, new FoodAdapter.IClick() {
            @Override
            public void onClick(Food food) {
//                Intent intent = new Intent(getContext(), Profile.class);
//                intent.putExtra("Food",food);
//                startActivity(intent);
                NavController navController = NavHostFragment.findNavController(SearchFragment.this);

                Bundle bundle = new Bundle();
                bundle.putSerializable("Food",food);
                bundle.putInt("food_id", food.getId());
                navController.navigate(R.id.action_search_to_fooddetail, bundle);
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
    public User GetUser(ArrayList<User> arrayList, String s){
        for (User user : arrayList) {
            if (user.getEmail().toLowerCase(Locale.ROOT).trim().equals(gmail.trim()))
            {
                return user;
            }
        }
        return new User(0,"Edit Pls!",gmail,"Your Name","Edit Pls!");
    }

    private void getList() {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Food");
        Query query = myRef.orderByChild("id_food_type");
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
}