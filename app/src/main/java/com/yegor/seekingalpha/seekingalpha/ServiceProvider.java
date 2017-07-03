package com.yegor.seekingalpha.seekingalpha;

import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Services factory
 */
class ServiceProvider {

    private static final String API_BASE_URL = "http://54.202.34.250:3000/page/";


    /**
     * creates instance for {@link SolarSystemService}
     *
     * @return service instance
     */
    static SolarSystemService createService() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();
        GsonBuilder gsonBuilder = new GsonBuilder();
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(API_BASE_URL);
        builder.addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()));
        Retrofit retrofit = builder.client(okHttpClient).build();
        return retrofit.create(SolarSystemService.class);
    }
}
