package com.example.trainfooddelivryapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private List<Product> foodList = new ArrayList<>();
    private List<Product> snacksList = new ArrayList<>();
    private List<Product> medicineList = new ArrayList<>();



    private ProductAdapter productAdapter;
    TextView foodView;
    TextView snackView;
    TextView medView;
    public HomeFragment() {
        // Required empty public constructor
    }


    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataLoader();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.prodcutList);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        productAdapter = new ProductAdapter(foodList,getContext());
        recyclerView.setAdapter(productAdapter);
        productAdapter.updateData(foodList);

        foodView=view.findViewById(R.id.food);
         snackView=view.findViewById(R.id.snacks);
         medView=view.findViewById(R.id.medicine);

        foodView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Update the data set of the ProductAdapter with the food products
                productAdapter.updateData(foodList);
            }
        });

        snackView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Update the data set of the ProductAdapter with the snack products
                productAdapter.updateData(snacksList);
            }
        });

        medView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Update the data set of the ProductAdapter with the medicine products
                productAdapter.updateData(medicineList);
            }
        });
         return  view;
    }



    public  void dataLoader(){
        Product product1 = new Product("Sandwich", "Food", "Classic sandwich with a variety of fillings", 5.99, "https://images.unsplash.com/photo-1553909489-cd47e0907980?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NHx8c2FuZHdpY2h8ZW58MHx8MHx8fDA%3D&auto=format&fit=crop&w=500&q=60",34);
        foodList.add(product1);

        Product product2 = new Product("Fruit Salad", "Food", "Assortment of fresh fruits for a healthy snack", 4.49, "https://www.thefoodexcafe.com/static/www/images/productImages/800x800/Fruits-Salad-0-1658142534562.jpg",43);
        foodList.add(product2);

        Product product3 = new Product("Trail Mix", "Food", "Nutritious mix of dried fruits, nuts, and seeds", 3.99, "https://encrypted-tbn0.gstatic.com/shopping?q=tbn:ANd9GcTKKp0t_kkG3RL72A8etVskNRU600YSr-2lg5ZWdw4qLOWIgL6QgJz7jZPVUoajzvnteqDCJeu9Z5Xr0MwhqJCJqFiy6UT1ym1NrGNtHipwJpbS_VrEXGeR",12);
        foodList.add(product3);

        Product product4 = new Product("Instant Noodles", "Food", "Quick and easy noodles for a convenient meal", 2.49, "https://encrypted-tbn0.gstatic.com/shopping?q=tbn:ANd9GcQTgChMXJVQz-cWcqGw8g_zZwh5cDkOB6Tcy_ZO2KWXmnsUcoC_V2B9zjoDsRyyNZkMmF9e-_mTywy8IEndz431VK8tSYkZ2Ddy6tFzvkHupAlZ_s8-f-H-NA",34);
        foodList.add(product4);

        Product food1 = new Product("Apple", "Food", "Fresh and juicy apple", 1.99, "https://www.bigbasket.com/media/uploads/p/s/40128384_2-fresho-apple-pink-lady.jpg",23);
        foodList.add(food1);

        Product food2 = new Product("Bread", "Food", "Freshly baked bread", 2.49, "https://www.bigbasket.com/media/uploads/p/s/70001169_11-english-oven-bread-brown.jpg",35);
        foodList.add(food2);

        // Snacks
        Product snacks1 = new Product("Chips", "Snacks", "Crispy potato chips", 1.49, "https://www.bigbasket.com/media/uploads/p/s/40263293_1-beyond-snack-kerala-banana-chips-original-style-thin-crispy-delicious.jpg",23);
        snacksList.add(snacks1);

        Product snacks2 = new Product("Cookies", "Snacks", "Delicious chocolate chip cookies", 2.99, "https://encrypted-tbn3.gstatic.com/shopping?q=tbn:ANd9GcRbqQ23UHzyzy3n_DLLLDzpgnNWk0AEfRtvqzrJFKHf8SvFlxZ3dkCC6m4U2YZIzHQ7bvr-_Nv33zlL-2hHvgzKywakvHhBzGrp-_q7BNY",54);
        snacksList.add(snacks2);

        Product snacks3 = new Product("Popcorn", "Snacks", "Butter-flavored popcorn", 1.99, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR7bU2rhKz5lruEgBc_lqidNxxSpi7c9GXbT_SfOUtg9b8Ip-SXLT5ACPVgkg&s",54);
        snacksList.add(snacks3);

        Product snacks4 = new Product("Pretzels", "Snacks", "Crunchy pretzel sticks", 1.79, "https://www.bigbasket.com/media/uploads/p/s/40280417_1-ben-jerrys-netflix-chilld-peanut-butter-ice-cream-with-sweet-salty-pretzel-swirls-brownie-pieces.jpg",65);
        snacksList.add(snacks4);

        Product snacks5 = new Product("Granola Bars", "Snacks", "Healthy and energy-boosting bars", 3.49, "https://encrypted-tbn2.gstatic.com/shopping?q=tbn:ANd9GcS-_WMELfyvsguDrsSadvnccXhmzX-JdvX8BoFZIVlxEVLYKOXPy5yop0iXiAp-Edo-0LXN2VXTAzMa8gkCvoFP32moKZfA2uHguT0IHUk",41);
        snacksList.add(snacks5);

        // Medicine
        Product medicine1 = new Product("Painkiller", "Medicine", "Relieves pain and reduces fever", 5.99, "https://onemg.gumlet.io/q_auto,w_150,f_auto,h_150,c_fit/bzvdcidctdke0ndmdagh.jpg",21);
        medicineList.add(medicine1);

        Product medicine2 = new Product("Cold & Flu Medicine", "Medicine", "Provides relief from cold and flu symptoms", 7.49, "https://onemg.gumlet.io/f_auto,w_150,c_fit,q_auto,h_150/zaoniru9woncxf4acu5z.jpg",39);
        medicineList.add(medicine2);

        Product medicine3 = new Product("Allergy Relief", "Medicine", "Helps relieve allergy symptoms", 4.99, "https://onemg.gumlet.io/w_150,c_fit,f_auto,h_150,q_auto/91b3f2c196834fcbb44ef1a0a4a419d9.jpg",43);
        medicineList.add(medicine3);

        Product medicine4 = new Product("Antacid", "Medicine", "Relieves heartburn and acid indigestion", 3.99, "https://onemg.gumlet.io/c_fit,w_150,q_auto,h_150,f_auto/cropped/jk20l4faeuw7l3w5t2zo.jpg",23);
        medicineList.add(medicine4);

        Product medicine5 = new Product("Vitamin C", "Medicine", "Boosts immune system and supports overall health", 9.99, "https://onemg.gumlet.io/w_150,c_fit,f_auto,h_150,q_auto/ptttdpxsbgds0mbflhvi.jpg",41);
        medicineList.add(medicine5);
    }
}