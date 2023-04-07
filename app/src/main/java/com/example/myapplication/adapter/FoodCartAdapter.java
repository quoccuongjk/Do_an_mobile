package com.example.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.FoodCart;

import java.util.List;

public class FoodCartAdapter extends RecyclerView.Adapter<FoodCartAdapter.FoodCartViewHolder> {
    private List<FoodCart> list;

    public FoodCartAdapter(List<FoodCart> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public FoodCartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart,parent,false);
        return new FoodCartAdapter.FoodCartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodCartViewHolder holder, int position) {
        FoodCart foodCart = list.get(position);
        holder.tvName.setText(foodCart.getName());
        holder.tvPrice.setText("Price : "+foodCart.getPrice());
        holder.imageView.setImageResource(foodCart.getImage());

    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public class FoodCartViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView tvName,tvPrice;
        public FoodCartViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_food_c);
            tvName = itemView.findViewById(R.id.tv_name_c);
            tvPrice = itemView.findViewById(R.id.tv_price_c);
        }
    }
}
