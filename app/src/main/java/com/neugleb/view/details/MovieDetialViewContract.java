package com.neugleb.view.details;

import androidx.annotation.NonNull;

import com.neugleb.model.detail.MovieDetail;

public interface MovieDetialViewContract {

    void displayMovieResults(@NonNull MovieDetail movies);

    void displayError(Throwable throwable);

}
