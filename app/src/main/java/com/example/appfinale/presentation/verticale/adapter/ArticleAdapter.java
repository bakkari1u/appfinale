package com.example.appfinale.presentation.verticale.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.example.appfinale.R;
import com.example.appfinale.data.ArticleDetails;
import com.example.appfinale.presentation.verticale.ArticleSearchContract;
import com.example.appfinale.presentation.verticale.fragment.ArticleDetailsFragment;
import com.example.appfinale.utils.Utils;
import java.util.ArrayList;
import java.util.List;

/**
 * une classe represente un adaptateur  pour afficher une liste déroulante grace à recyclerview
 */

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>  {

    private List<ArticleDetails> articles;
    private Context context;
    private FragmentManager fragmentManager;
    private int display = 0 ;


    public ArticleAdapter(FragmentManager fragmentManager) {
        this.fragmentManager=fragmentManager;
        articles = new ArrayList<>();

    }

    public void setDisplay ( int display) {
        this.display = display ;
    }
    public void bindViewModels(List<ArticleDetails> articleDetailsList){
        this.articles.addAll(articleDetailsList);
        notifyDataSetChanged();
    }


    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view ;

        if(this.display ==0){
             view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ver, parent, false);
        }
        else{
             view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hor, parent, false);
        }
        return new ArticleViewHolder(view, fragmentManager);

    }

    @Override
    public void onBindViewHolder( ArticleViewHolder holder, int position) {
        final ArticleDetails model = articles.get(position);
        holder.bind(model);
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager.beginTransaction().replace(R.id.fragment_container,
                        ArticleDetailsFragment.newInstance(model)).addToBackStack(null).commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        return articles.size();
    }


    public static class ArticleViewHolder extends RecyclerView.ViewHolder  {

        TextView title, desc, author, published_ad, source, time;
        ImageView imageView;
        private FragmentManager fragmentManager;
        private View mView;
        private ArticleDetails articleDetails;


        public ArticleViewHolder(View itemView, FragmentManager fragmentManager) {

            super(itemView);
            mView = itemView;
            this.fragmentManager = fragmentManager;
            title = itemView.findViewById(R.id.title);
//            desc = itemView.findViewById(R.id.desc);
//            author = itemView.findViewById(R.id.author);
            published_ad = itemView.findViewById(R.id.publishedAt);
//            source = itemView.findViewById(R.id.source);
            time = itemView.findViewById(R.id.time);
            imageView = itemView.findViewById(R.id.img);


        }
        void bind(ArticleDetails articleDetails) {
            this.articleDetails = articleDetails;
            title.setText(articleDetails.getTitle());
//            desc.setText(articleDetails.getDescription());
            time.setText(" \u2022 " + Utils.DateToTimeFormat(articleDetails.getPublishedAt()));
            published_ad.setText(Utils.DateFormat(articleDetails.getPublishedAt()));
//            author.setText(articleDetails.getAuthor());
            Glide.with(mView)
                    .load(articleDetails.getUrlToImage())
                    .apply(new RequestOptions().override(200,200))
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imageView);

        }

    }

}
