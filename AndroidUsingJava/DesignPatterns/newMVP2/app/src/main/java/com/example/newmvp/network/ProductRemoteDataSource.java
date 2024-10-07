package com.example.newmvp.network;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductRemoteDataSource {
    final static String url = "https://dummyjson.com/";
    public static final String TAG = "Retrofit connection";
    Retrofit connectRetro;
    ServiceAPI api;
    private static ProductRemoteDataSource productSrc = null;
    private ProductRemoteDataSource(){
        connectRetro = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create())
                .build();
        api = connectRetro.create(ServiceAPI.class);
    }
    public static ProductRemoteDataSource getInstance(){
        if(productSrc == null){
            productSrc = new ProductRemoteDataSource();
        }
        return productSrc;
    }
    public void makeNetworkCall(NetworkCallBackInterface networkCallBackInterface){
        Call<ProductResponse> call = api.getResponse();
        call.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                Log.i(TAG, "onResponse: Successed");
                networkCallBackInterface.onSuccessful(response.body().products);

            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable throwable) {
                networkCallBackInterface.onFailureResult(throwable.getMessage());
                throwable.printStackTrace();
            }
        });
    }
}
