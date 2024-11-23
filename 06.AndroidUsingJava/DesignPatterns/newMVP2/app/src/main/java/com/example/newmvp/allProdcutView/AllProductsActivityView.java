package com.example.newmvp.allProdcutView;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newmvp.R;
import com.example.newmvp.allPresenter.AllProductsPresenter;
import com.example.newmvp.db.ProductLocalDataSource;
import com.example.newmvp.model.Product;
import com.example.newmvp.network.ProductRemoteDataSource;

import java.util.List;

public class AllProductsActivityView extends AppCompatActivity implements IViewAllProducts, AddProductToFav {
    AllProductsPresenter allProductsPresenter;
    AllProductAdapter allProductsAdapter;
    RecyclerView allProductsRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_recycleview_layout);

        allProductsPresenter = new AllProductsPresenter(this, ProductRemoteDataSource.getInstance(),ProductLocalDataSource.getInstance(this));

        allProductsRecyclerView = findViewById(R.id.recViewAllProductsID);
        allProductsRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager =  new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        allProductsRecyclerView.setLayoutManager(layoutManager);
        allProductsAdapter = new AllProductAdapter(this,this);
        allProductsPresenter.requestData();
    }

    @Override
    public void updateList(List<Product> products) {
        allProductsRecyclerView.setAdapter(allProductsAdapter);
        allProductsAdapter.setList(products);
        System.out.println("Done Sending");
        allProductsAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String errorMsg) {

    }

    @Override
    public void onProductClickListener(Product product) {
        allProductsPresenter.requestAdd(product);
    }
}