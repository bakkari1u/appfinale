package com.example.appfinale.presentation.verticale.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.example.appfinale.R;
import com.example.appfinale.data.ArticleDetails;
import com.example.appfinale.utils.Utils;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class ArticleDetailsFragment extends Fragment implements AppBarLayout.OnOffsetChangedListener {

    private ImageView imageView;
    private TextView appbar_title, appbar_subtitle, date, time, title , desc;
    private boolean isHideToolbarView = false;
    private FrameLayout date_behavior;
    private LinearLayout titleAppbar;
    private AppBarLayout appBarLayout;
    private Toolbar toolbar;
    private String mUrl, mImg, mTitle, mDate, mSource, mAuthor , mDesc;
    private View rootView;


    public ArticleDetailsFragment(ArticleDetails articleDetails){

        mUrl = articleDetails.getUrl();
        mImg = articleDetails.getUrlToImage();
        mTitle = articleDetails.getTitle();
        mDesc = articleDetails.getDescription();
        mDate = articleDetails.getPublishedAt();
        //mSource = articleDetails.getIngrdient1();
        mAuthor = articleDetails.getAuthor();
    }

    public static ArticleDetailsFragment newInstance(ArticleDetails articleDetails) {

        return  new ArticleDetailsFragment(articleDetails) ;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.activity_article_details, container, false);
        toolbar = rootView.findViewById(R.id.toolbar);
       /* setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); */

        final CollapsingToolbarLayout collapsingToolbarLayout = rootView.findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("");

        appBarLayout = rootView.findViewById(R.id.appbar);
        appBarLayout.addOnOffsetChangedListener(this);
        date_behavior = rootView.findViewById(R.id.date_behavior);
        titleAppbar = rootView.findViewById(R.id.title_appbar);
        imageView = rootView.findViewById(R.id.backdrop);
        appbar_title = rootView.findViewById(R.id.title_on_appbar);
        appbar_subtitle = rootView.findViewById(R.id.subtitle_on_appbar);
        date = rootView.findViewById(R.id.date);
        time = rootView.findViewById(R.id.time);
        title = rootView.findViewById(R.id.title);
        desc = rootView.findViewById(R.id.desc);

        appbar_title.setText(mSource);
        appbar_subtitle.setText(mUrl);
        date.setText(Utils.DateFormat(mDate));
        title.setText(mTitle);
        desc.setText(mDesc);

        String author;
        if (mAuthor != null){
            author = " \u2022 " + mAuthor;
        } else {
            author = "";
        }
        time.setText(mSource + author + " \u2022 " + Utils.DateToTimeFormat(mDate));
       // initWebView(mUrl);


        Glide.with(rootView)
                .load(mImg)
                .apply(new RequestOptions().override(500,500))
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView);
        return rootView;
    }

//    private void initWebView(String url){
//        WebView webView = rootView.findViewById(R.id.webView);
//        webView.getSettings().setLoadsImagesAutomatically(true);
//        webView.getSettings().setJavaScriptEnabled(true);
//        webView.getSettings().setDomStorageEnabled(true);
//        webView.getSettings().setSupportZoom(true);
//        webView.getSettings().setBuiltInZoomControls(true);
//        webView.getSettings().setDisplayZoomControls(false);
//        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
//        webView.setWebViewClient(new WebViewClient());
//        webView.loadUrl(url);
//    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        int maxScroll = appBarLayout.getTotalScrollRange();
        float percentage = (float) Math.abs(verticalOffset) / (float) maxScroll;

        if (percentage == 1f && isHideToolbarView) {
            date_behavior.setVisibility(View.GONE);
            titleAppbar.setVisibility(View.VISIBLE);
            isHideToolbarView = !isHideToolbarView;

        } else if (percentage < 1f && !isHideToolbarView) {
            date_behavior.setVisibility(View.VISIBLE);
            titleAppbar.setVisibility(View.GONE);
            isHideToolbarView = !isHideToolbarView;
        }

    }

}
