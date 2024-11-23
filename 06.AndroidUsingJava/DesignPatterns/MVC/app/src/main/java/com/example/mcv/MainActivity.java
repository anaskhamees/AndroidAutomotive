package com.example.mcv;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

import model.*;
import networkPKG.*;

public class MainActivity extends AppCompatActivity {


    Button btnGetAllProduct;
    Button btnGetFavProduct;
    Button btnExit;
    ImageView img;
    int thumbnail;

    /*
     * Tag: Used for logging purposes to identify log messages from MainActivity.
     */
    private final String Tag = "MainActivity";

    /*
     * @Method : onCreate
     * @Purpose: This method is the entry point of the activity. It is called when the activity is created.
     *          It sets up the UI and initiates a network call to fetch product data from the server.
     *
     * @param savedInstanceState: Bundle containing the activity's previously saved state.
     *                            If the activity is being re-initialized after previously being shut down, this contains the most recent data.
     *                            It may be null if the activity is being started for the first time.
     *
     * @return: No return value.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this); // Enables edge-to-edge display mode
        setContentView(R.layout.activity_main); // Sets the content view to the specified layout

        btnGetAllProduct = findViewById(R.id.allProductsID);
        btnGetFavProduct = findViewById(R.id.favProductID);
        btnExit = findViewById(R.id.exitID);
        btnGetAllProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AllProducts.class);
                startActivity(intent);
            }
        });

        btnGetFavProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FavProducts.class);
                startActivity(intent);
            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
