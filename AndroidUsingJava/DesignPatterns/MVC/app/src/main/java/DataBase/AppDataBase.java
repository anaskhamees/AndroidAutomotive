package DataBase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import model.Product;


@Database(entities = {Product.class},version = 1)
public abstract class AppDataBase extends RoomDatabase {
    private static AppDataBase instance=null;

    public AppDataBase() {
    }

    public abstract FavProductDAO productDAO();


    public static synchronized AppDataBase getInstance(Context context)
    {
        if(instance == null)
        {
            instance = Room.databaseBuilder(context.getApplicationContext(),AppDataBase.class,"productsdb").build();

        }
        return instance;
    }
}