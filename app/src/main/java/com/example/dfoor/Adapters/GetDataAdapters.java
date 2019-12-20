package com.example.dfoor.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dfoor.Activities.MainActivity;
import com.example.dfoor.Fragments.CartOrdered;
import com.example.dfoor.Models.GetDataModels;
import com.example.dfoor.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class GetDataAdapters extends RecyclerView.Adapter<GetDataAdapters.GetDataHolder> {

    LayoutInflater layoutInflater;
    public List<GetDataModels> data;
    Context c;
    View view;

    private int orderDef = 0;

    public GetDataAdapters(Context c , List<GetDataModels> data) {
        this.c = c;
        this.layoutInflater = LayoutInflater.from(this.c);
        this.data = data;
    }


    @NonNull
    @Override
    public GetDataAdapters.GetDataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = layoutInflater.inflate(R.layout.list_getdata,parent,false);
        return new GetDataHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final GetDataAdapters.GetDataHolder holder, int position) {
        final GetDataModels getDataModels = data.get(position);

        holder.imGetData.setImageResource(getDataModels.getgImg());
        holder.titleGetData.setText(getDataModels.getgTitle());
        holder.hargaGetData.setText(String.valueOf(getDataModels.getgHarga()));
        holder.rpGetData.setText("Rp. ");
        holder.orderDet.setText(String.valueOf(orderDef));
        holder.descGet.setText(getDataModels.getgDesc());

        holder.incOrdered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderDef += 1;
                holder.orderDet.setText(String.valueOf(orderDef));
            }
        });
        holder.decOrdered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderDef -= 1;
                if(orderDef == 0 || orderDef < 0) {
                    orderDef = 0;
                }
                holder.orderDet.setText(String.valueOf(orderDef));
            }
        });

        holder.orderedOkay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(orderDef != 0){
                    Intent i = new Intent(c, MainActivity.class);
                    i.putExtra("iTitle",getDataModels.getgTitle());
                    i.putExtra("image",getDataModels.getgImg());
                    i.putExtra("jumlah",orderDef);
                    i.putExtra("harga",getDataModels.getgHarga());
                    i.putExtra("desc",getDataModels.getgDesc());
                    i.putExtra("status",3);
                    c.startActivity(i);
                }
                else{
                    Toast.makeText(c,"Order Minimal 1 Item",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return (data != null) ? data.size() : 0;
    }

    public class GetDataHolder extends RecyclerView.ViewHolder {
        public TextView titleGetData, hargaGetData, rpGetData, descGet;
        public ImageView imGetData;
        public EditText orderDet;
        public Button incOrdered, decOrdered, orderedOkay;

        public GetDataHolder(@NonNull View itemView)
        {
            super(itemView);

            titleGetData = itemView.findViewById(R.id.titleGet);
            hargaGetData = itemView.findViewById(R.id.hargaGet);
            imGetData = itemView.findViewById(R.id.imageGet);
            rpGetData = itemView.findViewById(R.id.rpGet);
            incOrdered = itemView.findViewById(R.id.btnPlus);
            decOrdered = itemView.findViewById(R.id.btnMinus);
            orderDet = itemView.findViewById(R.id.textGet);
            descGet = itemView.findViewById(R.id.descGet);
            orderedOkay = itemView.findViewById(R.id.btnOrder);

        }
    }
}
