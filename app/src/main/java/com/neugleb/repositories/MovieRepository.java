package com.neugleb.repositories;

import androidx.annotation.NonNull;

import com.neugleb.model.MovieResponse;
import com.neugleb.model.detail.MovieDetail;

public interface MovieRepository {

    void getMovies(@NonNull int pageCount,
                   @NonNull MovieRepositoryCallback callback);

    void getMovieDetail(@NonNull int id,
                   @NonNull MovieDetailRepositoryCallback callback);

    interface MovieRepositoryCallback {
        void handleMovieResponse(MovieResponse response);

        void handleMovieError(Throwable throwable);
    }

    interface MovieDetailRepositoryCallback {
        void handleMovieDetailResponse(MovieDetail response);

        void handleMovieError(Throwable throwable);
    }
}
