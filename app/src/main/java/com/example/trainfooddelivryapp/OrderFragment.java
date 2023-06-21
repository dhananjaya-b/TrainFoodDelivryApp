package com.example.trainfooddelivryapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderFragment extends Fragment {

    private FirebaseFirestore firestore;
    private RecyclerView recyclerView;
    private OrdersAdapter ordersAdapter;
    private List<Map<String, Object>> ordersList;

    private  String cartid=null;
    Button buttonPlaceOrder;
    public interface OnRefreshListener {
        void onRefresh();
    }

    private OnRefreshListener refreshListener;

    public void setOnRefreshListener(OnRefreshListener listener) {
        refreshListener = listener;
    }
    public OrderFragment() {
        // Required empty public constructor
    }

    public static OrderFragment newInstance() {
        return new OrderFragment();
    }
    public static OrderFragment newInstance(String param1, String param2) {
        OrderFragment fragment = new OrderFragment();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firestore = FirebaseFirestore.getInstance();
        ordersList = new ArrayList<>();
       // fetchOrdersData();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);

        recyclerView = view.findViewById(R.id.orderList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        ordersAdapter = new OrdersAdapter(ordersList);
        recyclerView.setAdapter(ordersAdapter);
        return view;
    }

    private void fetchOrdersData() {
        // Get the current user's ID, you can replace it with your own logic
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        String userID = null;
        if (currentUser != null) {
            userID = currentUser.getUid();
            System.out.println(userID);
        } else {
            // User is not logged in or authenticated
        }

        if (userID != null) {
            // Retrieve the orders documents for the user
            firestore.collection("orders")
                    .whereEqualTo("userID", userID)
                    .get()
                    .addOnSuccessListener(queryDocumentSnapshots -> {
                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            if (documentSnapshot.exists()) {
                                String orderId = documentSnapshot.getId();
                                List<Map<String, Object>> items = (List<Map<String, Object>>) documentSnapshot.get("items");
                                for (Map<String, Object> item : items) {
                                    String productName = (String) item.get("productName");
                                    int quantity = (int) item.get("quantity");

                                    // Perform actions with the retrieved item details
                                    // For example, you can add them to a list or update the adapter
                                    Map<String, Object> itemDetails = new HashMap<>();
                                    itemDetails.put("productName", productName);
                                    itemDetails.put("quantity", quantity);
                                    ordersList.add(itemDetails);
                                }
                            } else {
                                System.out.println("Order document does not exist.");
                            }
                        }
                        ordersAdapter.notifyDataSetChanged();
                    })
                    .addOnFailureListener(e -> {
                        // Handle the failure to retrieve orders data
                        System.out.println("Error retrieving orders data: " + e.getMessage());
                    });
        } else {
            // User ID is null, handle the scenario accordingly
        }
    }

}
