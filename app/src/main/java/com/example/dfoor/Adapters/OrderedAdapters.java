package com.example.dfoor.Adapters;

import android.content.Context;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class OrderedAdapters extends RecyclerView.Adapter<OrderedAdapters.MyOrderedHolder> {

    List<OrderedItem> listOrderedItems;
    LayoutInflater layoutInflater;
    Context c;

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
    public void onBindViewHolder(@NonNull OrderedAdapters.MyOrderedHolder holder, int position) {
        OrderedItem getDataModelsOrder = listOrderedItems.get(position);

        holder.titleOrder.setText(getDataModelsOrder.getTitle());
        holder.imgOrder.setImageResource(getDataModelsOrder.getImage());
        holder.descOrder.setText(getDataModelsOrder.getDesc());
        holder.hargaOrder.setText(String.valueOf(getDataModelsOrder.getHarga()));
        holder.textOrder.setText(String.valueOf(getDataModelsOrder.getJumlahPesanan()));
        holder.totalOrderHarga.setText(String.valueOf(getDataModelsOrder.getTotalOrder()));

        final String id = getDataModelsOrder.getKey();
        Toast.makeText(c,id,Toast.LENGTH_SHORT).show();

        holder.btnCancelOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database = FirebaseDatabase.getInstance().getReference();

                database.child("Ordered").child(id).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
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
        public Button btnCancelOrder;

        public MyOrderedHolder(@NonNull View itemView) {
            super(itemView);

            titleOrder = itemView.findViewById(R.id.titleOrdered);
            imgOrder = itemView.findViewById(R.id.imageOrdered);
            descOrder = itemView.findViewById(R.id.descOrdered);
            hargaOrder = itemView.findViewById(R.id.hargaOrdered);
            textOrder = itemView.findViewById(R.id.textOrdered);
            totalOrderHarga = itemView.findViewById(R.id.totalOrderProc);
            btnCancelOrder = itemView.findViewById(R.id.btnDelOrder);
        }
    }
}
