package com.example.marjolein.studentportal;

import android.os.Parcel;
import android.os.Parcelable;

public class Portal implements Parcelable {

    private String mPortalName;
    private String mUrl;

    public Portal(String mPortalName, String mUrl) {
        this.mPortalName = mPortalName;
        this.mUrl = mUrl;
    }

    public String getmPortalName() {
        return mPortalName;
    }

    public void setmPortalName(String mPortalName) {
        this.mPortalName = mPortalName;
    }

    public String getmUrl() {
        return mUrl;
    }

    public void setmUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    @Override public String toString(){
        return mPortalName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mPortalName);
        dest.writeString(this.mUrl);
    }

    protected Portal(Parcel in) {
        this.mPortalName = in.readString();
        this.mUrl = in.readString();
    }

    public static final Parcelable.Creator<Portal> CREATOR = new Parcelable.Creator<Portal>() {
        @Override
        public Portal createFromParcel(Parcel source) {
            return new Portal(source);
        }

        @Override
        public Portal[] newArray(int size) {
            return new Portal[size];
        }
    };
}
