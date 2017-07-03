package com.yegor.seekingalpha.seekingalpha;

import android.app.Application;

import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

/**
 * Singleton of application
 */
public class ApplicationSingleton extends Application {

    private static final Long CACHE_SIZE = 250000000L;//250mb

    @Override
    public void onCreate() {
        super.onCreate();
        Picasso picasso = new Picasso.Builder(this).downloader(new OkHttpDownloader(getCacheDir(), CACHE_SIZE)).build();
        picasso.setIndicatorsEnabled(true);
        Picasso.setSingletonInstance(picasso);
    }
}
