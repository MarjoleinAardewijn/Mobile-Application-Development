package com.example.marjolein.bucketlist.Repository;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.example.marjolein.bucketlist.Database.AppDatabase;
import com.example.marjolein.bucketlist.Database.BucketListDao;
import com.example.marjolein.bucketlist.Model.BucketListItem;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class BucketListRepository {

    private BucketListDao mBucketListDao;
    private LiveData<List<BucketListItem>> mBucketListItems;
    private Executor mExecutor = Executors.newSingleThreadExecutor();

    public BucketListRepository(Context context){
        mBucketListDao = AppDatabase.getInstance(context).bucketListItemDao();
        mBucketListItems = mBucketListDao.getAllItems();
    }

    public LiveData<List<BucketListItem>> getAllItems(){
        return mBucketListItems;
    }

    public void insert(final BucketListItem item){
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mBucketListDao.insert(item);
            }
        });
    }

    public void update(final BucketListItem item){
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mBucketListDao.update(item);
            }
        });
    }

    public void delete(final BucketListItem item){
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mBucketListDao.delete(item);
            }
        });
    }
}
