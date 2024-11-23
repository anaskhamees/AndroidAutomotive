package com.example.newmvp.db;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;

import com.example.newmvp.model.Product;

import java.util.List;

public class ProductLocalDataSource {
    private Context context;
    private ProductDAO productDAO;
    private LiveData<List<Product>> storedFavProducts;
    private static ProductLocalDataSource repo = null;

    private ProductLocalDataSource(Context _context) {
        context = _context;
        ProductDataBase db = ProductDataBase.getInstance(context.getApplicationContext());
        productDAO = db.getProductDAO();
        storedFavProducts = productDAO.getFavProducts();
    }

    public static ProductLocalDataSource getInstance(Context m_context) {
        if (repo == null) {
            repo = new ProductLocalDataSource(m_context);
        }
        return repo;
    }

    public LiveData<List<Product>> getStoredFavProducts() {
        return storedFavProducts;
    }

    public void deleteProduct(Product product) {
        new Thread() {
            @Override
            public void run() {
                productDAO.deleteProduct(product);
            }
        }.start();
        Toast.makeText(context, "Product Deleted from Fav", Toast.LENGTH_SHORT).show();

    }

    public void insert(Product product) {
        new Thread() {
            @Override
            public void run() {
                productDAO.insertProduct(product);
            }
        }.start();
        Toast.makeText(context, "Product Added To Fav", Toast.LENGTH_SHORT).show();
    }
}
