package com.example.dfoor.Fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dfoor.Adapters.ItemAdapters;
import com.example.dfoor.Models.ItemModels;
import com.example.dfoor.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ItemFragments extends Fragment {

    RecyclerView recyclerView;
    List<ItemModels> dataItem = new ArrayList<>();
    View view;
    ItemAdapters itemAdapters;
    ItemModels itemModels;

    public ItemFragments() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        Intent i = getActivity().getIntent();

        String titleQuery = i.getStringExtra("iTitle");

        view = inflater.inflate(R.layout.fragment_item, container, false);

        viewItemQuery(titleQuery);

        return view;
    }

    public void viewItemQuery(String titleQuery) {
        itemModels = new ItemModels();
        if(titleQuery.equals("Coffee")){
            itemModels = new ItemModels("Tea Pull","Tea Pull New Varian",R.drawable.tea_tarik,20000);
            dataItem.add(itemModels);

            itemModels = new ItemModels("Coffee Gayo","Cofee Gayoo New Varian",R.drawable.coffee_gayo,10000);
            dataItem.add(itemModels);

            itemModels = new ItemModels("Coffee Late","Cofee Late New Varian",R.drawable.choco_cofee,15000);
            dataItem.add(itemModels);

            itemModels = new ItemModels("Coffee Expreso","Cofee Expresso New Varian",R.drawable.expresso_cofee,30000);
            dataItem.add(itemModels);


        }
        else if(titleQuery.equals("Food")){

            itemModels = new ItemModels("Destroy Chicken","Destroy Chicken New Varian",R.drawable.ayam_geprek,10000);
            dataItem.add(itemModels);

            itemModels = new ItemModels("Churry Chicken","Churry Chicken New Varian",R.drawable.chicken,20000);
            dataItem.add(itemModels);
        }
        else if(titleQuery.equals("Ice")){

            itemModels = new ItemModels("Ice Mocachino","Ice Mocachino New Varian",R.drawable.mocachino,5000);
            dataItem.add(itemModels);

            itemModels = new ItemModels("Ice Capuchino","Ice Capuchino Expresso",R.drawable.cappuccino,8000);
            dataItem.add(itemModels);
        }
        else if(titleQuery.equals("Salad")){

            itemModels = new ItemModels("Salad Vegetales","Salad Vegetales New Varian",R.drawable.salad_idn,10000);
            dataItem.add(itemModels);

            itemModels = new ItemModels("Salad New Varian","New Varian Salad Strawberry",R.drawable.salad_mly,30000);
            dataItem.add(itemModels);
        }
        else if(titleQuery.equals("Snack")){

            itemModels = new ItemModels("Pizza","Pizza New Varian",R.drawable.pizza,10000);
            dataItem.add(itemModels);

            itemModels = new ItemModels("Hot Dog","Hot Dog New Varian",R.drawable.hot_dog,10000);
            dataItem.add(itemModels);

            itemModels = new ItemModels("Sticky Rise","Sticky Rise New Varian",R.drawable.stickyrise,10000);
            dataItem.add(itemModels);
        }

        recyclerView = view.findViewById(R.id.rvItem);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        itemAdapters = new ItemAdapters(getActivity(),dataItem);
        recyclerView.setAdapter(itemAdapters);
    }

}
