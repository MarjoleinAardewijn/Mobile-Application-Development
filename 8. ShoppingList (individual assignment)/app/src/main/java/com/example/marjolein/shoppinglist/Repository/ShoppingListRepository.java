package com.example.marjolein.shoppinglist.Repository;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.example.marjolein.shoppinglist.Database.AppDatabase;
import com.example.marjolein.shoppinglist.Database.ShoppingListDao;
import com.example.marjolein.shoppinglist.Model.ShoppingList.ShoppingListItem;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ShoppingListRepository {

    private ShoppingListDao mShoppingListDao;
    private LiveData<List<ShoppingListItem>> mShoppingListItems;
    private Executor mExecutor = Executors.newSingleThreadExecutor();

    public ShoppingListRepository(Context context){
        mShoppingListDao = AppDatabase.getsInstance(context).shoppingListDao();
        mShoppingListItems = mShoppingListDao.getAllItems();
    }

    // get all the items
    public LiveData<List<ShoppingListItem>> getAllItems() {
        return mShoppingListItems;
    }

    /**
     * Insert method for inserting new data in the database.
     * @param item
     */
    public void insert(final ShoppingListItem item){
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mShoppingListDao.insert(item);
            }
        });
    }

    /**
     * Update method for updating the data in the database.
     * @param item
     */
    public void update(final ShoppingListItem item){
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mShoppingListDao.update(item);
            }
        });
    }

    /**
     * Delete method for deleting data in the database.
     * @param item
     */
    public void delete(final ShoppingListItem item){
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mShoppingListDao.delete(item);
            }
        });
    }
}
