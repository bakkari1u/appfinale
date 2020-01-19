package com.example.appfinale.data.api;

import com.example.appfinale.data.api.model.ArticleListResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * interface pour chercher les new depuis l'api
 */

public interface ApiInterface {

    /**
     * method qui prend en parametre le country et l'apikey et retourne  ArticleListResponse de Article .
     * @param country
     * @param apiKey
     * @return
     */

    @GET("top-headlines")
    Single<ArticleListResponse> getNewsArticles(

            @Query("country") String country ,
            @Query("apiKey") String apiKey

    );
}
