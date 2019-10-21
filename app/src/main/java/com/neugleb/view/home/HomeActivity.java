package com.neugleb.view.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.neugleb.R;
import com.neugleb.model.Movie;
import com.neugleb.repositories.MovieRepoProvider;
import com.neugleb.view.BaseActivity;
import com.neugleb.view.details.DetailActivity;

import java.util.List;

public class HomeActivity extends BaseActivity implements HomeViewContract, MovieResultRvAdapter.onClickListener {

    private MovieResultRvAdapter rvAdapter;
    private int mPageCount = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

        showDialog(getString(R.string.loading));

        final HomePresenter homePresenter = new HomePresenter(this, MovieRepoProvider.provide());
        homePresenter.getMovies(mPageCount);

        RecyclerView rvRepos = findViewById(R.id.rv_movies);
        rvRepos.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1)) {
                    Toast.makeText(HomeActivity.this, "Last", Toast.LENGTH_LONG).show();

                    if (homePresenter != null)
                        homePresenter.getMovies(++mPageCount);
                }
            }
        });


        rvRepos.setHasFixedSize(true);
        rvAdapter = new MovieResultRvAdapter();
        rvRepos.setAdapter(rvAdapter);
        rvAdapter.setOnClickListener(this);
    }

    @Override
    public void displayMovieResults(@NonNull List<Movie> movies, @NonNull Integer totalCount) {
        rvAdapter.updateResults(movies);
        closeDialog();
    }

    @Override
    public void displayError(Throwable throwable) {
        Log.e("Error", throwable.getLocalizedMessage(), throwable);
        closeDialog();
        showToast(throwable.getLocalizedMessage());
    }


    @Override
    public void onClick(Movie movie) {
        Intent mIntent = new Intent(this, DetailActivity.class);
        Bundle extras = new Bundle();
        extras.putInt("movieId", movie.getId());
        mIntent.putExtras(extras);
        startActivity(mIntent);

    }
}
