package com.example.newmvp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newmvp.allProdcutView.AllProductsActivityView;
import com.example.newmvp.favProductView.FavAcitivtyView;

public class MainActivity extends AppCompatActivity {

    Button btnGetAllProducts;
    Button btnGetFavProducts;
    Button btnExit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnGetAllProducts = findViewById(R.id.btnGetAllProductsID);
        btnGetFavProducts = findViewById(R.id.btnGetFavProductID);
        btnExit = findViewById(R.id.btnExitID);
        btnGetAllProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent outAllIntent = new Intent(MainActivity.this, AllProductsActivityView.class);
                startActivity(outAllIntent);
            }
        });

        btnGetFavProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent outFavIntent = new Intent(MainActivity.this, FavAcitivtyView.class);
                startActivity(outFavIntent);
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