package com.polly.program.ui.main.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author linfeizheng
 * @date 2017/3/13 11:27
 */

public class HomeAdapter extends FragmentPagerAdapter {

    private String[] tabNames;
    private List<Fragment> fragments = new ArrayList<>();

    public HomeAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabNames[position];
    }

    public void setData(String[] tabNames, List<Fragment> fragments) {
        this.tabNames = tabNames;
        this.fragments = fragments;
        notifyDataSetChanged();
    }
}
