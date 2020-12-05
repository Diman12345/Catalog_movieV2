package com.wardiman.movie_project_stickear.network;

import com.wardiman.movie_project_stickear.response.ResponseNowPlaying;
import com.wardiman.movie_project_stickear.response.ResponsePopular;
import com.wardiman.movie_project_stickear.response.ResponseTopRated;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("movie/popular?api_key=418b3e8479d2d057f65d3b24a55a5769")
    Call<ResponsePopular> reques_show_all_popular();

    @GET("movie/now_playing?api_key=418b3e8479d2d057f65d3b24a55a5769")
    Call<ResponseNowPlaying> request_show_all_now_playing();

    @GET("movie/top_rated?api_key=418b3e8479d2d057f65d3b24a55a5769")
    Call<ResponseTopRated> request_show_all_top_rated();
}
