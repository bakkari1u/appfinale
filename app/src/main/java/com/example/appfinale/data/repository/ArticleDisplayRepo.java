package com.example.appfinale.data.repository;

import com.example.appfinale.data.api.model.ArticleListResponse;

import io.reactivex.Single;

/**
 * une interface pour la gestion des opérations des données fournies par l'api
 */

public interface ArticleDisplayRepo {


    /**
     *
     * @return ArticleListResponse
     */
    Single<ArticleListResponse> getArtilcesListResponse();
}
