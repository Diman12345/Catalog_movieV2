package com.wardiman.movie_project_stickear;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.squareup.picasso.Picasso;
import com.wardiman.movie_project_stickear.adapter.AdapterReviews;
import com.wardiman.movie_project_stickear.network.ApiUrl;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DetailMovieActivity extends AppCompatActivity {

    ImageView ivDetailMovie;
    TextView tvDetailTitle, tvDetailDescription, tvRev, tvDetailReview;
    String tag_json = "json_obj";

    ImageButton btnShare, btnFavorite;
    AdapterReviews adapterReviews;

    int page;

    public static final String TAG_PAGE = "page";

    DataHelper dataHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        ivDetailMovie = findViewById(R.id.ivDetailMovie);
        tvDetailTitle = findViewById(R.id.tvDetailTitle);
        tvDetailDescription = findViewById(R.id.tvDetailDescription);
        tvDetailReview = findViewById(R.id.tvDetailReview);
        tvRev = findViewById(R.id.tvRev);
        btnShare = findViewById(R.id.btnShare);
        btnFavorite = findViewById(R.id.btnFavorite);

        String idx = getIntent().getStringExtra("ID_MOVIE");

        ShowDetailMovie();

        String title = getIntent().getStringExtra("TITLE");
        String description = getIntent().getStringExtra("DESC");
        String backdrop = getIntent().getStringExtra("BACKDROP");

        dataHelper = new DataHelper(this);

//        ShowReview(idx);

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

        btnFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnFavorite.setImageResource(R.drawable.love);
                SQLiteDatabase db = dataHelper.getWritableDatabase();
                db.execSQL("INSERT INTO film (title, description) values('" +
                        tvDetailTitle.getText().toString() + "',''" +
                        tvDetailDescription.getText().toString() + "')");
                Toast.makeText(getApplicationContext(), "Berhasil ditambahkan", Toast.LENGTH_LONG).show();
                finish();
            }
        });

    }

    private void ShowReview(String idx) {
//        final String id = getIntent().getStringExtra("ID_MOVIE");

        StringRequest stringRequest = new StringRequest(Request.Method.GET, ApiUrl.API_URL + "/movie/" + idx + "/reviews?api_key=418b3e8479d2d057f65d3b24a55a5769", new Response.Listener<String>(){

            @Override
            public void onResponse(String response) {

                Log.d("Response : ", response.toString());
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    page = jsonObject.getInt(TAG_PAGE);
                    if (page == 1){

                        String review = jsonObject.getString("content");

                        adapterReviews.notifyDataSetChanged();

                        tvDetailReview.setText(review);

                    } else {
                        Toast.makeText(getApplicationContext(), "Data tidak tersedia", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        } , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d( "Error: " , error.getMessage());
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError{
                Map<String, String> params = new HashMap<String,String>();
                params.put("id", idx);
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest, tag_json);
    }

    private void ShowDetailMovie() {
        String idx = getIntent().getStringExtra("XX");
        String title = getIntent().getStringExtra("TITLE");
        String description = getIntent().getStringExtra("DESC");
        String backdrop = getIntent().getStringExtra("BACKDROP");

        tvDetailTitle.setText(title);
        tvDetailDescription.setText(description);
        tvRev.setText(idx);
        Picasso.get().load(backdrop).into(ivDetailMovie);
    }
}