package com.example.chaitanya.carsdetail.rest;


import com.example.chaitanya.carsdetail.model.CarResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * interface for getting the data from the json API
 */
public interface ApiInterface {

    @GET("v2/makes")
    Call<CarResponse> getMakes(@Query("api_key") String apiKey);

}
