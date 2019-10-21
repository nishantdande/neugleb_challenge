package com.neugleb.view.home;

import com.neugleb.model.MovieResponse;
import com.neugleb.repositories.MovieRepositoryImpl;
import com.neugleb.repositories.MovieRepository;

public class HomePresenter implements HomePresenterContract, MovieRepositoryImpl.MovieRepositoryCallback {

    private HomeViewContract mHomeViewContract;
    private MovieRepository mMovieRepository;

    public HomePresenter(HomeViewContract homeViewContract, MovieRepository movieRepository) {
        this.mHomeViewContract = homeViewContract;
        this.mMovieRepository = movieRepository;
    }

    @Override
    public void getMovies(int pageCount) {
        mMovieRepository.getMovies(pageCount,this);
    }

    @Override
    public void handleMovieResponse(MovieResponse response) {
        if (response.getMovies() != null){
            mHomeViewContract.displayMovieResults(response.getMovies(), response.getPage());
        }
    }

    @Override
    public void handleMovieError(Throwable throwable) {
        mHomeViewContract.displayError(throwable);
    }
}
