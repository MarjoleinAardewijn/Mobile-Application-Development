package com.example.marjolein.shoppinglist.View.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.marjolein.shoppinglist.Model.QuoteOfTheDay.DayQuoteItem;
import com.example.marjolein.shoppinglist.R;
import com.example.marjolein.shoppinglist.Service.NumbersApiService;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class QuoteFragment extends Fragment {

    private TextView mQuoteTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestNumberData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quote,container,false);

        mQuoteTextView = view.findViewById(R.id.tv_quote_message);

        return view;
    }

    /**
     * Set the quote message to the TextView.
     * @param quoteMessage
     */
    public void setQuoteTextView(String quoteMessage) {
        mQuoteTextView.setText(quoteMessage);
    }

    /**
     * Request the data from NumbersApi
     */
    private void requestNumberData() {
        NumbersApiService service = NumbersApiService.retrofit.create(NumbersApiService.class);
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH) + 1; //Calendar.MONTH starts at zero
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        /**
         * Make an a-synchronous call by enqueing an definition of callbacks.
         */
        Call<DayQuoteItem> call = service.getTodaysQuote(month, dayOfMonth);
        call.enqueue(new Callback<DayQuoteItem>() {
            @Override
            public void onResponse(Call<DayQuoteItem> call, Response<DayQuoteItem> response) {
                DayQuoteItem dayQuoteItem = response.body();
                setQuoteTextView(dayQuoteItem.getText());
            }

            @Override
            public void onFailure(Call<DayQuoteItem> call, Throwable t) {
                Log.d("error",t.toString());
            }
        });
    }

}
