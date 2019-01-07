package com.example.marjolein.shoppinglist.Database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.marjolein.shoppinglist.Model.ShoppingList.ShoppingListItem;

import java.util.List;

@Dao
public interface ShoppingListDao {

    @Query("SELECT * FROM shoppingListItem")
    public LiveData<List<ShoppingListItem>> getAllItems();

    @Insert
    public void insert(ShoppingListItem item);

    @Update
    public void update(ShoppingListItem item);

    @Delete
    public void delete(ShoppingListItem item);

}
