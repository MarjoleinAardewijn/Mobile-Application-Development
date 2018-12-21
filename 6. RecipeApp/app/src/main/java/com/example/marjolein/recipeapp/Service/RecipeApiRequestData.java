package com.example.marjolein.recipeapp.Service;

import android.util.Log;

import com.example.marjolein.recipeapp.Model.Recipe;
import com.example.marjolein.recipeapp.Model.RecipeIngredient;
import com.example.marjolein.recipeapp.Model.Recipes;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeApiRequestData {

    private RecipeApiService service;
    private final RecipeCallback recipeCallback;
    private final int MAX_RECIPES = 3;

    public RecipeApiRequestData(RecipeCallback recipeCallback){
        this.recipeCallback = recipeCallback;
        service = RecipeApiService.retrofit.create(RecipeApiService.class);
    }

    public void getTopThreeRecipes(){
        final Recipe[] topThreeRecipes = new Recipe[MAX_RECIPES];

        Call<Recipes> call = service.getTopThreeRecipes();
        call.enqueue(new Callback<Recipes>() {
            @Override
            public void onResponse(Call<Recipes> call, Response<Recipes> response) {
                Recipes recipeResponse = response.body();

                if(recipeResponse == null){
                    return;
                }

                for(int i = 0; i < topThreeRecipes.length; i++){
                    topThreeRecipes[i] = recipeResponse.getRecipes().get(i);
                }

                recipeCallback.onRecipeResponse(topThreeRecipes);
            }

            @Override
            public void onFailure(Call<Recipes> call, Throwable t) {
                Log.d("error", t.toString());
            }
        });
    }

    public void getRecipeIngredients(String recipeId){
        Call<RecipeIngredient> call = service.getRecipeIngredients(recipeId);
        call.enqueue(new Callback<RecipeIngredient>() {
            @Override
            public void onResponse(Call<RecipeIngredient> call, Response<RecipeIngredient> response) {
                RecipeIngredient recipeIngredientResponse = response.body();

                if(recipeIngredientResponse == null){
                    return;
                }

                recipeCallback.onRecipeIngredientsResponse(recipeIngredientResponse);
            }

            @Override
            public void onFailure(Call<RecipeIngredient> call, Throwable t) { Log.d("error", t.toString()); }
        });
    }
}
