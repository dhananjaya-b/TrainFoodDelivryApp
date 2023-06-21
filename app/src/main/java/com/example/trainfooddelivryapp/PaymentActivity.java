package com.example.trainfooddelivryapp;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentActivity extends AppCompatActivity implements PaymentResultListener {

    private Product product;
    private String userId;
    Button makePaymentButton;
    Button paymentSuccessButton;
    Button paymentFailureButton;
    int quantity;
    FirebaseFirestore firestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        // Retrieve the product object from the intent
        product = getIntent().getParcelableExtra("product");
        quantity=getIntent().getIntExtra("quantity",0);
        makePaymentButton = findViewById(R.id.payment);
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        userId = currentUser.getUid();
        makePaymentButton.setOnClickListener(view -> startPayment());
    }


    private void startPayment() {
        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_test_8ZxcNyMoCFszKL");

        try {
            JSONObject options = new JSONObject();
            options.put("name", product.getName());
            options.put("description", "Payment for " + product.getName());
            options.put("image", product.getImageLink());
            options.put("currency", "INR");
            options.put("amount", (int) (product.getPrice() * 100*quantity)); // Amount in paise

            checkout.open(this, options);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onPaymentSuccess(String razorpayPaymentID) {
        // Payment was successful

        // Save the order details to Firestore
        firestore = FirebaseFirestore.getInstance();

        // Create a map of the order data
        Map<String, Object> orderData = new HashMap<>();
        orderData.put("userId", userId);  // Replace "YOUR_USER_ID" with the actual user ID
        orderData.put("items", getProductListMap(product, quantity));

        // Add the order data to Firestore
        firestore.collection("orders")
                .add(orderData)
                .addOnSuccessListener(documentReference -> {
                    // Firestore write was successful
                    String orderId = documentReference.getId();
                    // You can perform additional actions here, such as displaying a success message or navigating to another activity
                })
                .addOnFailureListener(e -> {
                    // Firestore write failed

                    // You can handle the failure scenario here, such as displaying an error message or logging the error
                });
    }

    private List<Map<String, Object>> getProductListMap(Product product, int quantity) {
        List<Map<String, Object>> productList = new ArrayList<>();

        // Create a map of the product details
        Map<String, Object> productData = new HashMap<>();
        productData.put("productName", product.getName());
        productData.put("price", product.getPrice());
        productData.put("image", product.getImageLink());
        productData.put("quantity", quantity);

        productList.add(productData);

        return productList;
    }


    @Override
    public void onPaymentError(int i, String s) {

    }
}
