package com.example.marjolein.gamebacklogmanager;

import android.app.Activity;
import android.arch.persistence.room.Update;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.ViewHolder>{

    private List<Game> mGames;
    final private GameClickListener mGameClickListener;

    public interface GameClickListener{
        void gameOnClick(int i);
    }

    public GameAdapter(List<Game> games, GameClickListener mGameClickListener){
        this.mGames = games;
        this.mGameClickListener = mGameClickListener;
    }

    /*
     * inflate our layout to display the row items in the RecyclerView.
     * In the onCreateViewHolder method we need to inflate the game_cards.xml
     */
    @Override
    public GameAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.game_cards, viewGroup, false);
        GameAdapter.ViewHolder viewHolder = new GameAdapter.ViewHolder(view);
        return viewHolder;
//        Context context = viewGroup.getContext();
//        LayoutInflater inflater = LayoutInflater.from(context);
//        View view = inflater.inflate(R.layout.game_cards, null);
//
//        //Return a new holder instance
//        GameAdapter.ViewHolder viewHolder =  new GameAdapter.ViewHolder(view);
//        return viewHolder;
    }

    /*
     * Bind our data to the viewHolders.
     * To do this we need to initialize our Game list and use the position
     * argument of the method to find an object's position in the list.
     * Now we can put the data from the list in the views.
     * Therefore we use the holder argument to reference the views inside a viewholder.
     * We can also put the onClick methods on specific views inside a viewHolder.
     * */
    @Override
    public void onBindViewHolder(GameAdapter.ViewHolder holder, int position){
        // Get a single item in the list from its position
        Game game = mGames.get(position);
        // The holder argument is used to reference the view inside the viewHolder
        // Populate the views with the data from the list
        holder.titleTextView.setText(game.getGameTitle());
        holder.platformTextView.setText(game.getGamePlatform());
        holder.statusTextView.setText(game.getGameStatus());
        holder.dateTextView.setText(game.getGameDate());
    }

    // this method needs to return the size of a list
    @Override
    public int getItemCount() {
        return mGames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView titleTextView;
        public TextView platformTextView;
        public TextView statusTextView;
        public TextView dateTextView;
        public View view;

        public ViewHolder(View itemView){
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            platformTextView = itemView.findViewById(R.id.platformTextView);
            statusTextView = itemView.findViewById(R.id.statusTextView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
            view = itemView;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view){
            int clickedPosition = getAdapterPosition();
            mGameClickListener.gameOnClick(clickedPosition);
        }
    }

    public void swapList(List<Game> newList){
        mGames = newList;
        if(newList != null){
            //Force the RecyclerView to refresh
            this.notifyDataSetChanged();
        }
    }
}
