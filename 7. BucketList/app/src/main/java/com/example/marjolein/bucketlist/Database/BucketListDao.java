package com.example.marjolein.bucketlist.Database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.marjolein.bucketlist.Model.BucketListItem;

import java.util.List;

@Dao
public interface BucketListDao {
    @Query("SELECT * FROM bucketListItem")
    public LiveData<List<BucketListItem>> getAllItems();

    @Insert
    public void insert(BucketListItem item);

    @Update
    public void update(BucketListItem item);

    @Delete
    public void delete(BucketListItem item);
}
