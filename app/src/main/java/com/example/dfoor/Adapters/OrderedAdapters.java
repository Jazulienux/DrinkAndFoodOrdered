package com.example.dfoor.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dfoor.Models.OrderedItem;
import com.example.dfoor.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class OrderedAdapters extends RecyclerView.Adapter<OrderedAdapters.MyOrderedHolder> {

    List<OrderedItem> listOrderedItems;
    LayoutInflater layoutInflater;
    Context c;

    int orderDef = 0;

    DatabaseReference database;

    public OrderedAdapters(Context c , List<OrderedItem> data) {
        this.c = c;
        this.layoutInflater = LayoutInflater.from(this.c);
        this.listOrderedItems = data;
    }

    @NonNull
    @Override
    public OrderedAdapters.MyOrderedHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.list_ordered,parent,false);
        return new MyOrderedHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final OrderedAdapters.MyOrderedHolder holder, int position) {
        final OrderedItem getDataModelsOrder = listOrderedItems.get(position);
        final int sum = 0;

        holder.titleOrder.setText(getDataModelsOrder.getTitle());
        holder.imgOrder.setImageResource(getDataModelsOrder.getImage());
        holder.descOrder.setText(getDataModelsOrder.getDesc());
        holder.hargaOrder.setText(String.valueOf(getDataModelsOrder.getHarga()));
        holder.textOrder.setText(String.valueOf(getDataModelsOrder.getJumlahPesanan()));
        holder.totalOrderHarga.setText(String.valueOf(getDataModelsOrder.getTotalOrder()));

        final String id = getDataModelsOrder.getKey();

        database = FirebaseDatabase.getInstance().getReference();


        holder.btnCancelOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                database.child("Ordered").child(id).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(c,"Order Canceled",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        holder.btnPlusOr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                orderDef = getDataModelsOrder.getJumlahPesanan();
                orderDef++;

                if(orderDef != 0 ){
                    int sum = getDataModelsOrder.getHarga() * orderDef;
                    holder.totalOrderHarga.setText(String.valueOf(sum));
                    OrderedItem or = new OrderedItem(getDataModelsOrder.getTitle(),getDataModelsOrder.getDesc(),getDataModelsOrder.getHarga(),orderDef,getDataModelsOrder.getImage(),sum);

                    database.child("Ordered").child(id).setValue(or).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(c,"Update Success",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else {
                    database.child("Ordered").child(id).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(c,"Order Canceled",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        holder.btnMinusOr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderDef = getDataModelsOrder.getJumlahPesanan();
                if(orderDef <= 0){
                    orderDef = 0;
                }
                else{
                    orderDef--;
                }

                if(orderDef != 0 ){
                    int sum = getDataModelsOrder.getHarga() * orderDef;
                    holder.totalOrderHarga.setText(String.valueOf(sum));
                    OrderedItem or = new OrderedItem(getDataModelsOrder.getTitle(),getDataModelsOrder.getDesc(),getDataModelsOrder.getHarga(),orderDef,getDataModelsOrder.getImage(),sum);

                    database.child("Ordered").child(id).setValue(or).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(c,"Update Success",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else{
                    database.child("Ordered").child(id).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(c,"Order Canceled",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });


    }

    @Override
    public int getItemCount() {
            return (listOrderedItems != null) ? listOrderedItems.size() : 0;
    }

    public class MyOrderedHolder extends RecyclerView.ViewHolder {
        public TextView titleOrder , descOrder , hargaOrder ,totalOrderHarga;
        public ImageView imgOrder;
        public EditText textOrder;
        public Button btnCancelOrder, btnMinusOr, btnPlusOr;

        public MyOrderedHolder(@NonNull View itemView) {
            super(itemView);

            titleOrder = itemView.findViewById(R.id.titleOrdered);
            imgOrder = itemView.findViewById(R.id.imageOrdered);
            descOrder = itemView.findViewById(R.id.descOrdered);
            hargaOrder = itemView.findViewById(R.id.hargaOrdered);
            textOrder = itemView.findViewById(R.id.textOrdered);
            totalOrderHarga = itemView.findViewById(R.id.totalOrderProc);
            btnCancelOrder = itemView.findViewById(R.id.btnDelOrder);
            btnMinusOr = itemView.findViewById(R.id.btnMinusOrdered);
            btnPlusOr = itemView.findViewById(R.id.btnPlusOrdered);
        }
    }
}
