package com.polly.program.ui.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * @author linfeizheng
 * @date 2017/3/10 16:19
 */

public class MainAdapter extends FragmentPagerAdapter {

    private static final String[] mTitles = new String[]{"首页", "GIF", "视频"};

    private List<Fragment> fragments;

    public MainAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

}
