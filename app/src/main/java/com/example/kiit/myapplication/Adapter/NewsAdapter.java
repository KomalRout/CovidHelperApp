package com.example.kiit.myapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kiit.myapplication.DetailedNews;
import com.example.kiit.myapplication.R;
import com.example.kiit.myapplication.modal.Articles;
import com.squareup.picasso.Picasso;

import org.ocpsoft.prettytime.PrettyTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
Context context;
List<Articles> articlesList;

    public NewsAdapter(Context context, List<Articles> articlesList) {
        this.context = context;
        this.articlesList = articlesList;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cellnews_items,parent,false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {

        final Articles art = articlesList.get(position);
        holder.tvtitle.setText(art.getTitle());
        holder.tvSource.setText(art.getSource().getName());
        holder.tvDate.setText("\u2022"+dateTime(art.getPublishedAt()));

        String imageUrl = art.getUrlToImage();
        String url = art.getUrl();
        Picasso.with(context).load(imageUrl).into(holder.imageView_news);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(context, DetailedNews.class);
                in.putExtra("title",art.getTitle());
                in.putExtra("source",art.getSource().getName());
                in.putExtra("time",dateTime(art.getPublishedAt()));
                in.putExtra("imageUrl",art.getUrlToImage());
                in.putExtra("url",art.getUrl());
                context.startActivity(in);
            }
        });

    }

    @Override
    public int getItemCount() {
        return articlesList.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {
        TextView tvtitle,tvSource,tvDate;
        ImageView imageView_news;
        CardView cardView;
        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            tvtitle = itemView.findViewById(R.id.tv_title);
            tvSource = itemView.findViewById(R.id.tv_source);
            tvDate = itemView.findViewById(R.id.tv_date);
            imageView_news = itemView.findViewById(R.id.image_news);
            cardView = itemView.findViewById(R.id.news_card);
        }
    }
    public String dateTime(String t){
        PrettyTime prettyTime = new PrettyTime(new Locale(getCountry()));
        String time = null;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:",Locale.ENGLISH);
            Date date = simpleDateFormat.parse(t);
            time = prettyTime.format(date);
        }catch (ParseException e) {
            e.printStackTrace();
        }
        return time;

    }
    public String getCountry(){
        Locale locale = Locale.getDefault();
        String country = locale.getCountry();
        return country.toLowerCase();
    }
}
