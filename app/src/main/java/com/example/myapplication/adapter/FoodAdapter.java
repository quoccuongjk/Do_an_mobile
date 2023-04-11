package com.example.myapplication.adapter;

import android.content.Context;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Profile;
import com.example.myapplication.R;
import com.example.myapplication.fragment.FoodFragment;
import com.example.myapplication.model.Food;
import com.example.myapplication.model.FoodCart;
import com.example.myapplication.model.FoodType;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {
    private Context context;
    private List<Food> list;
    private IClick iClick;
    public interface IClick{
        void onClick(Food food);
        void onClickIcon(Food food);
    }

    public FoodAdapter(Context context, List<Food> list, IClick iClick) {
        this.context = context;
        this.list = list;
        this.iClick = iClick;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food,parent,false);
        return new FoodAdapter.FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        Food food = list.get(position);
        holder.tvName.setText(food.getName());
        holder.tvPrice.setText("Price : "+food.getPrice());
        Picasso.with(context).load(food.getImage()).into(holder.imageView);
        holder.icon_cart.setOnClickListener(view -> iClick.onClickIcon(food));
        holder.layout.setOnClickListener(view -> iClick.onClick(food));



    }


    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public class FoodViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView,icon_cart;
        TextView tvName,tvPrice;
        LinearLayout layout;
        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_food);
            tvName = itemView.findViewById(R.id.tv_name_food);
            tvPrice = itemView.findViewById(R.id.tv_price_food);
            layout = itemView.findViewById(R.id.linear_food);
            icon_cart = itemView.findViewById(R.id.img_cart);
        }
    }
}
