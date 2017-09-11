package com.example.priya.audiolisting.backend;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.priya.audiolisting.utility.AppContants.ROOT_URL;

/**
 * Created by priya on 25/7/17.
 * For retrofit Client and converting json receive from hhtp url
 */
public class ApiClient {

    private static Retrofit getRetrofitInstance() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);
        return new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
    }
    public static SendDataInterface getApiService() {
        return getRetrofitInstance().create(SendDataInterface.class);
    }
}


