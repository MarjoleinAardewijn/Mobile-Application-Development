package com.example.marjolein.shoppinglist.Adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.marjolein.shoppinglist.Model.ShoppingList.ShoppingListItem;
import com.example.marjolein.shoppinglist.R;
import com.example.marjolein.shoppinglist.View.Fragment.ShoppingListFragment;
import com.example.marjolein.shoppinglist.ViewModel.MainViewModel;

import java.util.List;

public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListAdapter.ViewHolder>{

    private List<ShoppingListItem> mShoppingListItems;

    public ShoppingListAdapter(List<ShoppingListItem> mShoppingListItems){
        this.mShoppingListItems = mShoppingListItems;
    }

    /*
     * inflate our layout to display the row items in the RecyclerView.
     * In the onCreateViewHolder method we need to inflate the shopping_list_item.xml
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.shopping_list_item, parent, false);
        return new ViewHolder(view);
    }

    /*
     * Bind our data to the viewHolders.
     * To do this we need to initialize our list of shopping items and use the position
     * argument of the method to find an object's position in the list.
     * Now we can put the data from the list in the views.
     * Therefore we use the holder argument to reference the views inside a viewholder.
     *
     * Put an onClick method on the checkbox for making the text strike through when checked.
     * */
    @Override
    public void onBindViewHolder(@NonNull ShoppingListAdapter.ViewHolder holder, int position) {
        final ShoppingListItem shoppingListItem = mShoppingListItems.get(position);
        holder.title.setText(shoppingListItem.getTitle());
        holder.date.setText(shoppingListItem.getDate());

        strikeThroughWhenChecked(holder);
    }

    /**
     * this method needs to return the size of a list
     */
    @Override
    public int getItemCount() {
        return mShoppingListItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        CheckBox checkBox;
        TextView title;
        TextView date;

        ViewHolder(View itemView){
            super(itemView);
            checkBox = itemView.findViewById(R.id.cb_shopping_list_item);
            title = itemView.findViewById(R.id.tv_item_title);
            date = itemView.findViewById(R.id.tv_item_date);
        }
    }

    public void swapList(List<ShoppingListItem> newList){
        mShoppingListItems = newList;
        if(newList != null){
            this.notifyDataSetChanged();
        }
    }

    /**
     * Put an onClick method on the checkbox to make the text strike through when checked.
     * @param holder
     */
    private void strikeThroughWhenChecked(final ShoppingListAdapter.ViewHolder holder){
        holder.checkBox.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                boolean checked = ((CheckBox) v).isChecked();
                switch (v.getId()){
                    case R.id.cb_shopping_list_item:
                        if(checked){
                            holder.title.setPaintFlags(holder.title.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                        } else {
                            holder.title.setPaintFlags(holder.title.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                        }
                        break;
                    default:
                        break;
                }
            }
        });
    }
}
