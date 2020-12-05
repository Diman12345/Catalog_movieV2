package com.wardiman.movie_project_stickear;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.wardiman.movie_project_stickear.adapter.AdapterNowPlaying;
import com.wardiman.movie_project_stickear.adapter.AdapterPopular;
import com.wardiman.movie_project_stickear.adapter.AdapterTopRate;
import com.wardiman.movie_project_stickear.model.ItemNowPlaying;
import com.wardiman.movie_project_stickear.model.ItemPopular;
import com.wardiman.movie_project_stickear.model.ItemTopRated;
import com.wardiman.movie_project_stickear.network.ApiService;
import com.wardiman.movie_project_stickear.network.ApiUrl;
import com.wardiman.movie_project_stickear.response.ResponseNowPlaying;
import com.wardiman.movie_project_stickear.response.ResponsePopular;
import com.wardiman.movie_project_stickear.response.ResponseTopRated;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvPopular, rvTopRate, rvNowPlaying;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        LinearLayoutManager layoutManagerToprate
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        LinearLayoutManager layoutManagerNowPlaying
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);


        rvPopular = findViewById(R.id.rvPopular);
        rvTopRate = findViewById(R.id.rvTopRate);
        rvNowPlaying = findViewById(R.id.rvNowPlay);

        rvPopular.setLayoutManager(layoutManager);
        rvTopRate.setLayoutManager(layoutManagerToprate);
        rvNowPlaying.setLayoutManager(layoutManagerNowPlaying);

        tampilMoviePopular();
        tampilMovieTopRate();
        tampilMovieNowPlaying();
    }

    private void tampilMovieNowPlaying() {
        ApiService apiNowPlay = ApiUrl.getInstance();
        Call<ResponseNowPlaying> nowPlayingCall = apiNowPlay.request_show_all_now_playing();
        nowPlayingCall.enqueue(new Callback<ResponseNowPlaying>() {
            @Override
            public void onResponse(Call<ResponseNowPlaying> call, Response<ResponseNowPlaying> response) {
                if (response.isSuccessful()){
                    Log.d("Response api : ", response.body().toString());
                    List<ItemNowPlaying> data_now_popular = response.body().getResults();

                    if (response.body().getPage() == 1){
                        AdapterNowPlaying adapterNowPlaying = new AdapterNowPlaying(MainActivity.this, data_now_popular);
                        rvNowPlaying.setAdapter(adapterNowPlaying);

                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseNowPlaying> call, Throwable t) {

            }
        });
    }

    private void tampilMovieTopRate() {

        ApiService apiTopRate = ApiUrl.getInstance();
        Call<ResponseTopRated> topRatedCall = apiTopRate.request_show_all_top_rated();
        topRatedCall.enqueue(new Callback<ResponseTopRated>() {
            @Override
            public void onResponse(Call<ResponseTopRated> call, Response<ResponseTopRated> response) {
                if (response.isSuccessful()){
                    Log.d("Response api : ", response.body().toString());
                    List<ItemTopRated> data_top_rated = response.body().getResults();

                    if (response.body().getPage() == 1){
                        AdapterTopRate adapterTopRate = new AdapterTopRate(MainActivity.this, data_top_rated);
                        rvTopRate.setAdapter(adapterTopRate);

                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseTopRated> call, Throwable t) {

            }
        });

    }

    private void tampilMoviePopular() {

        ApiService apiPopular = ApiUrl.getInstance();
        Call<ResponsePopular> popularCall = apiPopular.reques_show_all_popular();
        popularCall.enqueue(new Callback<ResponsePopular>() {
            @Override
            public void onResponse(Call<ResponsePopular> call, Response<ResponsePopular> response) {
                if (response.isSuccessful()){
                    Log.d("Response api : ", response.body().toString());
                    List<ItemPopular> data_popular = response.body().getResults();

                    if (response.body().getPage() == 1){
                        AdapterPopular adapter = new AdapterPopular(MainActivity.this, data_popular);
                        rvPopular.setAdapter(adapter);

                    }
                }
            }

            @Override
            public void onFailure(Call<ResponsePopular> call, Throwable t) {

            }
        });

    }
}