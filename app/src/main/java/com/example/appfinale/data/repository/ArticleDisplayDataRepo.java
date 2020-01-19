package com.example.appfinale.data.repository;

import com.example.appfinale.data.api.model.ArticleListResponse;

import io.reactivex.Single;

/**
 * une classe pour la gestion des opérations des données fournies par l'api
 */

public class ArticleDisplayDataRepo implements ArticleDisplayRepo {

    private ArticleDisplayRemoteDataSource articleDisplayRemoteDataSource;


    public ArticleDisplayDataRepo(ArticleDisplayRemoteDataSource articleDisplayRemoteDataSource) {
        this.articleDisplayRemoteDataSource = articleDisplayRemoteDataSource;
    }


    /**
     *
     * @return ArticleListResponse
     */
    @Override
    public Single<ArticleListResponse> getArtilcesListResponse() {
        return articleDisplayRemoteDataSource.getArticlesSearchResponse();
    }



}
