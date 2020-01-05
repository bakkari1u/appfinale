package com.example.appfinale.data.repository;


import com.example.appfinale.data.ArticleDetails;
import com.example.appfinale.data.api.model.Article;

import java.util.ArrayList;
import java.util.List;

public class ArticleToViewModel {

    public ArticleDetails map(Article article) {
        ArticleDetails articleDetails = new ArticleDetails();
        articleDetails.setAuthor(article.getAuthor());
        articleDetails.setDescription(article.getDescription());
        articleDetails.setPublishedAt(article.getPublishedAt());
        articleDetails.setTitle(article.getTitle());
        articleDetails.setUrl(article.getUrl());
        articleDetails.setUrlToImage(article.getUrlToImage());

        return articleDetails;
    }

    public List<ArticleDetails> map(List<Article> articleList) {
        List<ArticleDetails> articleDetailsList = new ArrayList<>();
        for (Article article : articleList) {
            articleDetailsList.add(map(article));
        }
        return articleDetailsList;
    }

}
