package com.example.marjolein.shoppinglist.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.marjolein.shoppinglist.Model.ShoppingList.ShoppingListItem;

@Database(entities = {ShoppingListItem.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase{

    public abstract ShoppingListDao shoppingListDao();
    private final static String NAME_DATABASE = "shoppinglist_db";

    //static instance
    private static AppDatabase sInstance;
    public static AppDatabase getsInstance(Context context){
        if(sInstance == null){
            sInstance = Room.databaseBuilder(context, AppDatabase.class, NAME_DATABASE).build();
        }
        return sInstance;
    }
}
