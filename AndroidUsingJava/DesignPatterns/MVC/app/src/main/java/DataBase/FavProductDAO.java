package DataBase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Ignore;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import java.util.List;
import model.Product;

@Dao
public interface FavProductDAO {

    @Query("Select * FROM Favproducts_table")
    LiveData<List<Product>> getFavProduct();
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertProduct (Product product);
    @Delete
    void deleteProduct(Product product);


}
