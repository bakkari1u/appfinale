package com.example.appfinale.presentation.verticale;

import com.example.appfinale.data.api.model.ArticleListResponse;
import com.example.appfinale.data.repository.ArticleDisplayRepo;
import com.example.appfinale.data.repository.ArticleToViewModel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class ArticleSearchPresenter  implements ArticleSearchContract.Presenter {

    private ArticleSearchContract.View view;
    private ArticleDisplayRepo articleDisplayRepo;
    private CompositeDisposable compositeDisposable;
    private ArticleToViewModel articleToViewModel;

    public ArticleSearchPresenter(ArticleDisplayRepo articleDisplayRepo, ArticleToViewModel articleToViewModel) {
        this.articleDisplayRepo = articleDisplayRepo;
        compositeDisposable = new CompositeDisposable();
        this.articleToViewModel = articleToViewModel;
    }

    @Override
    public void searchArticles() {
        compositeDisposable.add(articleDisplayRepo.getArtilcesListResponse()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<ArticleListResponse>() {
                    @Override
                    public void onSuccess(ArticleListResponse articleListResponse) {
                        view.displayArticles(articleToViewModel.map(articleListResponse.getArticles()));
                    }
                    @Override
                    public void onError(Throwable e) {
                        System.out.println(e.toString());
                    }
                }));
    }

    @Override
    public void attachView(ArticleSearchContract.View view) {
        this.view = view;
    }

}
