package com.neugleb.view.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.neugleb.R;
import com.neugleb.model.Movie;
import com.neugleb.repositories.MovieRepoProvider;

import java.util.ArrayList;
import java.util.List;


public class MovieResultRvAdapter extends RecyclerView.Adapter<MovieResultRvAdapter
        .MovieViewHolder> {
    public interface onClickListener{
        void onClick(Movie movie);
    }

    private List<Movie> results = new ArrayList<>();
    private onClickListener onClickListener;

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new MovieViewHolder(parent.getContext(),
                inflater.inflate(R.layout.rv_item_repo, null));
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder,
                                 int position) {
        final Movie movie = results.get(position);
        holder.bind(movie);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (movie != null){
                    onClickListener.onClick(movie);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    void updateResults(@NonNull List<Movie> results) {
        this.results.addAll(this.results.size(),results);
        notifyDataSetChanged();
    }

    public void setOnClickListener(MovieResultRvAdapter.onClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {

        private Context context;
        private final TextView tvMovieName;
        private final TextView tvMovieYear;
        private final TextView tvMoviePopularity;
        private final TextView tvMovieVoting;
        private final ImageView ivMoviePoster;
        private final AppCompatImageView ivMovieBackdrop;

        MovieViewHolder(Context context,View itemView) {
            super(itemView);

            this.context = context;
            tvMovieName = itemView.findViewById(R.id.movie_name);
            tvMovieYear = itemView.findViewById(R.id.movie_year);
            tvMoviePopularity = itemView.findViewById(R.id.movie_popularity);
            tvMovieVoting = itemView.findViewById(R.id.movie_voting);
            ivMoviePoster = itemView.findViewById(R.id.movie_image);
            ivMovieBackdrop = itemView.findViewById(R.id.ll_movie_item);
        }

        void bind(Movie searchResult) {
            tvMovieName.setText(searchResult.getTitle());
            tvMovieYear.setText(this.context.getString(R.string.movie_release_date, String.valueOf(searchResult.getReleaseDate())));
            tvMoviePopularity.setText(this.context.getString(R.string.movie_popularity, String.valueOf(searchResult.getPopularity())));
            tvMovieVoting.setText(this.context.getString(R.string.movie_voting, String.valueOf(searchResult.getVoteAverage())));
            Glide.with(this.context)
                    .load(MovieRepoProvider.MOVIE_IMAGE_URL+searchResult.getPosterPath())
                    .into(ivMoviePoster);

            Glide.with(this.context)
                    .load(MovieRepoProvider.MOVIE_IMAGE_URL+searchResult.getBackdropPath())
                    .into(ivMovieBackdrop);


        }
    }
}
