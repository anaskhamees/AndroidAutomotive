package com.example.newmvp.favProductView;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newmvp.R;
import com.example.newmvp.allProdcutView.AddProductToFav;
import com.example.newmvp.db.ProductLocalDataSource;
import com.example.newmvp.favPresenter.FavPresenter;
import com.example.newmvp.model.Product;

import java.util.List;

public class FavAcitivtyView extends AppCompatActivity implements IViewFav, AddProductToFav {
    RecyclerView favRecyclerView;
    FavProductAdapter favAdapter;
    FavPresenter favPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_recycleview_layout);

        favPresenter = new FavPresenter(this,ProductLocalDataSource.getInstance(this));
        favRecyclerView = findViewById(R.id.favRecycleID);
        favRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager =  new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        favRecyclerView.setLayoutManager(layoutManager);
        favAdapter = new FavProductAdapter(this,this);

         Observer<List<Product>> observer = new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                favAdapter.setList(products);
                favRecyclerView.setAdapter(favAdapter);
                favAdapter.notifyDataSetChanged();
            }
        };

        LiveData<List<Product>> liveData = favPresenter.getUpdatedData();


        liveData.observe(this,observer);
    }
    @Override
    public LiveData<List<Product>> updateList() {
        //This should Call presenter to
        return null;
    }

    @Override
    public void showError(String errorMsg) {

    }

    @Override
    public void onProductClickListener(Product product) {
        favPresenter.deleteRequest(product);
    }
}