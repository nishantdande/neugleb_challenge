package com.neugleb.view.details;

import com.neugleb.model.detail.MovieDetail;
import com.neugleb.repositories.MovieRepositoryImpl;
import com.neugleb.repositories.MovieRepository;

public class MovieDetailPresenter implements MovieDetailPresenterContract,
        MovieRepositoryImpl.MovieDetailRepositoryCallback {

    private MovieDetialViewContract mHomeViewContract;
    private MovieRepository mMovieRepository;

    public MovieDetailPresenter(MovieDetialViewContract homeViewContract, MovieRepository movieRepository) {
        this.mHomeViewContract = homeViewContract;
        this.mMovieRepository = movieRepository;
    }

    @Override
    public void handleMovieDetailResponse(MovieDetail response) {
        mHomeViewContract.displayMovieResults(response);
    }

    @Override
    public void handleMovieError(Throwable throwable) {
        mHomeViewContract.displayError(throwable);
    }

    @Override
    public void getMovieDetail(int movieId) {
        mMovieRepository.getMovieDetail(movieId, this);
    }
}
