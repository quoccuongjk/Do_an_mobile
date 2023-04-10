package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.Details;
import com.example.myapplication.model.FoodCart;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FoodCartAdapter extends RecyclerView.Adapter<FoodCartAdapter.FoodCartViewHolder> {
    private List<Details> list;
    private Context context;

    public FoodCartAdapter(Context context,List<Details> list) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public FoodCartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart,parent,false);
        return new FoodCartAdapter.FoodCartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodCartViewHolder holder, int position) {
        Details details = list.get(position);
        holder.tvName.setText(details.getName_Food());
        holder.tvPrice.setText("Price : "+details.getPrice_Food());
        Picasso.with(context).load(details.getImage_Food()).into(holder.imageView);
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
