package com.example.marjolein.numbertrivia;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView mTriviaRecyclerView;
    public List<TriviaObject> mListTriviaObjects;
    public TriviaObjectAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //initializing list view with the custom adapter
        mListTriviaObjects = new ArrayList<>();

        mAdapter = new TriviaObjectAdapter(this, mListTriviaObjects);
        mTriviaRecyclerView = findViewById(R.id.recyclerView);

        mTriviaRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mTriviaRecyclerView.setAdapter(mAdapter);

        requestData();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestData();
                updateUI();
            }
        });
    }

    private void requestData(){
        NumbersApiService service = NumbersApiService.retrofit.create(NumbersApiService.class);
        int max = 999;
        int number = new Random().nextInt(max - 0);
        /**
         * Make an a-synchronous call by enqueing an definition of callbacks.
         */
        Call<TriviaObject> call = service.getNumberQuote(number);
        call.enqueue(new Callback<TriviaObject>() {
            @Override
            public void onResponse(Call<TriviaObject> call, Response<TriviaObject> response) {
                TriviaObject triviaObject = response.body();
                mListTriviaObjects.add(0,triviaObject);
                updateUI();
            }

            @Override
            public void onFailure(Call<TriviaObject> call, Throwable t) {
            }
        });
    }

    private void updateUI() {
        if (mAdapter == null) {
            mAdapter = new TriviaObjectAdapter(this, mListTriviaObjects);
            mTriviaRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }
}
