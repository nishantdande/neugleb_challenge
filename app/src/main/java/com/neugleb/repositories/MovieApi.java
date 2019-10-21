package com.neugleb.repositories;

import com.neugleb.model.MovieResponse;
import com.neugleb.model.detail.MovieDetail;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApi {

    @GET("movie/top_rated")
    Call<MovieResponse> getMovies(@Query("page") Integer pageCount);

    @GET("movie/{movie_id}")
    Call<MovieDetail> getMovieDetail(@Path("movie_id") int movie_id);

}
