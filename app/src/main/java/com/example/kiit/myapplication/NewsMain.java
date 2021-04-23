package com.example.kiit.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.kiit.myapplication.Adapter.NewsAdapter;
import com.example.kiit.myapplication.modal.Articles;
import com.example.kiit.myapplication.modal.Headlines;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsMain extends AppCompatActivity {
    RecyclerView recyclerView;
    final String API_KEY="2df2e965726f4d599f11ebfc182ee9d0";
    final String Q="COVID";
    NewsAdapter newsAdapter;
    List<Articles> articles = new ArrayList<>();
    MainActivity activity =new MainActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_main);
        recyclerView = findViewById(R.id.recyclerview_news);
        recyclerView.setLayoutManager(new LinearLayoutManager(NewsMain.this));
        String country = "in";
        retrieveJson(country,Q,API_KEY);
        getSupportActionBar().setTitle("News");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
    public void retrieveJson(String country, String q, String apiKey){
        activity.ShowDialog(this);
        Call<Headlines> call = NewsApiClient.getInstance().getApi().getHeadlines(country,q,apiKey);
        call.enqueue(new Callback<Headlines>() {
            @Override
            public void onResponse(Call<Headlines> call, Response<Headlines> response) {
                if(response.isSuccessful() && response.body().getArticles()!=null){
                    articles.clear();
                    articles = response.body().getArticles();
                    newsAdapter = new NewsAdapter(NewsMain.this,articles);
                    recyclerView.setAdapter(newsAdapter);
                    activity.DismissDialog();
                }
            }

            @Override
            public void onFailure(Call<Headlines> call, Throwable t) {
                Toast.makeText(NewsMain.this,t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
            }
        });

    }
    public String getCount(){
        Locale locale = Locale.getDefault();
        String country = locale.getCountry();
        return country.toLowerCase();
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}