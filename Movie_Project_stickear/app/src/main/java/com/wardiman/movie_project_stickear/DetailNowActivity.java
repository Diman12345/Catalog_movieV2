package com.wardiman.movie_project_stickear;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class DetailNowActivity extends AppCompatActivity {

    ImageView ivDetailMovie;
    TextView tvDetailTitle, tvDetailDescription, tvDetailReview;
    ImageButton btnShare, btnFavoritenow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_now);

        ivDetailMovie = findViewById(R.id.ivDetailNow);
        tvDetailTitle = findViewById(R.id.tvDetailTitleNow);
        tvDetailDescription = findViewById(R.id.tvDetailDescriptionNow);
        btnFavoritenow = findViewById(R.id.btnDetailNowFavorite);
        btnShare = findViewById(R.id.btnShare);

        ShowDetailMovie();

        String title = getIntent().getStringExtra("TITLE_NOW");
        String description = getIntent().getStringExtra("DESC_NOW");
        String backdrop = getIntent().getStringExtra("BACKDROP_NOW");

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = title + "\n" + description + "\n" + backdrop;
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        });

        btnFavoritenow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnFavoritenow.setImageResource(R.drawable.love);
//                SQLiteDatabase db = dataHelper.getWritableDatabase();
//                db.execSQL("INSERT INTO film (title, description) values('" +
//                        tvDetailTitle.getText().toString() + "',''" +
//                        tvDetailDescription.getText().toString() + "')");
                Toast.makeText(getApplicationContext(), "Berhasil ditambahkan", Toast.LENGTH_LONG).show();
//                finish();
            }
        });


//        ShowReview();
    }

//    private void ShowReview() {
//    }

    private void ShowDetailMovie() {
        String id = getIntent().getStringExtra("ID_NOW");
        String title = getIntent().getStringExtra("TITLE_NOW");
        String description = getIntent().getStringExtra("DESC_NOW");
        String backdrop = getIntent().getStringExtra("BACKDROP_NOW");

        tvDetailTitle.setText(title);
        tvDetailDescription.setText(description);
        Picasso.get().load(backdrop).into(ivDetailMovie);
    }
}