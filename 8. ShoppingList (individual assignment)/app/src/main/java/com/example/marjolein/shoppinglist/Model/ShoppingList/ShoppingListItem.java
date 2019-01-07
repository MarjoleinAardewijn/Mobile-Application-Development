package com.example.marjolein.shoppinglist.Model.ShoppingList;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

@Entity(tableName = "shoppingListItem")
public class ShoppingListItem implements Parcelable{

    @PrimaryKey(autoGenerate = true)
    private Long id;

    @ColumnInfo(name = "itemTitle")
    private String title;

    @ColumnInfo(name = "itemDate")
    private String date;

    public ShoppingListItem(String title, String date) {
        this.title = title;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.title);
        dest.writeString(this.date);
    }

    protected ShoppingListItem(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.title = in.readString();
        this.date = in.readString();
    }

    public static final Creator<ShoppingListItem> CREATOR = new Creator<ShoppingListItem>() {
        @Override
        public ShoppingListItem createFromParcel(Parcel source) {
            return new ShoppingListItem(source);
        }

        @Override
        public ShoppingListItem[] newArray(int size) {
            return new ShoppingListItem[size];
        }
    };
}
