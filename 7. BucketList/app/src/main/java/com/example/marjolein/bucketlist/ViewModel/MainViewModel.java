package com.example.marjolein.bucketlist.ViewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.example.marjolein.bucketlist.Model.BucketListItem;
import com.example.marjolein.bucketlist.Repository.BucketListRepository;

import java.util.List;

public class MainViewModel extends ViewModel{
    private BucketListRepository mBucketListRepository;
    private LiveData<List<BucketListItem>> mBucketListItems;

    public MainViewModel(Context context){
        this.mBucketListRepository = new BucketListRepository(context);
        this.mBucketListItems = mBucketListRepository.getAllItems();
    }

    public LiveData<List<BucketListItem>> getAllItems() {
        return mBucketListItems;
    }

    public void insert(BucketListItem item){
        mBucketListRepository.insert(item);
    }

    public void update(BucketListItem item){
        mBucketListRepository.update(item);
    }

    public void delete(BucketListItem item){
        mBucketListRepository.delete(item);
    }
}
