package com.example.kiit.myapplication;


import com.example.kiit.myapplication.modal.Headlines;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApiInterface {
    @GET("top-headlines")
    Call<Headlines> getHeadlines(
            @Query("country")String country,
            @Query("q") String q,
            @Query("apiKey") String apiKey

    );
}
