package com.example.marjolein.recipeapp.View;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import com.example.marjolein.recipeapp.Model.Recipe;
import com.example.marjolein.recipeapp.Model.RecipeIngredient;
import com.example.marjolein.recipeapp.R;
import com.example.marjolein.recipeapp.Service.RecipeApiRequestData;
import com.example.marjolein.recipeapp.Service.RecipeCallback;
import com.example.marjolein.recipeapp.View.Fragment.RecipeFragment;

public class MainActivity extends AppCompatActivity implements RecipeCallback{

    /**
     * The {@link ViewPager} that will host the contents.
     */
    private ViewPager mViewPager;

    private RecipeApiRequestData service;
    private Recipe[] recipes = new Recipe[3];
    private RecipeIngredient[] recipeIngredients = new RecipeIngredient[recipes.length];

    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        service = new RecipeApiRequestData(this);
        service.getTopThreeRecipes();

    }

    private void updateUI(){
        mViewPager = findViewById(R.id.viewPager);
        mViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        mViewPager.setCurrentItem(0);
        mViewPager.setOffscreenPageLimit(3);
    }

    @Override
    public void onRecipeResponse(Recipe[] recipes){
        this.recipes = recipes;

        for(Recipe recipe : recipes){
            service.getRecipeIngredients(recipe.getRecipeId());
        }
    }

    @Override
    public void onRecipeIngredientsResponse(RecipeIngredient recipeIngredient){
        recipeIngredients[count] = recipeIngredient;
        count++;

        // When count is the same length as recipes we've got all the ingredients and can
        // execute updateUI().
        if(count == recipes.length){
            updateUI();
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the pages.
     */
    private class MyPagerAdapter extends FragmentPagerAdapter{
        private MyPagerAdapter(FragmentManager fragmentManager){
            super(fragmentManager);
        }

        // getItem is called to instantiate the fragment for the given page.
        @Override
        public Fragment getItem(int position){
            RecipeIngredient recipeIngredient = recipeIngredients[position];

            switch (position){
                case 0:
                    return RecipeFragment.newInstance(recipeIngredient);
                case 1:
                    return RecipeFragment.newInstance(recipeIngredient);
                case 2:
                    return RecipeFragment.newInstance(recipeIngredient);
                default:
                    return null;
            }
        }

        @Override
        public int getCount(){
            // Show 3 total pages
            return 3;
        }
    }
}
