package com.example.mvvm.FavProducts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm.Product
import com.example.mvvm.Model.Repo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AllFavProductsViewModel (private val repo: Repo) : ViewModel()   {


    private val _productList = MutableLiveData<List<Product>>()
    val productList: LiveData<List<Product>> = _productList


    fun getAllFav(){
        viewModelScope.launch(Dispatchers.IO) {
           val products = repo.getAllFav()
            _productList.postValue(products)
        }
    }

    fun delete(product : Product) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.delete(product)
        }
    }

}

