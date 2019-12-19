package com.example.dfoor.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dfoor.Filter.MenuFilter;
import com.example.dfoor.Interface.ItemClickListener;
import com.example.dfoor.Activities.MainActivity;
import com.example.dfoor.Models.HomeModels;
import com.example.dfoor.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HomeAdapters extends RecyclerView.Adapter<HomeAdapters.MyViewHolder> implements Filterable
        {

        public List<HomeModels> data, filteList;
        LayoutInflater layoutInflater;
        MenuFilter menuFilter;
        Context c;

        public HomeAdapters(Context c , List<HomeModels> data) {
            this.c = c;
            this.layoutInflater = LayoutInflater.from(this.c);
            this.data = data;
            this.filteList = data;
        }

        @NonNull
        @Override
        public HomeAdapters.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = layoutInflater.inflate(R.layout.list_menu,parent,false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final HomeAdapters.MyViewHolder holder, final int position) {
            HomeModels homeModels = data.get(position);

            holder.imName.setImageResource(homeModels.getImg());
            holder.titleName.setText(homeModels.getTitle());
            holder.descName.setText(homeModels.getDes());


            holder.setItemClickListener(new ItemClickListener() {
                @Override
                public void onItemClick(View v, int position) {
                    String gTitle = data.get(position).getTitle();

                    Intent i = new Intent(c, MainActivity.class);
                    i.putExtra("iTitle",gTitle);
                    i.putExtra("status",1);
                    c.startActivity(i);
                }
            });

        }

        @Override
        public int getItemCount() {
            return (data != null) ? data.size() : 0;
        }

        @Override
        public Filter getFilter() {

            if(menuFilter == null){
                menuFilter = new MenuFilter(filteList,this);

            }
            return menuFilter;
        }


        public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
            public TextView titleName, descName;
            public ImageView imName;
            ItemClickListener itemClickListener;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                titleName = itemView.findViewById(R.id.titleView);
                descName = itemView.findViewById(R.id.descView);
                imName = itemView.findViewById(R.id.imageView);

                itemView.setOnClickListener(this);

            }
            @Override
            public void onClick(View view) {
                this.itemClickListener.onItemClick(view,getLayoutPosition());
            }

            public void setItemClickListener(ItemClickListener ic){
                this.itemClickListener = ic;
            }
        }
}
