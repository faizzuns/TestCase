package com.example.user.testcase;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

/**
 * Created by User on 24/09/2017.
 */

public class TabFragmentPagerAdapter extends FragmentPagerAdapter {

    String[] title = new String[]{
            "Booking","History"
    };


    Fragment bookFragment;
    Fragment historyFragment;

    public TabFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new BookingFragment();
                bookFragment = fragment;
                break;
            case 1:
                fragment = new HistoryFragment();
                historyFragment =  fragment;
                break;
            default:
                fragment = null;
                break;
        }
        return fragment;
    }

    public Fragment getBookFragment() {
        return bookFragment;
    }

    public Fragment getHistoryFragment() {
        return historyFragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }

    @Override
    public int getCount() {
        return title.length;
    }

}
