package com.example.dfoor.Fragments;


import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dfoor.Adapters.OrderedAdapters;
import com.example.dfoor.Models.OrderedItem;
import com.example.dfoor.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartOrdered extends Fragment {


    RecyclerView recyclerView;
    View view;
    OrderedAdapters orderedAdapters;

    List<OrderedItem> orderedItemsList;
    private DatabaseReference database;
    private ProgressDialog loading;

    public CartOrdered() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_cart_ordered, container, false);

        database = FirebaseDatabase.getInstance().getReference();
        recyclerView = view.findViewById(R.id.rvProcessOrdered);


        database.child("Ordered").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                orderedItemsList = new ArrayList<>();
                if(dataSnapshot.exists()){
                    for (DataSnapshot ds : dataSnapshot.getChildren()){
                        OrderedItem or = ds.getValue(OrderedItem.class);
                        or.setKey(ds.getKey());
                        orderedItemsList.add(or);
                    }
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    orderedAdapters = new OrderedAdapters(getActivity(),orderedItemsList);
                    recyclerView.setAdapter(orderedAdapters);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return view;
    }

}
