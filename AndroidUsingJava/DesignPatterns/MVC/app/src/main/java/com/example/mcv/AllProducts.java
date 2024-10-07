package com.example.mcv;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import DataBase.FavProductDAO;
import model.Product;
import networkPKG.*;

public class AllProducts extends AppCompatActivity implements AddFavProduct,networkCallBackInterface  {

    RecyclerView allProductsRecyclerView;
    FavProductDAO dao;
    Button btnAddToFav;
    Repository repo;
    String Tag="AllProducts";
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_all_recycleview_layout);
        allProductsRecyclerView = findViewById(R.id.recViewAllProductsID);
        allProductsRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        allProductsRecyclerView.setLayoutManager(layoutManager);

        repo = Repository.getInstance(this);

        ProductClient product_client = ProductClient.getInstance();
        product_client.makeNetworkCall(this); // Calls the method to fetch product data from the server

    }

    @Override
    public void addFavOnClick(Product favProduct) {
        repo.insert(favProduct);
        Toast.makeText(this,"Add" , Toast.LENGTH_SHORT).show();
    }


    /*
     * Create an instance of ProductsClient (singleton) to initiate the network call
     */


/*
 * @Method: onSuccessResult
 * @Purpose: This method is triggered when the network call successfully fetches the product data.
 *          It logs the size of the list of products returned by the server.
 *
 * @param products: List of Product objects received from the server, representing the product data.
 *
 * @return: No return value.
 */
@Override
public void onSuccessResult(@NonNull List<Product> products) {

    AllProductsAdaptor all_productAdaptor= new AllProductsAdaptor(this,products,this);
    allProductsRecyclerView.setAdapter(all_productAdaptor);

}

/*
 * @Method : onFailureResult
 * @Purpose: This method is triggered when the network call fails to fetch the product data.
 *           It logs the error message received from the failure.
 *
 * @param errorMsg: String representing the error message from the network call failure.
 *
 * @return: No return value.
 */
@Override
public void onFailureResult(String errorMsg) {
    Log.i(Tag, "onFailureResult: " + errorMsg); // Logs the error message received
}

}