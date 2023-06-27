package com.example.trainfooddelivryapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

// Inside your Fragment class
public class MyFragment extends Fragment {
    private RecyclerView recyclerView;
    private OrderAdapter orderAdapter;
    private List<Product> orders;
    private List<Map<String, Object>> cartList;

    // ...

    // In onCreateView or onViewCreated method
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);

        recyclerView = view.findViewById(R.id.orderList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Initialize the orders list
        orders = new ArrayList<>();

        orderAdapter = new OrderAdapter(orders);
        recyclerView.setAdapter(orderAdapter);

        // Fetch orders from Firestore and populate the orders list
        fetchOrders();

        return view;
    }

    private void fetchOrders() {
        String currentUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        CollectionReference ordersCollection = firestore.collection("orders");

        ordersCollection.whereEqualTo("userId", currentUserId).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            QuerySnapshot querySnapshot = task.getResult();
                            if (querySnapshot != null) {
                                orders = new ArrayList<>();
                                for (DocumentSnapshot document : querySnapshot.getDocuments()) {
                                    // Get order data
                                    String orderId = document.getId();
                                    String userId = document.getString("userId");
                                    // Get item data
                                    List<Map<String, Object>> items = (List<Map<String, Object>>) document.get("item");
                                    // Assuming there's only one item in the array
                                    Map<String, Object> itemData = items.get(0);
                                    String image = (String) itemData.get("iamge");
                                    String productName = (String) itemData.get("productName");
                                    int quantity= (int) itemData.get("quantiy");
                                    Double price = (Double) itemData.get("price");
                                    // Create an Order object
                                    Product order = new Product(productName, price, image, quantity);
                                    orders.add(order);
                                }
                                orderAdapter.notifyDataSetChanged();
                            }
                        } else {
                            // Handle the error
                            Exception exception = task.getException();
                            // ...
                        }
                    }
                });
    }


}
