package com.example.marjolein.bucketlist.View;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.marjolein.bucketlist.Adapter.BucketListAdapter;
import com.example.marjolein.bucketlist.Model.BucketListItem;
import com.example.marjolein.bucketlist.R;
import com.example.marjolein.bucketlist.ViewModel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    private RecyclerView mRecyclerView;
    private BucketListAdapter mAdapter;
    private List<BucketListItem> mBucketListItems;
    private MainViewModel mMainViewModel;
    private Toolbar toolbar;

    //Constants used when calling the add activity
    private static final int RESULT_CODE = 1234;
    public static final String RESULT_KEY = "BucketListItem";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mBucketListItems = new ArrayList<>();

        mMainViewModel = new MainViewModel(this);
        mMainViewModel.getAllItems().observe(this, new Observer<List<BucketListItem>>() {
            @Override
            public void onChanged(@Nullable List<BucketListItem> bucketListItems) {
                mBucketListItems = bucketListItems;
                updateUI();
            }
        });

        setItemTouchHelper();
        setFab();
        setToolbarNavigation();
    }

    private void updateUI(){
        if(mAdapter == null){
            mAdapter = new BucketListAdapter(mBucketListItems);
            mRecyclerView.setAdapter(mAdapter);
        } else{
            mAdapter.swapList(mBucketListItems);
        }
    }

    private void setFab(){
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddBucketListItemActivity.class);
                startActivityForResult(intent, RESULT_CODE);
            }
        });
    }

    private void setItemTouchHelper(){
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = (viewHolder.getAdapterPosition());
                mMainViewModel.delete(mBucketListItems.get(position));
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    private void setToolbarNavigation(){
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == RESULT_CODE){
            if(resultCode == RESULT_OK){
                BucketListItem bucketListItem = data.getParcelableExtra(MainActivity.RESULT_KEY);
                mMainViewModel.insert(bucketListItem);
            }
        }
    }
}
