package com.example.appfinale.data.repository;

import com.example.appfinale.data.api.ApiInterface;
import com.example.appfinale.data.api.model.ArticleListResponse;

import io.reactivex.Single;

public class ArticleDisplayRemoteDataSource {

    private ApiInterface apiInterface;


    public ArticleDisplayRemoteDataSource(ApiInterface apiInterface) {
        this.apiInterface = apiInterface;
    }


    /**
     * @return ArticleListResponse (retourne tous les articles de pays : france )
     */
    public Single<ArticleListResponse> getArticlesSearchResponse() {
        return apiInterface.getNewsArticles("fr","25bba3e0bd534edfbe4f35e93463a3d6");
    }


}
