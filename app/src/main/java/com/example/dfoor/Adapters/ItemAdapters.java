package com.example.dfoor.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dfoor.Interface.ItemClickListener;
import com.example.dfoor.Activities.MainActivity;
import com.example.dfoor.Models.ItemModels;
import com.example.dfoor.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ItemAdapters extends RecyclerView.Adapter<ItemAdapters.MyHolderItem> {

    public List<ItemModels> data;
    LayoutInflater layoutInflater;
    Context c;

    public ItemAdapters(Context c , List<ItemModels> data) {
        this.c = c;
        this.layoutInflater = LayoutInflater.from(this.c);
        this.data = data;
    }

    @NonNull
    @Override
    public ItemAdapters.MyHolderItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.list_item,parent,false);
        return new MyHolderItem(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapters.MyHolderItem holder, int position) {
        ItemModels itemModels = data.get(position);

        holder.imItemName.setImageResource(itemModels.getImgItem());
        holder.titleItemName.setText(itemModels.getTextItem());
        holder.descItemName.setText(itemModels.getDescItem());
        holder.ratingItemName.setText(String.valueOf(itemModels.getRatingItem()));
        holder.rpItem.setText("Rp. ");

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                String gTitle = data.get(position).getTextItem();
                int gHarga = data.get(position).getRatingItem();
                int gImage = data.get(position).getImgItem();
                String gDesc = data.get(position).getDescItem();

                Intent i = new Intent(c, MainActivity.class);
                i.putExtra("iTitle",gTitle);
                i.putExtra("status",2);
                i.putExtra("harga",gHarga);
                i.putExtra("gambar",gImage);
                i.putExtra("desc",gDesc);
                c.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return (data != null) ? data.size() : 0;
    }

    public class MyHolderItem extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView titleItemName, descItemName, ratingItemName, rpItem;
        public ImageView imItemName;
        ItemClickListener itemClickListener;

        public MyHolderItem(@NonNull View itemView)

        {
            super(itemView);

            titleItemName = itemView.findViewById(R.id.titleItem);
            descItemName = itemView.findViewById(R.id.descItem);
            imItemName = itemView.findViewById(R.id.imageItem);
            ratingItemName = itemView.findViewById(R.id.ratingItem);
            rpItem = itemView.findViewById(R.id.rp);

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
