package com.example.trainfooddelivryapp;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Map;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.ViewHolder> {

    private List<Map<String, Object>> cartList;

    public OrdersAdapter(List<Map<String, Object>> cartList) {
        this.cartList = cartList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Map<String, Object> cartItem = cartList.get(position);

        long quantity = (long) cartItem.get("quantity");
        String name = (String) cartItem.get("ProductName");
        double price = (double) cartItem.get("price");
        price=price*quantity;
        String imageUrl = (String) cartItem.get("image");
        System.out.println("calling");
        // Set the product details to the views
        holder.textViewTitle.setText(name);
        holder.textViewQuantity.setText(String.valueOf(quantity));
        holder.textViewPrice.setText(String.valueOf(price));

        // Load the product image using Picasso or any other image loading library
        Picasso.get().load(imageUrl).into(holder.imageView);


    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textViewTitle;
        TextView textViewQuantity;
        TextView textViewPrice;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.cartImage);
            textViewTitle = itemView.findViewById(R.id.title);
            textViewQuantity = itemView.findViewById(R.id.quantiycart);
            textViewPrice = itemView.findViewById(R.id.pricecart);
        }
    }
}
