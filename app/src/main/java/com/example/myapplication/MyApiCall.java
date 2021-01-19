package com.example.myapplication;

import com.example.myapplication.model.SearchPhotoModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MyApiCall {


    @GET("search/photos")
    Call<SearchPhotoModel> getPhoto(
            @Query("query") String query,
            @Query("client_id") String client_id,
            @Query("page") String page,
            @Query("per_page") String per_page
    );
}
