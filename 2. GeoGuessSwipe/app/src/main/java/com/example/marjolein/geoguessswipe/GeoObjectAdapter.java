package com.example.marjolein.geoguessswipe;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class GeoObjectAdapter extends RecyclerView.Adapter<GeoObjectViewHolder> {

    private Context context;
    public List<GeoObject> listGeoObject;

    public GeoObjectAdapter(Context context, List<GeoObject> listGeoObject){
        this.context = context;
        this.listGeoObject = listGeoObject;
    }

    /*
     * inflate our layout to display the row items in th eRecyclerView.
     * In the onCreateViewHolder method we need to inflate the grid_cell.xml
     */
    @Override
    public GeoObjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_cell, parent, false);
        return new GeoObjectViewHolder(view);
    }

    /*
     * Bind our data to the viewHolders.
     * To do this we need to initialize our GeoObject list and use the position
     * argument of the method to find an object's position in the list.
     * Now we can put the data from the list in the views.
     * Therefore we use the holder argument to reference the views inside a viewholder.
     * We can also put the onClick methods on specific views inside a viewHolder.
     * */
    @Override
    public void onBindViewHolder(final GeoObjectViewHolder holder, final int position){
        // Gets a single item in th elist from its position
        final GeoObject geoObject = listGeoObject.get(position);
        // The holder argument is used to reference the views inside the viewHolder
        // Populate the views with the data from the list
        holder.geoImage.setImageResource(geoObject.getmGeoImageName());
    }

    // this method needs to return the size of a list
    @Override
    public int getItemCount() {
        return listGeoObject.size();
    }

    public GeoObject getItem(int position){
        return listGeoObject.get(position);
    }

    public void remove(int position){
        listGeoObject.remove(position);
    }

}
