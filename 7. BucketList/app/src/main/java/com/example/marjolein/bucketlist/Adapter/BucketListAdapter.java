package com.example.marjolein.bucketlist.Adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.marjolein.bucketlist.Model.BucketListItem;
import com.example.marjolein.bucketlist.R;

import java.util.List;

public class BucketListAdapter extends RecyclerView.Adapter<BucketListAdapter.ViewHolder>{

    private List<BucketListItem> mBucketListItems;

    public BucketListAdapter(List<BucketListItem> mBucketListItems){
        this.mBucketListItems = mBucketListItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.bucket_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final BucketListAdapter.ViewHolder holder, int position) {
        final BucketListItem bucketListItem = mBucketListItems.get(position);
        holder.title.setText(bucketListItem.getTitle());
        holder.description.setText(bucketListItem.getDescription());

        strikeThroughWhenChecked(holder);
    }

    @Override
    public int getItemCount() {
        return mBucketListItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        CheckBox checkBox;
        TextView title;
        TextView description;

        ViewHolder(View itemView){
            super(itemView);
            checkBox = itemView.findViewById(R.id.cb_bucket_list_item);
            title = itemView.findViewById(R.id.tv_item_title);
            description = itemView.findViewById(R.id.tv_item_description);
        }
    }

    public void swapList(List<BucketListItem> newList){
        mBucketListItems = newList;
        if(newList != null){
            this.notifyDataSetChanged();
        }
    }

    private void strikeThroughWhenChecked(final BucketListAdapter.ViewHolder holder){
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) v).isChecked();
                switch(v.getId()){
                    case R.id.cb_bucket_list_item:
                        if(checked) {
                            holder.title.setPaintFlags(holder.title.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                            holder.description.setPaintFlags(holder.description.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                        }else{
                            holder.title.setPaintFlags(holder.title.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                            holder.description.setPaintFlags(holder.description.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                        }
                        break;
                    default:
                        break;
                }
            }
        });
    }
}
