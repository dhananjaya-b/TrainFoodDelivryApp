package com.example.trainfooddelivryapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {
    private List<Product> orders;

    public OrderAdapter(List<Product> orders) {
        this.orders = orders;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Product order = orders.get(position);
        // Bind order data to views in the ViewHolder
        // For example:
        holder.orderTitleTextView.setText(order.getName());
        holder.orderTitleTextView.setText(order.getName());
        holder.orderTitleTextView.setText(order.getName());
        Picasso.get().load(order.getImageLink()).into(holder.image);

        // Bind other order properties as needed
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView orderTitleTextView;
        TextView orderQuantityTextView;
        TextView orderPriceTextView;
        ImageView image;

        // Other views in the item layout

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);

             orderTitleTextView = itemView.findViewById(R.id.title);
             orderQuantityTextView= itemView.findViewById(R.id.quantiycart);
             orderPriceTextView= itemView.findViewById(R.id.pricecart);
             image= itemView.findViewById(R.id.cartImage);
        }
    }
}
