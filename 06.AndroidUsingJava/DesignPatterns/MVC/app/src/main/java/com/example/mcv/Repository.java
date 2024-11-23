package com.example.mcv;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

import DataBase.AppDataBase;
import DataBase.FavProductDAO;
import model.Product;

public class Repository {

    private Context context;
    private FavProductDAO DAO;

    private LiveData<List<Product>> favStoredProducts;

    private static Repository repo = null;

    private Repository(Context _context)

    {
        this.context = _context;
        AppDataBase DB = AppDataBase.getInstance(context.getApplicationContext());
        DAO = DB.productDAO();
        favStoredProducts = DAO.getFavProduct();
        Log.i("TAG", "GetDataBaseSucessfull");
    }

    public static Repository getInstance(Context _context)
    {
        if(repo == null) {
            repo = new Repository(_context);
        }
        return  repo;
    }

    //get products from database
    public LiveData<List<Product>> getStoredData() {

        if(favStoredProducts == null)
        {
            Log.i("TAG", "NULL");
        }
        Log.i("TAG", favStoredProducts.toString());
        Log.i("TAG", "Favourate Products From Data base");
        return favStoredProducts;

    }


    public void delete(Product product) {
        if(product.getId()!=1)
        {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    DAO.deleteProduct(product);
                }
            }).start();
        }

    }

    public void insert(Product Product) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                DAO.insertProduct(Product);
            }
        }).start();
    }
}
