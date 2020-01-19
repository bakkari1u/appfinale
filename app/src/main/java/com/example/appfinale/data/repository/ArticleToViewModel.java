package com.example.appfinale.data.repository;


import com.example.appfinale.data.ArticleDetails;
import com.example.appfinale.data.api.model.Article;

import java.util.ArrayList;
import java.util.List;

/**
 * une classe permet le chargment de données depuis le retour de l'api vers un model
 */

public class ArticleToViewModel {

    /**
     * method permet de creer et retourner un model d'article depuis un article retourné par l'api
     * @param article
     * @return ArticleDetails
     */

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

    /**
     *
     * @param articleList
     * @return list of  ArticleDetails
     */
    public List<ArticleDetails> map(List<Article> articleList) {
        List<ArticleDetails> articleDetailsList = new ArrayList<>();
        for (Article article : articleList) {
            articleDetailsList.add(map(article));
        }
        return articleDetailsList;
    }

}
