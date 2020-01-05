package com.example.appfinale.data.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ArticleListResponse {

    @SerializedName("articles")
    List<Article> articles;


    public List<Article> getArticles() {
        return articles;
    }
}
