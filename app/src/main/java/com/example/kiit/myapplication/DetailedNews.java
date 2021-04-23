package com.example.kiit.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailedNews extends AppCompatActivity {

    TextView ttitle,tsource,ttime,tdesc;
    ImageView nimage;
    WebView webView;
    ProgressBar loader;
    String title,source,time,desc,imageurl,url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_news);
        tdesc= findViewById(R.id.tvDesc);
        tsource=findViewById(R.id.tvSource);
        ttime=findViewById(R.id.tvDate);
        ttitle=findViewById(R.id.tvTitle);
        nimage=findViewById(R.id.imageView);
        webView=findViewById(R.id.webView);
        loader=findViewById(R.id.webViewLoader);
        loader.setVisibility(View.VISIBLE);

        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        source = intent.getStringExtra("source");
        time=intent.getStringExtra("time");
        desc=intent.getStringExtra("desc");
        imageurl=intent.getStringExtra("imageurl");
        url=intent.getStringExtra("url");

        ttitle.setText(title);
        tsource.setText(source);
        ttime.setText(time);
        tdesc.setText(desc);

        Picasso.with(DetailedNews.this).load(imageurl).into(nimage);

        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
        if(webView.isShown()){
            loader.setVisibility(View.INVISIBLE);
        }
    }
}