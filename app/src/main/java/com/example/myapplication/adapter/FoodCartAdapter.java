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
import com.example.myapplication.fragment.CartFragment;
import com.example.myapplication.model.Details;
import com.example.myapplication.model.FoodCart;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FoodCartAdapter extends RecyclerView.Adapter<FoodCartAdapter.FoodCartViewHolder> {
    private List<Details> list;
    private Context context;
    Details details;
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
        details = list.get(position);
        holder.count=details.getCunt_Food();
        holder.tvName.setText(details.getName_Food());
        holder.tvCount.setText(details.getCunt_Food()+"");
        holder.tvPrice.setText("Price : "+details.getPrice_Food()+" USD");
        holder.total= details.getPrice_Food();
        Picasso.with(context).load(details.getImage_Food()).into(holder.imageView);
        if (holder.count==1){holder.textView.setVisibility(View.INVISIBLE);}
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i = holder.count;
                if(holder.count>1){
                holder.count--;}
                if (holder.count==1){holder.textView.setVisibility(View.INVISIBLE);}
                holder.tvCount.setText(holder.count+"");
                holder.tvPrice.setText("Price: "+holder.total*holder.count+" USD");
            }
        });
        holder.textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.textView.setVisibility(View.VISIBLE);
                int i= holder.count;
                holder.count++;
                holder.tvCount.setText(holder.count+"");
                holder.tvPrice.setText("Price: "+holder.total*holder.count+" USD");
            }
        });
        holder.imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                details=list.get(holder.getAdapterPosition());
                int i=holder.getAdapterPosition();
                list.remove(i);
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference ref = database.getReference();
                DatabaseReference objectRef = ref.child("Details");
                DatabaseReference objectRef2 = objectRef.child(details.getId_User()+"");
                DatabaseReference objectRef3 = objectRef2.child(details.getId_Food()+"");
                objectRef3.removeValue();
                notifyItemChanged(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }
    public class FoodCartViewHolder extends RecyclerView.ViewHolder {

        int count;
        int total;
        ImageView imageView,imageView2;
        TextView tvName,tvPrice,tvCount,textView,textView2;
        public FoodCartViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView2=itemView.findViewById(R.id.delete_cart);
            textView=itemView.findViewById(R.id.textView);
            textView2=itemView.findViewById(R.id.textView2);
            imageView = itemView.findViewById(R.id.iv_food_c);
            tvName = itemView.findViewById(R.id.tv_name_c);
            tvPrice = itemView.findViewById(R.id.tv_price_c);
            tvCount= itemView.findViewById(R.id.tv_count);
        }
    }
}
