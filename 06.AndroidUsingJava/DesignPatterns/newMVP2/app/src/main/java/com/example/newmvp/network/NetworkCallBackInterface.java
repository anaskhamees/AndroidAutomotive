package com.example.newmvp.network;

import com.example.newmvp.model.Product;

import java.util.List;

public interface NetworkCallBackInterface {
    public void onSuccessful(List<Product> products);
    public void onFailureResult(String errorMsg);
}
