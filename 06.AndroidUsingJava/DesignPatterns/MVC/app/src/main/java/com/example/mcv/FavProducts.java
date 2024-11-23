package com.example.mcv;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import model.Product;

public class FavProducts extends AppCompatActivity implements RemoveFavProduct {

    RecyclerView favRecyclerView;

    LiveData<List<Product>> MyFavProducts;
    Repository repo;
    FavProductsAdaptor favAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_fav_recycleview_layout);
        repo = Repository.getInstance(this);
        MyFavProducts =  repo.getStoredData();
        Log.i("TAG", MyFavProducts.toString());
        favRecyclerView = findViewById(R.id.favRecycleID);
        favRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        favRecyclerView.setLayoutManager(layoutManager);
        favAdapter=new FavProductsAdaptor(this,this);

        favRecyclerView.setAdapter(favAdapter);

        // Observe LiveData and update the recycle view by the newest data
        MyFavProducts.observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                favAdapter.setAdapter(products);
            }
        });

    }



    @Override
    public void deleteFavOnClick(Product favProduct) {
        repo.delete(favProduct);
        Toast.makeText(this,"Deleted" , Toast.LENGTH_SHORT).show();
    }
}