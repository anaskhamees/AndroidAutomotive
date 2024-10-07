package com.example.newmvp.favPresenter;

import androidx.lifecycle.LiveData;

import com.example.newmvp.db.ProductLocalDataSource;
import com.example.newmvp.favProductView.IViewFav;
import com.example.newmvp.model.Product;

import java.util.List;

public class FavPresenter {
    IViewFav iViewRef;
    ProductLocalDataSource localDataSrc;

    public FavPresenter(IViewFav _iView , ProductLocalDataSource localDataSrc){
        iViewRef = _iView;
        this.localDataSrc = localDataSrc;

    }
    //Function to request to delete Data from productLocal
    public void deleteRequest(Product product){
        localDataSrc.deleteProduct(product);
    }

    //I want to use something that will get all live data
    public LiveData<List<Product>> getUpdatedData(){
        return localDataSrc.getStoredFavProducts();
    }

    //This will show error
}
