package com.example.workerlab

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.google.gson.Gson

class ProductWorker(val context:Context,val workerParameters:WorkerParameters) :
        Worker(context,workerParameters){
    override fun doWork(): Result {
        /* Fetch the data from API usin Retorfit*/
        val productResponse=RetrofitClient.api.getProducts().execute()
        if(productResponse.isSuccessful){
            /* pass the data to main activity*/
            val products=productResponse.body()?.products?:emptyList()
            val productJson=Gson().toJson(products)

            val output= workDataOf("data" to productJson )
            return Result.success(output)
        }
        else{
            return Result.failure()
        }


    }
        }
