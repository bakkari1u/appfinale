package com.example.appfinale.data.api;

import com.example.appfinale.data.api.model.ArticleListResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("top-headlines")
    Single<ArticleListResponse> getNewsArticles(

            @Query("country") String country ,
            @Query("apiKey") String apiKey

    );
}
