package com.example.newmvp.favProductView;

import androidx.lifecycle.LiveData;

import com.example.newmvp.model.Product;

import java.util.List;

public interface IViewFav {
    public LiveData<List<Product>> updateList();
    public void showError(String errorMsg);
}
