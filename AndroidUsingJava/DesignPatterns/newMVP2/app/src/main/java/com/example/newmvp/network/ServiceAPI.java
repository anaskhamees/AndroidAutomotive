package com.example.newmvp.network;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ServiceAPI {
    @GET("products")
    Call<ProductResponse> getResponse();
}
