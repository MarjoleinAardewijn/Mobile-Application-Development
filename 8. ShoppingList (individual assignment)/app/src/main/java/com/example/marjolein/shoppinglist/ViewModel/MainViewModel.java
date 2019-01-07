package com.example.marjolein.shoppinglist.ViewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.example.marjolein.shoppinglist.Model.ShoppingList.ShoppingListItem;
import com.example.marjolein.shoppinglist.Repository.ShoppingListRepository;

import java.util.List;

public class MainViewModel extends ViewModel {

    private ShoppingListRepository mShoppingListRepository;
    private LiveData<List<ShoppingListItem>> mShoppingListItems;

    public MainViewModel(Context context){
        this.mShoppingListRepository = new ShoppingListRepository(context);
        this.mShoppingListItems = mShoppingListRepository.getAllItems();
    }

    public LiveData<List<ShoppingListItem>> getAllItems() {
        return mShoppingListItems;
    }

    /**
     * Insert items in the Repository.
     * @param item New ShoppingListItem that need to be inserted.
     */
    public void insert(ShoppingListItem item){
        mShoppingListRepository.insert(item);
    }

    /**
     * Update the items in the Repository.
     * @param item ShoppingListItem that needs to be updated.
     */
    public void update(ShoppingListItem item){
        mShoppingListRepository.update(item);
    }

    /**
     * Delete items in the Repository.
     * @param item ShoppingListItem that need to be deleted.
     */
    public void delete(ShoppingListItem item){
        mShoppingListRepository.delete(item);
    }
}
