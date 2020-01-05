package com.example.appfinale.data.repository;

import com.example.appfinale.data.api.model.ArticleListResponse;

import io.reactivex.Single;

public class ArticleDisplayDataRepo implements ArticleDisplayRepo {

    private ArticleDisplayRemoteDataSource articleDisplayRemoteDataSource;


    public ArticleDisplayDataRepo(ArticleDisplayRemoteDataSource articleDisplayRemoteDataSource) {
        this.articleDisplayRemoteDataSource = articleDisplayRemoteDataSource;
    }


    @Override
    public Single<ArticleListResponse> getArtilcesListResponse() {
        return articleDisplayRemoteDataSource.getArticlesSearchResponse();
    }



}
