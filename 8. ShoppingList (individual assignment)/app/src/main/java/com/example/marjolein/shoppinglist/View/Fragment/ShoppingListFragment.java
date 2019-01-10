package com.example.marjolein.shoppinglist.View.Fragment;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.marjolein.shoppinglist.Adapter.ShoppingListAdapter;
import com.example.marjolein.shoppinglist.Model.ShoppingList.ShoppingListItem;
import com.example.marjolein.shoppinglist.R;
import com.example.marjolein.shoppinglist.ViewModel.MainViewModel;

import java.util.ArrayList;
import java.util.List;


public class ShoppingListFragment extends Fragment {

    private FloatingActionButton fab;

    private RecyclerView mRecyclerView;
    private ShoppingListAdapter mAdapter;
    private List<ShoppingListItem> mShoppingListItems;
    private MainViewModel mMainViewModel;

    // Constants used when calling the add fragment
    public static final String RESULT_KEY = "ShoppingListItem";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shopping_list,container,false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // get the current fragment
        FragmentActivity fa = getActivity();
        // init the recyclerView and set the layoutManager
        mRecyclerView = view.findViewById(R.id.rv_shopping_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(fa, LinearLayoutManager.VERTICAL, false));

        mShoppingListItems = new ArrayList<>();

        // get all the items and update the UI
        mMainViewModel = new MainViewModel(fa);
        mMainViewModel.getAllItems().observe(this, new Observer<List<ShoppingListItem>>() {
            @Override
            public void onChanged(@Nullable List<ShoppingListItem> shoppingListItems) {
                mShoppingListItems = shoppingListItems;
                updateUI();
            }
        });

        setFab(view);
        setItemTouchHelper();
        onFragmentResult();
    }

    // Set the adapter
    public void updateUI(){
        if(mAdapter == null){
            // set the adapter to the recyclerView when that isn't already the case
            mAdapter = new ShoppingListAdapter(mShoppingListItems);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            // when the adapter is already set, replace it with the updates list
            mAdapter.swapList(mShoppingListItems);
        }
    }

    /**
     * Set the Fab;
     * Go to another fragment;
     * Make current elements invisible.
     * @param view
     */
    private void setFab(View view){
        fab = view.findViewById(R.id.fab_add_item);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragment(new AddShoppingListItemFragment());
                // make elements of ShoppingListFragment invisible
                fab.setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.GONE);
            }
        });
    }

    /**
     * Set the ItemTouchHelper
     */
    private void setItemTouchHelper(){
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                // get the position of the item and delete it when swiped to left or right
                int position = (viewHolder.getAdapterPosition());
                mMainViewModel.delete(mShoppingListItems.get(position));
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    /**
     * Check if there is data parsed from another fragment and get + insert the data
     */
    protected void onFragmentResult(){
        Bundle data = this.getArguments();
        if(data != null) {
            ShoppingListItem shoppingListItem = data.getParcelable(ShoppingListFragment.RESULT_KEY);
            mMainViewModel.insert(shoppingListItem);
        }
    }

    /**
     * Get another Fragment and go to that Fragment
     * @param fragment the fragment that needs to replace the current one.
     */
    private void getFragment(Fragment fragment) {
        // create a FragmentManager
        FragmentManager fm = getFragmentManager();
        // create a FragmentTransaction to begin the transaction and replace the Fragment
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        // replace the FrameLayout with new Fragment
        fragmentTransaction.replace(R.id.fragment_layout, fragment);
        // save the changes
        fragmentTransaction.commit();
    }

}
