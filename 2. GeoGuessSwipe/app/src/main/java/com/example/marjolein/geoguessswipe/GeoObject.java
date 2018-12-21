package com.example.marjolein.geoguessswipe;

import java.util.HashMap;
import java.util.Map;

/*
 * This class will be the data model for each object that will be shown in our
 * RecyclerView.
 * */

public class GeoObject {

    private int mGeoImageName;
    private boolean mIsEurope;

    public GeoObject(int mGeoImageName, boolean mIsEurope){
        this.mGeoImageName = mGeoImageName;
        this.mIsEurope = mIsEurope;
    }

    public boolean getmIsEurope(){
        return mIsEurope;
    }

    public void setmIsEurope(boolean mIsEurope){
        this.mIsEurope = mIsEurope;
    }

    public int getmGeoImageName(){
        return mGeoImageName;
    }

    public void setmGeoImageName(int mGeoImageName){
        this.mGeoImageName = mGeoImageName;
    }

    // create a Map to store the images (objects) with a boolean value so that it can be retrieved later
    public static Map<Integer, Boolean> getCountries(){
        Map<Integer, Boolean> countries = new HashMap<Integer, Boolean>();

        countries.put(R.drawable.img1_yes_denmark, true);
        countries.put(R.drawable.img2_no_canada, false);
        countries.put(R.drawable.img3_no_bangladesh, false);
        countries.put(R.drawable.img4_yes_kazachstan, true);
        countries.put(R.drawable.img5_no_colombia, false);
        countries.put(R.drawable.img6_yes_poland, true);
        countries.put(R.drawable.img7_yes_malta, true);
        countries.put(R.drawable.img8_no_thailand, false);

        return countries;
    }


}
