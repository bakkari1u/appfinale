package com.example.appfinale.presentation.verticale;

import com.example.appfinale.data.ArticleDetails;


import java.util.List;

public interface ArticleSearchContract {

    interface View {
        void displayArticles(List<ArticleDetails> articleDetailsList);
    }

    interface Presenter {

        void searchArticles();
        void attachView(View view);


    }
}
