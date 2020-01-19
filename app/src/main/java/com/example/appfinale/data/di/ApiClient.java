package com.example.appfinale.data.di;

import com.example.appfinale.data.api.ApiInterface;
import com.example.appfinale.data.repository.ArticleDisplayDataRepo;
import com.example.appfinale.data.repository.ArticleDisplayRemoteDataSource;
import com.example.appfinale.data.repository.ArticleDisplayRepo;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * classe pour récupéer les données JSON avec retrofit
 */

public class ApiClient {

    public static final String BASE_URL = "https://newsapi.org/v2/";
    public static Retrofit retrofit;
    private static Gson gson;
    private static ApiInterface apiInterface;
    private static ArticleDisplayRepo articleDisplayRepo;

    /**
     * methode de configuration
     * @return retrofit
     */

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .addNetworkInterceptor(new StethoInterceptor())
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(getGson()))
                    .build();
        }
        return retrofit;
    }

    public static Gson getGson() {
        if (gson == null) {
            gson = new Gson();
        }
        return gson;
    }

    public static ArticleDisplayRepo getArticleDisplayRepository() {
        if (articleDisplayRepo == null) {
            articleDisplayRepo = new ArticleDisplayDataRepo(
                    new ArticleDisplayRemoteDataSource(getApiInterface())
            );
        }
        return articleDisplayRepo;
    }

    public static ApiInterface getApiInterface() {
        if (apiInterface == null) {
            apiInterface = getRetrofit().create(ApiInterface.class);
        }
        return apiInterface;
    }

}
