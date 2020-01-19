package com.example.appfinale.data.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * une classe represente la liste de tous les articles retourné par l'api
 */

public class ArticleListResponse {

    @SerializedName("articles")
    List<Article> articles;


    public List<Article> getArticles() {
        return articles;
    }
}
