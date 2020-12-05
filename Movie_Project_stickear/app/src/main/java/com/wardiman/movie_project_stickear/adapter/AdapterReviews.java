package com.wardiman.movie_project_stickear.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wardiman.movie_project_stickear.R;
import com.wardiman.movie_project_stickear.model.ItemReviews;

import java.util.List;

public class AdapterReviews extends BaseAdapter {

    Activity activity;
    List<ItemReviews> itemReviews;
    LayoutInflater layoutInflater;

    public AdapterReviews(Activity activity, List<ItemReviews> itemReviews){
        this.activity = activity;
        this.itemReviews = itemReviews;
    }

    @Override
    public int getCount() {
        return itemReviews.size();
    }

    @Override
    public Object getItem(int position) {
        return itemReviews.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (layoutInflater == null){
            layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if (convertView == null){
            assert layoutInflater != null;
            convertView = layoutInflater.inflate(R.layout.activity_detail_movie, null);
        }

        TextView reviews = convertView.findViewById(R.id.tvDetailReview);

        ItemReviews itemReview = itemReviews.get(position);

        reviews.setText(itemReview.getId());

        return convertView;
    }
}
