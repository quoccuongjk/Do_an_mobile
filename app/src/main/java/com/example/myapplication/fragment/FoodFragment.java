package com.example.myapplication.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.FoodAdapter;
import com.example.myapplication.adapter.FoodCartAdapter;
import com.example.myapplication.model.Food;
import com.example.myapplication.model.FoodCart;

import java.util.ArrayList;
import java.util.List;

public class FoodFragment extends Fragment {

    RecyclerView recyclerView;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_food, container, false);
        Bundle bundle = getArguments();
        int productId = bundle.getInt("product_id");
        Log.d("CUONG", "product id = " + productId);
        recyclerView = v.findViewById(R.id.rcv_food);
        rcv_Food();;
        return v;

    }
    private void rcv_Food() {
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(),1);
        recyclerView.setLayoutManager(layoutManager);
        FoodAdapter foodAdapter = new FoodAdapter(getList());
        recyclerView.setAdapter(foodAdapter);
    }

    private List<Food> getList() {
        List<Food> list = new ArrayList<>();
        list.add(new Food(1,"South Indian",R.drawable.picture_3,"con mẹ khoa"));
        list.add(new Food(1,"South Indian",R.drawable.picture_3,"con mẹ khoa"));
        list.add(new Food(1,"South Indian",R.drawable.picture_3,"con mẹ khoa"));
        list.add(new Food(1,"South Indian",R.drawable.picture_3,"con mẹ khoa"));
        list.add(new Food(1,"South Indian",R.drawable.picture_3,"con mẹ khoa"));
        list.add(new Food(1,"South Indian",R.drawable.picture_3,"con mẹ khoa"));
        return list;
    }
}