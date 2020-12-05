package com.wardiman.movie_project_stickear.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.wardiman.movie_project_stickear.DetailMovieActivity;
import com.wardiman.movie_project_stickear.DetailNowActivity;
import com.wardiman.movie_project_stickear.R;
import com.wardiman.movie_project_stickear.model.ItemNowPlaying;
import com.wardiman.movie_project_stickear.model.ItemTopRated;

import java.util.List;

public class AdapterNowPlaying extends RecyclerView.Adapter<AdapterNowPlaying.MyViewHolder> {
    // Buat Global variable untuk manampung context
    Context context;
    List<ItemNowPlaying> nowPlayings;
    public AdapterNowPlaying(Context context, List<ItemNowPlaying> data_now_playing) {
        // Inisialisasi
        this.context = context;
        this.nowPlayings= data_now_playing;
    }

    @Override
    public AdapterNowPlaying.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Layout inflater
        View view = LayoutInflater.from(context).inflate(R.layout.now_playing_item, parent, false);

        // Hubungkan dengan MyViewHolder
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(AdapterNowPlaying.MyViewHolder holder, final int position) {

        holder.tvTitleNowPlaying.setText(nowPlayings.get(position).getTitle());
        holder.tvReleaseNowPlaying.setText(nowPlayings.get(position).getReleaseDate());

        final String urlGambarNow = "https://image.tmdb.org/t/p/w500/" + nowPlayings.get(position).getPosterPath();
//        // Set image ke widget dengna menggunakan Library Piccasso
//        // krena imagenya dari internet
        Picasso.get().load(urlGambarNow).into(holder.ivNowPlaying);

        // Event klik ketika item list nya di klik
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Mulai activity Detail
                Intent Intent = new Intent(context, DetailNowActivity.class);
                // sisipkan data ke intent
                Intent.putExtra("ID_NOW", nowPlayings.get(position).getId());
                Intent.putExtra("TITLE_NOW", nowPlayings.get(position).getTitle());
                Intent.putExtra("DESC_NOW", nowPlayings.get(position).getOverview());
                Intent.putExtra("RELEASE_NOW", nowPlayings.get(position).getReleaseDate());
                Intent.putExtra("BACKDROP_NOW", urlGambarNow);



                // method startActivity cma bisa di pake di activity/fragment
                // jadi harus masuk ke context dulu
                context.startActivity(Intent);
            }
        });
    }
    // Menentukan Jumlah item yang tampil
    @Override
    public int getItemCount() {
        return nowPlayings.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        // Deklarasi widget

        ImageView ivNowPlaying;
        TextView tvTitleNowPlaying, tvReleaseNowPlaying;

        public MyViewHolder(View itemView) {
            super(itemView);
            ivNowPlaying = itemView.findViewById(R.id.ivNowPlay);
            tvTitleNowPlaying = itemView.findViewById(R.id.tvTitleNowPlay);
            tvReleaseNowPlaying = itemView.findViewById(R.id.tvReleaseNowPlay);
        }
    }
}