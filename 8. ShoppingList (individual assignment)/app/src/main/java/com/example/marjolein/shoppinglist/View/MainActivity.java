package com.example.marjolein.shoppinglist.View;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import com.example.marjolein.shoppinglist.Adapter.SectionsPagerAdapter;
import com.example.marjolein.shoppinglist.R;
import com.example.marjolein.shoppinglist.View.Fragment.QuoteFragment;
import com.example.marjolein.shoppinglist.View.Fragment.ShoppingListFragment;

public class MainActivity extends AppCompatActivity{

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = findViewById(R.id.viewPager);
        setupViewPager(mViewPager);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

    }

    /**
     * Add the fragments to the ViewPager.
     * @param viewPager
     */
    private void setupViewPager(ViewPager viewPager) {
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new ShoppingListFragment(), getResources().getString(R.string.tab_1));
        adapter.addFragment(new QuoteFragment(), getResources().getString(R.string.tab_2));
        viewPager.setAdapter(adapter);
    }

}
