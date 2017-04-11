package com.polly.program.ui.main;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;

import com.polly.program.R;
import com.polly.program.base.BaseActivity;
import com.polly.program.ui.main.meizi.MeiziFragment;
import com.polly.program.ui.main.home.HomeFragment;
import com.polly.program.ui.main.video.VideoFragment;
import com.polly.program.widget.StatusBarCompat;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class MainActivity extends BaseActivity {

    @Bind(R.id.viewpager_main)
    ViewPager mViewPager;
    @Bind(R.id.tab_main)
    TabLayout mTabLayout;

    private HomeFragment mHomeFragment;
    private MeiziFragment mMeiziFragment;
    private VideoFragment mVideoFragment;

    private TabLayout.Tab mTabOne;
    private TabLayout.Tab mTabTwo;
    private TabLayout.Tab mTabThree;

    private MainAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initTitleBar() {
        StatusBarCompat.compat(mContext);
    }

    @Override
    protected void initData() {
        List<Fragment> fragments = new ArrayList<>();
        mHomeFragment = new HomeFragment();
        mMeiziFragment = new MeiziFragment();
        mVideoFragment = new VideoFragment();
        fragments.add(mHomeFragment);
        fragments.add(mMeiziFragment);
        fragments.add(mVideoFragment);
        mAdapter = new MainAdapter(getSupportFragmentManager(), fragments);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOffscreenPageLimit(3);
        mTabLayout.setupWithViewPager(mViewPager);

        mTabOne = mTabLayout.getTabAt(0);
        mTabTwo = mTabLayout.getTabAt(1);
        mTabThree = mTabLayout.getTabAt(2);

        mTabOne.setIcon(R.drawable.ic_home_checked);
        mTabTwo.setIcon(R.drawable.ic_gif_unchecked);
        mTabThree.setIcon(R.drawable.ic_video_unchecked);
    }

    @Override
    protected void initListener() {
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    mTabOne.setIcon(R.drawable.ic_home_checked);
                    mTabTwo.setIcon(R.drawable.ic_gif_unchecked);
                    mTabThree.setIcon(R.drawable.ic_video_unchecked);
                } else if (position == 1) {
                    mTabOne.setIcon(R.drawable.ic_home_unchecked);
                    mTabTwo.setIcon(R.drawable.ic_gif_checked);
                    mTabThree.setIcon(R.drawable.ic_video_unchecked);
                } else if (position == 2) {
                    mTabOne.setIcon(R.drawable.ic_home_unchecked);
                    mTabTwo.setIcon(R.drawable.ic_gif_unchecked);
                    mTabThree.setIcon(R.drawable.ic_video_checked);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
