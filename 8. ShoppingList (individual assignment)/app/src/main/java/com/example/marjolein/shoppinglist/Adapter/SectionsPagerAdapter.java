package com.example.marjolein.shoppinglist.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class SectionsPagerAdapter extends FragmentPagerAdapter{

    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    /**
     * Method for adding Fragments to the PagerAdapter
     * @param fragment The fragment to be added
     * @param title The title of the fragment
     */
    public void addFragment(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }
}
