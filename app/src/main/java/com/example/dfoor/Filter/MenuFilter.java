package com.example.dfoor.Filter;

import android.widget.Filter;

import com.example.dfoor.Adapters.HomeAdapters;
import com.example.dfoor.Models.HomeModels;

import java.util.ArrayList;
import java.util.List;

public class MenuFilter extends Filter {

    List<HomeModels> filterList;
    HomeAdapters homeAdapters;

    public MenuFilter(List<HomeModels> filterList, HomeAdapters homeAdapters) {
        this.filterList = filterList;
        this.homeAdapters = homeAdapters;
    }

    @Override
    protected FilterResults performFiltering(CharSequence charSequence) {

        FilterResults results = new FilterResults();

        if(charSequence != null && charSequence.length() > 0){
            charSequence = charSequence.toString().toUpperCase();

            List<HomeModels> filterModel = new ArrayList<>();

            for (int i = 0; i < filterList.size(); i ++){
                if(filterList.get(i).getTitle().toUpperCase().contains(charSequence)){
                    filterModel.add(filterList.get(i));
                }
            }

            results.count = filterModel.size();
            results.values = filterModel;
        }
        else {
            results.count = filterList.size();
            results.values = filterList;
        }

        return results;
    }

    @Override
    protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
        homeAdapters.data = (List<HomeModels>) filterResults.values;
        homeAdapters.notifyDataSetChanged();
    }
}
