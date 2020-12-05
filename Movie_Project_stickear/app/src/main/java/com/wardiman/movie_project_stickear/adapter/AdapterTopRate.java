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
import com.wardiman.movie_project_stickear.DetailTopActivity;
import com.wardiman.movie_project_stickear.R;
import com.wardiman.movie_project_stickear.model.ItemPopular;
import com.wardiman.movie_project_stickear.model.ItemTopRated;

import java.util.List;

public class AdapterTopRate extends RecyclerView.Adapter<AdapterTopRate.MyViewHolder> {
    // Buat Global variable untuk manampung context
    Context context;
    List<ItemTopRated> topRateds;
    public AdapterTopRate(Context context, List<ItemTopRated> data_top_rate) {
        // Inisialisasi
        this.context = context;
        this.topRateds = data_top_rate;
    }

    @Override
    public AdapterTopRate.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Layout inflater
        View view = LayoutInflater.from(context).inflate(R.layout.top_rated_item, parent, false);

        // Hubungkan dengan MyViewHolder
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(AdapterTopRate.MyViewHolder holder, final int position) {

        holder.tvTitleTopRate.setText(topRateds.get(position).getTitle());
        holder.tvReleaseTopRate.setText(topRateds.get(position).getTitle());

        final String urlGambarTop = "https://image.tmdb.org/t/p/w500/" + topRateds.get(position).getPosterPath();
//        // Set image ke widget dengna menggunakan Library Piccasso
//        // krena imagenya dari internet
        Picasso.get().load(urlGambarTop).into(holder.ivTopRate);

        // Event klik ketika item list nya di klik
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Mulai activity Detail
                Intent TopIntent = new Intent(context, DetailTopActivity.class);
                // sisipkan data ke intent
                TopIntent.putExtra("ID_TOP", topRateds.get(position).getId());
                TopIntent.putExtra("TITLE_TOP", topRateds.get(position).getTitle());
                TopIntent.putExtra("DESC_TOP", topRateds.get(position).getOverview());
                TopIntent.putExtra("RELEASE_TOP", topRateds.get(position).getReleaseDate());
                TopIntent.putExtra("BACKDROP_TOP", urlGambarTop);

                // method startActivity cma bisa di pake di activity/fragment
                // jadi harus masuk ke context dulu
                context.startActivity(TopIntent);
            }
        });
    }
    // Menentukan Jumlah item yang tampil
    @Override
    public int getItemCount() {
        return topRateds.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        // Deklarasi widget

        ImageView ivTopRate;
        TextView tvTitleTopRate, tvReleaseTopRate;
        public MyViewHolder(View itemView) {
            super(itemView);
            ivTopRate = itemView.findViewById(R.id.ivToprate);
            tvTitleTopRate = itemView.findViewById(R.id.tvTitleToprate);
            tvReleaseTopRate = itemView.findViewById(R.id.tvReleaseTopRate);
        }
    }
}
