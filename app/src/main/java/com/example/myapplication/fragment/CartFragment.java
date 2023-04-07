package com.example.myapplication.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.model.FoodCart;
import com.example.myapplication.adapter.FoodCartAdapter;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;


public class CartFragment extends Fragment {
    MainActivity mainActivity;
    View mView;
    RecyclerView recyclerView;

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

        recyclerView = mView.findViewById(R.id.rcv_cart);

    }
    private void foodCart() {
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(mainActivity,1);
        recyclerView.setLayoutManager(layoutManager);
        FoodCartAdapter foodCartAdapter = new FoodCartAdapter(getList());
        recyclerView.setAdapter(foodCartAdapter);
    }

    private List<FoodCart> getList() {
        List<FoodCart> list = new ArrayList<>();
        list.add(new FoodCart(1,"South Indian",R.drawable.picture_3,150));
        list.add(new FoodCart(1,"South Indian",R.drawable.picture_3,150));
        list.add(new FoodCart(1,"South Indian",R.drawable.picture_3,150));
        list.add(new FoodCart(1,"South Indian",R.drawable.picture_3,150));
        return list;
    }
}