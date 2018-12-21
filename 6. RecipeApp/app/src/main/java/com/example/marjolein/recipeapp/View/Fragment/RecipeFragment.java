package com.example.marjolein.recipeapp.View.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.marjolein.recipeapp.Model.RecipeIngredient;
import com.example.marjolein.recipeapp.R;

public class RecipeFragment extends Fragment {

    private RecipeIngredient recipeIngredient;

    public RecipeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param recipeIngredients ingredients of the recipe
     * @return A new instance of fragment RecipeFragment.
     */
    public static RecipeFragment newInstance(RecipeIngredient recipeIngredients) {
        RecipeFragment fragment = new RecipeFragment();
        fragment.recipeIngredient = recipeIngredients;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recipe, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        ImageView imageView = view.findViewById(R.id.recipe_image);
        TextView mRecipeTitle = view.findViewById(R.id.recipe_title);
        TextView mRecipeIngredients = view.findViewById(R.id.recipe_ingredient);

        StringBuilder ingredients = new StringBuilder();
        for(String s : recipeIngredient.getRecipe().getIngredients()){
            ingredients.append("\u002D").append("\t").append(s).append("\n");
        }

        Glide.with(this).load(recipeIngredient.getRecipe().getImageUrl()).into(imageView);
        mRecipeTitle.setText(recipeIngredient.getRecipe().getTitle());
        mRecipeIngredients.setText(ingredients.toString());
    }
}
