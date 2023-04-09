package com.example.myapplication.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.FoodDetailActivity;
import com.example.myapplication.R;
import com.example.myapplication.adapter.FoodAdapter;
import com.example.myapplication.adapter.FoodCartAdapter;
import com.example.myapplication.model.Food;
import com.example.myapplication.model.FoodCart;
import com.example.myapplication.model.FoodType;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FoodFragment extends Fragment {

    RecyclerView recyclerView;
    FoodAdapter foodAdapter;
    List<Food> mlist;
    int foodTypeId;

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
                Intent intent = new Intent(getContext(), FoodDetailActivity.class);
                intent.putExtra("Food",food);
                startActivity(intent);
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
}