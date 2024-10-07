package com.example.newmvp.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.newmvp.model.Product;

@Database(entities = {Product.class},version = 1 )
public abstract class ProductDataBase extends RoomDatabase {
    private static ProductDataBase DBinstance = null;
    public abstract ProductDAO getProductDAO();
    public static synchronized ProductDataBase getInstance(Context context){
        if(DBinstance == null){
            DBinstance = Room.databaseBuilder(context.getApplicationContext(),ProductDataBase.class,"product").build();
        }
        return DBinstance;
    }

}
