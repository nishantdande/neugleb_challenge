package com.neugleb;

import android.app.Application;
import android.content.Context;

public class MovieApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

         context = MovieApplication.this;
    }

    public static Context getContext() {
        return context;
    }
}
