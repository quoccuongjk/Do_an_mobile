package com.example.myapplication.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Profile;
import com.example.myapplication.model.Details;
import com.example.myapplication.model.Food;
import com.example.myapplication.model.FoodCart;
import com.example.myapplication.adapter.FoodCartAdapter;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class CartFragment extends Fragment {
    MainActivity mainActivity;
    View mView;
    RecyclerView recyclerView;
    TextView textView;
    List<Details> detailsList,detailsList2;
    Details details;
    FoodCartAdapter foodCartAdapter;
    int i;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_cart, container, false);
        mainActivity = (MainActivity) getActivity();
        init();
        foodCart();
        return mView;
    }
    private void init() {
        textView = mView.findViewById(R.id.cart_pay);
        recyclerView = mView.findViewById(R.id.rcv_cart);
    }
    private void foodCart() {
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(mainActivity,1);
        recyclerView.setLayoutManager(layoutManager);
        detailsList2=new ArrayList<>();
        Update();
        foodCartAdapter = new FoodCartAdapter(getContext(),detailsList2);
        recyclerView.setAdapter(foodCartAdapter);
    }

    private void Update() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Details");
        DatabaseReference myRef2 = myRef.child("2");
        myRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    details=new Details();
                    details = dataSnapshot.getValue(Details.class);
                    detailsList2.add(details);
                }
                foodCartAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "k thanh công", Toast.LENGTH_SHORT).show();
            }
        });
}
}