package com.example.dfoor.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.dfoor.Fragments.CartOrdered;
import com.example.dfoor.Fragments.GetDataFragments;
import com.example.dfoor.Fragments.HomeFragments;
import com.example.dfoor.Fragments.ItemFragments;
import com.example.dfoor.Models.OrderedItem;
import com.example.dfoor.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private  DatabaseReference database;
    private ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent i = getIntent();
        final FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        String gTitleMain = i.getStringExtra("iTitle");
        int status = i.getIntExtra("status",0);
        int gtImg = i.getIntExtra("image",0);
        int gtJumlah = i.getIntExtra("jumlah",0);
        int gtHarga = i.getIntExtra("harga",0);
        String gDesc = i.getStringExtra("desc");


        loadFragment(new HomeFragments());

        loadFragment(new HomeFragments());
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);
        // beri listener pada saat item/menu bottomnavigation terpilih
        bottomNavigationView.setOnNavigationItemSelectedListener(this);




        try {
            database = FirebaseDatabase.getInstance().getReference();
        }
        catch (Exception e){
            System.out.println(e);
        }

        if(status == 1)
        {
            loadFragment(new ItemFragments());
        }
        else if(status == 2){
            loadFragment(new GetDataFragments());
        }
        else if(status == 3){

            int total = 0;
            if(gtJumlah != 0){
                total = gtHarga * gtJumlah;
            }


            OrderedItem orderedItem = new OrderedItem(gTitleMain,gDesc,gtHarga,gtJumlah,gtImg,total);

            loading = ProgressDialog.show(MainActivity.this,null,"Waiting ....",
                    true,false);

            database.child("Ordered")
                    .push().
                    setValue(orderedItem).addOnSuccessListener(this, new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    loading.dismiss();

                    Toast.makeText(MainActivity.this,"Success Save To Firebase",Toast.LENGTH_SHORT).show();
                    loadFragment(new CartOrdered());

                }
            });

        }
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.changeFrame, fragment)
                    .addToBackStack(null)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;
        switch (menuItem.getItemId()) {
            case R.id.home:
                fragment = new HomeFragments();
                break;
            case R.id.cart:
                fragment = new CartOrdered();
                break;
            case R.id.invoice:
                break;

            case R.id.logout:
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
                break;


        }
        return loadFragment(fragment);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
