package com.wardiman.movie_project_stickear.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.wardiman.movie_project_stickear.DetailMovieActivity;
import com.wardiman.movie_project_stickear.R;
import com.wardiman.movie_project_stickear.model.ItemPopular;
import com.wardiman.movie_project_stickear.network.ApiUrl;

import java.util.List;

public class AdapterPopular extends RecyclerView.Adapter<AdapterPopular.MyViewHolder> {
    // Buat Global variable untuk manampung context
    Context context;
    List<ItemPopular> popular;
    public AdapterPopular(Context context, List<ItemPopular> data_popular) {
        // Inisialisasi
        this.context = context;
        this.popular = data_popular;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Layout inflater
        View view = LayoutInflater.from(context).inflate(R.layout.popular_item, parent, false);

        // Hubungkan dengan MyViewHolder
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        final String urlGambarBerita = "https://image.tmdb.org/t/p/w500/" + popular.get(position).getPosterPath();

//        String review =  ApiUrl.API_URL + "/movie/" + popular.get(position).getId() + "/reviews?api_key=418b3e8479d2d057f65d3b24a55a5769";
//        // Set image ke widget dengna menggunakan Library Piccasso
//        // krena imagenya dari internet
        Picasso.get().load(urlGambarBerita).into(holder.ivPosterPopular);

        // Event klik ketika item list nya di klik
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Mulai activity Detail
                Intent varIntent = new Intent(context, DetailMovieActivity.class);
                // sisipkan data ke intent
                varIntent.putExtra("XX", popular.get(position).getId());
                varIntent.putExtra("TITLE", popular.get(position).getTitle());
                varIntent.putExtra("DESC", popular.get(position).getOverview());
                varIntent.putExtra("RELEASE", popular.get(position).getReleaseDate());
                varIntent.putExtra("BACKDROP", urlGambarBerita);
//                varIntent.putExtra("content", popular.get(position).getContent());

                // method startActivity cma bisa di pake di activity/fragment
                // jadi harus masuk ke context dulu
                context.startActivity(varIntent);
            }
        });
    }
    // Menentukan Jumlah item yang tampil
    @Override
    public int getItemCount() {
        return popular.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        // Deklarasi widget

        ImageView ivPosterPopular;
        public MyViewHolder(View itemView) {
            super(itemView);
            ivPosterPopular = itemView.findViewById(R.id.ivPosterPopular);
        }
    }
}
