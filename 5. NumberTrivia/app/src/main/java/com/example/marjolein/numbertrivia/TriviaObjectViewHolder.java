package com.example.marjolein.numbertrivia;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class TriviaObjectViewHolder extends RecyclerView.ViewHolder {

    public TextView mQuoteText;
    public TextView mNumberText;
    public View view;

    public TriviaObjectViewHolder(View itemView){
        super(itemView);
        mQuoteText = itemView.findViewById(R.id.quote_text);
        mNumberText = itemView.findViewById(R.id.quote_number);
        view = itemView;
    }

}
