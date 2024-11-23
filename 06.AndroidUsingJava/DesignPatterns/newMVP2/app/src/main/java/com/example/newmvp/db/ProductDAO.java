package com.example.newmvp.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.newmvp.model.Product;

import java.util.List;

@Dao
public interface ProductDAO {
    @Query("SELECT * FROM products_table")
    LiveData<List<Product>> getFavProducts();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertProduct(Product product);

    @Delete
    void deleteProduct(Product product);
}

