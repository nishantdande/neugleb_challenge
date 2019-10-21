package com.neugleb.view.details;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.neugleb.R;
import com.neugleb.model.detail.MovieDetail;
import com.neugleb.repositories.MovieRepoProvider;
import com.neugleb.view.BaseActivity;

public class DetailActivity extends BaseActivity implements MovieDetialViewContract {

    private TextView tvMovieName;
    private TextView tvMovieYear;
    private TextView tvMoviePopularity;
    private TextView tvMovieVoting;
    private TextView tvMovieOverview;
    private TextView tvMovieGenres;
    private TextView tvMovieStatus;
    private ImageView ivMoviePoster;
    private CardView cvMovieDetail;
    private AppCompatImageView ivMovieBackdrop;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        tvMovieName = findViewById(R.id.movie_name);
        tvMovieYear = findViewById(R.id.movie_year);
        tvMoviePopularity = findViewById(R.id.movie_popularity);
        tvMovieVoting = findViewById(R.id.movie_voting);
        ivMoviePoster = findViewById(R.id.movie_image);
        ivMovieBackdrop = findViewById(R.id.ll_movie_item);
        tvMovieOverview = findViewById(R.id.overview);
        tvMovieGenres = findViewById(R.id.movie_genres);
        tvMovieStatus = findViewById(R.id.movie_status);
        cvMovieDetail = findViewById(R.id.detail);

        int movieId = getIntent().getExtras().getInt("movieId");

        showDialog(getString(R.string.loading));
        final MovieDetailPresenter presenter= new MovieDetailPresenter(this, MovieRepoProvider.provide());
        presenter.getMovieDetail(movieId);
    }

    @Override
    public void displayMovieResults(@NonNull MovieDetail movies) {
        tvMovieName.setText(movies.getTitle());
        tvMovieYear.setText(this.getString(R.string.movie_release_date, String.valueOf(movies.getReleaseDate())));
        tvMoviePopularity.setText(this.getString(R.string.movie_popularity, String.valueOf(movies.getPopularity())));
        tvMovieVoting.setText(this.getString(R.string.movie_voting, String.valueOf(movies.getVoteAverage())));
        tvMovieOverview.setText(movies.getOverview());
        tvMovieGenres.setText(movies.getMovieGenres());
        tvMovieStatus.setText(getString(R.string.movie_status,movies.getStatus()));

        Glide.with(this)
                .load(MovieRepoProvider.MOVIE_IMAGE_URL+movies.getPosterPath())
                .into(ivMoviePoster);

        Glide.with(this)
                .load(MovieRepoProvider.MOVIE_IMAGE_URL+movies.getBackdropPath())
                .into(ivMovieBackdrop);

        closeDialog();
    }

    @Override
    public void displayError(Throwable throwable) {
        Log.e("Error", throwable.getLocalizedMessage(), throwable);
        closeDialog();
        cvMovieDetail.setVisibility(View.GONE);
        showToast(throwable.getLocalizedMessage());
    }
}
