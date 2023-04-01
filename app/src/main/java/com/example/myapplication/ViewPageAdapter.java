package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;


public class ViewPageAdapter extends FragmentStatePagerAdapter {


    public ViewPageAdapter(@NonNull FragmentManager fragmentManager, @NonNull int lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new HomeFragment();
            case 1:
                return new SearchFragment();
            case 2:
                return new CartFragment();
            default:
                return new HomeFragment();
        }
    }

    @NonNull

    @Override
    public int getCount() {
        return 3;
    }
}
