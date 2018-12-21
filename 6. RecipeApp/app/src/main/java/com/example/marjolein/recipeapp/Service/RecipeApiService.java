package com.example.marjolein.recipeapp.Service;

import com.example.marjolein.recipeapp.Model.RecipeIngredient;
import com.example.marjolein.recipeapp.Model.Recipes;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RecipeApiService {
    String BASE_URL = "https://www.food2fork.com";
    String API_KEY = "becb7527747e892b133249c6d82aa011";
    String SORT_MODE = "&?sort=t";

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @POST("/api/search?key=" + API_KEY + SORT_MODE)
    Call<Recipes> getTopThreeRecipes();

    @GET("/api/get?key=" + API_KEY)
    Call<RecipeIngredient> getRecipeIngredients(@Query(value = "rId") String recipeId);
}
