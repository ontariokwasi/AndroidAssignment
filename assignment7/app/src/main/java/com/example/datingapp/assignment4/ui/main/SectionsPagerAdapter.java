package com.example.datingapp.assignment4.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.datingapp.assignment4.ProfileFragment;
import com.example.datingapp.ui.main.MainFragment;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private static final String[] TAB_TITLES = new String[]{"Profile", "Matches", "Settings"};
    private Fragment[] fragments;
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm, Fragment[] fragments) {
        super(fm);
        mContext = context;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {

        return fragments[position];
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return TAB_TITLES[position];
    }

    @Override
    public int getCount() {
        return TAB_TITLES.length;
    }
}