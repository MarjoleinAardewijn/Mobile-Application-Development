package com.example.marjolein.bucketlist.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.marjolein.bucketlist.Model.BucketListItem;

@Database(entities = {BucketListItem.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase{

    public abstract BucketListDao bucketListItemDao();
    private final static String NAME_DATABASE = "bucketlist_db";

    //static instance
    private static AppDatabase sInstance;
    public static AppDatabase getInstance(Context context){
        if(sInstance == null){
            sInstance = Room.databaseBuilder(context, AppDatabase.class, NAME_DATABASE).build();
        }

        return sInstance;
    }
}
