package com.example.appfinale.data.repository;

import com.example.appfinale.data.api.model.ArticleListResponse;

import io.reactivex.Single;

public interface ArticleDisplayRepo {

    Single<ArticleListResponse> getArtilcesListResponse();
}
