package com.neugleb.repositories;
import com.neugleb.repositories.interceptor.ApiKeyInterceptor;
import com.neugleb.repositories.interceptor.NetworkConnectionInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieRepoProvider {

    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    public static final String MOVIE_IMAGE_URL = "https://image.tmdb.org/t/p/w185/";

    private static class SingletonHelper {
        static OkHttpClient.Builder oktHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new NetworkConnectionInterceptor())
                .addInterceptor(new ApiKeyInterceptor());


        static Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(oktHttpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        private static final MovieRepository INSTANCE = new MovieRepositoryImpl(retrofit
                .create(MovieApi.class));
    }

    public static MovieRepository provide() {
        return SingletonHelper.INSTANCE;
    }

}
