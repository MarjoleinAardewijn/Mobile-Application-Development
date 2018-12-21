package com.example.marjolein.recipeapp.Service;

import com.example.marjolein.recipeapp.Model.Recipe;
import com.example.marjolein.recipeapp.Model.RecipeIngredient;

public interface RecipeCallback {
    void onRecipeResponse(Recipe[] recipes);
    void onRecipeIngredientsResponse(RecipeIngredient recipeIngredient);
}
