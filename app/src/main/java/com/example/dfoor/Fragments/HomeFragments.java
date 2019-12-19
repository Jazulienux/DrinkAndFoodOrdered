package com.example.dfoor.Fragments;


import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.dfoor.Adapters.HomeAdapters;
import com.example.dfoor.Models.HomeModels;
import com.example.dfoor.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragments extends Fragment {

    RecyclerView recyclerView;
    List<HomeModels> data = new ArrayList<>();
    SharedPreferences preferences;
    View view;
    HomeAdapters homeAdapters;

    public HomeFragments() {
        // Required empty public constructor
    }


    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu,menu);

        MenuItem item = menu.findItem(R.id.seachMenu);

        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                homeAdapters.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newQuery) {
                homeAdapters.getFilter().filter(newQuery);
                return false;
            }
        });
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.sortingMenu:
                sortingMenu();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void sortingMenu() {
        String[] ops = {"Ascending","Descending"};

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Sort By");
        builder.setIcon(R.drawable.ic_action_name);

        builder.setItems(ops, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(i == 0){
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("Sort","ascending");
                    editor.apply();
                    getMyList();
                }
                else if(i == 1){
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("Sort","descending");
                    editor.apply();
                    getMyList();
                }
            }
        });

        builder.create().show();
    }

    private void getMyList(){
        data.removeAll(data);

        HomeModels homeModels = new HomeModels("Coffee","New Varian From Indonesian Cofee, Ordered Now",R.drawable.cofee);
        data.add(homeModels);

        homeModels = new HomeModels("Ice","New Varian From Ice Ready In Here, Ordered Now",R.drawable.ice);
        data.add(homeModels);

        homeModels = new HomeModels("Food","New Varian From Food Ready In Here, Ordered Now",R.drawable.food);
        data.add(homeModels);

        homeModels = new HomeModels("Salad","New Varian From Salad Ready In Here, Ordered Now",R.drawable.salad);
        data.add(homeModels);

        homeModels = new HomeModels("Snack","New Varian From Snack Ready In Here, Ordered Now",R.drawable.snack);
        data.add(homeModels);

        recyclerView = view.findViewById(R.id.rvSuperHero);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        homeAdapters = new HomeAdapters(getActivity(),data);
        recyclerView.setAdapter(homeAdapters);

        String mSort = preferences.getString("Sort","ascending");

        if(mSort.equals("ascending")){
            Collections.sort(data,HomeModels.BY_TITLE_ASCENDING);
        }
        else if(mSort.equals("descending")){
            Collections.sort(data,HomeModels.BY_TITLE_DESCENDING);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);

        view = inflater.inflate(R.layout.fragment_home, container, false);

        preferences = getActivity().getSharedPreferences("My Pref", Context.MODE_PRIVATE);
        getMyList();

        return view;
    }

}
