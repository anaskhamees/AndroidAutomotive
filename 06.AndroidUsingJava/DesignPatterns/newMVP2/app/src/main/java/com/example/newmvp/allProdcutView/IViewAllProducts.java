package com.example.newmvp.allProdcutView;

import com.example.newmvp.model.Product;

import java.util.List;

public interface IViewAllProducts {
    public void updateList(List<Product> products);
    public void showError(String errorMsg);
}
