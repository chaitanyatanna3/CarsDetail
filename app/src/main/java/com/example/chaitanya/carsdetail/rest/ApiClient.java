package com.example.chaitanya.carsdetail.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * class for creating the retrofit instance
 */
public class ApiClient {

    public static final String BASE_URL = "https://api.edmunds.com/api/vehicle/";
    private static Retrofit retrofit  = null;

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
