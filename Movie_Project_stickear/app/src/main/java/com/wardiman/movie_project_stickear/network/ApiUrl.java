package com.wardiman.movie_project_stickear.network;

import java.security.PublicKey;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiUrl {
    public static String API_URL = "https://api.themoviedb.org/3/";

    public static Retrofit setInit() {
        return new Retrofit.Builder().baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ApiService getInstance() {
        return setInit().create(ApiService.class);
    }
}
