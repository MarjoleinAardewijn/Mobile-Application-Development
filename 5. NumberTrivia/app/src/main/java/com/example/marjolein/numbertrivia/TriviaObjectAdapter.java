package com.example.marjolein.numbertrivia;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class TriviaObjectAdapter extends RecyclerView.Adapter<TriviaObjectViewHolder>{

    private Context context;
    private List<TriviaObject> mTriviaObjects;

    private static final int LAYOUT_LEFT = 0;

    //constructor of the class
    public TriviaObjectAdapter(Context context, List<TriviaObject> mTriviaObjects){
        this.context = context;
        this.mTriviaObjects = mTriviaObjects;
    }

    //Determine which layout to use for the row
    @Override
    public int getItemViewType(int position){
        return position % 2;
    }

    /**
     * specify the row layout file for each row.
     * @param parent
     * @param viewType 0 or 1
     * @return layout file
     */
    @Override
    public TriviaObjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        if(viewType == LAYOUT_LEFT){
            view = inflater.inflate(R.layout.grid_cell_left, parent, false);
        }else{
            view = inflater.inflate(R.layout.grid_cell_right, parent, false);
        }

        return new TriviaObjectViewHolder(view);
    }

    // set the text in the TextViews.
    @Override
    public void onBindViewHolder(TriviaObjectViewHolder holder, int position) {
        holder.mNumberText.setText(String.valueOf(mTriviaObjects.get(position).getNumber()));
        holder.mQuoteText.setText(mTriviaObjects.get(position).getText());
    }

    // return the size of the list.
    @Override
    public int getItemCount() {
        return mTriviaObjects.size();
    }
}
