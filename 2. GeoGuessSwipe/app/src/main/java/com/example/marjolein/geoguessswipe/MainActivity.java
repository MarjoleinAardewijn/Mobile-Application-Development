package com.example.marjolein.geoguessswipe;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final List<GeoObject> mGeoObjects = new ArrayList<>();
        Map<Integer, Boolean> countries = GeoObject.getCountries();

        // Add data to the list
        for (Map.Entry<Integer, Boolean> entry : countries.entrySet()){
            Integer image = entry.getKey();
            Boolean isEurope = entry.getValue();

            mGeoObjects.add(new GeoObject(image, isEurope));
        }

        RecyclerView mGeoRecyclerView = findViewById(R.id.recyclerView);

        RecyclerView.LayoutManager mLayoutManager = new StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL);

        mGeoRecyclerView.setLayoutManager(mLayoutManager);
        final GeoObjectAdapter mAdapter =  new GeoObjectAdapter(this, mGeoObjects);
        mGeoRecyclerView.setAdapter(mAdapter);

        /*
        * Add a touch helper to the RecyclerView to recognize when a user swipes to left or right.
        * An ItemTouchHelper enables touch behavior (like swipe and move) on each ViewHolder, and
        * uses callbacks to signal when a user is performing these actions.
        * */
        ItemTouchHelper.SimpleCallback simpleItemTouchCallBack = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                //Get the index corresponding to the selected position
                int position = (viewHolder.getAdapterPosition());
                boolean isEurope = mAdapter.getItem(position).getmIsEurope();

                if((swipeDir == ItemTouchHelper.LEFT && isEurope) || (swipeDir == ItemTouchHelper.RIGHT && !isEurope)){
                    Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Correct!", Snackbar.LENGTH_SHORT);
                    snackbar.show();
                }else{
                    Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Wrong!", Snackbar.LENGTH_SHORT);
                    snackbar.show();
                }
                mAdapter.remove(position);
                mAdapter.notifyItemRemoved(position);

            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallBack);
        itemTouchHelper.attachToRecyclerView(mGeoRecyclerView);
    }


}
