package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.FoodType;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FoodTypeAdapter extends RecyclerView.Adapter<FoodTypeAdapter.ProductTypeViewHolder> {

    private Context context;
    private List<FoodType> listFoodType;
    private IClick iClick;
    public interface IClick{
        void onClick(FoodType productType);
    }

    public FoodTypeAdapter(Context context, List<FoodType> listFoodType, IClick iClick) {
        this.context = context;
        this.listFoodType = listFoodType;
        this.iClick = iClick;
    }

    @NonNull
    @Override
    public ProductTypeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food_type,parent,false);
        return new ProductTypeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductTypeViewHolder holder, int position) {
        FoodType productType = listFoodType.get(position);
        holder.textView.setText(productType.getName());
        Picasso.with(context).load(productType.getImg()).into(holder.imageView);
        holder.relativeLayout.setOnClickListener(view -> iClick.onClick(productType));
    }

    @Override
    public int getItemCount() {
        if (listFoodType != null) {
            return listFoodType.size();
        }
        return 0;
    }

    public class ProductTypeViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private RelativeLayout relativeLayout;
        private TextView textView;
        public ProductTypeViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_food_type);
            textView  = itemView.findViewById(R.id.tv_food_type);
            relativeLayout = itemView.findViewById(R.id.relativeLayout);
        }
    }
}
