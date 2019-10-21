package com.neugleb.repositories;

import androidx.annotation.NonNull;

import com.neugleb.model.MovieResponse;
import com.neugleb.model.detail.MovieDetail;

import retrofit2.Call;
import retrofit2.Response;

public class MovieRepositoryImpl implements MovieRepository {

    private MovieApi mMovieApi;

    public MovieRepositoryImpl(MovieApi mMovieApi) {
        this.mMovieApi = mMovieApi;
    }

    @Override
    public void getMovies(@NonNull int pageCount,
                          @NonNull final MovieRepositoryCallback callback) {
        final Call<MovieResponse> call = mMovieApi.getMovies(pageCount);
        call.enqueue(new retrofit2.Callback<MovieResponse>() {
            @Override
            public void onResponse(@NonNull Call<MovieResponse> call,
                                   @NonNull Response<MovieResponse> response) {
                if (response.isSuccessful()){
                    callback.handleMovieResponse(response.body());
                }else{
                    callback.handleMovieError(new Exception(response.message()));
                }
            }

            @Override
            public void onFailure(@NonNull Call<MovieResponse> call,
                                  @NonNull Throwable t) {
                callback.handleMovieError(t);
            }
        });
    }

    @Override
    public void getMovieDetail(@NonNull int id, @NonNull final MovieDetailRepositoryCallback callback) {
        final Call<MovieDetail> call = mMovieApi.getMovieDetail(id);
        call.enqueue(new retrofit2.Callback<MovieDetail>() {
            @Override
            public void onResponse(@NonNull Call<MovieDetail> call,
                                   @NonNull Response<MovieDetail> response) {
                if (response.isSuccessful()){
                    callback.handleMovieDetailResponse(response.body());
                }else{
                    callback.handleMovieError(new Exception(response.message()));
                }
            }

            @Override
            public void onFailure(@NonNull Call<MovieDetail> call,
                                  @NonNull Throwable t) {
                callback.handleMovieError(t);
            }
        });
    }
}
