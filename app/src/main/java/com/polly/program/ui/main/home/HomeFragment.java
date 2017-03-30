package com.polly.program.ui.main.home;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.polly.program.Constants;
import com.polly.program.R;
import com.polly.program.base.BaseFragment;
import com.polly.program.ui.main.home.list.HomeListFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import rx.Observable;
import rx.Subscriber;

/**
 * @author linfeizheng
 * @date 2017/3/10 16:00
 */

public class HomeFragment extends BaseFragment {

    @Bind(R.id.tab_home)
    TabLayout mTabLayout;
    @Bind(R.id.viewpager_home)
    ViewPager mViewPager;

    private static final String[] mHomeTabInfo = {"Android", "iOS", "拓展资源", "前端", "瞎推荐", "App"};
    private HomeAdapter mAdapter;
    private List<Fragment> fragments = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initTitleBar() {
        setTitle("Home");
    }

    @Override
    protected void initData() {
        mAdapter = new HomeAdapter(getChildFragmentManager());
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

        if (mHomeTabInfo.length > 4) {
            mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        } else {
            mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        }
        mViewPager.setOffscreenPageLimit(mHomeTabInfo.length);
        Observable.from(mHomeTabInfo).subscribe(new Subscriber<String>() {

            @Override
            public void onStart() {
                fragments.clear();
            }

            @Override
            public void onCompleted() {
                mAdapter.setData(mHomeTabInfo, fragments);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                HomeListFragment fragment = new HomeListFragment();
                Bundle bundle = new Bundle();
                bundle.putString(Constants.INTENT_EXTRA_ID, s);
                fragment.setArguments(bundle);
                fragments.add(fragment);
            }
        });
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void lazyLoad() {
    }

    @Override
    public void onClick(View view) {

    }

}
