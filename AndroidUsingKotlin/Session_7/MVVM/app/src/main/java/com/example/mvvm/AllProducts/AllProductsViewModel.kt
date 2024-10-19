package com.example.mvvm.AllProducts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm.Product
import com.example.mvvm.Model.Repo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AllProductsViewModel(private val repo: Repo) : ViewModel() {


    private val _productList = MutableLiveData<List<Product>>()
    val productList: LiveData<List<Product>> = _productList

     fun getProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val products = repo.getProducts()
                _productList.postValue(products)
            } catch (e: Exception) {

            }
        }
    }

    fun insertToFav(product : Product){
       viewModelScope.launch(Dispatchers.IO) {
         repo.insertProduct(product)
       }
    }
}