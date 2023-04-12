package com.example.myapplication.fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Pay;
import com.example.myapplication.Profile;
import com.example.myapplication.model.Details;
import com.example.myapplication.model.Food;
import com.example.myapplication.model.FoodCart;
import com.example.myapplication.adapter.FoodCartAdapter;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class CartFragment extends Fragment {
    MainActivity mainActivity;
    View mView;
    RecyclerView recyclerView;
    static TextView textView;
    TextView textView2;
    List<Details> detailsList2;
    Details details;
    FoodCartAdapter foodCartAdapter;
    String gmail;
    String id;
    List<User> userList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_cart, container, false);
        mainActivity = (MainActivity) getActivity();
        init();
        foodCart();
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(textView2.getText().toString().trim().equals("Buy Now")||textView2.getText().toString().trim().equals("Cart is Empty")){
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                }
                else
                {
                    Intent intent = new Intent(getActivity(), Pay.class);
                    startActivity(intent);
                }
            }
        });
        return mView;
    }
    public static void Set_Text(String a){
        textView.setText(a);
    }
    private void init() {
        SharedPreferences prefs = requireContext().getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE);
        gmail = prefs.getString("Email", "default value");
        textView = mView.findViewById(R.id.cart_pay);
        recyclerView = mView.findViewById(R.id.rcv_cart);
        textView2 =mView.findViewById(R.id.cart_pay);
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
        DatabaseReference myRef3 = database.getReference("User");
        userList=new ArrayList<>();
        DatabaseReference myRef = database.getReference("Details");
        SharedPreferences prefs = requireContext().getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE);
        id = prefs.getString("Id", "default value");
        DatabaseReference myRef2 = myRef.child(id+"");
        myRef2.addListenerForSingleValueEvent(new ValueEventListener() {
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