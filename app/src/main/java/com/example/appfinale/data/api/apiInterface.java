package com.example.appfinale.data.api;

import com.example.appfinale.data.api.model.NewsResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface apiInterface {

    @GET("top-headlines")
    Single<NewsResponse> getNews(

            @Query("country") String country ,
            @Query("apiKey") String apiKey

    );
}
