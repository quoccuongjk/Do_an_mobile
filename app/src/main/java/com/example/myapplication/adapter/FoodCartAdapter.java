package com.example.myapplication.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.fragment.CartFragment;
import com.example.myapplication.model.Details;
import com.example.myapplication.model.FoodCart;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FoodCartAdapter extends RecyclerView.Adapter<FoodCartAdapter.FoodCartViewHolder> {
    private List<Details> list;
    private Context context;
    public  static  int total;
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
    public void onBindViewHolder(@NonNull FoodCartViewHolder holder, @SuppressLint("RecyclerView") int position) {
        total=0;
        for(Details details1: list){
            total=total+details1.getPrice_Food()*details1.getCunt_Food();
        }
        if(total!=0){
            CartFragment.Set_Text(total+" USD");
        }
        else {
            CartFragment.Set_Text("Cart is Empty");
        }
        details = list.get(position);
        holder.count=details.getCunt_Food();
        holder.tvName.setText(details.getName_Food());
        holder.tvCount.setText(details.getCunt_Food()+"");
        holder.tvPrice.setText("Price : "+details.getPrice_Food()*holder.count+" USD");
        holder.total= details.getPrice_Food();
        Picasso.with(context).load(details.getImage_Food()).into(holder.imageView);
        if (holder.count==1){holder.textView.setVisibility(View.INVISIBLE);}
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("Details");
                DatabaseReference dbRef2 = dbRef.child(details.getId_User()+"");
                DatabaseReference dbRef3 = dbRef2.child(details.getId_Food()+"");
                dbRef3.setValue(new Details(details.getId_User(),details.getId_Food(), details.getName_Food(), details.getPrice_Food(), holder.count-1, details.getImage_Food()));
                if(holder.count>1){
                holder.count--;}
                if (holder.count==1){holder.textView.setVisibility(View.INVISIBLE);}
                holder.tvCount.setText(holder.count+"");
                holder.tvPrice.setText("Price: "+holder.total*holder.count+" USD");
                total=total-list.get(position).getPrice_Food();
                if(total!=0){
                    CartFragment.Set_Text(total+" USD");}else {CartFragment.Set_Text("Cart is Empty");}            }
        });
        holder.textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("Details");
                DatabaseReference dbRef2 = dbRef.child(details.getId_User()+"");
                DatabaseReference dbRef3 = dbRef2.child(details.getId_Food()+"");
                dbRef3.setValue(new Details(details.getId_User(),details.getId_Food(), details.getName_Food(), details.getPrice_Food(), holder.count+1, details.getImage_Food()));
                holder.textView.setVisibility(View.VISIBLE);
                int i= holder.count;
                holder.count++;
                holder.tvCount.setText(holder.count+"");
                holder.tvPrice.setText("Price: "+holder.total*holder.count+" USD");
                total=total+list.get(position).getPrice_Food();
                if(total!=0){
                CartFragment.Set_Text(total+" USD");}else {CartFragment.Set_Text("Cart is Empty");}
            }
        });
        holder.imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i=holder.getAdapterPosition();
                total=total-list.get(i).getPrice_Food()*list.get(i).getCunt_Food();
                if(total!=0){
                    CartFragment.Set_Text(total+" USD");}else {CartFragment.Set_Text("Cart is Empty");}                Toast.makeText(context,total+"", Toast.LENGTH_SHORT).show();
                removeItem(i);
                deleteDataFromFirebase(details, new OnDeleteDataSuccessListener() {
                    @Override
                    public void onDeleteDataSuccess() {
                        notifyDataSetChanged();
                    }
                });
            }
        });
    }
    private void deleteDataFromFirebase(Details details, final OnDeleteDataSuccessListener listener) {
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("Details");
        DatabaseReference dbRef2 = dbRef.child(details.getId_User()+"");
        DatabaseReference dbRef3 = dbRef2.child(details.getId_Food()+"");
        dbRef3.removeValue(new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                if (error == null) {
                    listener.onDeleteDataSuccess();
                } else {
                }
            }
        });
    }

    public void removeItem(int position) {
        list.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, getItemCount());
    }
    public interface OnDeleteDataSuccessListener {
        void onDeleteDataSuccess();
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
            count=Integer.parseInt(tvCount.getText().toString());
        }
    }
}
