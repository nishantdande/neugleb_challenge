package com.neugleb.view.home;

import androidx.annotation.NonNull;

import com.neugleb.model.Movie;

import java.util.List;

public interface HomeViewContract {

    void displayMovieResults(@NonNull List<Movie> movies,
                              @NonNull Integer totalCount);
    void displayError(Throwable throwable);
}
