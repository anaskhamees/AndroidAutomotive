package com.example.newmvp.allPresenter;

import com.example.newmvp.allProdcutView.IViewAllProducts;
import com.example.newmvp.db.ProductLocalDataSource;
import com.example.newmvp.model.Product;
import com.example.newmvp.network.NetworkCallBackInterface;
import com.example.newmvp.network.ProductRemoteDataSource;

import java.util.List;

public class AllProductsPresenter implements NetworkCallBackInterface {

    IViewAllProducts iViewRef;
    ProductRemoteDataSource productRef;
    ProductLocalDataSource localSrc;
    public AllProductsPresenter(IViewAllProducts iview , ProductRemoteDataSource productRef, ProductLocalDataSource localSrc){
        iViewRef = iview;
        this.productRef = productRef;
        this.localSrc =localSrc ;
    }
    public void requestData(){ productRef.makeNetworkCall(this); }

    public void requestAdd(Product product)
    {
        localSrc.insert(product);
    }
    public void onSuccessful(List<Product> products)
    {
        iViewRef.updateList(products);
        System.out.println("I am here");
    }

    @Override
    public void onFailureResult(String errorMsg) {
        iViewRef.showError(errorMsg);
    }
}
