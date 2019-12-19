package com.example.dfoor.Fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dfoor.Adapters.GetDataAdapters;
import com.example.dfoor.Models.GetDataModels;
import com.example.dfoor.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class GetDataFragments extends Fragment {

    RecyclerView recyclerView;
    View view;
    GetDataAdapters getDataAdapters;

    List<GetDataModels> dataItem = new ArrayList<>();

    public GetDataFragments() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Intent i = getActivity().getIntent();
        String titleQuery = i.getStringExtra("iTitle");
        int hargaQuery = i.getIntExtra("harga",0);
        int gambarQuery = i.getIntExtra("gambar",0);
        String descQuery = i.getStringExtra("desc");

        GetDataModels getDataModels = new GetDataModels();
        getDataModels.setgTitle(titleQuery);
        getDataModels.setgHarga(hargaQuery);
        getDataModels.setgImg(gambarQuery);
        getDataModels.setgDesc(descQuery);

        dataItem.add(getDataModels);

        view = inflater.inflate(R.layout.fragment_getdata, container, false);

        recyclerView = view.findViewById(R.id.rvGetData);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        getDataAdapters = new GetDataAdapters(getActivity(),dataItem);
        recyclerView.setAdapter(getDataAdapters);

        return view;
    }

}
