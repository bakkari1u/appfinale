package com.example.appfinale.presentation.verticale.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appfinale.R;
import com.example.appfinale.data.ArticleDetails;
import com.example.appfinale.data.di.ApiClient;
import com.example.appfinale.data.repository.ArticleToViewModel;
import com.example.appfinale.presentation.verticale.ArticleSearchContract;
import com.example.appfinale.presentation.verticale.ArticleSearchPresenter;
import com.example.appfinale.presentation.verticale.adapter.ArticleAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ArticleFragment extends Fragment implements ArticleSearchContract.View {

    public static final String TAB_NAME = "Search";
    private View rootView;
    ArticleSearchContract.Presenter presenter;
    private RecyclerView recyclerView;
    private ArticleAdapter articleAdapter;
    private FragmentManager fragmentManager;

    private ArticleFragment(){}

    public static ArticleFragment newInstance() {
        return new ArticleFragment();
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.display_ver, container, false);
        recyclerView = rootView.findViewById(R.id.recycler_view);
        recyclerView.addItemDecoration(new DividerItemDecoration(this.getContext(), DividerItemDecoration.VERTICAL));
        fragmentManager = getActivity().getSupportFragmentManager();
        setupRecyclerView();
        presenter = new ArticleSearchPresenter(
                ApiClient.getArticleDisplayRepository(),
                new ArticleToViewModel());
        presenter.searchArticles();
        presenter.attachView(this);
        return rootView;
    }



    public void setupRecyclerView(){
        recyclerView = rootView.findViewById(R.id.recycler_view);
        articleAdapter = new ArticleAdapter(fragmentManager);
        recyclerView.setAdapter(articleAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }


    @Override
    public void displayArticles(List<ArticleDetails> articleDetailsList) {
        articleAdapter.bindViewModels(articleDetailsList);
    }



}
