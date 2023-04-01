package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductTypeAdapter extends RecyclerView.Adapter<ProductTypeAdapter.ProductTypeViewHolder> {

    private List<ProductType> listProductType;
    private IClick iClick;
    public interface IClick{
        void onClick();
    }
    public ProductTypeAdapter(List<ProductType> listProductType, IClick iClick) {
        this.listProductType = listProductType;
        this.iClick = iClick;
    }

    @NonNull
    @Override
    public ProductTypeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_type,parent,false);
        return new ProductTypeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductTypeViewHolder holder, int position) {
        ProductType productType = listProductType.get(position);
        holder.textView.setText(productType.getTitle());
        holder.imageView.setImageResource(productType.getImage());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClick.onClick();
            }
        });
    }

    @Override
    public int getItemCount() {
        if (listProductType != null) {
            return listProductType.size();
        }
        return 0;
    }

    public class ProductTypeViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private RelativeLayout relativeLayout;
        private TextView textView;
        public ProductTypeViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_product_type);
            textView  = itemView.findViewById(R.id.tv_product_type);
            relativeLayout = itemView.findViewById(R.id.relativeLayout);
        }
    }
}
