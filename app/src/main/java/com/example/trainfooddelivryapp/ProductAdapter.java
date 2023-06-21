package com.example.trainfooddelivryapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private List<Product> newsList;
    private FirebaseFirestore firestore;
    private List<Product> productList=new ArrayList<>();
    private Context context; // Added context variable
    public ProductAdapter(List<Product> newsList, Context context) {
        this.newsList = newsList;
        this.context = context;
        this.firestore = FirebaseFirestore.getInstance();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = newsList.get(position);
        holder.textViewQuantity.setText(String.valueOf(holder.q));
        holder.textViewTitle.setText(product.getName());
        Double price = (Double) product.getPrice();
        holder.textViewPrice.setText(price.toString());
        Double available = (Double) product.getAvailable();
        Picasso.get().load(product.getImageLink()).into(holder.imageView);
        holder.available.setText("Available :"+available.toString());
        holder.imageViewSub.setOnClickListener(view -> {
            if (holder.q > 1) {
                holder.q--;
                holder.textViewQuantity.setText(String.valueOf(holder.q));
            }
        });

        holder.imageViewAdd.setOnClickListener(view -> {

            holder.q++;
            product.setAvailable(product.getAvailable()-1);
            holder.textViewQuantity.setText(String.valueOf(holder.q));
            Double a = (Double) product.getAvailable();
            holder.available.setText("Available :"+a.toString());
        });

        holder.buttonAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PaymentActivity.class); // Use context to start the activity
                Bundle bundle = new Bundle();
                bundle.putParcelable("product",product);
                bundle.putInt("quantity",holder.q);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public void updateData(List<Product> productList) {
        this.newsList = productList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        ImageView imageViewAdd;
        ImageView imageViewSub;
        TextView textViewTitle;
        TextView textViewQuantity;
        TextView available;

        TextView textViewPrice;
        Button buttonAddToCart;
int q;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageviewProdcut);
            textViewTitle = itemView.findViewById(R.id.ProductTitle);
            textViewPrice = itemView.findViewById(R.id.price);
            buttonAddToCart =itemView.findViewById(R.id.buy);
            imageViewAdd=itemView.findViewById(R.id.addQuantity);
            imageViewSub=itemView.findViewById(R.id.subQuantity);
            textViewQuantity=itemView.findViewById(R.id.quantity);
            available=itemView.findViewById(R.id.availabiliy);
            q = 1; // Set initial quantity value
        }
    }
}

